package com.yjb.leetcode.medium;

/**
 * 209. Minimum Size Subarray Sum
 * <p>
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 * <p>
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 */
public class No209MinimumSizeSubarraySum {

    public static void main(String[] args) {
        System.out.println(solution1(7, new int[]{4, 1, 2, 2, 3, 3}));
        System.out.println(solution2(7, new int[]{4, 1, 2, 2, 3, 3}));
        System.out.println(mySolutionDp(7, new int[]{4, 1, 2, 2, 3, 3}));
        System.out.println(solution1(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(solution2(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(mySolutionDp(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    /**
     * 滑动窗口
     * <p>
     * We can use 2 points to mark the left and right boundaries of the sliding window.
     * When the sum is greater than the target, shift the left pointer; // 因为右指针是从左边滑过来的，所以右指针向左滑的情况已经考虑过了
     * when the sum is less than the target, shift the right pointer. // 同理， 左指针向右滑的情况已经考虑过了
     * <p>
     * 定义两个指针left和right，分别记录子数组的左右的边界位置，
     * 然后我们让right向右移，直到子数组和大于等于给定值或者right达到数组末尾，
     * 此时我们更新最短距离，并且将left像右移一位，然后再sum中减去移去的值，
     * 然后重复上面的步骤，直到right到达末尾，且left到达临界位置，即要么到达边界，要么再往右移动，和就会小于给定值
     * <p>
     * 时间n 空间1
     */
    private static int solution1(int s, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int i = 0;
        int j = 0;
        int sum = 0;

        int minLen = Integer.MAX_VALUE;

        while (j < nums.length) {
            // 当sum<s时，右移j直到sum>=s
            while (sum < s && j < nums.length) {
                sum += nums[j++];
            }
            // 当sum>=s时，右移i直到sum<s
            while (sum >= s) {
                minLen = Math.min(minLen, j - i);
                sum -= nums[i++];
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    /**
     * 二分
     * <p>
     * 我们建立一个比原数组长一位的sums数组，其中sums[i]表示nums数组中[0, i - 1]的和
     * 然后我们对于sums中每一个值sums[i]，用二分查找法找到子数组的右边界位置，使该子数组之和大于sums[i] + s，然后我们更新最短长度的距离即可
     * <p>
     * 时间 nlogn 空间n
     */
    private static int solution2(int s, int[] nums) {
        int minLen = Integer.MAX_VALUE;

        // sums[i]存储nums[0]到nums[i-1]的和
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        for (int i = 0; i < nums.length; i++) {
            // 子数组范围[i+1, right]
            int right = searchRight(i + 1, nums.length, sums[i] + s, sums);
            if (right == nums.length + 1) {
                // right到边了还小于target => 当前i没找到子数组 =>接下来的i更找不到子数组
                break;
            }
            minLen = Math.min(minLen, right - i);
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    private static int searchRight(int start, int end, int target, int[] sums) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (sums[mid] >= target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    /**
     * dp
     * <p>
     * 时间n2 空间n2
     * <p>
     * Memory Limit Exceeded
     */
    private static int mySolutionDp(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[][] sums = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= s) {
                return 1;
            }
            sums[i][i] = nums[i];
        }
        for (int len = 2; len <= nums.length; len++) {
            for (int i = 0; i <= nums.length - len; i++) {
                int j = i + len - 1;
                sums[i][j] = sums[i][j - 1] + nums[j];
                if (sums[i][j] >= s) {
                    return len;
                }
            }
        }
        return 0;
    }
}
