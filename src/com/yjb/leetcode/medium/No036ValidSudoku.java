package com.yjb.leetcode.medium;

import java.util.Arrays;

/**
 * 36. Valid Sudoku
 * <p>
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 * <p>
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png">
 * A partially filled sudoku which is valid.
 * <p>
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [
 * ["5","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: true
 * Example 2:
 * <p>
 * Input:
 * [
 * ["8","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being
 * modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 * Note:
 * <p>
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 * The given board contain only digits 1-9 and the character '.'.
 * The given board size is always 9x9.
 */
public class No036ValidSudoku {

    /**
     * https://www.programcreek.com/2014/05/leetcode-valid-sudoku-java/
     */
    private static boolean solution1(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            return false;
        }
        // check each column
        for (int i = 0; i < 9; i++) {
            boolean[] map = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (map[board[i][j] - '1']) {
                        return false;
                    }
                    map[board[i][j] - '1'] = true;
                }
            }
        }

        //check each row
        for (int j = 0; j < 9; j++) {
            boolean[] map = new boolean[9];
            for (int i = 0; i < 9; i++) {
                if (board[i][j] != '.') {
                    if (map[board[i][j] - '1']) {
                        return false;
                    }
                    map[board[i][j] - '1'] = true;
                }
            }
        }

        //check each 3*3 matrix
        for (int block = 0; block < 9; block++) {
            boolean[] m = new boolean[9];
            for (int i = block / 3 * 3; i < block / 3 * 3 + 3; i++) {
                for (int j = block % 3 * 3; j < block % 3 * 3 + 3; j++) {
                    if (board[i][j] != '.') {
                        if (m[board[i][j] - '1']) {
                            return false;
                        }
                        m[board[i][j] - '1'] = true;
                    }
                }
            }
        }

        return true;
    }

    /**
     * 53.61%
     */
    private static boolean mySolution(char[][] board) {
        boolean[] map = new boolean[9];
        for (int i = 0; i < 9; i++) {
            Arrays.fill(map, false);
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num == '.') {
                    continue;
                }
                if (map[num - '1']) {
                    return false;
                }
                map[num - '1'] = true;
            }
        }
        for (int j = 0; j < 9; j++) {
            Arrays.fill(map, false);
            for (int i = 0; i < 9; i++) {
                char num = board[i][j];
                if (num == '.') {
                    continue;
                }
                if (map[num - '1']) {
                    return false;
                }
                map[num - '1'] = true;
            }
        }
        for (int m = 0; m < 9; m += 3) {
            for (int n = 0; n < 9; n += 3) {
                Arrays.fill(map, false);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        char num = board[m + i][n + j];
                        if (num == '.') {
                            continue;
                        }
                        if (map[num - '1']) {
                            return false;
                        }
                        map[num - '1'] = true;
                    }
                }
            }
        }
        return true;
    }
}
