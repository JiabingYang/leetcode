package com.yjb.leetcode.hard;

/**
 * 42. Trapping Rain Water
 * <p>
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * <p>
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * <p>
 * <img src="https://leetcode.com/static/images/problemset/rainwatertrap.png">
 * <p>
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 * Thanks Marcos for contributing this image!
 */
public class No042TrappingRainWater {

    public static void main(String[] args) {
        System.out.println(solution1(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}) + "[6]");
        System.out.println(solution1(new int[]{2, 0, 2}) + "[2]");
    }

    /**
     * https://www.programcreek.com/2014/06/leetcode-trapping-rain-water-java/
     * <p>
     * Analysis
     * <p>
     * This problem is similar to Candy. It can be solve by scanning from both sides and then get the total.
     * <p>
     * 通过左右各一遍遍历，找出每个节点i左右的最高点，再减去节点本身的高度就可以算出节点的水量
     */
    private static int solution1(int[] height) {
        int total = 0;

        if (height == null || height.length <= 2)
            return total;

        int left[] = new int[height.length];
        int right[] = new int[height.length];

        //scan from left to right
        int max = height[0];
        left[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            if (height[i] < max) {
                left[i] = max;
            } else {
                left[i] = height[i];
                max = height[i];
            }
        }

        //scan from right to left
        max = height[height.length - 1];
        right[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            if (height[i] < max) {
                right[i] = max;
            } else {
                right[i] = height[i];
                max = height[i];
            }
        }

        //calculate total
        for (int i = 0; i < height.length; i++) {
            total += Math.min(left[i], right[i]) - height[i];
        }

        return total;
    }

    private static int mySolution(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }

        //scan from left to right
        int[] water = new int[height.length];
        int total = 0;
        int top = height[0];
        int topIndex = 0;
        for (int i = 1; i < height.length; i++) {
            int h = height[i];
            if (h >= top) { // 找到新的高点，计算和之前的高点之间的水量
                for (int j = topIndex + 1; j < i; j++) {
                    total += water[j];
                }
                // 更新top和topIndex
                top = h;
                topIndex = i;
            } else {
                // 储存水量
                water[i] = top - h;
            }
        }

        //scan from right to left
        water = new int[height.length];
        topIndex = height.length - 1;
        top = height[topIndex];
        for (int i = height.length - 2; i >= 0; i--) {
            int h = height[i];
            if (h > top) { // 等于的情况在上面已经考虑了
                for (int j = topIndex - 1; j > i; j--) {
                    total += water[j];
                }
                top = h;
                topIndex = i;
            } else {
                water[i] = top - h;
            }
        }

        return total;
    }
}
