package com.yjb.leetcode.hard;

import java.util.Arrays;

/**
 * 154. Find Minimum in Rotated Sorted Array II
 * <p>
 * Follow up for "Find Minimum in Rotated Sorted Array":
 * What if duplicates are allowed?
 * <p>
 * Would this affect the run-time complexity? How and why?
 * <p>
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p>
 * Find the minimum element.
 * <p>
 * The array may contain duplicates.
 */
public class No154FindMinimumInRotatedSortedArrayII {

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{3, 3, 1, 3}));
        System.out.println(findMin(new int[]{10, 1, 10, 10, 10}));
    }

    /**
     * 如果mid和end相等只需要end左移一位
     * 或者：start和end相等，start右移一位或者end左移一位
     */
    private static int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] > nums[end]) { // mid -> 最大值 -> end <=> nums[mid] > nums[end]
                start = mid + 1;
            } else if (nums[mid] < nums[end]) { // 最大值 -> mid -> end  || 未旋转的数组 <=> nums[mid] <= nums[end]
                end = mid;
            } else {
                end--;
            }
        }
        return nums[end];
    }
}
