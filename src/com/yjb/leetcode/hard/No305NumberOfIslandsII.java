package com.yjb.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 305. Number of Islands II
 * <p>
 * A 2d grid map of m rows and n columns is initially filled with water.
 * We may perform an addLand operation which turns the water at position (row, col) into a land.
 * Given a list of positions to operate, count the number of islands after each addLand operation.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example:
 * <p>
 * Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
 * Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
 * <p>
 * 0 0 0
 * 0 0 0
 * 0 0 0
 * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
 * <p>
 * 1 0 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
 * <p>
 * 1 1 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
 * <p>
 * 1 1 0
 * 0 0 1   Number of islands = 2
 * 0 0 0
 * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
 * <p>
 * 1 1 0
 * 0 0 1   Number of islands = 3
 * 0 1 0
 * We return the result as an array: [1, 1, 2, 3]
 * <p>
 * Challenge:
 * <p>
 * Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */
public class No305NumberOfIslandsII {

    /**
     * https://www.programcreek.com/2015/01/leetcode-number-of-islands-ii-java/
     * <p>
     * Use an array to track the parent node for each cell.
     */
    private static List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[] parents = new int[m * n];
        Arrays.fill(parents, -1);

        List<Integer> result = new ArrayList<>();

        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int count = 0;

        for (int[] position : positions) {
            count++;

            int index = position[0] * n + position[1];
            parents[index] = index; // set parent to be itself for each node

            for (int r = 0; r < 4; r++) {
                int i = position[0] + directions[r][0];
                int j = position[1] + directions[r][1];

                if (i >= 0 && j >= 0 && i < m && j < n && parents[i * n + j] != -1) {
                    // get neighbor's root
                    int nRoot = getRoot(parents, i * n + j);
                    if (nRoot != index) {
                        parents[nRoot] = index;// set previous root's root
                        count--;
                    }
                }
            }

            result.add(count);
        }

        return result;
    }

    private static int getRoot(int[] parents, int i) {
        while (i != parents[i]) {
            i = parents[i]; // 写成 i = parents[parents[i]];; 也可以，因为 root 的父节点还是 root
        }
        return i;
    }
}
