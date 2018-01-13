package com.yjb.leetcode.medium;

import java.util.HashMap;

/**
 * 454. 4Sum II
 * <p>
 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
 * <p>
 * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
 * <p>
 * Example:
 * <p>
 * Input:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * <p>
 * Output:
 * 2
 * <p>
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
public class No454FourSumII {

    /**
     * 来自：
     * http://blog.csdn.net/mebiuw/article/details/53192762
     * <p>
     * 思路：
     * 将四数转变为两个部分，首先遍历AB的组合（任意两个都可以），存下他们组合后的和的情况，
     * 然后遍历CD（另外两个）的和，看之前AB遍历的组合里有没有与此时值相反的值，有的话就加上AB中这个相反数出现的次数。
     * <p>
     * 先保存a+b的所有取值，然后遍历所有c+d的组合，分成两部分算起来更快
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int n = A.length;
        if (n == 0) {
            return 0;
        }
        HashMap<Integer, Integer> ab = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                ab.put(a + b, ab.getOrDefault(a + b, 0) + 1);
            }
        }
        int result = 0;
        for (int c : C) {
            for (int d : D) {
                result += ab.getOrDefault(-(c + d), 0);
            }
        }
        return result;
    }
}
