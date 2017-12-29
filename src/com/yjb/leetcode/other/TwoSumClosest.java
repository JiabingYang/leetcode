package com.yjb.leetcode.other;

import java.util.Arrays;

/**
 * 2Sum Closest
 * <p>
 * Given an array nums of n integers, find two integers in nums such that the sum is closest to a given number, target.
 * Return the difference between the sum of the two integers and the target.
 * <p>
 * Example
 * Given array nums = [-1, 2, 1, -4], and target = 4.
 * The minimum difference is 1. (4 - (2 + 1) = 1).
 */
public class TwoSumClosest {

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        TwoSumClosest twoSumClosest = new TwoSumClosest();
        System.out.println(twoSumClosest.twoSumCloset(nums, 4));
        System.out.println(twoSumClosest.twoSumClosetReturnDiff(nums, 4));
    }

    public int twoSumCloset(int[] nums, int target) {
        int min = Integer.MAX_VALUE;
        int result = 0;

        Arrays.sort(nums);

        int i = 0;
        int j = nums.length - 1;

        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == target) {
                return sum;
            }

            int diff = Math.abs(sum - target);
            if (diff < min) {
                min = diff;
                result = sum;
            }

            if (sum < target) {
                i++;
            } else {
                j--;
            }
        }

        return result;
    }

    public int twoSumClosetReturnDiff(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("nums must has at least two elements");
        }

        int min = Integer.MAX_VALUE;

        Arrays.sort(nums);

        int i = 0;
        int j = nums.length - 1;

        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == target) {
                return 0;
            }
            int diff = Math.abs(sum - target);
            if (diff < min) {
                min = diff;
            }

            if (sum > target) {
                j--;
            } else {
                i++;
            }
        }

        return min;
    }
}
