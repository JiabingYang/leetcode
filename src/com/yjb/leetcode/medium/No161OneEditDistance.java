package com.yjb.leetcode.medium;

/**
 * 161. One Edit Distance
 * <p>
 * Given two strings S and T, determine if they are both one edit distance apart.
 */
public class No161OneEditDistance {

    public static void main(String[] args) {
        System.out.println(solution1("bc", "ba"));
    }

    /**
     * 参考：
     * https://www.cnblogs.com/grandyang/p/5184698.html
     * <p>
     * 1. 两个字符串的长度之差大于1，那么直接返回False
     * 2. 两个字符串的长度之差等于1，那么长的那个字符串去掉一个字符，剩下的应该和短的字符串相同
     * 3. 两个字符串的长度之差等于0，那么两个字符串对应位置的字符只能有一处不同。
     */
    private static boolean solution1(String s, String t) {
        if (s.length() < t.length()) {
            String temp = s;
            s = t;
            t = temp;
        }
        int diff = s.length() - t.length();
        if (diff > 2) {
            return false;
        }
        if (diff == 1) {
            for (int i = 0; i < t.length(); i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    return s.substring(i + 1).equals(t.substring(i));
                }
            }
            return true;
        }
        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                return s.substring(i + 1).equals(t.substring(i + 1));
            }
        }
        return false;
    }

    /**
     * 参考：
     * https://www.cnblogs.com/grandyang/p/5184698.html
     * <p>
     * 我们实际上可以让代码写的更加简洁
     * 只需要对比两个字符串对应位置上的字符，如果遇到不同的时候，这时我们看两个字符串的长度关系，
     * 如果相等，那么我们比较当前位置后的字串是否相同，
     * 如果s的长度大，那么我们比较s的下一个位置开始的子串，和t的当前位置开始的子串是否相同，
     * 反之如果t的长度大，那么我们比较t的下一个位置开始的子串，和s的当前位置开始的子串是否相同。
     * 如果循环结束，都没有找到不同的字符，那么此时我们看两个字符串的长度是否相差1
     */
    private static boolean solution2(String s, String t) {
        int len = Math.min(s.length(), t.length());
        for (int i = 0; i < len; ++i) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                }
                if (s.length() < t.length()) {
                    return s.substring(i).equals(t.substring(i + 1));
                }
                return s.substring(i + 1).equals(t.substring(i));
            }
        }
        return Math.abs(s.length() - t.length()) == 1;
    }
}
