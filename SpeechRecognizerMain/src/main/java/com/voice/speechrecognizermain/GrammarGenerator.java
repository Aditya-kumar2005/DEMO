/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.voice.speechrecognizermain;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

/**
 *
 * @author nanua
 */
public class GrammarGenerator {
    public static void generateGrammarAndDictionary(Set<String> commands, String outputDir) throws IOException {
    generateGrammar(commands, outputDir + "/grammar.gram");
    generateCommandsTxt(commands, outputDir + "/commands.txt");
//    DictionaryGenerator.generateDictionary(commands, outputDir + "/custom.dict");
    }

    private static void generateGrammar(Set<String> commands, String grammarPath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(grammarPath))) {
            writer.write("#JSGF V1.0;\n");
            writer.write("grammar grammar;\n");
            writer.write("public <command> = ");
            writer.write(String.join(" | ", commands));
            writer.write(";");
        }
    }

    private static void generateCommandsTxt(Set<String> commands, String txtPath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtPath))) {
            for (String command : commands) {
                writer.write(command);
                writer.newLine();
            }
        }
    }

    // Optional: Use CMU Sphinx tools to generate .dict from commands.txt
}
