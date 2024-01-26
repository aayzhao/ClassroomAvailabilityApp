package com.aayzhaonaribn.parsing;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class ResourceLoader {
    public File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            return new File(resource.toURI());
        }

    }

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
