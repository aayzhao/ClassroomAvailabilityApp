package com.aayzhaonaribn.parsing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import classes.Course;
import com.aayzhaonaribn.classroomapp.Main;

/**
 * Encapsulates methods for parsing .txt files
 */
public class TextParse {
    /**
     * Parse the given text file and extract class information
     * @param filePath      relative path to .txt file
     * @param loader        resource loader object
     * @param DEBUG_MODE    if true, print out extra information to console
     * @return              returns a list of course objects
     */
    public List<Course> parseTextFile(String filePath, ResourceLoader loader, boolean DEBUG_MODE) {
        File textFile = loader.loadFileWrapper(filePath);
        List<Course> courses = new ArrayList<>();

        Scanner scan = initScanner(textFile, DEBUG_MODE);

        // regex patterns
        Pattern majorCodePattern = Pattern.compile("[A-Z][A-Z][A-Z][A-Z]|[A-Z][A-Z][A-Z]");
        Pattern endOfSectionPattern = Pattern.compile("__.*");
        Pattern classNumPattern = Pattern.compile("[0-9]*H?"); // some classes are honors classes, thus an H can be at the end
        Pattern roomPattern = Pattern.compile(".*Room:|.*R[a-zA-Z]oom:|oom:"); // some room identifier tokens are  corrupted, misspelled
        Pattern buildingPattern = Pattern.compile("Bldg:");
        Pattern daysPattern = Pattern.compile("Days:");
        // end regex patterns

        System.out.println(scan.nextLine());
        String token, courseCode, building, room, days;
        StringBuilder sb = new StringBuilder();
        int courseNumber;
        while (scan.hasNext()) {
            if (scan.hasNext(majorCodePattern)) {
                token = scan.next();
                if (scan.hasNext(classNumPattern)) {
                    String classNum = scan.next();

                    courseCode = "" + token + " " + classNum + " " + scan.next();
                    if (DEBUG_MODE) System.out.println(courseCode); // debug info

                    courseNumber = Integer.parseInt(scan.next());
                    if (!Main.classCodes.add(courseNumber)) throw new IllegalArgumentException("Repeat class identifier found!");
                    if (DEBUG_MODE) System.out.println(courseNumber);

                    while (scan.hasNext() && !scan.hasNext(buildingPattern) && !scan.hasNext(endOfSectionPattern)) {
                        scan.next(); // parse to the "bldg:" pattern
                    }
                    if (scan.hasNext(endOfSectionPattern)) throw new IndexOutOfBoundsException("Reached end of section before parsing required information");
                    scan.next(); // parse past the "Bldg:" pattern

                    while (scan.hasNext() && !scan.hasNext(roomPattern) && !scan.hasNext(endOfSectionPattern)) {
                        sb.append(scan.next()); // parse building name
                    }
                    if (scan.hasNext(endOfSectionPattern)) throw new IndexOutOfBoundsException("Reached end of section before parsing required information");
                    building = sb.toString();
                    scan.next(); // parse past the room pattern
                    room = scan.next(); // parse room code

//                    while (scan.hasNext() && !scan.hasNext(daysPattern)) {
//                        scan.next(); // parse to the "Days:" pattern
//                    }
//                    scan.next(); // parse past the "Days:" pattern
//                    days = scan.next();

                    courses.add(new Course(courseCode, building, room, courseNumber));

                }
                this.endSection(scan, endOfSectionPattern); // move to end of this class section
            } else {
                scan.next();
            }
        }
        if (DEBUG_MODE) {
            System.out.println("No more matches");
            System.out.println(courses.size());
        }

        scan.close();
        return new ArrayList<>();
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
