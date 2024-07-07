package ru.job4j.algo.hash;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LongestUniqueSubstring {

    public static Set<Character> toSet(String str) {
        return str.chars()
                .mapToObj(c -> (char) c)
                .collect(
                        Collectors.toCollection(HashSet::new)
                );
    }

    public static String longestUniqueSubstring(String str) {
        String longest = "";
        int start = 0;
        for (int end = 1; end < str.length() + 1; end++) {
            String subStr = str.substring(start, end);
            Set<Character> current = toSet(subStr);
            if (current.size() < end - start) {
                start++;
            }
            if (current.size() > longest.length()) {
                longest = subStr;
            }
        }
        return longest;
    }
}
