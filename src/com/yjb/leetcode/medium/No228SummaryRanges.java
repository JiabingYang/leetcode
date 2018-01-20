package com.yjb.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 228. Summary Ranges
 * <p>
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * <p>
 * Example 1:
 * Input: [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Example 2:
 * Input: [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Credits:
 * Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
 */
public class No228SummaryRanges {

    private static List<String> mySolution(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }
        int start = nums[0];
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1] - 1) {
                if (nums[i] == start) {
                    result.add(String.valueOf(nums[i]));
                } else {
                    result.add(start + "->" + nums[i]);
                }
                start = nums[i + 1];
            }
        }
        if (start == nums[nums.length - 1]) {
            result.add(String.valueOf(start));
        } else {
            result.add(start + "->" + nums[nums.length - 1]);
        }
        return result;
    }

    /**
     * https://www.programcreek.com/2014/07/leetcode-summary-ranges-java/
     */
    private static List<String> solution1(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        if (nums.length == 1) {
            result.add(nums[0] + "");
        }
        int pre = nums[0]; // previous element
        int first = pre; // first element of each range
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == pre + 1) {
                if (i == nums.length - 1) {
                    result.add(first + "->" + nums[i]);
                }
            } else {
                if (first == pre) {
                    result.add(first + "");
                } else {
                    result.add(first + "->" + pre);
                }
                if (i == nums.length - 1) {
                    result.add(nums[i] + "");
                }
                first = nums[i];
            }
            pre = nums[i];
        }
        return result;
    }
}
