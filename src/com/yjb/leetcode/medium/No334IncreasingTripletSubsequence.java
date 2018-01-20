package com.yjb.leetcode.medium;

/**
 * 334. Increasing Triplet Subsequence
 * <p>
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 * <p>
 * Formally the function should:
 * Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * <p>
 * Examples:
 * Given [1, 2, 3, 4, 5],
 * return true.
 * <p>
 * Given [5, 4, 3, 2, 1],
 * return false.
 * <p>
 * Credits:
 * Special thanks to @DjangoUnchained for adding this problem and creating all test cases.
 */
public class No334IncreasingTripletSubsequence {

    /**
     * https://www.programcreek.com/2015/02/leetcode-increasing-triplet-subsequence-java/
     * https://www.cnblogs.com/grandyang/p/5194599.html
     * <p>
     * Analysis
     * <p>
     * This problem can be converted to be finding if there is a sequence such that
     * the_smallest_so_far < the_second_smallest_so_far < current.
     * We use m1, m2 and num to denote the 3 number respectively.
     * <p>
     * 使用两个指针m1和m2，初始化为整型最大值，我们遍历数组，
     * 如果m1大于等于当前数字，则将当前数字赋给m1；
     * 如果m1小于当前数字且m2大于等于当前数字，那么将当前数字赋给m2，
     * 一旦m2被更新了，说明一定会有一个数小于m2，那么我们就成功的组成了一个长度为2的递增子序列，
     * 所以我们一旦遍历到比m2还大的数，我们直接返回ture。
     * 如果我们遇到比m1小的数，还是要更新m1，
     * 有可能的话也要更新m2为更小的值，毕竟m2的值越小，能组成长度为3的递增序列的可能性越大
     */
    private static boolean solution1(int[] nums) {
        int m1 = Integer.MAX_VALUE;
        int m2 = Integer.MAX_VALUE;

        for (int num : nums) {
            if (m1 >= num) {
                m1 = num;// update m1 to be a smaller value
            } else if (m2 >= num) {
                m2 = num; // update m2 to be a smaller value
            } else {
                return true;
            }
        }

        return false;
    }

    /**
     * 如果觉得上面的解法不容易想出来，那么如果能想出下面这种解法，估计面试官也会为你点赞。
     * 这种方法的虽然不满足常数空间的要求，但是作为对暴力搜索的优化，也是一种非常好的解题思路。
     * 这个解法的思路是建立两个数组，forward数组和backward数组，
     * 其中forward[i]表示[0, i]之间最小的数，backward[i]表示[i, n-1]之间最大的数，
     * 那么对于任意一个位置i，如果满足 forward[i] < nums[i] < backward[i]，则表示这个递增三元子序列存在
     */
    private static boolean solution2(int[] nums) {
        if (nums.length < 3) {
            return false;
        }

        int[] f = new int[nums.length];
        f[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            f[i] = Math.min(f[i - 1], nums[i]);
        }

        int[] b = new int[nums.length];
        b[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; --i) {
            b[i] = Math.max(b[i + 1], nums[i]);
        }

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > f[i] && nums[i] < b[i]) {
                return true;
            }
        }
        return false;
    }
}
