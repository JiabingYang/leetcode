package com.yjb.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 169. Majority Element
 * <p>
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * <p>
 * You may assume that the array is non-empty and the majority element always exist in the array.
 * <p>
 * Credits:
 * Special thanks to @ts for adding this problem and creating all test cases.
 */
public class No169MajorityElement {

    public int mySolution(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int t = nums.length / 2;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) > t) {
                return num;
            }
        }
        return -1;
    }

    /**
     * https://www.programcreek.com/2014/02/leetcode-majority-element-java/
     * <p>
     * Java Solution 1 - Naive
     * <p>
     * We can sort the array first, which takes time of nlog(n). Then scan once to find the longest consecutive substrings.
     */
    public int solution1(int[] num) {
        if (num.length == 1) {
            return num[0];
        }

        Arrays.sort(num);

        int prev = num[0];
        int count = 1;
        for (int i = 1; i < num.length; i++) {
            if (num[i] == prev) {
                count++;
                if (count > num.length / 2) return num[i];
            } else {
                count = 1;
                prev = num[i];
            }
        }

        return 0;
    }

    /**
     * https://www.programcreek.com/2014/02/leetcode-majority-element-java/
     * <p>
     * Java Solution 2 - Much Simpler
     * <p>
     * Thanks to SK. His/her solution is much efficient and simpler.
     * Since the majority always take more than a half space, the middle element is guaranteed to be the majority.
     * Sorting array takes nlog(n). So the time complexity of this solution is nlog(n). Cheers!
     */
    public int solution2(int[] num) {
        if (num.length == 1) {
            return num[0];
        }

        Arrays.sort(num);
        return num[num.length / 2];
    }

    /**
     * https://www.programcreek.com/2014/02/leetcode-majority-element-java/
     * <p>
     * Java Solution 3 - Linear Time Majority Vote Algorithm
     */
    public int majorityElement(int[] nums) {
        int result = 0, count = 0;

        for (int num : nums) {
            if (count == 0) {
                result = num;
                count = 1;
            } else if (result == num) {
                count++;
            } else {
                count--;
            }
        }

        return result;
    }
}
