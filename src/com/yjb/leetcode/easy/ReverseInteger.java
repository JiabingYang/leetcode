package com.yjb.leetcode.easy;

import java.util.LinkedList;

/**
 * 7. Reverse Integer
 *
 * Reverse digits of an integer.
 * <p>
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 * <p>
 * Note:
 * The input is assumed to be a 32-bit signed integer.
 * Your function should return 0 when the reversed integer overflows.
 */
public class ReverseInteger {

    /**
     * beats 66.7%
     */
    public int mySolutionNoLinkedList(int x) {
        int result = 0;
        int num;
        do {
            num = x % 10;
            int newResult = result * 10 + num;
            if (result != (newResult - num) / 10)
                return 0; // return 0 when the reversed integer overflows.
            result = newResult;
            x /= 10;
        } while (x != 0);
        return result;
    }

    /**
     * beats 34.40%
     */
    public int mySolutionLinkedList(int x) {
        if (x == 0) {
            return 0;
        }
        LinkedList<Integer> nums = new LinkedList<>();
        int num;
        do {
            num = x % 10;
            nums.add(num);
            x /= 10;
        } while (x != 0);
        int power = 0;
        long result = 0;
        while (!nums.isEmpty()) {
            result += nums.removeLast() * Math.pow(10, power);
            power++;
        }
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
//        double border = Math.pow(2, 31);
//        if (result > border - 1 || result < -border) {
//            return 0;
//        }
        return (int) result;
    }

    /**
     * beats 58.44%
     */
    public int topAnswer(int x) {
        int result = 0;
        while (x != 0) {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            if (result != (newResult - tail) / 10)
                return 0;
            result = newResult;
            x = x / 10;
        }
        return result;
    }

}
