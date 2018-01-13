package com.yjb.leetcode.easy;

/**
 * 88. Merge Sorted Array
 * <p>
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * <p>
 * Note:
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold
 * additional elements from nums2. The number of elements initialized in nums1 and nums2 are m
 * and n respectively.
 * <p>
 * 双指针，从后往前归并
 */
public class MergeSortedArray {

    public void mySolution(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
        if (i < 0) {
            System.arraycopy(nums2, 0, nums1, 0, j + 1);
        }
    }

    /**
     * https://www.programcreek.com/2012/12/leetcode-merge-sorted-array-java/
     */
    public void solution2(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (k >= 0) {
            if (j < 0 || (i >= 0 && nums1[i] > nums2[j]))
                nums1[k--] = nums1[i--];
            else
                nums1[k--] = nums2[j--];
        }
    }

    /**
     * https://www.programcreek.com/2012/12/leetcode-merge-sorted-array-java/
     */
    public void solution1(int[] nums1, int m, int[] nums2, int n) {

        while (m > 0 && n > 0) {
            if (nums1[m - 1] > nums2[n - 1]) {
                nums1[m + n - 1] = nums1[m - 1];
                m--;
            } else {
                nums1[m + n - 1] = nums2[n - 1];
                n--;
            }
        }

        while (n > 0) {
            nums1[m + n - 1] = nums2[n - 1];
            n--;
        }
    }
}
