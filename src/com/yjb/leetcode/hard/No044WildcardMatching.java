package com.yjb.leetcode.hard;

import java.util.Arrays;

/**
 * 44. Wildcard Matching
 * <p>
 * Implement wildcard pattern matching with support for '?' and '*'.
 * <p>
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * <p>
 * The matching should cover the entire input string (not partial).
 * <p>
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * <p>
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 */
public class No044WildcardMatching {

    // ------------------------ 测试 ------------------------
    public static void main(String[] args) {
        String[][] testCases = {
                {"aa", "a"},
                {"aa", "aa"},
                {"aaa", "aa"},
                {"aa", "*"},
                {"aa", "a*"},
                {"ab", "?*"},
                {"aab", "c*a*b"},
                {"aab", "*ab"},
                {"zacabz", "*a?b*"},
        };
        boolean[] results = new boolean[testCases.length];
        for (int i = 0; i < testCases.length; i++) {
            String[] testCase = testCases[i];
            results[i] = solution1V1(testCase[0], testCase[1]);

        }
        System.out.println(Arrays.equals(results, new boolean[]{false, true, false, true, true, true, false, true, false}));
    }

    // ------------------------ 依赖的方法 ------------------------
    private static boolean isCharMatch(char c, char p) {
        return (p == '?' || p == c);
    }

    // ------------------------ solution1V1 ------------------------

    /**
     * 参考自：
     * https://www.cnblogs.com/yuzhangcmu/p/4116153.html
     * https://www.programcreek.com/2014/06/leetcode-wildcard-matching-java/
     * <p>
     * 89.01%
     */
    private static boolean solution1V1(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int i = 0;
        int j = 0;

        int sLen = s.length();
        int pLen = p.length();

        int iPre = -1;
        int jStar = -1;

        while (i < sLen) {
            if (j < pLen && isCharMatch(s.charAt(i), p.charAt(j))) {
                // 普通匹配成功
                i++;
                j++;
                continue;
            }
            if (j < pLen && p.charAt(j) == '*') {
                // j为*
                iPre = i;
                jStar = j;
                // Move to the next character of P.
                j++;
                continue;
            }
            if (jStar != -1) {
                j = jStar + 1;
                i = ++iPre;
                continue;
            }
            return false;
        }
        // 退出循环，i已经走完
        // j跳过末尾的所有的*.
        while (j < pLen && p.charAt(j) == '*') {
            j++;
        }

        // i走完了，如果j跳过末尾的所有的*.后也走完了，那么匹配成功，否则匹配失败。
        return j == pLen;
    }

    // ------------------------ solution1V2 ------------------------

    /**
     * 参考自：
     * https://www.cnblogs.com/yuzhangcmu/p/4116153.html
     * <p>
     * 85.55%
     */
    private static boolean solution1V2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int i = 0;
        int j = 0;

        int sLen = s.length();
        int pLen = p.length();

        int iPre = 0;
        int jAfterStars = 0;

        boolean back = false;

        while (i < sLen) {
            if (j < pLen && isCharMatch(s.charAt(i), p.charAt(j))) {
                // 普通匹配成功
                i++;
                j++;
                continue;
            }
            if (j < pLen && p.charAt(j) == '*') {
                // j为*
                while (j < pLen && p.charAt(j) == '*') {
                    // j移动到当前位置开始的所有*的下一个位置
                    j++;
                }

                if (j == pLen) {
                    //j从当前位置直到最后都是*，可以匹配任何字符串
                    return true;
                }

                // 记录下这个匹配位置。
                iPre = i;
                jAfterStars = j;
                back = true;
                continue;
            }
            if (back) {
                // j为遇到*后的所有*的下一个位置或下下个位置或...
                i = ++iPre;
                j = jAfterStars;
                continue;
            }
            // 既没有普通匹配也没有碰到j为*或处在back状态
            return false;
        }
        // 退出循环，i已经走完
        // j跳过末尾的所有的*.
        while (j < pLen && p.charAt(j) == '*') {
            j++;
        }

        // 如果i走完了，j跳过末尾的所有的*.后也走完了，那么匹配成功，否则匹配失败。
        return j == pLen;
    }

    // ------------------------ solution1Dp ------------------------

    /**
     * 参考自：https://www.cnblogs.com/yuzhangcmu/p/4116153.html
     * <p>
     * 输入两个字符串s,p(p包含通配符，用p去匹配s),
     * 用match[i][j]表示字符串p的[0,j)的子字符串能否匹配s的[0,i)的子字符串(i==0或j==0表示s或p取空字符串)
     * 情况：
     * (1) 如果p.charAt(j-1)==s.charAt(i-1)||p.charAt(j-1)=='?',相当于将最后一个字符匹配掉，所以
     * match[i][j]=match[i-1][j-1];
     * <p>
     * (2) 如果p.charAt(j-1)=='*'，'*'可以选择匹配0个字符，此时match[i][j]=match[i][j-1];可以选择匹配1个字符，此时match[i][j]=match[i-1][j-1];……所以，
     * match[i][j]=match[i][j-1]||match[i-1][j-1]||……||match[0][j-1]。
     * 但是上面的公式可以化简，当p.charAt(j-1)=='*'时,有
     * match[i-1][j]=match[i-1][j-1]||match[i-2][j-1]||……||match[0][j-1]
     * 所以
     * match[i][j]==match[i][j-1]||match[i-1][j]
     * <p>
     * (3) 其他
     * match[i][j]=false;
     */
    private static boolean solution2Dp(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int sLen = s.length();
        int pLen = p.length();

        // match[i][j]表示字符串p的[0,j)的子字符串能否匹配s的[0,i)的子字符串(i==0或j==0表示s或p取空字符串)
        boolean[][] match = new boolean[sLen + 1][pLen + 1];

        boolean someJMatched = false;

        for (int i = 0; i <= sLen; i++) {
            someJMatched = false;
            for (int j = 0; j <= pLen; j++) {
                if (i == 0 && j == 0) {
                    // s empty, p empty
                    match[i][j] = true;
                    someJMatched = true;
                    continue;
                }

                if (j == 0) {
                    // s not empty, p empty
                    match[i][j] = false;
                    continue;
                }

                if (i == 0) {
                    // s empty, p not empty
                    match[i][j] = match[i][j - 1] && p.charAt(j - 1) == '*';
                } else {
                    // s not empty, p not empty
                    match[i][j] = (isCharMatch(s.charAt(i - 1), p.charAt(j - 1)) && match[i - 1][j - 1])
                            || (p.charAt(j - 1) == '*' && (match[i][j - 1] || match[i - 1][j]));
                }

                if (match[i][j]) {
                    someJMatched = true;
                }

                // 在此即可以退出，因为* 可以匹配余下的所有的字符串。
                if (match[i][j] && p.charAt(j - 1) == '*' && j == pLen) {
                    return true;
                }
            }

            if (!someJMatched) {
                return false;
            }
        }

        return match[sLen][pLen];
    }
}
