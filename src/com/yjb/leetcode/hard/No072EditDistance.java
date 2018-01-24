package com.yjb.leetcode.hard;

/**
 * 72. Edit Distance
 * <p>
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
 * <p>
 * You have the following 3 operations permitted on a word:
 * <p>
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 */
public class No072EditDistance {

    /**
     * 参考：
     * https://www.cnblogs.com/grandyang/p/4344107.html
     * <p>
     * 递归
     * <p>
     * 动态规划的思路：
     * dp[i][j]表示从word1的前i个字符转换到word2的前j个字符所需要的步骤
     * if word1[i - 1] == word2[j - 1]
     * dp[i][j] = dp[i - 1][j - 1];
     * else
     * dp[i][j] = min(dp[i - 1][j - 1], min(dp[i - 1][j], dp[i][j - 1])) + 1;
     * <p>
     * 超时
     */
    public int solution1(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return -1;
        }
        return solution1Core(word1.toCharArray(), word2.toCharArray(), word1.length(), word2.length());
    }

    private int solution1Core(char[] word1, char[] word2, int i, int j) {
        if (i == 0) {
            return j;
        }
        if (j == 0) {
            return i;
        }
        if (word1[i - 1] == word2[j - 1]) {
            return solution1Core(word1, word2, i - 1, j - 1);
        }
        return Math.min(solution1Core(word1, word2, i - 1, j - 1),
                Math.min(solution1Core(word1, word2, i, j - 1),
                        solution1Core(word1, word2, i - 1, j))) + 1;
    }

    /**
     * 参考：
     * https://www.cnblogs.com/grandyang/p/4344107.html
     * <p>
     * DP
     * solution1的DP版本
     * <p>
     * 动态规划的思路：
     * dp[i][j]表示从word1的前i个字符转换到word2的前j个字符所需要的步骤
     * if word1[i - 1] == word2[j - 1]
     * dp[i][j] = dp[i - 1][j - 1];
     * else
     * dp[i][j] = min(dp[i - 1][j - 1], min(dp[i - 1][j], dp[i][j - 1])) + 1;
     * <p>
     * 11.05%
     */
    public int solution2(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return -1;
        }
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
        return dp[len1][len2];
    }

    /**
     * 参考：
     * https://www.cnblogs.com/grandyang/p/4344107.html
     * <p>
     * DP
     * solution2中基础情况在循环体中分离开的版本（减少判断次数）
     * <p>
     * 动态规划的思路：
     * dp[i][j]表示从word1的前i个字符转换到word2的前j个字符所需要的步骤
     * if word1[i - 1] == word2[j - 1]
     * dp[i][j] = dp[i - 1][j - 1];
     * else
     * dp[i][j] = min(dp[i - 1][j - 1], min(dp[i - 1][j], dp[i][j - 1])) + 1;
     * <p>
     * 62.59%
     */
    public int solution3(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return -1;
        }
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= len2; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
        return dp[len1][len2];
    }
}
