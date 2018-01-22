package com.yjb.leetcode.easy;

import java.util.HashMap;

public class No290WordPattern {

    /**
     * 双向都用map
     * <p>
     * 时间 n
     */
    private static boolean mySolution(String pattern, String str) {
        String[] words = str.split(" ");

        if (pattern.length() != words.length) {
            return false;
        }

        HashMap<Character, String> mapA = new HashMap<>();
        HashMap<String, Character> mapB = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            char letter = pattern.charAt(i);
            String word = words[i];

            if (mapA.containsKey(letter)) {
                if (!mapA.get(letter).equals(word)) {
                    return false;
                }
            } else if (mapB.containsKey(word)) {
                if (mapB.get(word) != letter) {
                    return false;
                }
            } else {
                mapA.put(letter, word);
                mapB.put(word, letter);
            }
        }

        return true;
    }

    /**
     * https://www.programcreek.com/2014/05/leetcode-word-pattern-java/
     * <p>
     * 单向map
     * <p>
     * 时间 n2
     */
    public static boolean solution1(String pattern, String str) {
        String[] words = str.split(" ");

        //prevent out of boundary problem
        if (words.length != pattern.length())
            return false;

        HashMap<Character, String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char letter = pattern.charAt(i);
            if (map.containsKey(letter)) {
                if (!map.get(letter).equals(words[i])) {
                    return false;
                }
            } else if (map.containsValue(words[i])) { // containsValue复杂度是O(n)
                return false;
            }
            map.put(letter, words[i]);
        }

        return true;
    }
}
