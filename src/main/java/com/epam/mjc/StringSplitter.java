package com.epam.mjc;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        final String DELIMITER = "|";
        final String PREFIX = "[";
        final String SUFFIX = "]";

        String regex = delimiters.stream()
                .collect(Collectors.joining(DELIMITER, PREFIX, SUFFIX));
        return Arrays.stream(source.split(regex))
                .filter(e -> !e.isEmpty()).collect(Collectors.toList());
    }
}
