package com.yjb.leetcode.hard;

import java.util.HashMap;

/**
 * 76. Minimum Window Substring
 * <p>
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * <p>
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 * <p>
 * Note:
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * <p>
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class No076MinimumWindowSubstring {

    public static void main(String[] args) {
        System.out.println(solution1("ADOBECODEBANC", "ABC")); // "BANC"
        System.out.println(solution1("a", "a")); // "a"
        System.out.println(solution1("acbbaca", "aba")); // "baca"
    }

    /**
     * https://www.programcreek.com/2014/05/leetcode-minimum-window-substring-java/
     */
    public static String solution1(String s, String t) {
        if (t.length() > s.length())
            return "";
        String minWindow = "";

        //character counter for t
        HashMap<Character, Integer> target = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            target.put(c, target.getOrDefault(c, 0) + 1);
        }

        // character counter for s
        HashMap<Character, Integer> map = new HashMap<>(); // 保存当前子串中所有t中字符（超过t中该字符的数量还会存进去）
        int left = 0;
        int minWindowLen = s.length() + 1;

        int count = 0; // map中包含的t中字符的个数

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (target.containsKey(c)) {
                if (map.containsKey(c)) {
                    if (map.get(c) < target.get(c)) {
                        count++;
                    }
                    map.put(c, map.get(c) + 1);
                } else {
                    map.put(c, 1);
                    count++;
                }
            }

            if (count == t.length()) {
                char sc = s.charAt(left);
                while (!map.containsKey(sc) || map.get(sc) > target.get(sc)) {
                    if (map.containsKey(sc) && map.get(sc) > target.get(sc))
                        map.put(sc, map.get(sc) - 1);
                    left++;
                    sc = s.charAt(left);
                }

                if (i - left + 1 < minWindowLen) {
                    minWindow = s.substring(left, i + 1);
                    minWindowLen = i - left + 1;
                }
            }
        }

        return minWindow;
    }

    /**
     * https://segmentfault.com/a/1190000003707313
     * <p>
     * 用一个哈希表记录目标字符串每个字母的个数，一个哈希表记录窗口中每个字母的个数。
     * 先找到第一个有效的窗口，用两个指针标出它的上界和下界。
     * 然后每次窗口右界向右移时，将左边尽可能的右缩，右缩的条件是窗口中字母的个数不小于目标字符串中字母的个数。
     * <p>
     * 注意
     * 用一个数组来保存每个字符出现的次数，比哈希表容易
     * 保存结果子串的起始点初值为-1，方便最后判断是否有正确结果
     */
    public String solution2(String s, String t) {

        // 记录目标字符串每个字母出现次数
        int[] srcHash = new int[255];
        for (int i = 0; i < t.length(); i++) {
            srcHash[t.charAt(i)]++;
        }
        int start = 0, i = 0;

        // 用于记录窗口内每个字母出现次数
        int[] destHash = new int[255];
        int found = 0;
        int begin = -1, end = s.length(), minLength = s.length();
        for (start = i = 0; i < s.length(); i++) {

            // 每来一个字符给它的出现次数加1
            destHash[s.charAt(i)]++;

            // 如果加1后这个字符的数量不超过目标串中该字符的数量，则找到了一个匹配字符
            if (destHash[s.charAt(i)] <= srcHash[s.charAt(i)]) found++;

            // 如果找到的匹配字符数等于目标串长度，说明找到了一个符合要求的子串
            if (found == t.length()) {

                // 将开头没用的都跳过，没用是指该字符出现次数超过了目标串中出现的次数，并把它们出现次数都减1
                while (start < i && destHash[s.charAt(start)] > srcHash[s.charAt(start)]) {
                    destHash[s.charAt(start)]--;
                    start++;
                }

                // 这时候start指向该子串开头的字母，判断该子串长度
                if (i - start < minLength) {
                    minLength = i - start;
                    begin = start;
                    end = i;
                }

                // 把开头的这个匹配字符跳过，并将匹配字符数减1
                destHash[s.charAt(start)]--;
                found--;

                // 子串起始位置加1，我们开始看下一个子串了
                start++;
            }
        }
        // 如果begin没有修改过，返回空
        return begin == -1 ? "" : s.substring(begin, end + 1);
    }
}
