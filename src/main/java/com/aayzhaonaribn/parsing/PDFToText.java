package com.aayzhaonaribn.parsing;
import org.apache.pdfbox.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

/**
 * Utilizes Apache pdfbox to convert pdfs to text
 */
public class PDFToText {
    /**
     * Parses the given .pdf file into a String object
     * @param pdfFile       Relative path to the .pdf file
     * @return              String representation of the contents of the .pdf file
     * @throws IOException  Thrown if file does not exist
     */
    public String parse(File pdfFile) throws IOException {
        assert pdfFile != null;

        PDFTextStripper text = new PDFTextStripper();
        text.setSortByPosition(true);
        PDDocument doc = null;

        try {
            doc = Loader.loadPDF(pdfFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text.getText(doc);
    }
}
