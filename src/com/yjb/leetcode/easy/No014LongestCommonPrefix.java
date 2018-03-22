package com.yjb.leetcode.easy;

/**
 * 14. Longest Common Prefix.
 * <p>
 * Write a function to find the longest common prefix string amongst an array of strings.
 */
public class No014LongestCommonPrefix {

    /**
     * https://www.programcreek.com/2014/02/leetcode-longest-common-prefix-java/
     * <p>
     * To solve this problem, we need to find the two loop conditions.
     * One is the length of the shortest string.
     * The other is iteration over every element of the string array.
     */
    private static String solution1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1)
            return strs[0];
        int minLen = Integer.MAX_VALUE; // programcreek上写的是strs.length+1（应该是不对的）
        for (String str : strs) {
            if (minLen > str.length()) {
                minLen = str.length();
            }
        }
        for (int i = 0; i < minLen; i++) {
            for (int j = 0; j < strs.length - 1; j++) {
                String s1 = strs[j];
                String s2 = strs[j + 1];
                if (s1.charAt(i) != s2.charAt(i)) {
                    return s1.substring(0, i);
                }
            }
        }
        return strs[0].substring(0, minLen);
    }

    /**
     * beats 37.64%
     */
    private static String mySolution(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        if (strs.length == 1)
            return strs[0];
        String result = strs[0];
        for (int i = 1; i < strs.length; i++) {
            result = longestCommonPrefixString(result, strs[i]);
        }
        return result;
    }

    private static String longestCommonPrefixString(String a, String b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length() && i < b.length(); i++) {
            char charA = a.charAt(i);
            if (charA != b.charAt(i)) {
                return sb.toString();
            }
            sb.append(charA);
        }
        return sb.toString();
    }

    /**
     * From a leetCode User.
     * beats 73.44%
     */
    private static String solution2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String result = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (!strs[i].startsWith(result)) {
                result = result.substring(0, result.length() - 1);
            }
        }
        return result;
    }
}
