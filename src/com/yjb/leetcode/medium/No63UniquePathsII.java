package com.yjb.leetcode.medium;

import java.util.Arrays;

/**
 * 63. Unique Paths II
 * <p>
 * Follow up for "Unique Paths":
 * <p>
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * <p>
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * <p>
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 * <p>
 * [
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]
 * ]
 * The total number of unique paths is 2.
 * <p>
 * Note: m and n will be at most 100.
 */
public class No63UniquePathsII {

    public static void main(String[] args) {
        int[][] obstacleGrid = {{0, 0}, {0, 0}};
        System.out.println(Arrays.deepToString(obstacleGrid));
        System.out.println(solution1(obstacleGrid));
    }

    /**
     * 修改自：
     * https://www.programcreek.com/2014/05/leetcode-unique-paths-ii-java/
     * （思路和我的思路一样，但是一直没写对时间紧就参考了programcreek）
     * <p>
     * 1ms
     */
    private static int solution1(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (n == 0 || obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        obstacleGrid[m - 1][n - 1] = 1;

        for (int i = m - 2; i >= 0; i--) {
            if (obstacleGrid[i][n - 1] == 1) {
                obstacleGrid[i][n - 1] = 0;
            } else {
                obstacleGrid[i][n - 1] = obstacleGrid[i + 1][n - 1];
            }
        }

        for (int j = n - 2; j >= 0; j--) {
            if (obstacleGrid[m - 1][j] == 1) {
                obstacleGrid[m - 1][j] = 0;
            } else {
                obstacleGrid[m - 1][j] = obstacleGrid[m - 1][j + 1];
            }
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                } else {
                    obstacleGrid[i][j] = obstacleGrid[i + 1][j] + obstacleGrid[i][j + 1];
                }
            }
        }

        return obstacleGrid[0][0];
    }
}
