package com.yjb.leetcode.medium;

import java.util.Arrays;

/**
 * 300. Longest Increasing Subsequence
 * <p>
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * <p>
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
 * Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 * <p>
 * Your algorithm should run in O(n2) complexity.
 * <p>
 * Follow up: Could you improve it to O(n log n) time complexity?
 * <p>
 * Credits:
 * Special thanks to @pbrother for adding this problem and creating all test cases.
 */
public class No300LongestIncreasingSubsequence {

    /**
     * 思路参考自：
     * https://www.cnblogs.com/grandyang/p/4938187.html
     */
    private static int solution1(int[] nums) {
        int[] dp = new int[nums.length]; // dp[i]表示以nums[i]为结尾的最长递增子串的长度
        Arrays.fill(dp, 1);
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1); // 递推关系
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    // TODO: 18/3/28 solution2 二分查找
}
