package com.yjb.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 119. Pascal's Triangle II
 * <p>
 * Given an index k, return the kth row of the Pascal's triangle.
 * <p>
 * For example, given k = 3,
 * Return [1,3,3,1].
 * <p>
 * Note:
 * Could you optimize your algorithm to use only O(k) extra space?
 */
public class No119PascalsTriangleII {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println("-------------");
            System.out.println("i = " + i);
            System.out.println(solution1(i));
        }
    }

    /**
     * https://www.programcreek.com/2014/04/leetcode-pascals-triangle-ii-java/
     */
    private static List<Integer> solution1(int rowIndex) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (rowIndex < 0) {
            return result;
        }
        result.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = result.size() - 2; j >= 0; j--) {
                result.set(j + 1, result.get(j) + result.get(j + 1));
            }
            result.add(1);
        }
        return result;
    }

    private static List<Integer> mySolution(int rowIndex) {
        Integer[] row = new Integer[rowIndex + 1];
        for (int i = 0; i <= rowIndex; i++) {
            for (int j = i; j >= 0; j--) {
                if (j == 0) {
                    row[j] = 1;
                } else if (j == i) {
                    row[j] = 1;
                } else {
                    row[j] = row[j - 1] + row[j];
                }
            }
        }
        return Arrays.asList(row);
    }
}
