package com.yjb.leetcode.medium;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 220. Contains Duplicate III
 * <p>
 * Given an array of integers,
 * find out whether there are two distinct indices i and j in the array such that
 * the absolute difference between nums[i] and nums[j] is at most t and
 * the absolute difference between i and j is at most k.
 */
public class No220ContainsDuplicateIII {

    public static void main(String[] args) {
        System.out.println(mySolution(new int[]{5, 9, 2, 16}, 2, 1));
        System.out.println(mySolution(new int[]{-1, -1}, 1, 1));
        System.out.println(mySolution(new int[]{1, 4, 3}, 2, 1));
        System.out.println(mySolution(new int[]{0, 2147483647}, 1, 2147483647));
        System.out.println(mySolution(new int[]{-2147483648, -2147483647}, 3, 3));
    }

    public static boolean mySolution(int[] nums, int k, int t) {
        if (nums == null || nums.length < 2 || k == 0)
            return false;
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                set.remove((long) nums[i - k - 1]);
            }
            long num = nums[i];
            Long floor = set.floor((num + t));
            if (floor != null && floor >= num - t) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    /**
     * https://www.programcreek.com/2014/06/leetcode-contains-duplicate-iii-java/
     */
    public boolean solution1(int[] nums, int k, int t) {
        if (nums == null || nums.length < 2 || k < 0 || t < 0)
            return false;

        TreeSet<Long> set = new TreeSet<Long>();
        for (int i = 0; i < nums.length; i++) {
            long curr = (long) nums[i];

            long leftBoundary = (long) curr - t;
            long rightBoundary = (long) curr + t + 1; //right boundary is exclusive, so +1
            SortedSet<Long> sub = set.subSet(leftBoundary, rightBoundary);
            if (sub.size() > 0)
                return true;

            set.add(curr);

            if (i >= k) { // or if(set.size()>=k+1)
                set.remove((long) nums[i - k]);
            }
        }

        return false;
    }

    /**
     * https://www.programcreek.com/2014/06/leetcode-contains-duplicate-iii-java/
     */
    public boolean solution2Deprecated(int[] nums, int k, int t) {
        if (k < 1 || t < 0)
            return false;
        TreeSet<Integer> set = new TreeSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            int c = nums[i];
            if ((set.floor(c) != null && c <= set.floor(c) + t)
                    || (set.ceiling(c) != null && c >= set.ceiling(c) - t))
                return true;

            set.add(c);

            if (i >= k)
                set.remove(nums[i - k]);
        }
        return false;
    }
}
