package com.yjb.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 51. N-Queens
 * <p>
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * <p>
 * <img src="https://leetcode.com/static/images/problemset/8-queens.png">
 * <p>
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * <p>
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 * <p>
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 * <p>
 * [
 * [".Q..",  // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 */
public class No051NQueens {

    /**
     * 5ms
     * 93.60%
     * <p>
     * 思路参考剑指offer的P200
     */
    private static class MySolution {
        private char[] row;

        public List<List<String>> solveNQueens(int n) {
            List<List<String>> result = new ArrayList<>();
            if (n == 0) {
                return result;
            }
            int[] colIndexes = new int[n];
            row = new char[n];
            for (int i = 0; i < n; i++) {
                colIndexes[i] = i;
                row[i] = '.';
            }
            dfs(colIndexes, 0, new ArrayList<>(), result);
            return result;
        }

        private void dfs(int[] colIndexes, int start, List<String> current, List<List<String>> result) {
            if (start == colIndexes.length) {
                result.add(new ArrayList<>(current));
                return;
            }
            for (int i = start; i < colIndexes.length; i++) {
                if (!isValid(colIndexes, start, colIndexes[i])) {
                    continue;
                }
                swap(colIndexes, start, i);
                current.add(buildStr(colIndexes[start]));
                dfs(colIndexes, start + 1, current, result);
                current.remove(current.size() - 1);
                swap(colIndexes, i, start);
            }
        }

        private String buildStr(int j) {
            row[j] = 'Q';
            String result = new String(row);
            row[j] = '.';
            return result;
        }

        private void swap(int[] colIndexes, int i, int j) {
            int temp = colIndexes[i];
            colIndexes[i] = colIndexes[j];
            colIndexes[j] = temp;
        }

        private boolean isValid(int[] colIndexes, int end, int value) {
            for (int i = 0; i < end; i++) {
                int diff = value - colIndexes[i];
                if (end - i == diff || i - end == diff) {
                    return false;
                }
            }
            return true;
        }
    }
}
