package com.yjb.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. Subsets
 * <p>
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * <p>
 * Note: The solution set must not contain duplicate subsets.
 * <p>
 * For example,
 * If nums = [1,2,3], a solution is:
 * <p>
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class No078Subsets {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(solution1(nums));
    }

    /* ---------------- solution1 -------------- */

    /**
     * Modified from a leetcode user.
     * <p>
     * 1.32%
     * 1ms
     * <p>
     * 针对{1,2,3},
     * result变化过程：
     * result = [[]]
     * ----------num = 1
     * result = [[], [1]]
     * ----------num = 2
     * result = [[], [1], [2]]
     * result = [[], [1], [2], [1, 2]]
     * ----------num = 3
     * result = [[], [1], [2], [1, 2], [3]]
     * result = [[], [1], [2], [1, 2], [3], [1, 3]]
     * result = [[], [1], [2], [1, 2], [3], [1, 3], [2, 3]]
     * result = [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
     */
    private static List<List<Integer>> solution1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        result.add(new ArrayList<>());
        for (int num : nums) {
            // 对每一个num来说，对当前result里的每个结果（上轮的）加入num生成新的结果加入result中
            int size = result.size();
            for (int j = 0; j < size; j++) {
                ArrayList<Integer> temp = new ArrayList<>(result.get(j));
                temp.add(num);
                result.add(temp);
            }
        }
        return result;
    }

    /* ---------------- mySolution3 -------------- */

    /**
     * 参考自:
     * No090SubsetsII#solution1
     * <p>
     * 29.29%
     * 3ms
     */
    private static List<List<Integer>> mySolution3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        myDfs3(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void myDfs3(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            myDfs3(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    /* ---------------- mySolution2 -------------- */

    /**
     * 参考自:
     * No077Combinations#solution1
     * <p>
     * 29.29%
     * 3ms
     * <p>
     * 因为nums数组不包含重复元素，所以可以直接对nums排序以保证先取后取顺序
     * <p>
     * 比mySolution3复杂，不推荐
     */
    private static List<List<Integer>> mySolution2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int k = 0; k <= nums.length; k++) {
            myDfs2(nums, k, 0, new ArrayList<>(), result);
        }
        return result;
    }

    private static void myDfs2(int[] nums, int k, int start, List<Integer> current, List<List<Integer>> result) {
        if (k == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (nums.length - start < k) { // start == nums.length时，k != 0，这句一定return
            return;
        }
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            myDfs2(nums, k - 1, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    /* ---------------- mySolution1 -------------- */

    /**
     * 参考自:
     * No077Combinations#solution2
     * <p>
     * 29.29%
     * 3ms
     * <p>
     * 比mySolution3复杂，不推荐
     */
    private static List<List<Integer>> mySolution1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int k = 0; k <= nums.length; k++) {
            myDfs1(nums, k, 0, new ArrayList<>(), result);
        }
        return result;
    }

    private static void myDfs1(int[] nums, int k, int start, List<Integer> current, List<List<Integer>> result) {
        if (k == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (nums.length - start < k) { // start == nums.length时，k != 0，这句一定return
            return;
        }
        int num = nums[start];
        // 选择当前元素
        current.add(num);
        myDfs1(nums, k - 1, start + 1, current, result);
        current.remove(current.size() - 1);
        // 不选当前元素
        myDfs1(nums, k, start + 1, current, result);
    }
}
