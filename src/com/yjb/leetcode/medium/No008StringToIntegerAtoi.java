package com.yjb.leetcode.medium;

/**
 * 8. String to Integer (atoi)
 * <p>
 * Implement atoi to convert a string to an integer.
 * <p>
 * Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.
 * <p>
 * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.
 * <p>
 * Update (2015-02-10):
 * The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.
 */
public class No008StringToIntegerAtoi {

    /**
     * 边界处理参考自solution1
     */
    public int mySolution(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        str = str.trim();
        boolean negative = str.charAt(0) == '-';
        int start = negative ? 1 : (str.charAt(0) == '+' ? 1 : 0);
        double result = 0;
        for (int i = start; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                break;
            }
            result = result * 10 + (c - '0');
        }
        result = negative ? -result : result;
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) result;
    }

    /**
     * https://www.programcreek.com/2012/12/leetcode-string-to-integer-atoi/
     */
    public int solution1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        str = str.trim();
        int i = 0;
        int positive = 0;
        if (str.charAt(0) == '-') {
            positive = 1;
            i++;
        } else if (str.charAt(0) == '+') {
            i++;
        }
        double result = 0;
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            result = result * 10 + (str.charAt(i) - '0');
            i++;
        }
        if (positive == 1) {
            result = -result;
        }
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) result;
    }
}
