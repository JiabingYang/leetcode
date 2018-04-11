package com.yjb.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. Spiral Matrix
 * <p>
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * <p>
 * For example,
 * Given the following matrix:
 * <p>
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5].
 */
public class No054SpiralMatrix {

    /**
     * 思路同剑指offer: No29PrintMatrix
     */
    private static List<Integer> mySolution(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        int max = (Math.min(matrix.length, matrix[0].length) + 1) / 2;
        for (int lt = 0; lt < max; lt++) {
            mySolution(matrix, lt, result);
        }
        return result;
    }

    private static void mySolution(int[][] matrix, int lt, List<Integer> result) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int r = matrix[0].length - 1 - lt;
        int b = matrix.length - 1 - lt;
        for (int i = lt; i <= r; i++) {
            result.add(matrix[lt][i]);
        }
        for (int i = lt + 1; i <= b; i++) {
            result.add(matrix[i][r]);
        }
        if (lt != b) {
            for (int i = r - 1; i >= lt; i--) {
                result.add(matrix[b][i]);
            }
        }
        if (lt != r) {
            for (int i = b - 1; i > lt; i--) {
                result.add(matrix[i][lt]);
            }
        }
    }
}
