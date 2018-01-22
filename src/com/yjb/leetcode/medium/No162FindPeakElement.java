package com.yjb.leetcode.medium;

/**
 * 162. Find Peak Element
 * <p>
 * A peak element is an element that is greater than its neighbors.
 * <p>
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * <p>
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * <p>
 * You may imagine that num[-1] = num[n] = -∞.
 * <p>
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 * <p>
 * Note:
 * Your solution should be in logarithmic complexity.
 * <p>
 * Credits:
 * Special thanks to @ts for adding this problem and creating all test cases.
 * <p>
 * 关键点：
 * 数组的区间，两头小，那么中间一定有满足条件的峰值存在（原因见solution3的注释）
 * 这个点是solution2和solution3（二分）的原理
 */
public class No162FindPeakElement {

    /**
     * https://segmentfault.com/a/1190000003488794
     * <p>
     * 线性扫描1
     * <p>
     * 时间n
     */
    private static int solution1(int[] nums) {
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1] && nums[i] > nums[i - 1]) {
                return i;
            }
        }
        return nums.length <= 1 || nums[0] > nums[1] ? 0 : nums.length - 1;
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4217175.html
     * <p>
     * 线性扫描2
     * <p>
     * 由于题目中说明了局部峰值一定存在，那么实际上可以从第二个数字开始往后遍历，
     * 如果第二个数字比第一个数字小，说明此时第一个数字就是一个局部峰值；
     * 否则就往后继续遍历，现在是个递增趋势，
     * 如果此时某个数字小于前面那个数字，说明前面数字就是一个局部峰值，返回位置即可。
     * 如果循环结束了，说明原数组是个递增数组，返回最后一个位置即可
     * <p>
     * 时间n
     */
    private static int solution2(int[] nums) {
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] < nums[i - 1]) {
                return i - 1;
            }
        }
        return nums.length - 1;
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4217175.html
     * http://blog.csdn.net/u012162613/article/details/41791715
     * 二分查找
     * <p>
     * 思路：
     * 取数组的中间元素array[mid]，
     * 如果array[mid]>array[mid-1]且array[mid]>array[mid+1]，则array[mid]就是peak element，
     * 否则，说明array[mid-1]、array[mid+1]至少有一个大于array[mid]。
     * 如果array[mid-1]大于array[mid]，则左边的子数组array[0..mid-1]肯定有peak element；
     * 如果array[mid+1]大于array[mid]，则由边的子数组array[mid+1..end]肯定有peak element。
     * <p>
     * 解释：“如果array[mid-1]大于array[mid]，则左边的子数组array[0..mid-1]肯定有peak element”
     * <p>
     * 如果array[mid-1]再大于array[mid-2]的话，array[mid-1]就是peak element
     * 要是array[mid-1]小于array[mid-2]，那么接下来我们再比较array[mid-2]与array[mid-3]
     * <p>
     * 如果array[mid-2]大于array[mid-3]的话，array[mid-2]就是peak element
     * 要是array[mid-2]小于array[mid-3]，那么接下来我们再比较array[mid-3]与array[mid-4]
     * ........
     * 这个过程如果一直迭代到array[1]和array[0]，
     * 由于array[0]没有左相邻的，所以array[1]和array[0]必有一个是peak element，
     * array[1]>array[0]则array[1]是peak element，否则array[0]是peak element。
     * <p>
     * 时间 logn
     */
    private static int solution3(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
