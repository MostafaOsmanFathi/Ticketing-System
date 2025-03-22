package com.ticketing.util;

public class DataVisualizer {
    private DataVisualizer() {
    }

    public static String convertData(String className, String... data) {
        StringBuilder result = new StringBuilder("<" + className);
        boolean isHeader = true;
        for (int i = 0; i < data.length; i++) {
            if (isHeader) {
                result.append(data[i]).append(":");
            } else {
                result.append(data[i]);
                if (i == data.length - 1) {
                    result.append(">");
                } else {
                    result.append(",");
                }
            }
            isHeader = !isHeader;
        }
        return result.toString();
    }
}
