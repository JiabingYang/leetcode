package com.yjb.leetcode.medium;

/**
 * 223. Rectangle Area
 * <p>
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * <p>
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 * <p>
 * <img src="https://leetcode.com/static/images/problemset/rectangle_area.png">
 * <p>
 * Assume that the total area is never beyond the maximum possible value of int.
 * <p>
 * Credits:
 * Special thanks to @mithmatt for adding this problem, creating the above image and all test cases.
 */
public class No223RectangleArea {

    public static void main(String[] args) {
        System.out.println(solution1(-2, -2, 2, 2, -2, -2, 2, 2) + "[16]");
        System.out.println(solution1(-2, -2, 2, 2, -3, -3, -2, -2) + "[17]");
        System.out.println(solution1(0, 0, 0, 0, -1, -1, 1, 1) + "[4]");
        System.out.println(solution1(-2, -2, 2, 2, -1, -1, 1, 1) + "[16]");
    }

    /**
     * This problem can be converted as a overlap internal problem.
     * On the x-axis, there are (A,C) and (E,G);
     * on the y-axis, there are (F,H) and (B,D).
     * If they do not have overlap, the total area is the sum of 2 rectangle areas.
     * If they have overlap, the total area should minus the overlap area.
     * <p>
     * <img src="https://www.programcreek.com/wp-content/uploads/2015/06/rectangle-area-400x187.jpg">
     */
    private static int solution1(int A, int B, int C, int D, int E, int F, int G, int H) {
        if (C < E || G < A)
            return (G - E) * (H - F) + (C - A) * (D - B);

        if (D < F || H < B)
            return (G - E) * (H - F) + (C - A) * (D - B);

        int right = Math.min(C, G);
        int left = Math.max(A, E);
        int top = Math.min(H, D);
        int bottom = Math.max(F, B);

        return (G - E) * (H - F) + (C - A) * (D - B) - (right - left) * (top - bottom);
    }

    private static int mySolution(int A, int B, int C, int D, int E, int F, int G, int H) {
        int size1 = (C - A) * (D - B);
        int size2 = (G - E) * (H - F);
        int x = computeIntersection(A, C, E, G);
        if (x == 0) {
            return size1 + size2;
        }
        int y = computeIntersection(B, D, F, H);
        return size1 + size2 - x * y;
    }

    private static int computeIntersection(int left1, int right1, int left2, int right2) {
        if (left1 >= right2 || left2 >= right1) {
            return 0;
        }
        return Math.min(right1, right2) - Math.max(left1, left2);
    }
}
