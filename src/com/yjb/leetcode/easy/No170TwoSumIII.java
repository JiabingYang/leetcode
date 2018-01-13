package com.yjb.leetcode.easy;

import java.util.HashMap;

/**
 * 170. Two Sum III - Data structure design
 * <p>
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 * <p>
 * add - Add the number to an internal data structure.
 * <p>
 * <p>
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 * For example,
 * <p>
 * add(1);
 * add(3);
 * add(5);
 * find(4) -> true
 * find(7) -> false
 */
public class No170TwoSumIII {

    /**
     * 来自：
     * https://www.programcreek.com/2014/03/two-sum-iii-data-structure-design-java/
     */
    public class TwoSum {

        private HashMap<Integer, Integer> elements = new HashMap<>();

        public void add(int number) {
            if (elements.containsKey(number)) {
                elements.put(number, elements.get(number) + 1);
            } else {
                elements.put(number, 1);
            }
        }

        public boolean find(int value) {
            for (Integer i : elements.keySet()) {
                int target = value - i;
                if (elements.containsKey(target)) {
                    if (i == target && elements.get(target) < 2) {
                        continue;
                    }
                    return true;
                }
            }
            return false;
        }
    }
}
