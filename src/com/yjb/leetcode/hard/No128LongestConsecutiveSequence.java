package com.yjb.leetcode.hard;

import java.util.HashSet;

/**
 * 128. Longest Consecutive Sequence
 * <p>
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * <p>
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * <p>
 * Your algorithm should run in O(n) complexity.
 */
public class No128LongestConsecutiveSequence {

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }

    /**
     * https://www.programcreek.com/2013/01/leetcode-longest-consecutive-sequence-java/
     * 既然要O(n)那么每个数字的相邻数字查找必须是O(1)，所以首先想到的是HashMap，但是HashMap不适用于本题，
     * 如果用HashSet就可以以O(1)的速度查找相邻数字。
     * 这题我想到了用HashSet来查找，但是我想的是一个HashSet存储所有相连的数字，这样需要有多个HashSet，不行。
     * 正确的方式是用HashSet存储全部的数字，然后再遍历每个数字，从HashSet里找。
     */
    private static int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 0;
        for (int num : nums) {
            int count = 1;
            int left = num - 1;
            int right = num + 1;
            while (set.contains(left)) {
                count++;
                set.remove(left);
                left--;
            }
            while (set.contains(right)) {
                count++;
                set.remove(right);
                right++;
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }
}
