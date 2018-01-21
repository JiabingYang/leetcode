package com.yjb.leetcode.medium;

/**
 * 287. Find the Duplicate Number
 * <p>
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 * <p>
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 * Credits:
 * Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
 */
public class No287FindTheDuplicateNumber {

    /**
     * 剑指offer No3a
     * https://www.programcreek.com/2015/06/leetcode-find-the-duplicate-number-java/
     * <p>
     * 单指针+交换
     * <p>
     * 时间n 空间1 修改原数组（本题要求不能修改原数组）
     */
    private static int solution1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1) {
                int m = nums[i];
                int n = nums[m - 1];
                if (m == n) {
                    return m;
                }
                nums[i] = n;
                nums[m - 1] = m;
            }
        }
        return -1;
    }

    /**
     * 剑指offer No3b
     * https://www.programcreek.com/2015/06/leetcode-find-the-duplicate-number-java/
     * <p>
     * Binary Search
     * <p>
     * 时间nlogn 空间1 不修改原数组 假设一定有重复元素
     */
    private static int solution2(int[] nums) {
        int start = 1;
        int end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            int count = 0;
            for (int num : nums) {
                if (num >= start && num <= mid) {
                    count++;
                }
            }
            if (count > mid - start + 1) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    /**
     * 剑指offer No3b
     * <p>
     * Binary Search
     * <p>
     * 时间nlogn 空间1 考虑没有重复元素情况
     */
    private static int solution2IfHasNoDuplicate(int[] nums) {
        int start = 1;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int count = 0;
            for (int num : nums) {
                if (num >= start && num <= mid) {
                    count++;
                }
            }
            if (end == start) {
                if (count > 1) {
                    return end;
                }
                return -1;
            }
            if (count > mid - start + 1) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    /**
     * https://www.programcreek.com/2015/06/leetcode-find-the-duplicate-number-java/
     * https://www.cnblogs.com/grandyang/p/4843654.html
     * <p>
     * Finding Cycle
     * 快慢指针找环
     * 由于题目限定了区间[1,n]，所以可以巧妙的利用坐标和数值之间相互转换，
     * 而由于重复数字的存在，那么一定会形成环，
     * 我们用快慢指针可以找到环并确定环的起始位置(有两个index会走到该位置，即重复数字)
     * <p>
     * 时间n 空间1
     */
    private static int solution3(int[] nums) {
        int slow = 0;
        int fast = 0;

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        int find = 0;

        while (find != slow) {
            slow = nums[slow];
            find = nums[find];
        }
        return find;
    }
}
