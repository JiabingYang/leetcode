package com.yjb.leetcode.medium;

import java.util.*;

/**
 * 215. Group Shifted Strings
 * <p>
 * Given a string, we can "shift" each of its letter to its successive letter,
 * for example: "abc" -> "bcd".
 * We can keep "shifting" which forms the sequence: "abc" -> "bcd" -> ... -> "xyz".
 * <p>
 * Given a list of strings which contains only lowercase alphabets,
 * group all strings that belong to the same shifting sequence, return:
 * <p>
 * [
 * ["abc","bcd","xyz"],
 * ["az","ba"],
 * ["acef"],
 * ["a","z"]
 * ]
 */
public class No249GroupShiftedStrings {

    public static void main(String[] args) {
        String[] strings = {"abc", "bcd", "xyz", "az", "ba", "acef", "a", "z"};
        System.out.println(solution1(strings));
    }

    /**
     * https://www.programcreek.com/2014/05/leetcode-group-shifted-strings-java/
     */
    private static List<List<String>> solution1(String[] strings) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : strings) {
            char[] arr = s.toCharArray();
            if (arr.length > 0) {
                int diff = arr[0] - 'a';
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] - diff < 'a') {
                        arr[i] = (char) (arr[i] - diff + 26);
                    } else {
                        arr[i] = (char) (arr[i] - diff);
                    }
                }
            }

            String ns = new String(arr);
            List<String> list = map.getOrDefault(ns, new ArrayList<>());
            list.add(s);
            map.put(ns, list);
        }

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            Collections.sort(entry.getValue());
        }

        return new ArrayList<>(map.values());
    }

    private static List<List<String>> mySolution(String[] strings) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strings) {
            String f = toFirst(s);
            List<String> list = map.getOrDefault(f, new ArrayList<>());
            list.add(s);
            map.put(f, list);
        }

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            Collections.sort(entry.getValue());
        }

        return new ArrayList<>(map.values());
    }

    private static String toFirst(String s) {
        if (s.length() == 0) {
            return s;
        }
        if (s.length() == 1) {
            return "a";
        }
        if (s.charAt(0) == 'a') {
            return s;
        }
        int diff = s.charAt(0) - 'a';
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int a = s.charAt(i) - diff;
            a = a < 'a' ? a + 26 : a;
            sb.append((char) a);
        }
        return sb.toString();
    }
}
