package com.aayzhaonaribn.parsing;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * This class encapsulates methods to load in resource files
 */
public class ResourceLoader {
    /**
     * Loads the given file in the relative path from resources
     * @param fileName              Relative path to file
     * @return                      File object wrapper for given file path
     * @throws URISyntaxException   Thrown if URI is unable to be generated
     */
    private File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new file(resource.getFile());

            return new File(resource.toURI());
        }

    }

    /**
     * Wrapper for resource loading method that handles the URI syntax exception
     * @param fileName  File's relative path
     * @return          File object wrapper for given file
     */
    public File loadFileWrapper(String fileName) { // does the try catch to shorten code overall
        // wraps the getFileFromResource function
        File file = null;
        try {
            file = this.getFileFromResource(fileName);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.out.println(e.getReason());
        }
        assert file != null;
        return file;
    }
}
