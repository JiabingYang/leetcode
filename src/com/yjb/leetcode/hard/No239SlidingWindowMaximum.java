package com.yjb.leetcode.hard;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 239. Sliding Window Maximum
 * <p>
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * <p>
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * <p>
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * Therefore, return the max sliding window as [3,3,5,5,6,7].
 * <p>
 * Note:
 * You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 * <p>
 * Follow up:
 * Could you solve it in linear time?
 */
public class No239SlidingWindowMaximum {

    /**
     * 20ms
     * <p>
     * https://www.programcreek.com/2014/05/leetcode-sliding-window-maximum-java/
     * <p>
     * <img src=https://www.programcreek.com/wp-content/uploads/2014/05/sliding-window-maximum.png>
     * <p>
     * 时间 n 空间 k
     */
    private static int[] solution1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] result = new int[nums.length - k + 1];
        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!deque.isEmpty() && deque.peek() == i - k) { // 删除已经移除窗口的头
                deque.poll();
            }
            int num = nums[i];
            while (!deque.isEmpty() && nums[deque.getLast()] <= num) { // 保证队列中前面的比后面的大
                deque.removeLast();
            }
            deque.offer(i);
            if (i + 1 >= k) {
                result[i - k + 1] = nums[deque.peek()]; // 队列头部一定是最大的
            }
        }
        return result;
    }

    /**
     * From a leetcode user.
     * <p>
     * 68ms
     * <p>
     * 用堆来保证拿到最大值，
     * 在滑动窗口时，先把移出窗口的值在堆中删掉
     * <p>
     * 时间 nlogk 空间 k
     * <p>
     * 这个方法是我想到的方法
     */
    private static int[] solution2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, Collections.reverseOrder());
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            // 把窗口最左边的数去掉
            if (i >= k) {
                pq.remove(nums[i - k]);
            }
            // 把新的数加入窗口的堆中
            pq.offer(nums[i]);
            // 堆顶就是窗口的最大值
            if (i + 1 >= k) {
                res[i - k + 1] = pq.peek();
            }
        }
        return res;
    }

    /**
     * From a leetcode user.
     * <p>
     * 3ms
     * <p>
     * 时间 n*k 空间 1
     * <p>
     * 这个方法是我想到的方法
     */
    private static int[] solution3(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] result = new int[nums.length - k + 1];
        int left = 0;
        int right = k - 1;
        int max = -1;
        while (right < nums.length) {
            if (max < left) {
                max = left;
                // 每当max过期过后重新，重新寻找nums[left~right]的最大者。
                // 在最坏情况下（nums降序排列），复杂度是n*k
                for (int i = left + 1; i <= right; i++) {
                    if (nums[i] >= nums[max]) {
                        max = i;
                    }
                }
            } else if (nums[right] >= nums[max]) {
                max = right;
            }
            result[left] = nums[max];
            left++;
            right++;
        }
        return result;
    }
}
