package com.yjb.leetcode.other;

import com.yjb.leetcode.util.RandomUtils;

import java.util.HashMap;

/**
 * This is a problem asked by Google.
 * <p>
 * Given a string, find the longest substring that contains only two unique characters.
 * For example, given "abcbbbbcccbdddadacb", the longest substring that contains 2 unique character is "bcbbbbcccb".
 * <p>
 * https://www.programcreek.com/2013/02/longest-substring-which-contains-2-unique-characters/
 */
public class LongestSubstringWhichContainsTwoUniqueCharacters {

    public static void main(String[] args) {
        boolean failed = false;
        for (String s : RandomUtils.randomStrings(0, 20, 20)) {
            if (mySolution(s) != solution1(s)) {
                System.out.println(s);
                failed = true;
            }
        }
        if (!failed) {
            System.out.println("success");
        }
    }

    /**
     * https://www.programcreek.com/2013/02/longest-substring-which-contains-2-unique-characters/
     */
    private static int solution1(String s) {
        int max = 0;
        HashMap<Character, Integer> map = new HashMap<>(); // char -> count
        int start = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
            if (map.size() > 2) {
                max = Math.max(max, i - start);
                while (map.size() > 2) {
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
    private static int mySolution(String s) {
        if (s.length() <= 2) {
            return s.length();
        }
        int max = 1;
        int jStart = 0; // 当前字串开始位置
        int jEnd = 0; // 当前子串开头字符最后一次出现的位置
        char start = s.charAt(0); // 当前子串开头字符
        Character other = null; //当前子串中的另一个字符
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == start) {
                jEnd = i;
                continue;
            }
            if (other == null) {
                other = c;
                continue;
            }
            if (other != c) {
                max = Math.max(max, i - jStart);
                jStart = jEnd + 1;
                jEnd = jStart;
                start = other;
                other = c;
            }
        }
        max = Math.max(max, s.length() - jStart);
        return max;
    }
}
