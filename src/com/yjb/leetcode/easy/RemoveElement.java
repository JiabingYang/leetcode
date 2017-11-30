package com.yjb.leetcode.easy;

/**
 * 27. Remove Element
 * <p>
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * <p>
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * <p>
 * Example:
 * Given input array nums = [3,2,2,3], val = 3
 * <p>
 * Your function should return length = 2, with the first two elements of nums being 2.
 */
public class RemoveElement {

    /**
     * topIndex为结果数组当前写好的最后一个位置
     * 遍历数组，只有该数和val不一样时才写入结果数组的下一个位置
     * <p>
     * beats 51.88%
     */
    public int mySolution(int[] nums, int val) {
        int topIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                topIndex++;
                nums[topIndex] = nums[i];
            }
        }
        return topIndex + 1;
    }

    // -------- https://leetcode.com/articles/remove-element/ --------

    /**
     * Approach #1 (Two Pointers) [Accepted]
     * <p>
     * Intuition
     * Since question asked us to remove all elements of the given value in-place, we have to handle it with O(1)O(1) extra space. How to solve it? We can keep two pointers ii and jj, where ii is the slow-runner while jj is the fast-runner.
     * <p>
     * Algorithm
     * When nums[j] equals to the given value, skip this element by incrementing j. As long as nums[j] != val,
     * we copy nums[j] to nums[i] and increment both indexes at the same time. Repeat the process until j reaches
     * the end of the array and the new length is i.
     * <p>
     * This solution is very similar to the solution to Remove Duplicates from Sorted Array.
     * <p>
     * Complexity analysis
     * Time complexity : O(n). Assume the array has a total of n elements, both i and j(topIndex) traverse at most 2n steps.
     * Space complexity : O(1).
     */
    public int approach1(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    /**
     * Approach #2 (Two Pointers - when elements to remove are rare) [Accepted]
     * <p>
     * Intuition
     * Now consider cases where the array contains few elements to remove.
     * For example, nums = [1,2,3,5,4], val = 4.
     * The previous algorithm will do unnecessary copy operation of the first four elements.
     * Another example is nums = [4,1,2,3,5], val = 4.
     * It seems unnecessary to move elements [1,2,3,5]
     * one step left as the problem description mentions that the order of elements could be changed.
     * <p>
     * Algorithm
     * When we encounter nums[i] = val, we can swap the current element out with the last element and
     * dispose the last one.
     * This essentially reduces the array's size by 1.
     * <p>
     * Note that the last element that was swapped in could be the value you want to remove itself.
     * But don't worry, in the next iteration we will still check this element.
     * <p>
     * Complexity analysis
     * Time complexity : O(n). Both i and n traverse at most n steps.
     * In this approach, the number of assignment operation is equal to the number of elements to remove.
     * So it is more efficient if elements to remove are rare.
     * Space complexity : O(1).
     */
    public int approach2(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1];
                // reduce array size by one
                n--;
            } else {
                i++;
            }
        }
        return n;
    }
}