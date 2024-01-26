package com.aayzhaonaribn.classroomapp;
import com.aayzhaonaribn.parsing.Parser;

import java.io.IOException;

public class Main {
    static final String filePath = "spring2024.pdf";
    public static void main(String[] args) {
        System.out.println("Hello world");
        Parser parser = new Parser();
        String result = "";

        try {
            result = parser.parse(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(result);

    }

}

