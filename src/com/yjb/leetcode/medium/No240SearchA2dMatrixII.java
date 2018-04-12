package com.yjb.leetcode.medium;

/**
 * 240. Search a 2D Matrix II
 * <p>
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * <p>
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * For example,
 * <p>
 * Consider the following matrix:
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 * <p>
 * Given target = 20, return false.
 */
public class No240SearchA2dMatrixII {

    /**
     * 剑指offer: No04FindInPartiallySortedMatrix
     */
    private static boolean mySolutionIteration(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rtX = matrix[0].length - 1;
        int rtY = 0;
        while (rtX >= 0 && rtY <= matrix.length - 1) {
            int rt = matrix[rtY][rtX];
            if (rt == target) {
                return true;
            }
            if (rt > target) {
                rtX--;
                continue;
            }
            rtY++;
        }
        return false;
    }

    /**
     * 剑指offer: No04FindInPartiallySortedMatrix
     */
    private static boolean mySolutionRecursion(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        return mySolutionRecursion(matrix, target, matrix[0].length - 1, 0);
    }

    private static boolean mySolutionRecursion(int[][] matrix, int target, int rtX, int rtY) {
        if (rtX < 0 || rtY > matrix.length - 1) {
            return false;
        }
        int rt = matrix[rtY][rtX];
        if (rt == target) {
            return true;
        }
        if (rt > target) {
            return mySolutionRecursion(matrix, target, rtX - 1, rtY);
        }
        return mySolutionRecursion(matrix, target, rtX, rtY + 1);
    }
}
