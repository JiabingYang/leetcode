package com.yjb.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 350. Intersection of Two Arrays II
 * <p>
 * Given two arrays, write a function to compute their intersection.
 * <p>
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 * <p>
 * Note:
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 * Follow up:
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */
public class No350IntersectionOfTwoArraysII {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(mySolution(new int[]{1, 2, 2, 1}, new int[]{2, 2})));
    }

    /**
     * 35.44%
     * 7ms
     */
    private static int[] mySolution(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            Integer count = map.get(num);
            if (count == null) {
                continue;
            }
            if (count > 1) {
                map.put(num, count - 1);
            } else {
                map.remove(num);
            }
            list.add(num);
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * https://www.programcreek.com/2014/05/leetcode-intersection-of-two-arrays-ii-java/
     * <p>
     * 和我的做法思路一样
     */
    private static int[] solution1(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            if (map.containsKey(i)) {
                if (map.get(i) > 1) {
                    map.put(i, map.get(i) - 1);
                } else {
                    map.remove(i);
                }
                list.add(i);
            }
        }

        int[] result = new int[list.size()];
        int i = 0;
        while (i < list.size()) {
            result[i] = list.get(i);
            i++;
        }

        return result;
    }

    /**
     * https://www.programcreek.com/2014/05/leetcode-intersection-of-two-arrays-ii-java/
     * <p>
     * 双指针
     * <p>
     * 假如数组已排序可以用这种方式！
     */
    private static int[] solution2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        int p1 = 0;
        int p2 = 0;
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] < nums2[p2]) {
                p1++;
            } else if (nums1[p1] > nums2[p2]) {
                p2++;
            } else {
                list.add(nums1[p1]);
                p1++;
                p2++;
            }
        }

        int[] result = new int[list.size()];
        int i = 0;
        while (i < list.size()) {
            result[i++] = list.get(i);
        }
        return result;
    }

    /**
     * From a leetcode user.
     * <p>
     * 3ms
     * <p>
     * 双指针
     * <p>
     * 假如数组已排序可以用这种方式！
     */
    private static int[] solution3(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        int[] result = new int[Math.min(nums1.length, nums2.length)];
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] == nums2[p2]) {
                result[p3++] = nums1[p1];
                p1++;
                p2++;
            } else if (nums1[p1] < nums2[p2]) {
                while (p1 + 1 < nums1.length && nums1[p1] == nums1[p1 + 1]) {
                    p1++;
                }
                p1++;
            } else {
                while (p2 + 1 < nums2.length && nums2[p2] == nums2[p2 + 1]) {
                    p2++;
                }
                p2++;
            }
        }

        return Arrays.copyOf(result, p3);
    }
}
