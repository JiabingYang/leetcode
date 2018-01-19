package com.yjb.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. Pascal's Triangle
 * <p>
 * Given numRows, generate the first numRows of Pascal's triangle.
 * <p>
 * For example, given numRows = 5,
 * Return
 * <p>
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 */
public class No118PascalsTriangle {

    /**
     * https://www.programcreek.com/2014/03/leetcode-pascals-triangle-java/
     */
    private static ArrayList<ArrayList<Integer>> solution1(int numRows) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (numRows <= 0)
            return result;

        ArrayList<Integer> pre = new ArrayList<>();
        pre.add(1);
        result.add(pre);

        for (int i = 2; i <= numRows; i++) {
            ArrayList<Integer> cur = new ArrayList<>();

            cur.add(1); //first
            for (int j = 0; j < pre.size() - 1; j++) {
                cur.add(pre.get(j) + pre.get(j + 1)); //middle
            }
            cur.add(1);//last

            result.add(cur);
            pre = cur;
        }

        return result;
    }

    private static List<List<Integer>> mySolution(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int row = 0; row < numRows; row++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= row; j++) {
                if (j == 0 || j == row) {
                    list.add(1);
                } else {
                    List<Integer> upperList = result.get(row - 1);
                    list.add(upperList.get(j - 1) + upperList.get(j));
                }
            }
            result.add(list);
        }
        return result;
    }
}
