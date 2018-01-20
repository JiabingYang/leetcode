package com.yjb.leetcode.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a list of numbers and a target number,
 * write a program to determine whether the target number can be calculated by
 * applying "plus minus multiply divide" operations to the number list?
 * You can assume () is automatically added when necessary.
 * <p>
 * From:
 * https://www.programcreek.com/2016/04/get-target-using-number-list-and-arithmetic-operations-google/
 */
public class GetTargetUsingNumberListAndArithmeticOperations {

    public static void main(String[] args) {
        System.out.println(isReachable(Arrays.asList(1, 2, 3, 4), 21));
    }

    /**
     * Analysis
     * <p>
     * This is a partition problem which can be solved by using depth first search.
     */
    public static boolean isReachable(List<Integer> list, int target) {
        if (list == null || list.size() == 0)
            return false;

        int i = 0;
        int j = list.size() - 1;

        ArrayList<Integer> results = getResults(list, i, j);

        for (int num : results) {
            if (num == target) {
                return true;
            }
        }

        return false;
    }

    private static ArrayList<Integer> getResults(List<Integer> list,
                                                 int left, int right) {
        ArrayList<Integer> result = new ArrayList<>();

        if (left > right) {
            return result;
        }
        if (left == right) {
            result.add(list.get(left));
            return result;
        }

        for (int i = left; i < right; i++) {
            ArrayList<Integer> result1 = getResults(list, left, i);
            ArrayList<Integer> result2 = getResults(list, i + 1, right);

            for (int x : result1) {
                for (int y : result2) {
                    result.add(x + y);
                    result.add(x - y);
                    result.add(x * y);
                    if (y != 0)
                        result.add(x / y);
                }
            }
        }

        return result;
    }
}
