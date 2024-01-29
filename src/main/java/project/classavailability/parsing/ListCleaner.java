package project.classavailability.parsing;

import java.util.ArrayList;
import java.util.List;

public class ListCleaner {
    /**
     * Removes all occurrences of the string "TBA" from the provided list.
     *
     * @param inputList the list from which to remove the string "TBA"
     * @return a new list with "TBA" removed
     */

    public List<String> removeTBA(List<String> inputList) {
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
        List<String> cleanedList = cleaner.removeTBA(myList);

        // Print the cleaned list
        System.out.println("Cleaned List: " + cleanedList);
    }
}
