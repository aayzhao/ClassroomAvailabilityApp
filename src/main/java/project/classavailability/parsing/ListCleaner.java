package project.classavailability.parsing;

import project.classavailability.classes.Course;

import java.util.ArrayList;
import java.util.List;

public class ListCleaner {
    /**
     * Takes an input list, searches it for courses with TBA fields, then removes them.
     * Mutates the input list.
     * @param inputList List of courses to be cleaned
     * @return          original list, mutated
     */
    public List<Course> removeTBA(List<Course> inputList) {
        if (inputList == null) throw new IllegalArgumentException("Input list cannot be null");
        for (int i = inputList.size() - 1; i >= 0; i--) {
            // System.out.println(inputList.get(i).getCourseCode());
            // System.out.println(inputList.get(i).getTimeSlot());
            // System.out.println(inputList.get(i).getRoom());
            if (inputList.get(i).getRoom().equals("TBA") || inputList.get(i).getTimeSlot() == null) {
                inputList.remove(i);
                // System.out.println("Removed");
            }
        }
        return inputList;
    }

    /**
     * Removes all occurrences of the string "TBA" from the provided list.
     *
     * @param inputList the list from which to remove the string "TBA"
     * @return a new list with "TBA" removed
     */
    public List<String> removeTBAString(List<String> inputList) {
        List<String> resultList = new ArrayList<>();
        for (String item : inputList) {
            if (!"TBA".equals(item)) {
                resultList.add(item);
            }
        }
        return resultList;
    }

    // Example usage
    public static void main(String[] args) {
        ListCleaner cleaner = new ListCleaner();

        // Example list with "TBA"
        List<String> myList = new ArrayList<>();
        myList.add("Item1");
        myList.add("TBA");
        myList.add("Item2");
        myList.add("TBA");
        myList.add("Item3");

        // Remove "TBA" from the list
        List<String> cleanedList = cleaner.removeTBAString(myList);

        // Print the cleaned list
        System.out.println("Cleaned List: " + cleanedList);
    }
}
