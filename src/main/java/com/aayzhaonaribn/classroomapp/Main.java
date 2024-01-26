package com.aayzhaonaribn.classroomapp;
import com.aayzhaonaribn.parsing.PDFToText;
import com.aayzhaonaribn.parsing.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    static final String inputPath = "spring2024.pdf";
    static final String outputPath = "pdf.txt";
    public static void main(String[] args) {
        //System.out.println("Hello world");

        PDFToText pdfParser = new PDFToText();
        ResourceLoader loader = new ResourceLoader();
        File inputFile = null;
        File outputFile = null;
        try {
            outputFile = loader.getFileFromResource(outputPath);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.out.println("URI syntax error");
        }

        try {
            inputFile = loader.getFileFromResource(inputPath);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.out.println("URI syntax error");
        }

        assert outputFile != null;
        Path path = Paths.get(outputFile.getPath());
        String result = "";

        try {
            result = pdfParser.parse(inputFile);
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

