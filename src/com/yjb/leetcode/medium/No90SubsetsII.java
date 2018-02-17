package com.yjb.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. Subsets II
 * <p>
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 * <p>
 * Note: The solution set must not contain duplicate subsets.
 * <p>
 * For example,
 * If nums = [1,2,2], a solution is:
 * <p>
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 */
public class No90SubsetsII {

    public static void main(String[] args) {
        System.out.println(solution1(new int[]{1, 2, 2}));
    }

    /**
     * Modified from a leetcode user.
     * <p>
     * 57.30%
     * 4ms
     * <p>
     * 参考
     * No077Combinations#solution1
     * No078Subsets#mySolution3
     */
    private static List<List<Integer>> solution1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void dfs(int[] nums, int start, ArrayList<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));
        for (int i = start; i < nums.length; i++) {
            if (i > 0 && i != start && nums[i] == nums[i - 1]) {
                // 对每一个i来说，递归调用的start为i+1，即只要从i后面的元素中选择添加就行。
                // 而nums[i] == nums[i - 1]的情况中，i产生的情况已经被i-1产生的情况包含了
                continue;
            }
            current.add(nums[i]);
            dfs(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
}
