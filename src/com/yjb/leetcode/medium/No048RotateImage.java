package com.yjb.leetcode.medium;

/**
 * 48. Rotate Image
 * <p>
 * You are given an n x n 2D matrix representing an image.
 * <p>
 * Rotate the image by 90 degrees (clockwise).
 * <p>
 * Note:
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 * <p>
 * Example 1:
 * <p>
 * Given input matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * rotate the input matrix in-place such that it becomes:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * Example 2:
 * <p>
 * Given input matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * <p>
 * rotate the input matrix in-place such that it becomes:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 */
public class No048RotateImage {

    /**
     * 修改自：
     * https://www.programcreek.com/2013/01/leetcode-rotate-image-java/
     * <p>
     * By using the relation "matrix[i][j] = matrix[n-1-j][i]", we can loop through the matrix.
     */
    private static void solution1(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }
}
