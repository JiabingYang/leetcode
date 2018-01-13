package com.yjb.leetcode.medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 215. Kth Largest Element in an Array
 * <p>
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 * <p>
 * Credits:
 * Special thanks to @mithmatt for adding this problem and creating all test cases.
 */
public class No215KthLargestElementInAnArray {

    public static void main(String[] args) {
        int[] a = {2, 1};
        System.out.println(Arrays.toString(a));
        System.out.println(new No215KthLargestElementInAnArray().quickSelect(a, 1));
        System.out.println(Arrays.toString(a));
    }

    public int heap6A(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            pq.add(num);
        }
        for (int i = 0; i < k - 1; i++) {
            pq.poll();
        }
        return pq.poll();
    }

    public int heap6B(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            pq.add(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (pq.peek() < nums[i]) {
                pq.poll();
                pq.add(nums[i]);
            }
        }
        return pq.poll();
    }

    public int quickSelect(int[] nums, int k) {
        return qSelect(nums, k, 0, nums.length - 1);
    }

    private int qSelect(int[] nums, int k, int left, int right) {
        if (left >= right) {
            return nums[k - 1];
        }
        int pivot = median3(nums, left, right);
        int i = left;
        int j = right - 1;
        while (i < j) {
            while (nums[++i] > pivot) { // 右移i
            }
            while (nums[--j] < pivot) { // 左移j
            }
            // 此时i >= pivot, j <= pivot
            if (i < j) {
                swap(nums, i, j);
            } else { // i和j交错
                break;
            }
        }
        // 此时 i和j交错或重合， <i的元素必然小于pivot，>i的元素必然大于pivot
        swap(nums, i, right - 1);
        if (k <= i) {
            return qSelect(nums, k, left, i - 1);
        } else {
            return qSelect(nums, k, i + 1, right);
        }
    }

    private int median3(int[] nums, int left, int right) {
        int center = (left + right) / 2;

        // sort left, center, right
        if (nums[left] < nums[center]) {
            swap(nums, left, center);
        }
        if (nums[left] < nums[right]) {
            swap(nums, left, right);
        }
        if (nums[center] < nums[right]) {
            swap(nums, center, right);
        }

        // hide pivot
        swap(nums, center, right - 1);
        return nums[right - 1];
    }

    private void swap(int[] nums, int pos1, int pos2) {
        int temp = nums[pos1];
        nums[pos1] = nums[pos2];
        nums[pos2] = temp;
    }
}
