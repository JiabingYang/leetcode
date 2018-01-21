package com.yjb.leetcode.easy;

import java.util.Arrays;

/**
 * 268. Missing Number
 * <p>
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * <p>
 * Example 1
 * <p>
 * Input: [3,0,1]
 * Output: 2
 * Example 2
 * <p>
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * <p>
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 * <p>
 * Credits:
 * Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
 */
public class No268MissingNumber {

    private static int mySolution(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return nums.length * (nums.length + 1) / 2 - sum;
    }

    /**
     * https://www.programcreek.com/2014/05/leetcode-missing-number-java/
     */
    private static int solution2(int[] nums) {
        int miss = 0;
        for (int i = 0; i < nums.length; i++) {
            miss ^= (i + 1) ^ nums[i];
        }
        return miss;
    }

    /**
     * https://www.programcreek.com/2014/05/leetcode-missing-number-java/
     */
    private static int solution3(int[] nums) {
        Arrays.sort(nums);
        int l = 0, r = nums.length;// 这里r = nums.length - 1;会出错
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[m] > m) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        return r; // return l;也是可以的
    }
}
