package com.yjb.leetcode.other;

/**
 * https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20%E9%A2%98%E8%A7%A3.md
 * <p>
 * 最长公共子序列
 * 对于两个子序列 S1 和 S2，找出它们最长的公共子序列。
 * <p>
 * 定义一个二维数组 dp 用来存储最长公共子序列的长度，其中 dp[i][j] 表示 S1 的前 i 个字符与 S2 的前 j 个字符最长公共子序列的长度。
 * 考虑 S1i 与 S2j 值是否相等，分为两种情况：
 * 1. 当 S1i==S2j 时，dp[i][j] = dp[i-1][j-1] + 1。
 * 2. 当 S1i != S2j 时，dp[i][j] = max{ dp[i-1][j], dp[i][j-1] }。
 */
public class LongestCommonSubsequence {

    private static int lengthOfLCS(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[nums1.length][nums2.length];
    }
}
