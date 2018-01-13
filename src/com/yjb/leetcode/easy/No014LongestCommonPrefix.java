package com.yjb.leetcode.easy;

/**
 * 14. Longest Common Prefix.
 * <p>
 * Write a function to find the longest common prefix string amongst an array of strings.
 */
public class No014LongestCommonPrefix {

    /**
     * beats 37.64%
     */
    public String mySolution(String[] strs) {
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

    public String longestCommonPrefixString(String a, String b) {
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
     * beats 73.44%
     */
    public String leetCodeUserAnswer(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String res = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (!strs[i].startsWith(res)) res = res.substring(0, res.length() - 1);
        }
        return res;
    }
}
