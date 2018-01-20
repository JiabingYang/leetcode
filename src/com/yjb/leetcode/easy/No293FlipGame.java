package com.yjb.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * No.293 Flip Game
 * <p>
 * You are playing the following Flip Game with your friend:
 * Given a string that contains only these two characters: + and -,
 * you and your friend take turns to flip two consecutive "++" into "--".
 * The game ends when a person can no longer make a move
 * and therefore the other person will be the winner.
 * <p>
 * Write a function to compute all possible states of the string after one valid move.
 * <p>
 * For example, given s = "++++", after one move, it may become one of the following states:
 * <p>
 * [
 * "--++",
 * "+--+",
 * "++--"
 * ]
 * <p>
 * <p>
 * If there is no valid move, return an empty list [].
 */
public class No293FlipGame {

    private static List<String> mySolution(String s) {
        List<String> result = new ArrayList<>();
        char[] arr = s.toCharArray();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == '+' && arr[i - 1] == '+') {
                arr[i] = '-';
                arr[i + 1] = '-';
                result.add(new String(arr));
                arr[i] = '+';
                arr[i + 1] = '+';
            }
        }
        return result;
    }
}
