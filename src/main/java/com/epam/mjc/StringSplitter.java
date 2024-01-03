package com.epam.mjc;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringSplitter {

    /**
     * Splits given string applying all delimiters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {

        String resStr = source;

        for (String c : delimiters.toArray(new String[0])) {
            resStr = resStr.replaceAll(c, " ");
        }

        return Stream.of(resStr.trim()
                        .replaceAll("\\s\\s+", " ")
                        .split(" "))
                .collect(Collectors.toList());
    }
}
