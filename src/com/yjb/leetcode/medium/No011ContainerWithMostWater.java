package com.yjb.leetcode.medium;

/**
 * 11. Container With Most Water
 * <p>
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * <p>
 * Note: You may not slant the container and n is at least 2.
 * <p>
 * 需要两个指针表示一个面积，如果普通遍历，需要n2的时间复杂度（每次固定左边，遍历右边的所有情况）。
 * 问题的关键是，要想实现n复杂度，那么遍历应该是一层的。
 * 因此，需要找到一个一层遍历，能考虑所有情况。
 * 因为根本上我们遍历的是面积，所以遍历应该遍历过所有的面积情况。
 * 通过使用左右两个指针，“紧凑”地改变高度和宽度，避免了不必要的情况的计算，将两层遍历转成一层遍历（两边往中间夹）。
 */
public class No011ContainerWithMostWater {

    /**
     * https://www.programcreek.com/2014/03/leetcode-container-with-most-water-java/
     * <p>
     * Analysis
     * <p>
     * Initially we can assume the result is 0. Then we scan from both sides.
     * If leftHeight < rightHeight, move right and find a value that is greater than leftHeight.
     * Similarly, if leftHeight > rightHeight, move left and find a value that is greater than rightHeight.
     * Additionally, keep tracking the max value.
     */
    private static int solution1(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int max = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right])
                left++;
            else
                right--;
        }

        return max;
    }
}
