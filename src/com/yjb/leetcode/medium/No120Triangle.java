package com.yjb.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 120. Triangle
 * <p>
 * Given a triangle, find the minimum path sum from top to bottom.
 * Each step you may move to adjacent numbers on the row below.
 * <p>
 * For example, given the following triangle
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * <p>
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space,
 * where n is the total number of rows in the triangle.
 * <p>
 * 关键在于一个点的结果来自于它下面两个点的结果（动态规划），
 * 如果用递归实现，就是dfs（可以用缓存数组优化速度，应该也算一种dp吧）
 * 如果用迭代实现，那么从底而上地得到结果（我觉得自底向上迭代应该是更好的dp）
 */
public class No120Triangle {

    public static void main(String[] args) {
        //[
        //     [2],
        //    [3,4],
        //   [6,5,7],
        //  [4,1,8,3]
        //]
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Collections.singletonList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));
        System.out.println(bottomUp(triangle));// 2 + 3 + 5 + 1 = 11
    }

    // ------------------------ bottomUp ------------------------

    /**
     * https://www.programcreek.com/2013/01/leetcode-triangle-java/
     * <p>
     * We can actually start from the bottom of the triangle.
     * <p>
     * 50%
     */
    public static int bottomUp(List<List<Integer>> triangle) {
        int[] total = new int[triangle.size()];
        int lastRow = triangle.size() - 1;

        for (int i = 0; i < triangle.get(lastRow).size(); i++) {
            total[i] = triangle.get(lastRow).get(i);
        }

        // iterate from last second row
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                total[j] = triangle.get(i).get(j) + Math.min(total[j], total[j + 1]);
            }
        }

        return total[0];
    }

    // ------------------------ mySolutionCurrSum ------------------------

    /**
     * dfs
     * <p>
     * 超时
     */
    private static int mySolutionCurrSum(List<List<Integer>> triangle) {
        if (triangle == null || triangle.isEmpty() || triangle.get(0).isEmpty()) {
            return 0;
        }
        return mySolutionCurrSum(triangle, 0, 0, triangle.get(0).get(0));
    }

    private static int mySolutionCurrSum(List<List<Integer>> triangle, int row, int index, int currSum) {
        if (row == triangle.size() - 1) {
            return currSum;
        }
        return Math.min(mySolutionCurrSum(triangle, row + 1, index, currSum + triangle.get(row + 1).get(index)),
                mySolutionCurrSum(triangle, row + 1, index + 1, currSum + triangle.get(row + 1).get(index + 1)));
    }

    // ------------------------ mySolutionPoint ------------------------

    /**
     * dfs
     * <p>
     * 超时
     */
    private static int mySolutionPoint(List<List<Integer>> triangle) {
        if (triangle == null || triangle.isEmpty() || triangle.get(0).isEmpty()) {
            return 0;
        }
        return mySolutionPoint(triangle, 0, 0);
    }

    private static int mySolutionPoint(List<List<Integer>> triangle, int row, int index) {
        if (row == triangle.size() - 1) {
            return triangle.get(row).get(index);
        }
        return triangle.get(row).get(index) + Math.min(mySolutionPoint(triangle, row + 1, index),
                mySolutionPoint(triangle, row + 1, index + 1));
    }

    // ------------------------ mySolutionPointDp ------------------------

    /**
     * dfs + dp
     * <p>
     * 99%
     */
    private static int mySolutionPointDp(List<List<Integer>> triangle) {
        if (triangle == null || triangle.isEmpty() || triangle.get(0).isEmpty()) {
            return 0;
        }
        int n = triangle.size();
        return mySolutionPointDp(new int[n][n], triangle, 0, 0);
    }

    private static int mySolutionPointDp(int[][] mem, List<List<Integer>> triangle, int row, int index) {
        if (row == triangle.size() - 1) {
            return triangle.get(row).get(index);
        }
        if (mem[row][index] == 0) {
            mem[row][index] = triangle.get(row).get(index) + Math.min(mySolutionPointDp(mem, triangle, row + 1, index),
                    mySolutionPointDp(mem, triangle, row + 1, index + 1));
        }

        return mem[row][index];
    }
}
