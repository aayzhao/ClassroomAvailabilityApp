package com.aayzhaonaribn.parsing;
import org.apache.pdfbox.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class PDFToText {
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
