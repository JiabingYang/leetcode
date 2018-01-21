package com.yjb.leetcode.medium;

/**
 * 294. Flip Game II
 * <p>
 * You are playing the following Flip Game with your friend:
 * Given a string that contains only these two characters: + and -,
 * you and your friend take turns to flip two consecutive "++" into "--".
 * The game ends when a person can no longer make a move and therefore the other person will be the winner.
 * <p>
 * Write a function to determine if the starting player can guarantee a win.
 * <p>
 * For example, given s = "++++", return true.
 * The starting player can guarantee a win by flipping the middle "++" to become "+--+".
 */
public class No294FlipGameII {

    public static void main(String[] args) {
        System.out.println(solution1("++++"));
        System.out.println(solution1("+++++"));
        System.out.println(solution1("++++++"));
        System.out.println(solution1("+++++++++++++++++++"));
    }

    /**
     * https://www.programcreek.com/2014/05/leetcode-flip-game-ii-java/
     * <p>
     * This problem is solved by backtracking.
     * <p>
     * Roughly, the time is n*n*...n, which is O(n^n).
     * The reason is each recursion takes O(n) and there are totally n recursions.
     */
    private static boolean solution1(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        return canWinHelper(s.toCharArray());
    }

    private static boolean canWinHelper(char[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == '+' && arr[i + 1] == '+') {
                arr[i] = '-';
                arr[i + 1] = '-';

                boolean win = canWinHelper(arr);

                arr[i] = '+';
                arr[i + 1] = '+';

                // 注意点1：
                // if there is a flip which makes the other player lose, the first player wins
                // 注意点2：
                // 下面的判断不能放在arr[i] = '+';arr[i + 1] = '+';这两句上面，因为如果return了true，
                // 那么arr就不会恢复，对上一层递归来说得到win等于true就要继续循环，循环就会在错误的数组上循环了。
                if (!win) {
                    return true;
                }
            }
        }

        return false;
    }
}
