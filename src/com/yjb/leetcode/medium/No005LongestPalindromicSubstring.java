package com.yjb.leetcode.medium;

/**
 * 5. Longest Palindromic Substring
 * <p>
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Example:
 * <p>
 * Input: "babad"
 * <p>
 * Output: "bab"
 * <p>
 * Note: "aba" is also a valid answer.
 * Example:
 * <p>
 * Input: "cbbd"
 * <p>
 * Output: "bb"
 */
public class No005LongestPalindromicSubstring {

    public static void main(String[] args) {
        System.out.println(mySolutionBruteForce("babad"));
        System.out.println(mySolution("babad"));
        System.out.println(mySolutionBruteForce("cbbd"));
        System.out.println(mySolution("cbbd"));
        System.out.println(mySolutionBruteForce("cbbdccdbdcbdbcdcbdcbdcdcbdbcd"));
        System.out.println(mySolution("cbbdccdbdcbdbcdcbdcbdcdcbdbcd"));
    }

    // ---------------------------- solution1 ----------------------------

    /**
     * https://www.programcreek.com/2013/12/leetcode-solution-of-longest-palindromic-substring-java/
     * <p>
     * 1. Dynamic Programming
     * <p>
     * Let s be the input string, i and j are two indices of the string.
     * Define a 2-dimension array "table" and let table[i][j] denote whether a substring from i to j is palindrome.
     * <p>
     * Changing condition:
     * <p>
     * table[i+1][j-1] == true && s.charAt(i) == s.charAt(j)
     * =>
     * table[i][j] == true
     * <p>
     * 时间n2 空间n2
     */
    private static String solution1(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        int max = 1;
        boolean[][] dp = new boolean[s.length()][s.length()];

        String result = null;
        for (int len = 0; len < s.length(); len++) {
            for (int i = 0; i < s.length() - len; i++) {
                int j = i + len;
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;

                    if (j - i + 1 > max) {
                        max = j - i + 1;
                        result = s.substring(i, j + 1);
                    }
                }
            }
        }
        return result;
    }

    // ---------------------------- solution2 ----------------------------

    /**
     * https://www.programcreek.com/2013/12/leetcode-solution-of-longest-palindromic-substring-java/
     * <p>
     * 2. A Simple Algorithm
     * <p>
     * 思路和我的方法的思路一样
     * <p>
     * 时间n2 空间1
     */
    private static String solution2(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        String result = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            // get longest palindrome with center of i
            String tmp = helper(s, i, i);
            if (tmp.length() > result.length()) {
                result = tmp;
            }

            // get longest palindrome with center of i, i+1
            tmp = helper(s, i, i + 1);
            if (tmp.length() > result.length()) {
                result = tmp;
            }
        }
        return result;
    }

    // Given a center, either one letter or two letter,
    // Find longest palindrome
    private static String helper(String s, int begin, int end) {
        while (begin >= 0 && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        return s.substring(begin + 1, end);
    }

    // ---------------------------- mySolution ----------------------------

    /**
     * 34.34%
     * <p>
     * 时间n2 空间1
     */
    private static String mySolution(String s) {
        int max = 0;
        String result = null;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j <= i + 1 && j < s.length(); j++) {
                boolean isPalindrome;
                if (i == j) {
                    if (max < 1) {
                        max = 1;
                        result = s.substring(i, j + 1);
                    }
                    isPalindrome = true;
                } else {
                    isPalindrome = s.charAt(i) == s.charAt(j);
                    if (isPalindrome && max < 2) {
                        max = 2;
                        result = s.substring(i, j + 1);
                    }
                }
                if (isPalindrome) {
                    int count = 1;
                    while (i - count >= 0 && j + count < s.length()) {
                        if (s.charAt(i - count) != s.charAt(j + count)) {
                            break;
                        }
                        int length = count * 2 + j - i + 1;
                        if (max < length) {
                            max = length;
                            result = s.substring(i - count, j + count + 1);
                        }
                        count++;
                    }
                }
            }
        }
        return result;
    }

    // ---------------------------- bruteForce ----------------------------

    /**
     * bruteForce
     * 超时
     */
    private static String mySolutionBruteForce(String s) {
        int max = 0;
        String result = null;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome(s, i, j)) {
                    int len = j - i + 1;
                    if (len > max) {
                        max = len;
                        result = s.substring(i, j + 1);
                    }
                }
            }
        }
        return result;
    }

    private static boolean isPalindrome(String s, int start, int end) {
        int i = start;
        int j = end;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
