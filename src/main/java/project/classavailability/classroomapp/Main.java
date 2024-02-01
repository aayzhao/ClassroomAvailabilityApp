package project.classavailability.classroomapp;
import project.classavailability.classes.*;
import project.classavailability.parsing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Main {
    static final String INPUT_PATH = "spring2024.pdf";
    static final String OUTPUT_PATH = "pdf.txt";
    static final boolean DEBUG_MODE = true;
    // public static final HashSet<Integer> courseNumbers = new HashSet<>();
    // public static final HashSet<Integer> classCodes = new HashSet<>();

    public static void main(String[] args) {
        // startup and initialize classes
        ResourceLoader loader = new ResourceLoader(); // initialize resource loader
        ClassroomScheduleManager manager;
        ListCleaner cleaner = new ListCleaner();
        //System.out.println("Hello world"); // because why not

        // Startup operations
        // Load data from PDF
        // generates a text file with all the pdf text contained within
        loadPDFToText(loader);

        // create parser to parse the pdf.txt file
        TextParse parser = new TextParse();
        List<Course> listOfCourses = parser.parseTextFile(OUTPUT_PATH, loader, DEBUG_MODE); // generate list of classes
        // HashSet<Integer> visualizer = courseNumbers;
        // HashSet<Integer> visualizer2 = classCodes;
        // System.out.println(courseNumbers.size() + " Class types");
        // System.out.println(classCodes.size() + " Unique Classes in total");

        HashSet<String> buildings = new HashSet<>();
        for (Course course : listOfCourses) {
            buildings.add(course.getBuilding());
            System.out.println(course.getBuilding());
        }
        System.out.println("Buildings count: " + buildings.size());
        for (String building : buildings) System.out.println(building);

        // clean the list of classes
        System.out.println(listOfCourses.size());
        cleaner.removeTBA(listOfCourses);
        System.out.println(listOfCourses.size());

        // create hashmap of rooms
        HashMap<String, Room> map = new HashMap<>();
        for (Course course : listOfCourses) {
            if (map.containsKey(course.getLocation())) {
                map.get(course.getLocation()).addCourse(course);
            } else {
                Room newRoom = new Room(course.getBuilding(), course.getRoom());
                newRoom.addCourse(course);
                map.put(course.getLocation(), newRoom);
            }
        }

        // rudimentary testing of created hashmap
        System.out.println(map.size());
        String[] arr = new String[map.size()];
        int i = 0;
        for (String rm : map.keySet()) {
            // System.out.println(rm);
            arr[i++] = rm;
        }

        System.out.println("\n\n\n\nSorted list: ");
        Arrays.sort(arr);
        for (String rm : arr) {
            System.out.println(rm);
        }

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

