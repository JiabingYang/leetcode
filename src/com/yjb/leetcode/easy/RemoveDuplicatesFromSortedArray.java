package com.yjb.leetcode.easy;

/**
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * <p>
 * For example,
 * Given input array nums = [1,1,2],
 * <p>
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the new length.
 */
public class RemoveDuplicatesFromSortedArray {

    /**
     * beats 61.86%
     */
    public int myAnswer(int[] nums) {
        if (nums.length == 0)
            return 0;
        int topIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (nums[topIndex] != num) {
                topIndex++;
                nums[topIndex] = num;
            }
        }
        return topIndex + 1;
    }
}
