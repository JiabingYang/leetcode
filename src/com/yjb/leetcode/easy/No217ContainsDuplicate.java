package com.yjb.leetcode.easy;

import java.util.HashSet;

/**
 * 217. Contains Duplicate
 * <p>
 * Given an array of integers, find if the array contains any duplicates.
 * Your function should return true if any value appears at least twice in the array,
 * and it should return false if every element is distinct.
 */
public class No217ContainsDuplicate {

    public static void main(String[] args) {
        System.out.println(containsDuplicate(new int[]{0, 1, 2}));
        System.out.println(containsDuplicate(new int[]{0, 1, 2, 2}));
    }

    public static boolean mySolution(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    /**
     * https://www.programcreek.com/2014/05/leetcode-contains-duplicate-java/
     */
    public static boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (!set.add(i)) {
                return true;
            }
        }
        return false;
    }
}
