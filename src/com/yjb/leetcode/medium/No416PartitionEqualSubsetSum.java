package com.yjb.leetcode.medium;

/**
 * 416. Partition Equal Subset Sum
 * <p>
 * Given a non-empty array containing only positive integers, find if the array can be
 * partitioned into two subsets such that the sum of elements in both subsets is equal.
 * <p>
 * Note:
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 * Example 1:
 * <p>
 * Input: [1, 5, 11, 5]
 * <p>
 * Output: true
 * <p>
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2:
 * <p>
 * Input: [1, 2, 3, 5]
 * <p>
 * Output: false
 * <p>
 * Explanation: The array cannot be partitioned into equal sum subsets.
 */
public class No416PartitionEqualSubsetSum {

    public static void main(String[] args) {
        System.out.println(mySolution2(new int[]{1, 2, 5}));
    }

    /**
     * 思路参考自下面博客的评论：
     * https://www.cnblogs.com/grandyang/p/5951422.html
     * <p>
     * 19.44%
     * <p>
     * 0/1背包（二维数组）
     * <p>
     * dp[i][j]代表从第一个元素到第i个元素组成的集合中是否能挑出一个子集满足其和是刚好等于j的，
     * 那么我们的最终就是要算出dp[n][sum/2].
     * <p>
     * 对于dp[i][j]来说，分为两种情况，
     * 如果j >= nums[i-1]的话，那么dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]],
     * 否则的话，dp[i][j] = dp[i-1][j]
     */
    private static boolean mySolution1(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum >>> 1;
        boolean[][] dp = new boolean[nums.length + 1][target + 1];
        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][target];
    }

    /**
     * 思路参考自下面博客的评论：
     * https://www.cnblogs.com/grandyang/p/5951422.html
     * <p>
     * 65.17%
     * <p>
     * 0/1背包（滚动数组）
     * <p>
     * dp[i][j]代表从第一个元素到第i个元素组成的集合中是否能挑出一个子集满足其和是刚好等于j的，
     * 那么我们的最终就是要算出dp[n][sum/2].
     * <p>
     * 对于dp[i][j]来说，分为两种情况，
     * 如果j >= nums[i-1]的话，那么dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]],
     * 否则的话，dp[i][j] = dp[i-1][j]
     */
    private static boolean mySolution2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum >>> 1;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = target; j >= nums[i - 1]; j--) {
                dp[j] = dp[j] || dp[j - nums[i - 1]];
            }
        }
        return dp[target];
    }
}
