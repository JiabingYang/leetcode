package com.yjb.leetcode.easy;

import java.util.HashMap;

/**
 * 205. Isomorphic Strings
 * <p>
 * Given two strings s and t, determine if they are isomorphic.
 * <p>
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * <p>
 * All occurrences of a character must be replaced with another character while preserving the order
 * of characters. No two characters may map to the same character but a character may map to itself.
 * <p>
 * For example,
 * Given "egg", "add", return true.
 * Given "foo", "bar", return false.
 * Given "paper", "title", return true.
 * <p>
 * Note:
 * You may assume both s and t have the same length.
 */
public class IsomorphicStrings {

    public static void main(String[] args) {
        System.out.println(new IsomorphicStrings().mySolution2("ab", "ca"));
    }

    /**
     * 第一遍检查s到t的映射，保证不存在一个cS对多个cT的情况。
     * 第二遍检查t到s的映射，保证不存在一个cT对多个cS的情况。
     * 如果两遍检查都通过，则验证通过。
     */
    public boolean mySolution1(String s, String t) {
        return check(s, t) && check(t, s);
    }

    private boolean check(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (!map.containsKey(c1)) {
                map.put(c1, c2);
                continue;
            }
            if (map.get(c1) != c2) {
                return false;
            }
        }
        return true;
    }

    /**
     * 对每一对c1和c2，只要c1和HashMap中的一个key对应或c2和HashMap中的一个value对应，那么就要检查c1和c2的对应关系。
     * 否则c1和c2的对应关系在HashMap中没有，需要将对应关系加入HashMap。
     */
    private boolean mySolution2(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (map.containsKey(c1)) {
                if (map.get(c1) != c2) {
                    return false;
                }
                continue;
            }
            if (map.containsValue(c2)) {
                return false;
            }
            map.put(c1, c2);
        }
        return true;
    }
}
