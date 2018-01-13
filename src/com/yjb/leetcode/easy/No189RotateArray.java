package com.yjb.leetcode.easy;

import java.util.Arrays;

/**
 * 189. Rotate Array
 * <p>
 * Rotate an array of n elements to the right by k steps.
 * <p>
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 * <p>
 * Note:
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * <p>
 * Hint:
 * Could you do it in-place with O(1) extra space?
 * <p>
 * Related problem: Reverse Words in a String II
 */
public class No189RotateArray {

    public static void main(String[] args) {
        new No189RotateArray().bubbleRotate(new int[]{1, 2, 3, 4, 5, 6}, 2);// 561234
    }


    public void mySolution(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // optional
        if (n <= 1) {
            return;
        }
        int[] origin = Arrays.copyOf(nums, n);
        for (int j = 0; j < n; j++) {
            int temp = (j - k) % n;
            int i = temp < 0 ? temp + n : temp;
            nums[j] = origin[i];
        }
    }

    // -------- https://www.programcreek.com/2015/03/rotate-array-in-java/ --------
    public void intermediateArray(int[] nums, int k) {
        if (k > nums.length)
            k = k % nums.length;

        int[] result = new int[nums.length];

        System.arraycopy(nums, nums.length - k, result, 0, k);
//        for (int i = 0; i < k; i++) {
//            result[i] = nums[nums.length - k + i];
//        }

        System.arraycopy(nums, 0, result, k, nums.length - k);
//        for (int i = k; i < nums.length; i++) {
//            result[i] = nums[i-k];
//        }

        System.arraycopy(result, 0, nums, 0, nums.length);
    }

    // O(n*k) time
    public void bubbleRotate(int[] nums, int k) {
        if (nums == null || k < 0) {
            throw new IllegalArgumentException("Illegal argument!");
        }

        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < k; i++) { // k次冒泡
            for (int j = nums.length - 1; j > 0; j--) { //将最后一个冒泡到第一个，路上的每一个都往后移一位
                int temp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = temp;
                System.out.println(Arrays.toString(nums));
            }
        }
    }

    /**
     * O(1) space
     * O(n) time
     * <p>
     * Assuming we are given {1,2,3,4,5,6} and k = 2. The basic idea is:
     * 1. Divide the array two parts: 1,2,3,4 and 5, 6
     * 2. Reverse first part: 4,3,2,1,5,6
     * 3. Reverse second part: 4,3,2,1,6,5
     * 4. Reverse the whole array: 5,6,1,2,3,4
     */
    public void reversal(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) {
            throw new IllegalArgumentException("Illegal argument!");
        }

        if (k > nums.length) {
            k = k % nums.length;
        }

        //length of first part
        int a = nums.length - k;

        reverse(nums, 0, a - 1);
        reverse(nums, a, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    public void reverse(int[] arr, int left, int right) {
        if (arr == null || arr.length == 1)
            return;

        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

}
