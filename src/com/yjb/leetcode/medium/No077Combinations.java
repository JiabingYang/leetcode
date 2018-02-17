package com.yjb.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. Combinations
 * <p>
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * <p>
 * For example,
 * If n = 4 and k = 2, a solution is:
 * <p>
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public class No077Combinations {

    /* ---------------- solution2 -------------- */

    /**
     * Modified from a leetcode user.
     * <p>
     * 3ms
     */
    private static List<List<Integer>> solution2(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        dfs2(n, k, 1, new ArrayList<>(), result);
        return result;
    }

    private static void dfs2(int n, int k, int start, List<Integer> current, List<List<Integer>> result) {
        if (k == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (n - start + 1 < k) { // start == n时，k != 0，这句一定return
            return;
        }

        // current添加start
        current.add(start);
        dfs2(n, k - 1, start + 1, current, result);
        current.remove(current.size() - 1);

        // current不添加start
        dfs2(n, k, start + 1, current, result);
    }

    /* ---------------- solution1 -------------- */

    /**
     * 修改自:
     * https://www.programcreek.com/2014/03/leetcode-combinations-java/
     * <p>
     * 6ms
     */
    private static List<List<Integer>> solution1(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n < 1 || k > n) {
            return result;
        }
        dfs1(n, k, 1, new ArrayList<>(), result);
        return result;
    }

    private static void dfs1(int n, int k, int start, List<Integer> current, List<List<Integer>> result) {
        if (k == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (n - start + 1 < k) {
            return;
        }
        // current依次添加[start,n]
        for (int i = start; i <= n; i++) {
            current.add(i);
            dfs1(n, k - 1, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
}
