package com.yjb.leetcode.easy;

import java.util.Arrays;

/**
 * 283. Move Zeroes
 * <p>
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * <p>
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 * <p>
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class No283MoveZeroes {

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        System.out.println(Arrays.toString(nums));
        solution2(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * https://www.programcreek.com/2014/05/leetcode-move-zeroes-java/
     * <p>
     * Actually, we can use the similar code that is used to solve
     * Remove Duplicates from Sorted Array I, II, Remove Element.
     * We can use almost identical code to solve those problems!
     */
    public static void solution1(int[] nums) {
        int i = 0;
        int j = 0;

        while (j < nums.length) {
            if (nums[j] == 0) {
                j++;
                continue;
            }
            nums[i++] = nums[j++];
        }

        while (i < nums.length) {
            nums[i] = 0;
            i++;
        }
    }

    /**
     * 18.5%
     */
    public static void mySolution(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == 0) {
                count++;
                continue;
            }
            nums[i - count] = nums[i];
        }
        for (int i = 0; i < count; i++) {
            nums[nums.length - i - 1] = 0;
        }
    }

    /**
     * https://www.programcreek.com/2014/05/leetcode-move-zeroes-java/
     */
    public static void solution2(int[] nums) {
        int m = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (m == -1 || nums[m] != 0) {
                    m = i;
                }
            } else {
                if (m != -1) {
                    int temp = nums[i];
                    nums[i] = nums[m];
                    nums[m] = temp;
                    m++;
                    System.out.println(Arrays.toString(nums));
                }
            }
        }
    }
}
