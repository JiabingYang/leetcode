package com.yjb.leetcode.medium;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 3. Longest Substring Without Repeating Characters
 * <p>
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Examples:
 * <p>
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * <p>
 * Given "bbbbb", the answer is "b", with the length of 1.
 * <p>
 * Given "pwwkew", the answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class No003LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(mySolutionHashMap("abcabcbb"));// "abc" 3
        System.out.println(mySolutionHashMap("bbbbb"));// "b" 1
        System.out.println(mySolutionHashMap("pwwkew"));// "wke" 3
    }

    /**
     * 30.15%
     * 一遍遍历，一直计算包含当前字符在内的子串长度，维持max值
     * 通过HashMap的长度得到子串开始的位置（其实设个变量就行，不用HashMap来得到）
     * 通过HashMap#get得到要删除到哪儿（其实可以一直比较，不用HashMap来得到）
     */
    public static int mySolutionHashMap(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                int index = map.get(c);
                for (int j = i - map.size(); j <= index; j++) {
                    map.remove(s.charAt(j));
                }
            }
            map.put(c, i);
            max = Math.max(map.size(), max);
        }
        return max;
    }

    /**
     * 30.15%
     * 不需要用HashMap记录子串中的字符的位置，只需要用一个变量记录子串开始的位置
     * 删除的终止可以用比较字符来确定
     */
    public static int mySolutionHashSet(String s) {
        HashSet<Character> set = new HashSet<>();
        int max = 0;
        int jStart = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                int j;
                for (j = jStart; s.charAt(j) != c; j++) {
                    set.remove(s.charAt(j));
                }
                jStart = j + 1;
            }
            set.add(c);
            max = Math.max(set.size(), max);
        }
        return max;
    }

    /**
     * 27.11%
     * 去掉了mySolutionHashSet中的jStart（简化）
     */
    public static int mySolutionHashSet1(String s) {
        int max = 0;
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                for (int j = i - set.size(); s.charAt(j) != c; j++) {
                    set.remove(s.charAt(j));
                }
            } else {
                set.add(c);
            }
            max = Math.max(max, set.size());
        }
        return max;
    }

    /**
     * The first solution is like the problem of "determine if a string has all unique characters" in CC 150.
     * We can use a flag array to track the existing characters for the longest substring without repeating characters.
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null)
            return 0;
        boolean[] flag = new boolean[256];

        int result = 0;
        int start = 0;
        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            char current = arr[i];
            if (flag[current]) {
                result = Math.max(result, i - start);
                // the loop update the new start point
                // and reset flag array
                // for example, abccab, when it comes to 2nd c,
                // it update start from 0 to 3, reset flag for a,b
                for (int k = start; k < i; k++) {
                    if (arr[k] == current) {
                        start = k + 1;
                        break;
                    }
                    flag[arr[k]] = false;
                }
            } else {
                flag[current] = true;
            }
        }

        result = Math.max(arr.length - start, result);

        return result;
    }
}