package com.yjb.leetcode.medium;

import java.util.Arrays;

/**
 * 16. 3Sum Closest
 * <p>
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * <p>
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * <p>
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class No016ThreeSumClosest {

    /**
     * 参考自：
     * https://www.programcreek.com/2013/02/leetcode-3sum-closest-java/
     * <p>
     * 和ThreeSum的主要区别在于while循环体中增加diff的判断和min的修改
     */
    public int No016threeSumClosest(int[] nums, int target) {
        int min = Integer.MAX_VALUE;
        int result = 0;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return sum;
                }

                int diff = Math.abs(sum - target);
                if (diff < min) {
                    min = diff;
                    result = sum;
                }

                if (sum < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }

        return result;
    }
}
