package com.yjb.leetcode.easy;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * <p>
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 * <p>
 * Examples:
 * <p>
 * pattern = "abba", str = "dog cat cat dog" should return true.
 * pattern = "abba", str = "dog cat cat fish" should return false.
 * pattern = "aaaa", str = "dog cat cat dog" should return false.
 * pattern = "abba", str = "dog dog dog dog" should return false.
 * <p>
 * Notes:
 * You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
 */
public class No290WordPattern {

    /**
     * 双向都用map
     * <p>
     * 其实不需要反向的map，只要加一个HashSet就行
     * <p>
     * 时间 n
     */
    private static boolean mySolution1(String pattern, String str) {
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
     * HashSet替代反向map
     * <p>
     * 时间 n
     */
    private static boolean mySolution2(String pattern, String str) {
        String[] words = str.split(" ");

        if (pattern.length() != words.length) {
            return false;
        }

        HashMap<Character, String> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < words.length; i++) {
            char letter = pattern.charAt(i);
            String word = words[i];

            if (map.containsKey(letter)) {
                if (!map.get(letter).equals(word)) {
                    return false;
                }
            } else if (set.contains(word)) {
                return false;
            } else {
                map.put(letter, word);
                set.add(word);
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
