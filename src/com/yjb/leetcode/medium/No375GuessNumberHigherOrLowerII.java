package com.yjb.leetcode.medium;

/**
 * 375. Guess Number Higher or Lower II
 * <p>
 * We are playing the Guess Game. The game is as follows:
 * <p>
 * I pick a number from 1 to n. You have to guess which number I picked.
 * <p>
 * Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
 * <p>
 * However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.
 * <p>
 * Example:
 * <p>
 * n = 10, I pick 8.
 * <p>
 * First round:  You guess 5, I tell you that it's higher. You pay $5.
 * Second round: You guess 7, I tell you that it's higher. You pay $7.
 * Third round:  You guess 9, I tell you that it's lower. You pay $9.
 * <p>
 * Game over. 8 is the number I picked.
 * <p>
 * You end up paying $5 + $7 + $9 = $21.
 * Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
 * <p>
 * Hint:
 * <ol>
 * <li>The best strategy to play the game is to minimize the maximum loss you could possibly face. Another strategy is to minimize the expected loss. Here, we are interested in the first scenario.</li>
 * <li>Take a small example (n = 3). What do you end up paying in the worst case?</li>
 * <li>Check out <a href="https://en.wikipedia.org/wiki/Minimax">this article</a> if you’re still stuck.</li>
 * <li>The purely recursive implementation of minimax would be worthless for even a small n. You MUST use dynamic programming.</li>
 * <li>As a follow-up, how would you modify your code to solve the problem of minimizing the expected loss, instead of the worst-case loss?</li>
 * </ol>
 * <p>
 * 本题的理解见：https://www.cnblogs.com/grandyang/p/5677550.html
 */
public class No375GuessNumberHigherOrLowerII {

    /**
     * 参考：
     * https://www.cnblogs.com/grandyang/p/5677550.html
     * https://www.hrwhisper.me/leetcode-guess-number-higher-lower-ii/
     * <p>
     * 迭代写法（自底向上）
     * <p>
     * cost[1,n] = k + max{cost[1,k - 1] + cost[k+1,n]}
     * <p>
     * 这种写法的base case是：
     * cost[i][<=i] = 0;
     */
    private static int mySolution(int n) {
        // 1.n+2长度的解释：
        // 这里用n+2长度的数组避免下面dp[k + 1][j]中k+1超出数组长度，
        // 而且k + 1 == n + 2时, i>j确实应该按花费0算，所以这里声明n+2长度是没问题的
        // 其实dp[i][k - 1]中的k - 1也会出现k - 1 == 0的情况，这里dp[?][0]是0也是合理的。
        // 2.如果使用n+1长度
        // 如果这里用n+1长度的话，下面的k<=j改成k<j也是可以过OJ的，
        // 原因见solution1注释中“为什么这里k不考虑i和j也能过OJ?”
        int[][] dp = new int[n + 2][n + 2];
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    min = Math.min(min, k + Math.max(dp[i][k - 1], dp[k + 1][j]));
                }
                dp[i][j] = min;
            }
        }
        return dp[1][n];
    }

    /**
     * https://discuss.leetcode.com/topic/51353/simple-dp-solution-with-explanation/2
     * <p>
     * 迭代写法（自底向上）
     * <p>
     * cost[1,n] = k + max{cost[1,k - 1] + cost[k+1,n]}
     * <p>
     * 这种写法的base case是：
     * cost[i][<=i] = 0;
     * cost[i][i+1] = i;
     */
    private static int solution1(int n) {
        int[][] table = new int[n + 1][n + 1];
        for (int j = 2; j <= n; j++) {
            for (int i = j - 1; i > 0; i--) {
                int min = Integer.MAX_VALUE;
                // 为什么这里k不考虑i和j也能过OJ?
                // 这个问题网上目前我只找到这样一个解释（来自@jianywan）：
                // because we will prefer to choose i+1 or j-1, rather than i or j.
                // Assume we choose i+1, if it's lower, we will know the target is i.
                for (int k = i + 1; k < j; k++) {
                    min = Math.min(min, k + Math.max(table[i][k - 1], table[k + 1][j]));
                }
                table[i][j] = i + 1 == j ? i : min;
            }
        }
        return table[1][n];
    }

    /**
     * 参考：
     * https://www.cnblogs.com/grandyang/p/5677550.html
     * https://www.hrwhisper.me/leetcode-guess-number-higher-lower-ii/
     * <p>
     * 递归写法（缓存搜索，自顶向下）
     */
    private static int solutionRecursion(int n) {
        int[][] mem = new int[n + 1][n + 1];
        return solve(1, n, mem);
    }

    private static int solve(int start, int end, int[][] mem) {
        if (start >= end) {
            return 0;
        }
        if (mem[start][end] > 0) {
            return mem[start][end];
        }
        int min = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            min = Math.min(min, i + Math.max(solve(start, i - 1, mem), solve(i + 1, end, mem)));
        }
        mem[start][end] = min;
        return min;
    }
}
