package com.yjb.leetcode.medium;

/**
 * 275. H-Index II
 * <p>
 * Follow up for H-Index:
 * What if the citations array is sorted in ascending order? Could you optimize your algorithm?
 */
public class No275HIndexII {

    /**
     * 二分
     * <p>
     * 和No274HIndex#solution2一样
     */
    private static int mySolution1(int[] citations) {
        int len = citations.length;
        int start = 0;
        int end = citations.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int citation = citations[mid];
            int h = len - mid;
            if (citation == h) {
                return citation;
            }
            if (citation < h) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return len - start;
    }

    /**
     * 和No274HIndex#solution1一样
     */
    public int mySolution2(int[] citations) {
        int len = citations.length;
        int max = 0;
        for (int i = 0; i < len; i++) {
            int h = Math.min(citations[i], len - i);
            max = Math.max(max, h);
        }
        return max;
    }
}
