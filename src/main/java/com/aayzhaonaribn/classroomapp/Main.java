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
import java.util.Scanner;

public class Main {
    static final String INPUT_PATH = "spring2024.pdf";
    static final String OUTPUT_PATH = "pdf.txt";
    static final boolean DEBUG_MODE = true;

    public static void main(String[] args) {
        ResourceLoader loader = new ResourceLoader(); // initialize resource loader
        //System.out.println("Hello world"); // because why not

        // Startup operations
        // Load data from PDF
        // generates a text file with all the pdf text contained within
        loadPDFToText(loader);

        // open a scanner for the newly made .txt file
        File textFile = null;
        try {
            textFile = loader.getFileFromResource(OUTPUT_PATH);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            if (DEBUG_MODE) System.out.println("Error reading generated text file");
        }
        assert textFile != null;

        Scanner scan = null;
        try {
            scan = new Scanner(textFile);
        } catch (IOException e) {
            e.printStackTrace();
            if (DEBUG_MODE) System.out.println("Text ver of pdf missing");
        }



    }

    private static void loadPDFToText(ResourceLoader loader) {
        PDFToText pdfParser = new PDFToText();
        File inputFile = null;
        File outputFile = null;
        try {
            outputFile = loader.getFileFromResource(OUTPUT_PATH);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.out.println("URI syntax error");
        }

        try {
            inputFile = loader.getFileFromResource(INPUT_PATH);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            if (DEBUG_MODE) System.out.println("URI syntax error");
        }

        assert outputFile != null;
        Path path = Paths.get(outputFile.getPath());
        String result = "";

        try {
            result = pdfParser.parse(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (DEBUG_MODE) System.out.println(result); // several thousand lines of output to console

        try {
            Files.writeString(path, result, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Invalid path");
        }
    }

}

