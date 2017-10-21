package com.yjb.leetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class TwoSum {

    /**
     * beats 43.72%
     */
    public int[] myAnswer(int[] nums, int target) {
        Map<Integer, List<Integer>> numIndexesMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            List<Integer> indexes = numIndexesMap.get(num);
            if (indexes == null) {
                indexes = new ArrayList<>();
            }
            indexes.add(i);
            numIndexesMap.put(num, indexes);
        }
        for (int i = 0; i < nums.length; i++) {
            List<Integer> indexes = numIndexesMap.get(target - nums[i]);
            if (indexes == null) {
                continue;
            }
            for (Integer index : indexes) {
                if (index != i) {
                    return new int[]{i, index};
                }
            }
        }
        return null;
    }

    /**
     * Complexity Analysis:
     * <p>
     * Time complexity : O(n)O(n). We traverse the list containing nn elements exactly twice.
     * Since the hash table reduces the look up time to O(1)O(1), the time complexity is O(n)O(n).
     * <p>
     * Space complexity : O(n)O(n). The extra space required depends on the number of items
     * stored in the hash table, which stores exactly nn elements.
     */
    public int[] twoPassHashTable(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * Complexity Analysis:
     * <p>
     * Time complexity : O(n)O(n). We traverse the list containing nn elements only once.
     * Each look up in the table costs only O(1)O(1) time.
     * <p>
     * Space complexity : O(n)O(n). The extra space required depends on the number of items
     * stored in the hash table, which stores at most nn elements.
     */
    public int[] onePassHashTable(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}