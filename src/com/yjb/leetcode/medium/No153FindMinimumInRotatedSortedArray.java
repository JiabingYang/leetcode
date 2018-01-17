package com.yjb.leetcode.medium;

/**
 * 153. Find Minimum in Rotated Sorted Array
 * <p>
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p>
 * Find the minimum element.
 * <p>
 * You may assume no duplicate exists in the array.
 */
public class No153FindMinimumInRotatedSortedArray {

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{0, 1, 2, 4, 5, 6, 7}));
        System.out.println(findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
        System.out.println(findMin(new int[]{1, 2, 4, 5, 6, 7, 0}));
    }

    /**
     * http://blog.csdn.net/feliciafay/article/details/42962093
     */
    private static int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] > nums[end]) { // mid -> 最大值 -> end <=> nums[mid] > nums[end]
                start = mid + 1;
            } else { // 最大值 -> mid -> end <=> nums[mid] <= nums[end]
                end = mid;
            }
        }
        return nums[end];
    }

    private static int mySolution1(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return nums[i + 1];
            }
        }
        return nums[0];
    }

    private static int mySolution2(int[] nums) {
        return mySolution2(nums, 0, nums.length - 1);
    }

    private static int mySolution2(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        if (end - start == 1) {
            return Math.min(nums[start], nums[end]);
        }
        if (nums[start] < nums[end]) {
            return nums[start];
        }
        int middle = (start + end) / 2;
        if (nums[middle] > nums[end]) {
            return mySolution2(nums, middle, end);
        }
        return mySolution2(nums, start, middle);
    }

    private static int mySolution3(int[] nums) {
        if (nums[0] <= nums[nums.length - 1]) {
            return nums[0];
        }
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            if (end - start == 1) {
                return nums[end];
            }
            int middle = (start + end) / 2;
            if (nums[middle] > nums[end]) {
                start = middle;
            } else {
                end = middle;
            }
        }
        return nums[start];
    }
}
