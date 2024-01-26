package com.aayzhaonaribn.classroomapp;
import com.aayzhaonaribn.parsing.Parser;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    static final String filePath = "spring2024.pdf";
    static final String outputPath = "pdf.txt";
    public static void main(String[] args) {
        //System.out.println("Hello world");

        Parser parser = new Parser();
        File outputFile = null;
        try {
            outputFile = parser.getFileFromResource(outputPath);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.out.println("URI syntax error");
        }
        Path path = Paths.get(outputFile.getPath());
        String result = "";

        try {
            result = parser.parse(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(result);

        try {
            Files.writeString(path, result, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Invalid path");
        }

    }

}

