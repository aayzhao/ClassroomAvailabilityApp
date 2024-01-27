package com.aayzhaonaribn.parsing;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import com.aayzhaonaribn.classroomapp.Main;

/**
 * Encapsulates methods for parsing .txt files
 */
public class TextParse {
    public void parseTextFile(String filePath, ResourceLoader loader, boolean DEBUG_MODE) {
        File textFile = loader.loadFileWrapper(filePath);

        Scanner scan = initScanner(textFile, DEBUG_MODE);
        Pattern majorCode = Pattern.compile("[A-Z][A-Z][A-Z][A-Z]|[A-Z][A-Z][A-Z]");
        Pattern endOfSection = Pattern.compile("__.*");
        Pattern classNum = Pattern.compile("[0-9]*");
        System.out.println(scan.nextLine());
        String token;

        while (scan.hasNext()) {

            if (scan.hasNext(majorCode)) {
                token = scan.next();
                if (scan.hasNext(classNum)) {
                    int num = Integer.parseInt(scan.next());
                    Main.courseNumbers.add(num);
                    System.out.println("" + token + " " + num  + " " + scan.next());
                    Main.classCodes.add(Integer.parseInt(scan.next()));
                }
                this.endSection(scan, endOfSection);
            } else {
                scan.next();
            }
        }
        System.out.println("No more matches");

        scan.close();
    }

    /**
     * Navigates the given Scanner object to the end of section token
     * @param scan          Scanner object to manipulate
     * @param endOfSection  regex pattern for the end of section
     */
    private void endSection(Scanner scan, Pattern endOfSection) {
        while (scan.hasNext() && !scan.hasNext(endOfSection)) {
            scan.next();
        }
    }

    /**
     * Initializes a scanner for a given .txt file
     * @param textFile      File object wrapper for a .txt file
     * @param DEBUG_MODE    If true, prints out extra information to console
     * @return              Scanner object that reads the given .txt file. Must be closed later
     */
    private Scanner initScanner(File textFile, boolean DEBUG_MODE) {
        Scanner initialized = null;
        try {
            initialized = new Scanner(textFile);
        } catch (IOException e) {
            e.printStackTrace();
            if (DEBUG_MODE) System.out.println("Text ver of pdf missing");
        }

        assert initialized != null;
        return initialized;
    }
}
