package com.yjb.leetcode.hard;

/**
 * 214. Shortest Palindrome
 * <p>
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
 * Find and return the shortest palindrome you can find by performing this transformation.
 * <p>
 * For example:
 * <p>
 * Given "aacecaaa", return "aaacecaaa".
 * <p>
 * Given "abcd", return "dcbabcd".
 * <p>
 * Credits:
 * Special thanks to @ifanchu for adding this problem and creating all test cases.
 * Thanks to @Freezen for additional test cases.
 * <p>
 * 本题ProgramCreek上的solution2是错的。
 * ProgramCreek的solution2过不了OJ,在"aba"时返回错误。
 */
public class No214ShortestPalindrome {

    public static void main(String[] args) {
        System.out.println(solution1("aacecaaa") + "[aaacecaaa]");
        System.out.println(solution1("abcd") + "[dcbabcd]");
        System.out.println(solution1("aba") + "[dcbabcd]");
        System.out.println(solution1("aacecaaaaceecaaa") + "[dcbabcd]");
    }

    /**
     * https://www.programcreek.com/2014/06/leetcode-shortest-palindrome-java/
     * <p>
     * 下面的递归很重要。
     * i只会多走不会少走，所以需要对s.substring(0, i)递归调用solution1
     */
    public static String solution1(String s) {
        int i = 0;
        int j = s.length() - 1;

        while (j >= 0) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
            }
            j--;
        }

        // 基准情况，只有s完全是回文时才会i == s.length()
        if (i == s.length())
            return s;

        String suffix = s.substring(i);
        System.out.println(suffix);
        String prefix = new StringBuilder(suffix).reverse().toString();
        // 这里的递归很重要，不用这个递归可能会出错(aacecaaaaceecaaa)
        String mid = solution1(s.substring(0, i));
        return prefix + mid + suffix;
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4523624.html
     * https://segmentfault.com/a/1190000003059361
     * <p>
     * kmp
     * <p>
     * 求字符串s的翻转s_rev
     * 将两个字符串进行拼接：{s}#{s_rev}
     * 找出新字符串中最长公共前缀后缀长度comLen
     * s_rev.substring(0, s.length() - comLen)就是在原字符串头部插入的子串部分
     */
    private static String solution2(String s) {
        String r = new StringBuilder(s).reverse().toString();
        String t = s + "#" + r;
        int[] next = new int[t.length()];
        for (int i = 1; i < t.length(); i++) {
            int j = next[i - 1];
            while (j > 0 && t.charAt(i) != t.charAt(j)) {
                j = next[j - 1];
            }
            j += (t.charAt(i) == t.charAt(j)) ? 1 : 0;
            next[i] = j;
        }
        return r.substring(0, s.length() - next[t.length() - 1]) + s;
    }

    /**
     * 暴力解
     * <p>
     * 超时
     * <p>
     * 时间 n2
     */
    private static String mySolution(String s) {
        if (s.length() <= 1) {
            return s;
        }
        int j = 0;
        int i = s.length() - 1;
        int k = i;
        while (j < k) {
            if (s.charAt(j) == s.charAt(k)) {
                j++;
                k--;
            } else {
                j = 0;
                k = --i;
            }
        }
        return new StringBuilder(s.substring(i + 1, s.length())).reverse().append(s).toString();
    }
}
