package com.yjb.leetcode.easy;

/**
 * 58. Length of Last Word
 * <p>
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 * <p>
 * If the last word does not exist, return 0.
 * <p>
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * <p>
 * Example:
 * <p>
 * Input: "Hello World"
 * Output: 5
 */
public class No058LengthOfLastWord {

    public static void main(String[] args) {
        System.out.println(mySolution(""));
        System.out.println(mySolution("Hello World"));
        System.out.println(mySolution("Hello World "));
        System.out.println(mySolution("Hello World  "));
        System.out.println(mySolution("   World  "));
        System.out.println(mySolution("World"));
        System.out.println(mySolution(" "));
    }

    public static int mySolution(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int right = -1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (right != -1) {
                    return right - i;
                }
            } else if (right == -1) {
                right = i;
            }
        }
        return right == -1 ? 0 : right + 1;
    }

    /**
     * 改进自下面的solution1
     */
    public static int mySolution1(String s) {
        int result = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                result++;
            } else if (result != 0) {
                return result;
            }
        }
        return result;
    }

    /**
     * https://www.programcreek.com/2014/05/leetcode-length-of-last-word-java/
     */
    public static int solution1(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int result = 0;
        int len = s.length();

        boolean flag = false;
        for (int i = len - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                flag = true;
                result++;
            } else {
                if (flag)
                    return result;
            }
        }

        return result;
    }
}
