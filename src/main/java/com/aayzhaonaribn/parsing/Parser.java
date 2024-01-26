package com.aayzhaonaribn.parsing;
import org.apache.pdfbox.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Parser {
    public String parse(String fileName) throws IOException {
        Parser parser = new Parser();
        File pdfFile = null;

        try {
            pdfFile = parser.getFileFromResource(fileName);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        PDFTextStripper text = new PDFTextStripper();
        text.setSortByPosition(true);
        PDDocument doc = null;

        try {
            assert pdfFile != null;
            doc = Loader.loadPDF(pdfFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text.getText(doc);
    }

    public File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            return new File(resource.toURI());
        }

    }}
