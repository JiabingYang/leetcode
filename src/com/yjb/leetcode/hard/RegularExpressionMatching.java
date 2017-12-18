package com.yjb.leetcode.hard;

import java.util.Arrays;

/**
 * 10. Regular Expression Matching
 * <p>
 * Implement regular expression matching with support for '.' and '*'.
 * <p>
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element. "*"号表示它之前的字符可以出现任意多次（包括0）.
 * <p>
 * The matching should cover the entire input string (not partial).
 * <p>
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * <p>
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 */
public class RegularExpressionMatching {

    // ------------------------ 测试 ------------------------
    public static void main(String[] args) {
        String[][] testCases = {
                {"aa", "a"},
                {"aa", "aa"},
                {"aaa", "aa"},
                {"aa", "a*"},
                {"aa", ".*"},
                {"ab", ".*"},
                {"aab", "c*a*b"},
        };
        RegularExpressionMatching regexMatching = new RegularExpressionMatching();
        boolean[] results = new boolean[testCases.length];
        for (int i = 0; i < testCases.length; i++) {
            String[] testCase = testCases[i];
            results[i] = regexMatching.isMatch(testCase[0], testCase[1]);

        }
        System.out.println(Arrays.equals(results, new boolean[]{false, true, false, true, true, true, true}));
    }

    /**
     * 来自：
     * https://www.programcreek.com/2012/12/leetcode-regular-expression-matching-in-java/
     *
     * 注：
     * 从JDK1.7开始，subString()内部会复制字符数组，是O(n)操作，该解法需要优化的话，减少subString()调用的次数是最直接的。
     */
    public boolean isMatch(String s, String p) {
        // base case: p.length() == 0
        if (p.length() == 0) {
            return s.length() == 0;
        }

        // special case: p.length() == 1
        if (p.length() == 1) {

            // if the length of s is 0, return false
            if (s.length() < 1) {
                return false;
            }

            // if the first char of s and the first char of p is not the same,
            // and the char of p is not '.', return false
            if ((p.charAt(0) != s.charAt(0)) && (p.charAt(0) != '.')) {
                return false;
            }

            // otherwise, compare the rest of the string of s and p.
            return isMatch(s.substring(1), p.substring(1));
        }

        // cases below: p.length() >= 2

        // case 1: p.charAt(1) != '*'
        if (p.charAt(1) != '*') {
            if (s.length() < 1) {
                return false;
            }
            if ((p.charAt(0) != s.charAt(0)) && (p.charAt(0) != '.')) {
                return false;
            }
            return isMatch(s.substring(1), p.substring(1));
        }

        // case 2: p.charAt(1) == '*'
        // case 2.1: the '*' stands for 0 preceding element
        if (isMatch(s, p.substring(2))) {
            return true;
        }

        // case 2.2: the '*' stands for 1 or more preceding element
        // try every possible number
        int i = 0;
        while (i < s.length() && (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.')) {
            if (isMatch(s.substring(i + 1), p.substring(2))) {
                return true;
            }
            i++;
        }
        return false;
    }
}
