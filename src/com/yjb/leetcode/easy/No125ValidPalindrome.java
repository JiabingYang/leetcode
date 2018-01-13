package com.yjb.leetcode.easy;

/**
 * 125. Valid Palindrome
 * <p>
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * <p>
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 * <p>
 * Note:
 * Have you consider that the string might be empty? This is a good question to ask during an interview.
 * <p>
 * For the purpose of this problem, we define empty string as valid palindrome.
 */
public class No125ValidPalindrome {

    public static void main(String[] args) {
        System.out.println(mySolution("A man, a plan, a canal: Panama"));
        System.out.println(mySolution("race a car"));
        System.out.println(mySolution("0P"));
    }

    /**
     * 辅助函数参考自
     * https://www.programcreek.com/2012/11/top-10-algorithms-for-coding-interview/
     */
    public static boolean mySolution(String s) {
        if (s.length() <= 1) {
            return true;
        }
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char ci = s.charAt(i);
            if (!isAlpha(ci) && !isNum(ci)) {
                i++;
                continue;
            }
            char cj = s.charAt(j);
            if (!isAlpha(cj) && !isNum(cj)) {
                j--;
                continue;
            }
            if (!isSame(ci, cj)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static boolean isAlpha(char a) {
        return (a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z');
    }

    public static boolean isNum(char a) {
        return a >= '0' && a <= '9';
    }

    public static boolean isSame(char a, char b) {
        if (isNum(a) && isNum(b)) {
            return a == b;
        }
        return Character.toLowerCase(a) == Character.toLowerCase(b);
    }
}
