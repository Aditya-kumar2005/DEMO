/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.voice.speechrecognizermain;
import temp.CommandHandler;
import java.io.IOException;
import java.util.Set;

/**
 *
 * @author nanua
 */
public class VoiceAssistantBuilder {

    public static void main(String[] args) {
        CommandHandler handler = new CommandHandler();
        Set<String> commands = handler.getAllCommands();

        String outputDir = "src/main/resources/grammars";

        try {
            GrammarGenerator.generateGrammarAndDictionary(commands, outputDir);
            System.out.println("✅ Grammar and dictionary generated successfully.");
        } catch (IOException e) {
            System.err.println("❌ Failed to generate files: " + e.getMessage());
        }
    }
}
