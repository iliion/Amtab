package com.project.app.services.converters;

public class ConverterUtils {

    public static String convertLine(String line) {
        if (line.contains("\\/")) {
            line.replace("\\/", "barrato");
        }
        return line;

    }

}
