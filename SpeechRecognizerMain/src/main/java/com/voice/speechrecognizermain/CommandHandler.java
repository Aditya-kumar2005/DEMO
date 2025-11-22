/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package temp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;


/**
 *
 * @author nanua
 */

public class CommandHandler {

    private final Map<String, Runnable> commandMap = new HashMap<>();
    public CommandHandler() {
        commandMap.put("open notepad", () -> runCommand("notepad.exe", "Notepad started."));
        commandMap.put("close notepad", () -> runCommand("cmd /c taskkill /IM notepad.exe /F", "Notepad closed."));
        commandMap.put("exit", () -> System.out.println("Exiting..."));
        // Add more commands here
        
    }

	public Set<String> getAllCommands() {
        return commandMap.keySet();
    }
    public void registerCommand(String phrase, Runnable action) {
    commandMap.put(phrase.toLowerCase(), action);
    }
    public void handleCommand(String command) {
        Runnable action = commandMap.get(command.toLowerCase());
        if (action != null) action.run();
        else System.out.println("Unknown command: " + command);
    }

    private void runCommand(String cmd, String successMessage) {
        try {
            Runtime.getRuntime().exec(cmd);
            System.out.println(successMessage);
        } catch (IOException e) {
            System.out.println("Command failed: " + e.getMessage());
        }
    }
}