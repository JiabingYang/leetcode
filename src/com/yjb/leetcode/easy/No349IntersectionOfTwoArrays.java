package com.yjb.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 349. Intersection of Two Arrays
 * <p>
 * Given two arrays, write a function to compute their intersection.
 * <p>
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 * <p>
 * Note:
 * Each element in the result must be unique.
 * The result can be in any order.
 */
public class No349IntersectionOfTwoArrays {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(mySolution(new int[]{1, 2, 2, 1}, new int[]{2, 2})));
    }

    /**
     * 66.19%
     * 5ms
     * <p>
     * 时间n 空间n
     */
    private static int[] mySolution(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        List<Integer> temp = new ArrayList<>();
        for (int num : nums2) {
            if (set.contains(num)) {
                temp.add(num);
                set.remove(num);
            }
        }
        int[] result = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            result[i] = temp.get(i);
        }
        return result;
    }

    /**
     * From a leetcode user.
     * <p>
     * 2ms
     * <p>
     * 时间n 空间n
     */
    private static int[] solution3(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        List<Integer> list = new ArrayList<>();

        int max = nums1[0], min = nums1[0];
        for (int i = 1; i < nums1.length; i++) {
            if (nums1[i] > max) {
                max = nums1[i];
            } else if (nums1[i] < min) {
                min = nums1[i];
            }
        }

        boolean[] bucket = new boolean[max - min + 1];

        for (int num : nums1) {
            bucket[num - min] = true;
        }
        for (int num : nums2) {
            if (num >= min && num <= max) {
                if (bucket[num - min]) {
                    list.add(num);
                    bucket[num - min] = false;
                }
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    /**
     * https://www.programcreek.com/2015/05/leetcode-intersection-of-two-arrays-java/
     * <p>
     * 时间nlogn 空间n
     */
    private static int[] solution2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            if (i == 0 || nums1[i] != nums1[i - 1]) {
                if (Arrays.binarySearch(nums2, nums1[i]) > -1) {
                    list.add(nums1[i]);
                }
            }
        }

        int[] result = new int[list.size()];
        int k = 0;
        for (int i : list) {
            result[k++] = i;
        }

        return result;
    }

    /**
     * https://www.programcreek.com/2015/05/leetcode-intersection-of-two-arrays-java/
     * <p>
     * 时间n 空间n
     */
    private static int[] solution1(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }

        HashSet<Integer> set2 = new HashSet<>();
        for (int i : nums2) {
            if (set1.contains(i)) {
                set2.add(i);
            }
        }

        int[] result = new int[set2.size()];
        int i = 0;
        for (int n : set2) {
            result[i++] = n;
        }

        return result;
    }
}
