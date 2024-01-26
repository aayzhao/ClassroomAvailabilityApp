package com.aayzhaonaribn.classroomapp;
import com.aayzhaonaribn.parsing.PDFToText;
import com.aayzhaonaribn.parsing.ResourceLoader;
import com.aayzhaonaribn.parsing.TextParse;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

public class Main {
    static final String INPUT_PATH = "spring2024.pdf";
    static final String OUTPUT_PATH = "pdf.txt";
    static final boolean DEBUG_MODE = true;
    public static final HashSet<Integer> courseNumbers = new HashSet<>();
    public static final HashSet<Integer> classCodes = new HashSet<>();

    public static void main(String[] args) {
        ResourceLoader loader = new ResourceLoader(); // initialize resource loader
        //System.out.println("Hello world"); // because why not

        // Startup operations
        // Load data from PDF
        // generates a text file with all the pdf text contained within
        loadPDFToText(loader);

        // open a scanner for the newly made .txt file
        TextParse parser = new TextParse();
        parser.parseTextFile(OUTPUT_PATH, loader, DEBUG_MODE);
        HashSet<Integer> visualizer = courseNumbers;
        HashSet<Integer> visualizer2 = classCodes;
        System.out.println(courseNumbers.size() + " Class types");
        System.out.println(classCodes.size() + " Unique Classes in total");


    }

    private static void loadPDFToText(ResourceLoader loader) {
        PDFToText pdfParser = new PDFToText();
        File inputFile = loader.loadFileWrapper(INPUT_PATH);
        File outputFile = loader.loadFileWrapper(OUTPUT_PATH);
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

