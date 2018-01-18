package com.yjb.leetcode.hard;

import com.yjb.util.Timer;

import java.util.Arrays;
import java.util.Stack;

/**
 * 84. Largest Rectangle in Histogram
 * <p>
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 * <p>
 * <img src="https://leetcode.com/static/images/problemset/histogram.png" />
 * <p>
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 * <p>
 * <img src="https://leetcode.com/static/images/problemset/histogram_area.png" />
 * <p>
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * <p>
 * For example,
 * Given heights = [2,1,5,6,2,3],
 * return 10.
 * <p>
 * 算面积的关键在于，要计算的矩形一定是在两个矮的柱子中间，形成一个“凸起”。
 * <p>
 * solution1（时间n 空间n）思路：
 * stack中维护始终递增的柱子，栈顶始终是当前未算过的最高的柱子（下一个要计算的柱子，一旦碰到比栈顶低的情况就把栈顶算掉）。
 * 遍历完一遍后，还需要把栈中尚存的柱子给算完，这时右边的矮柱子就是右边界，左边的矮柱子是栈顶的下一个柱子。
 * <p>
 * mySolution2（时间n 空间1，超时）思路：
 * 对每一个柱子向两边找到矮柱子，计算中间的矩形大小，更新max值。（在两边寻找过程中，如果碰到高度和当前柱子一样的柱子，那么该柱子下次不用计算）
 * <p>
 * mySolution1（时间n2 空间n2，超时）思路：
 * 矩形的左边柱为i，右边柱为j，高为[i, j]范围内最矮的柱子的高度，
 * 通过动态规划，从i和j的offset为0开始依次计算出每个[i,j]情况下的矩形高和矩形面积，更新max值
 */
public class No084LargestRectangleInHistogram {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    /**
     * 时间n 空间n
     * <p>
     * https://www.programcreek.com/2014/05/leetcode-largest-rectangle-in-histogram-java/
     * <p>
     * The key to solve this problem is to maintain a stack to store bars' indexes. The stack only keeps the increasing bars.
     */
    private static int solution1(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();

        int max = 0;
        int i = 0;
        int count1 = 0;
        int count2 = 0;

        while (i < heights.length) {
            //push index to stack when the current height is larger than the previous one
            if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                //calculate max value when the current height is less than the previous one
                int p = stack.pop();
                int h = heights[p];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(h * w, max);
                count1++;
            }
        }

        while (!stack.isEmpty()) {
            int p = stack.pop();
            int h = heights[p];
            int w = stack.isEmpty() ? i : i - stack.peek() - 1; // i其实就是heights.length
            max = Math.max(h * w, max);
            count2++;
        }

        return max;
    }

    /**
     * 时间n2 空间n2
     * <p>
     * 超时情况
     * <pre>
     *     int[] a = new int[19999];
     *     for (int i = 0; i < 19999; i++) {
     *         a[i] = i;
     *     }
     * </pre>
     */
    private static int mySolution1(int[] heights) {
        int[][] dp = new int[heights.length][heights.length];
        int max = 0;
        for (int offset = 0; offset < heights.length; offset++) {
            for (int i = 0; i < heights.length - offset; i++) {
                int j = i + offset;
                if (i == j) {
                    dp[i][j] = heights[i];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], heights[j]);
                }
                max = Math.max(max, dp[i][j] * (offset + 1));
            }
        }
        return max;
    }

    /**
     * 时间n 空间1
     * <p>
     * 超时情况
     * <pre>
     *     int[] b = new int[30000];
     *     Arrays.fill(b, 1);
     * </pre>
     */
    private static int mySolution2(int[] heights) {
        int len = heights.length;
        int max = 0;
        for (int i = 0; i < len; i++) {
            int height = heights[i];
            if (height < 0) {
                continue;
            }
            int j = i;
            while (j >= 0 && Math.abs(heights[j]) >= height) {
                j--;
            }
            int k;
            for (k = i; k < len; k++) {
                if (Math.abs(heights[k]) < height) {
                    break;
                }
                if (heights[k] == height) {
                    heights[k] = -height;
                }
            }
            max = Math.max(max, height * (k - j - 1));
        }
        return max;
    }

    private static void test1() {
        System.out.println("-------- test1 --------");
        Timer.printlnNano("mySolution2", () -> System.out.println(mySolution2(new int[]{2, 1, 5, 6, 2, 3}) + "[10]"));
        Timer.printlnNano("solution1", () -> System.out.println(solution1(new int[]{2, 1, 5, 6, 2, 3}) + "[10]"));
    }

    private static void test2() {
        System.out.println("-------- test2 --------");
        int[] a = new int[19999];
        for (int i = 0; i < 19999; i++) {
            a[i] = i;
        }
        Timer.printlnMilli("mySolution2", () -> System.out.println(mySolution2(a) + "[99990000]"));
        int[] a1 = new int[19999];
        for (int i = 0; i < 19999; i++) {
            a1[i] = i;
        }
        Timer.printlnMilli("solution1", () -> System.out.println(solution1(a1) + "[99990000]"));
    }

    private static void test3() {
        System.out.println("-------- test3 --------");
        int[] b = new int[30000];
        Arrays.fill(b, 1);
        Timer.printlnNano("mySolution2", () -> System.out.println(mySolution2(b) + "[30000]"));
        int[] b1 = new int[30000];
        Arrays.fill(b1, 1);
        Timer.printlnNano("solution1", () -> System.out.println(solution1(b1) + "[30000]"));
    }
}
