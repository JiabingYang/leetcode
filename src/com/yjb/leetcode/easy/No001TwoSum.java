package com.yjb.leetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1. Two Sum
 * <p>
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class No001TwoSum {

    /**
     * beats 43.72%
     */
    public int[] mySolution(int[] nums, int target) {
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

    // ---------- https://leetcode.com/articles/two-sum/ ----------

    /**
     * Approach #2 (Two-pass Hash Table) [Accepted]
     * <p>
     * To improve our run time complexity, we need a more efficient way to check if the complement exists in the array.
     * If the complement exists, we need to look up its index. What is the best way to maintain a mapping of each element
     * in the array to its index? A hash table.
     * <p>
     * We reduce the look up time from O(n) to O(1) by trading space for speed. A hash table is built exactly for this purpose,
     * it supports fast look up in near constant time. I say "near" because if a collision occurred, a look up could degenerate
     * to O(n) time. But look up in hash table should be amortized O(1) time as long as the hash function was chosen carefully.
     * <p>
     * A simple implementation uses two iterations.
     * In the first iteration, we add each element's value and its index to the table.
     * Then, in the second iteration we check if each element's complement (target - nums[i]) exists in the table.
     * Beware that the complement must not be nums[i] itself!
     * <p>
     * Complexity Analysis:
     * Time complexity : O(n). We traverse the list containing nn elements exactly twice.
     * Since the hash table reduces the look up time to O(1)O(1), the time complexity is O(n).
     * Space complexity : O(n). The extra space required depends on the number of items
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
     * Approach #3 (One-pass Hash Table) [Accepted]
     * <p>
     * It turns out we can do it in one-pass.
     * While we iterate and inserting elements into the table, we also look back to check if current element's complement
     * already exists in the table.
     * If it exists, we have found a solution and return immediately.
     * <p>
     * Complexity Analysis:
     * Time complexity : O(n). We traverse the list containing nn elements only once.
     * Each look up in the table costs only O(1) time.
     * Space complexity : O(n). The extra space required depends on the number of items
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