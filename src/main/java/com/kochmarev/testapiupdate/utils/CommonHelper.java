package com.kochmarev.testapiupdate.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommonHelper {

    public static String readResourcesFileContentAsString(String filePath) throws IllegalArgumentException {

        try (InputStream inputStream = CommonHelper.class.getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException(String.format("%s file not found", filePath));
            }
            return readFromInputStream(inputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("%s failed to read file: %s", filePath, e.getMessage()));
        }
    }

    private static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            resultStringBuilder.append(line);
        }
        return resultStringBuilder.toString();
    }
}
