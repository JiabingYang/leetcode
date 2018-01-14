package com.yjb.leetcode.easy;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 219. Contains Duplicate II
 * <p>
 * Given an array of integers and an integer k,
 * find out whether there are two distinct indices i and j in the array such that
 * nums[i] = nums[j] and the absolute difference between i and j is at most k.
 */
public class No219ContainsDuplicateII {

    public static void main(String[] args) {
        System.out.println(solutionSet(new int[]{5, 9, 2, 16}, 2));
        System.out.println(solutionSet(new int[]{-1, -1}, 1));
        System.out.println(solutionSet(new int[]{1, 2, 1}, 2));
    }

    public static boolean mySolution(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k == 0)
            return false;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!map.containsKey(num)) {
                map.put(num, i);
                continue;
            }
            int j = map.get(num);
            if (i - j <= k) {
                return true;
            }
            map.put(num, i);
        }
        return false;
    }

    public static boolean simplified(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public static boolean solutionSet(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k == 0)
            return false;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }
}
