package com.yjb.leetcode.medium;

import java.util.Arrays;

/**
 * 80. Remove Duplicates from Sorted Array II
 * <p>
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * <p>
 * For example,
 * Given sorted array nums = [1,1,1,2,2,3],
 * <p>
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3.
 * It doesn't matter what you leave beyond the new length.
 */
public class No080RemoveDuplicatesFromSortedArrayII {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
//        int[] nums = {1, 1, 1, 2, 2, 3};
        System.out.println(mySolutionLoop(nums));// 5
        System.out.println("nums = " + Arrays.toString(nums));// first five elements of nums being 1, 1, 2, 2 and 3
    }

    /**
     * from leetcode user
     */
    public int solution1(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        int j = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[j - 2]) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }

    /**
     * 19.08%
     */
    private static int mySolutionTwice(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int j = 0;
        boolean twice = false;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num != nums[j]) {
                twice = false;
                nums[++j] = num;
                continue;
            }
            if (!twice) {
                twice = true;
                nums[++j] = num;
            }
        }
        return j + 1;
    }

    /**
     * 6.84%
     */
    private static int mySolutionLoop(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (nums[j] != num) {
                nums[++j] = num;
                continue;
            }
            while (i < nums.length - 1 && nums[i + 1] == num) {
                i++;
            }
            nums[++j] = num;
        }
        return j + 1;
    }
}
