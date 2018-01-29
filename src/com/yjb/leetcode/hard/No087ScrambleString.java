package com.yjb.leetcode.hard;

import java.util.Arrays;

/**
 * 87. Scramble String
 * <p>
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 * <p>
 * Below is one possible representation of s1 = "great":
 * <p>
 * great
 * /    \
 * gr    eat
 * / \    /  \
 * g   r  e   at
 * / \
 * a   t
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 * <p>
 * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 * <p>
 * rgeat
 * /    \
 * rg    eat
 * / \    /  \
 * r   g  e   at
 * / \
 * a   t
 * We say that "rgeat" is a scrambled string of "great".
 * <p>
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
 * <p>
 * rgtae
 * /    \
 * rg    tae
 * / \    /  \
 * r   g  ta  e
 * / \
 * t   a
 * We say that "rgtae" is a scrambled string of "great".
 * <p>
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 */
public class No087ScrambleString {

    /**
     * 参考自：
     * https://www.programcreek.com/2014/05/leetcode-scramble-string-java/
     * <p>
     * 归并
     */
    private static boolean solution1(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        int len = s1.length();
        for (int i = 1; i < arr1.length; i++) {
            String s11 = s1.substring(0, i);
            String s12 = s1.substring(i, len);
            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i, len);
            if (solution1(s11, s21) && solution1(s12, s22)) {
                return true;
            }
            String s23 = s2.substring(0, len - i);
            String s24 = s2.substring(len - i, len);
            if (solution1(s11, s24) && solution1(s12, s23)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 参考自：
     * A leetcode user
     * <p>
     * 归并
     * 特例判断中使用hash替代排序
     */
    private static boolean solution2(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }

        int[] hash = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            hash[s1.charAt(i) - 'a']++;
            hash[s2.charAt(i) - 'a']--;
        }
        for (int count : hash) {
            if (count != 0) {
                return false;
            }
        }

        int len = s1.length();
        for (int i = 1; i < len; i++) {
            String s11 = s1.substring(0, i);
            String s12 = s1.substring(i, len);
            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i, len);
            if (solution2(s11, s21) && solution2(s12, s22)) {
                return true;
            }
            String s23 = s2.substring(0, len - i);
            String s24 = s2.substring(len - i, len);
            if (solution2(s11, s24) && solution2(s12, s23)) {
                return true;
            }
        }
        return false;
    }
}
