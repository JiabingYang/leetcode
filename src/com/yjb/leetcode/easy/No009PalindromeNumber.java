package com.yjb.leetcode.easy;

/**
 * 9. Palindrome Number
 * <p>
 * Determine whether an integer is a palindrome. Do this without extra space.
 */
public class No009PalindromeNumber {

    /**
     * beats 51.78%
     */
    public boolean mySolution(int x) {
        if (x == 0) {
            return true;
        }
        if (x < 0) {
            return false;
        }
        int size = ((int) Math.log10(x)) + 1;// 不能使用转字符串
        for (int i = 0; i <= size / 2; i++) {
            if (getNumAt(x, i) != getNumAt(x, size - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public int getNumAt(int integer, int index) {
        return (integer / ((int) Math.pow(10, index))) % 10;
    }

    /**
     * https://leetcode.com/articles/palindrome-number/
     * <p>
     * Time complexity : O(log10n)O(log10n). We divided the input by 10 for every iteration,
     * so the time complexity is O(log10n)O(log10n)
     * <p>
     * Space complexity : O(1)O(1).
     */
    public boolean revertHalfOfTheNumber(int x) {
        // Special cases:
        // As discussed above, when x < 0, x is not a palindrome.
        // Also if the last digit of the number is 0, in order to be a palindrome,
        // the first digit of the number also needs to be 0.
        // Only 0 satisfy this property.
        if (x < 0 || (x % 10 == 0 && x != 0)) { // 负数与整十的情况
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) { // 逆转一半
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // When the length is an odd number, we can get rid of the middle digit by revertedNumber/10
        // For example when the input is 12321, at the end of the while loop we get x = 12, revertedNumber = 123,
        // since the middle digit doesn't matter in palidrome(it will always equal to itself), we can simply get rid of it.
        return x == revertedNumber || x == revertedNumber / 10;
    }

    /**
     * beats 48.76%
     * https://www.cnblogs.com/grandyang/p/4125510.html
     */
    public boolean grandyangSolution(int x) {
        if (x < 0)
            return false;
        int div = 1;
        while (x / div >= 10)
            div *= 10;
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right)
                return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

}
