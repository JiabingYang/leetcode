package com.yjb.leetcode.other;

import com.yjb.leetcode.util.RandomUtils;

import java.util.HashMap;
import java.util.HashSet;

/**
 * This problem is related with LongestSubstringWhichContainsKUniqueCharacters.
 * <p>
 * Now if this question is extended to be "the longest substring that contains k unique characters", what should we do?
 * <p>
 * For example, given "abcadcacacaca", the longest substring that contains 3 unique character is "cadcacacaca".
 */
public class LongestSubstringWhichContainsKUniqueCharacters {

    public static void main(String[] args) {
        boolean failed = false;
        for (String s : RandomUtils.randomStrings(0, 20, 20)) {
            for (int k = 0; k < 50; k++) {
                if (mySolution(s, k) != solution1(s, k)) {
                    System.out.println(k + " " + s);
                    failed = true;
                }
            }
        }
        if (!failed) {
            System.out.println("success");
        }
    }

    /**
     * https://www.programcreek.com/2013/02/longest-substring-which-contains-2-unique-characters/
     */
    private static int solution1(String s, int k) {
        if (k == 0 || s == null || s.length() == 0)
            return 0;
        if (s.length() < k)
            return s.length();

        int max = k;
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
            if (map.size() > k) {
                max = Math.max(max, i - start);
                while (map.size() > k) {
                    char t = s.charAt(start);
                    int count = map.get(t);
                    if (count > 1) {
                        map.put(t, count - 1);
                    } else {
                        map.remove(t);
                    }
                    start++;
                }
            }
        }
        max = Math.max(max, s.length() - start);
        return max;
    }

    /**
     * 错误
     * 必须用solution1的HashMap做，关键在于缩的时候不一定就是缩到jEnd的位置
     */
    private static int mySolution(String s, int k) {
        if (k == 0 || s == null)
            return 0;
        if (s.length() <= k) {
            return s.length();
        }

        int max = 1;
        int jStart = 0;
        int jEnd = 0;
        char start = s.charAt(0);
        HashSet<Character> set = new HashSet<>(); //当前子串中的其他字符
        set.add(start);
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == start) {
                jEnd = i;
                continue;
            }
            if (set.size() < k) {
                set.add(c);
                continue;
            }
            if (!set.contains(c)) {
                max = Math.max(max, i - jStart);
                set.add(c);
                set.remove(start);
                jStart = jEnd + 1;
                jEnd = jStart;
                start = s.charAt(jStart);
            }
        }
        max = Math.max(max, s.length() - jStart);
        return max;
    }
}
