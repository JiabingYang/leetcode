package com.yjb.leetcode.medium;

/**
 * 59. Spiral Matrix II
 * <p>
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * <p>
 * For example,
 * Given n = 3,
 * <p>
 * You should return the following matrix:
 * <p>
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 */
public class No059SpiralMatrixII {

    private static int[][] mySolution(int n) {
        int[][] matrix = new int[n][n];
        int max = (n + 1) / 2;
        int num = 1;
        for (int lt = 0; lt < max; lt++) {
            int rb = n - 1 - lt;
            for (int i = lt; i <= rb; i++) {
                matrix[lt][i] = num++;
            }
            for (int i = lt + 1; i <= rb; i++) {
                matrix[i][rb] = num++;
            }
            if (lt != rb) {
                for (int i = rb - 1; i >= lt; i--) {
                    matrix[rb][i] = num++;
                }
                for (int i = rb - 1; i > lt; i--) {
                    matrix[i][lt] = num++;
                }
            }
        }
        return matrix;
    }
}
