package com.yjb.leetcode.easy;

import java.util.Arrays;

/**
 * 35. Search Insert Position
 * <p>
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * <p>
 * You may assume no duplicates in the array.
 */
public class SearchInsertPosition {

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 6};
        int key1 = 5; // 2
        int key2 = 2; // 1
        int key3 = 7; // 4
        int key4 = 0; // 0
        System.out.println(Arrays.binarySearch(a, key1));
        System.out.println(-Arrays.binarySearch(a, key2) - 1);
        System.out.println(-Arrays.binarySearch(a, key3) - 1);
        System.out.println(-Arrays.binarySearch(a, key4) - 1);
        System.out.println(mySolution(a, key1));
        System.out.println(mySolution(a, key2));
        System.out.println(mySolution(a, key3));
        System.out.println(mySolution(a, key4));
    }

    /**
     * 二分查找
     */
    private static int mySolution(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            int midVal = nums[mid];
            if (midVal > target) {
                right = mid - 1;
            } else if (midVal < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }
}
