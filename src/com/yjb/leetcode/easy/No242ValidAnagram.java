package com.yjb.leetcode.easy;

import java.util.HashMap;

/**
 * 242. Valid Anagram
 * <p>
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 * <p>
 * For example,
 * s = "anagram", t = "nagaram", return true.
 * s = "rat", t = "car", return false.
 * <p>
 * Note:
 * You may assume the string contains only lowercase alphabets.
 * <p>
 * Follow up:
 * What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
public class No242ValidAnagram {

    /**
     * https://www.programcreek.com/2014/05/leetcode-valid-anagram-java/
     */
    private static boolean solution1(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }

        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
            arr[t.charAt(i) - 'a']--;
        }

        for (int i : arr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 28.61%
     * <p>
     * 不适合于Unicode
     */
    private static boolean mySolution1(String s, String t) {
        return sort(s).equals(sort(t));
    }

    private static String sort(String src) {
        // 桶排序
        int[] arr = new int[26];
        for (int i = 0; i < src.length(); i++) {
            arr[src.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < arr[i]; j++) {
                sb.append((char) ('a' + i));
            }
        }
        return sb.toString();
    }

    /**
     * 16.95%
     * <p>
     * 适合于Unicode
     * <p>
     * 和ProgramCreek的Solution 2一样
     */
    private static boolean mySolution2(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            }
            int count = map.get(c);
            if (count == 1) {
                map.remove(c);
            } else {
                map.put(c, count - 1);
            }
        }
        return map.isEmpty();
    }
}
