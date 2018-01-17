package com.yjb.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 229. Majority Element II
 * <p>
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * The algorithm should run in linear time and in O(1) space.
 */
public class No229MajorityElementII {

    public static void main(String[] args) {
        System.out.println(solution1(new int[]{1, 3, 3, 5, 5, 6}));
    }

    /**
     * https://www.programcreek.com/2014/07/leetcode-majority-element-ii-java/
     * <p>
     * 摩尔投票算法
     * 每次取三个不同的数，取到取不出来的时候剩下的就是答案
     */
    public static List<Integer> solution1(int[] nums) {
        List<Integer> result = new ArrayList<>();

        Integer num1 = null, num2 = null;
        int count1 = 0, count2 = 0;

        for (int num : nums) {
            if (num1 != null && num == num1) {
                count1++;
            } else if (num2 != null && num == num2) {
                count2++;
            } else if (count1 == 0) {
                count1 = 1;
                num1 = num;
            } else if (count2 == 0) {
                count2 = 1;
                num2 = num;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = count2 = 0;

        for (int num : nums) {
            if (num == num1) {
                count1++;
            } else if (num == num2) {
                count2++;
            }
        }

        if (count1 > nums.length / 3) {
            result.add(num1);
        }
        if (count2 > nums.length / 3) {
            result.add(num2);
        }

        return result;
    }
}
