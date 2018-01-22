package com.yjb.leetcode.hard;

/**
 * 41. First Missing Positive
 * <p>
 * Given an unsorted integer array, find the first missing positive integer.
 * <p>
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 * <p>
 * Your algorithm should run in O(n) time and uses constant space.
 */
public class No041FirstMissingPositive {

    /**
     * https://www.programcreek.com/2014/05/leetcode-first-missing-positive-java/
     * <p>
     * 类似于桶排序，因为空间限制不能再声明一个数组，那么就在原数组上操作。
     * 遍历的时候如果是小于等于数组长度的正数i+1，就保证把它放在i的位置上。
     * 这题如果按正常思路，碰到位置i就想找到i+1并把i+1放在位置i上就想不通了。
     * 倒过来想，把i+1的数放在i上，就行了。
     */
    private static int solution1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1) {
                // 下面判断中programcreek上是nums[i] >= nums.length
                // 但我认为应该是nums[i] > nums.length，虽然写成>=能Accepted
                if (nums[i] < 1 || nums[i] > nums.length) {
                    break;
                }
                if (nums[i] == nums[nums[i] - 1]) {
                    break;
                }
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
