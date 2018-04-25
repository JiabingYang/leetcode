package com.yjb.leetcode.medium;

/**
 * 200. Number of Islands
 * <p>
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example 1:
 * <p>
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 * <p>
 * Example 2:
 * <p>
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer: 3
 * <p>
 * Credits:
 * Special thanks to @mithmatt for adding this problem and creating all test cases.
 */
public class No200NumberOfIslands {

    // ------------------------ mySolution ------------------------

    /**
     * 82.98%
     */
    private static int mySolution(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j, m, n);
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfs(char[][] grid, int i, int j, int m, int n) {
        if (grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        if (i > 0) {
            dfs(grid, i - 1, j, m, n);
        }
        if (i < m - 1) {
            dfs(grid, i + 1, j, m, n);
        }
        if (j > 0) {
            dfs(grid, i, j - 1, m, n);
        }
        if (j < n - 1) {
            dfs(grid, i, j + 1, m, n);
        }
    }

    // ------------------------ solution1 ------------------------

    /**
     * https://www.programcreek.com/2014/04/leetcode-number-of-islands-java/
     * <p>
     * Java Solution 1 - DFS
     * <p>
     * The basic idea of the following solution is merging adjacent lands, and the merging should be done recursively.
     * <p>
     * Each element is visited once only. So time is O(m*n).
     * <p>
     * 和mySolution思路一样
     */
    private static int solution1(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int m = grid.length;
        int n = grid[0].length;

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    merge(grid, i, j);
                }
            }
        }

        return count;
    }

    private static void merge(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1')
            return;

        grid[i][j] = 'X';

        merge(grid, i - 1, j);
        merge(grid, i + 1, j);
        merge(grid, i, j - 1);
        merge(grid, i, j + 1);
    }

    // ------------------------ solution2 ------------------------

    /**
     * 修改自：
     * https://www.programcreek.com/2014/04/leetcode-number-of-islands-java/
     * <p>
     * Java Solution 2 - Union-Find
     * <p>
     * 时间 m*n*log(k)
     */
    private static int solution2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int[] parents = new int[m * n];

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    parents[i * n + j] = i * n + j;
                    count++;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];

                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                            int cRoot = getRoot(parents, i * n + j);
                            int nRoot = getRoot(parents, x * n + y);
                            if (nRoot != cRoot) {
                                parents[cRoot] = nRoot; //update cRoot's parent to be nRoot
                                count--;
                            }
                        }
                    }
                }
            }
        }

        return count;
    }

    private static int getRoot(int[] parents, int i) {
        while (parents[i] != i) {
            i = parents[parents[i]]; // 写成 i = parents[i]; 也可以，因为 root 的父节点还是 root
        }
        return i;
    }
}
