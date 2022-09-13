package com.ichmal.trainingspringboot.crud;

public class StringExtensions {
    public static String camelToSnake(String camel) {
        if (camel.isEmpty()) return "";

        // Empty strings
        StringBuilder result = new StringBuilder();

        // Append first character(in lower case)
        // to result string
        char c = camel.charAt(0);
        result.append(Character.toLowerCase(c));

        // Traverse the string from
        // ist index to last index
        for (int i = 1; i < camel.length(); i++) {
            char ch = camel.charAt(i);

            // Check if the character is upper case
            // then append '_' and such character
            // (in lower case) to result string
            if (Character.isUpperCase(ch)) {
                result.append('_');
                result.append(Character.toLowerCase(ch));
            }
            // if the character is lower case then
            // and such character into result string
            else {
                result.append(ch);
            }
        }
        // return the result
        return result.toString();
    }
}
