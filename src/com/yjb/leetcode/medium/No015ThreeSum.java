package com.yjb.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 3Sum
 * <p>
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * Note: The solution set must not contain duplicate triplets.
 * <p>
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 * <p>
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class No015ThreeSum {

    public static void main(String[] args) {
        int[][] ss = {
                {-1, 0, 1, 2, -1, -4},
                {0, 0, 0, 0},
                {-2, 0, 0, 2, 2},
                {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6}
        };
        for (int[] s : ss) {
            System.out.println(threeSum(s));
        }
    }

    /**
     * 来自：
     * https://www.programcreek.com/2012/12/leetcode-3sum/
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length < 3) {
            return result;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // nums[i] == nums[i-1]的情况已经在i-1时考虑过了，避免重复
            // 注意方向，如果i向右遍历，那么应该在i的右边找j和k，这样才能在碰到连续相同i时跳过，
            // 如果在i的左边找，那么出现连续相同i时，前一次就不能包含后一次了
            if (i == 0 || nums[i] > nums[i - 1]) {
                // [i+1, nums.length-1]范围内做TwoSumII里的查找方式（数组已排序）
                int j = i + 1;
                int k = nums.length - 1;

                while (j < k) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));

                        j++;
                        k--;

                        //跳过相同的j和k，避免重复
                        while (j < k && nums[j] == nums[j - 1])
                            j++;
                        while (j < k && nums[k] == nums[k + 1])
                            k--;
                    } else if (nums[i] + nums[j] + nums[k] < 0) {
                        j++;
                    } else {
                        k--;
                    }
                }
            }
        }
        return result;
    }
}
