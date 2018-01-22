package com.yjb.leetcode.medium;

import java.util.*;

/**
 * 347. Top K Frequent Elements
 * <p>
 * Given a non-empty array of integers, return the k most frequent elements.
 * <p>
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class No347TopKFrequentElements {

    /**
     * HashMap + 堆
     *
     * @param nums
     * @param k
     * @return
     */
    private static List<Integer> mySolution(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((e1, e2) -> e2.getValue() - e1.getValue());
        queue.addAll(map.entrySet());
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(queue.poll().getKey());
        }
        return result;
    }

    /**
     * 来自：
     * Some LeetCode user.
     * <p>
     * HashMap + 桶排序
     * <p>
     * 时间 n
     */
    public List<Integer> solution1(int[] nums, int k) {
        //count the frequency for each element
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        //get the max frequency
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            max = Math.max(max, entry.getValue());
        }

        // index is frequency, value is list of numbers
        List<Integer>[] bucket = new ArrayList[max + 1];
        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(key);
        }

        //add most frequent numbers to result
        List<Integer> result = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != null) {
                for (int j = 0; j < bucket[i].size() && result.size() < k; j++) {
                    result.add(bucket[i].get(j));
                }
            }
        }
        return result;
    }

    /**
     * 来自：
     * Some LeetCode user.
     * <p>
     * HashMap + TreeMap
     */
    public List<Integer> solution2(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return result;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        TreeMap<Integer, List<Integer>> freqMap = new TreeMap<>();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int freq = entry.getValue();

            if (!freqMap.containsKey(freq)) {
                freqMap.put(freq, new ArrayList<>());
            }

            freqMap.get(freq).add(entry.getKey());
        }

        while (result.size() < k) {
            result.addAll(freqMap.pollLastEntry().getValue());
        }

        return result;
    }

    /**
     * 数组散列表 + 桶排序
     */
    private static List<Integer> solution3(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        int min = nums[0];
        int max = nums[0];


        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int[] data = new int[max - min + 1];
        for (int n : nums) {
            data[n - min]++;
        }

        List<Integer>[] bucket = new List[nums.length + 1];
        for (int i = data.length - 1; i >= 0; i--) {
            if (data[i] > 0) {
                if (bucket[data[i]] == null)
                    bucket[data[i]] = new ArrayList<>();

                List<Integer> list = bucket[data[i]];
                list.add(i + min);
                bucket[data[i]] = list;
            }
        }

        for (int i = bucket.length - 1; i >= 0 && result.size() < k; i--) {
            if (bucket[i] != null)
                result.addAll(bucket[i]);
        }

        return result;
    }
}
