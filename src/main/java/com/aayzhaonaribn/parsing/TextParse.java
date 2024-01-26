package com.aayzhaonaribn.parsing;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import com.aayzhaonaribn.classroomapp.Main;

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
                    System.out.println("" + token);
                    Main.courseNumbers.add(Integer.parseInt(scan.next()));
                    scan.next();
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

    private void endSection(Scanner scan, Pattern endOfSection) {
        while (scan.hasNext() && !scan.hasNext(endOfSection)) {
            scan.next();
        }
    }

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
