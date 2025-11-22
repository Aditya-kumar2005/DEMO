package com.voice.speechrecognizermain;

import temp.CommandHandler;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GrammarController {

    @PostMapping("/generate")
    public ResponseEntity<String> generateGrammar() {
        CommandHandler handler = new CommandHandler();
        Set<String> commands = handler.getAllCommands();
        String outputDir = "src/main/resources/grammars";

        try {
            GrammarGenerator.generateGrammarAndDictionary(commands, outputDir);
            return ResponseEntity.ok("✅ Grammar and dictionary generated.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Generation failed: " + e.getMessage());
        }
    }
    @PostMapping("/suggest")
    public ResponseEntity<String> suggestCommand(@RequestBody Map<String, String> payload) {
    String suggestion = payload.get("command");
    if (suggestion == null || suggestion.trim().isEmpty()) {
        return ResponseEntity.badRequest().body("❌ Command cannot be empty.");
    }

    try {
        Files.write(
            Paths.get("suggested_commands.txt"),
            (suggestion.trim() + System.lineSeparator()).getBytes(),
            StandardOpenOption.CREATE, StandardOpenOption.APPEND
        );
        return ResponseEntity.ok("✅ Suggestion received: \"" + suggestion + "\"");
    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("❌ Failed to save suggestion.");
    }
    }
    @PostMapping("/approve")
    public ResponseEntity<String> approveCommand(@RequestBody Map<String, String> payload) {
    String command = payload.get("command");
    if (command == null || command.trim().isEmpty()) {
        return ResponseEntity.badRequest().body("❌ Command cannot be empty.");
    }

    try {
        Files.write(
            Paths.get("approved_commands.txt"),
            (command.trim() + System.lineSeparator()).getBytes(),
            StandardOpenOption.CREATE, StandardOpenOption.APPEND
        );

        // Merge and regenerate grammar/dictionary
        Set<String> mergedCommands = new HashSet<>(new CommandHandler().getAllCommands());
        mergedCommands.addAll(Files.readAllLines(Paths.get("approved_commands.txt")));

        GrammarGenerator.generateGrammarAndDictionary(mergedCommands, "src/main/resources/grammars");

        return ResponseEntity.ok("✅ Approved and merged: \"" + command + "\"");
    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("❌ Failed to approve command.");
    }
    }
    @GetMapping("/commands")
    public ResponseEntity<List<String>> getCommands() {
    CommandHandler handler = new CommandHandler();
    List<String> commands = new ArrayList<>(handler.getAllCommands());
    Collections.sort(commands);
    return ResponseEntity.ok(commands);
    }
}
