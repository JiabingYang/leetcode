package com.yjb.leetcode.medium;

import java.util.Arrays;

/**
 * 62. Unique Paths
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * <p>
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * <p>
 * How many possible unique paths are there?
 * <p>
 * <img src="https://leetcode.com/static/images/problemset/robot_maze.png">
 * <p>
 * Above is a 3 x 7 grid. How many possible unique paths are there?
 * <p>
 * Note: m and n will be at most 100.
 */
public class No062UniquePaths {

    public static void main(String[] args) {
        System.out.println(193536720);
        System.out.println(solution2(23, 12));
    }

    /**
     * 参考自:
     * https://www.programcreek.com/2014/05/leetcode-unique-paths-java/
     * <p>
     * 递推关系直接用dfs写
     * <p>
     * 超时
     */
    private static int solution1(int m, int n) {
        return dfs(m, n, 0, 0);
    }

    private static int dfs(int m, int n, int i, int j) {
        if (i == m - 1 || j == n - 1) {
            return 1;
        }
        if (i < m - 1 && j < n - 1) {
            return dfs(m, n, i + 1, j) + dfs(m, n, i, j + 1);
        }
        return 0;
    }

    /**
     * 递推关系直接用dp写
     * <p>
     * 1ms
     */
    private static int solution2(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][n - 1] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[m - 1][j] = 1;
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
            }
        }
        return dp[0][0];
    }

    /**
     * Modified a leetcode user.
     * <p>
     * 其实solution2中是一行一行从下往上算，所以dp数组只需要保存上一行的结果即可
     * <p>
     * 0ms
     */
    private static int solution3(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[j] = dp[j + 1] + dp[j];
            }
        }
        return dp[0];
    }
}
