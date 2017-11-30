package com.yjb.leetcode.easy;

/**
 * 26. Remove Duplicates from Sorted Array
 * <p>
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
     * topIndex为结果数组当前写好的最后一个位置
     * 遍历数组，只有该数和topIndex的数不一样时才写入结果数组的下一个位置
     * <p>
     * beats 61.86%
     */
    public int mySolution(int[] nums) {
        if (nums.length == 0)
            return 0;
        int topIndex = 0; // 0为初始topIndex，num[0]不用设置就为正确的值
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
