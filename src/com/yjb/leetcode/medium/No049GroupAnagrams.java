package com.yjb.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 49. Group Anagrams
 */
public class No049GroupAnagrams {

    public static void main(String[] args) {
        System.out.println(mySolution(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

    /**
     * 95.95%
     */
    private static List<List<String>> mySolution(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String sorted = sort(str);
            List<String> list = map.getOrDefault(sorted, new ArrayList<>());
            list.add(str);
            map.put(sorted, list);
        }
        return new ArrayList<>(map.values());
    }

    private static String sort(String src) {
        // 桶排序
        int[] arr = new int[26];
        for (int i = 0; i < src.length(); i++) {
            arr[src.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < arr[i]; j++) {
                sb.append((char) ('a' + i));
            }
        }
        return sb.toString();
    }

    /**
     * https://www.programcreek.com/2014/04/leetcode-anagrams-java/
     * <p>
     * Time Complexity
     * <p>
     * If the average length of verbs is m and array length is n, then the time is O(n*m).
     * <p>
     * 44.39%
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] arr = new char[26];
            for (int i = 0; i < str.length(); i++) {
                arr[str.charAt(i) - 'a']++;
            }
            String ns = new String(arr);
            List<String> list = map.getOrDefault(ns, new ArrayList<>());
            list.add(str);
            map.put(ns, list);
        }
        return new ArrayList<>(map.values());
    }
}
