package com.yjb.leetcode.medium;

/**
 * 299. Bulls and Cows
 * <p>
 * You are playing the following Bulls and Cows game with your friend:
 * You write down a number and ask your friend to guess what the number is.
 * Each time your friend makes a guess, you provide a hint that indicates
 * how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and
 * how many digits match the secret number but locate in the wrong position (called "cows").
 * Your friend will use successive guesses and hints to eventually derive the secret number.
 * <p>
 * For example:
 * <p>
 * Secret number:  "1807"
 * Friend's guess: "7810"
 * Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
 * Write a function to return a hint according to the secret number and friend's guess,
 * use A to indicate the bulls and B to indicate the cows. In the above example, your function should return "1A3B".
 * <p>
 * Please note that both secret number and friend's guess may contain duplicate digits, for example:
 * <p>
 * Secret number:  "1123"
 * Friend's guess: "0111"
 * In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return "1A1B".
 * You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
 * <p>
 * Credits:
 * Special thanks to @jeantimex for adding this problem and creating all test cases.
 */
public class No299BullsAndCows {

    public static void main(String[] args) {
        System.out.println(mySolution("1807", "7810") + "[1A3B]");
        System.out.println(mySolution("1123", "0111") + "[1A1B]");
        System.out.println(mySolution("11", "11") + "[2A0B]");
        System.out.println(mySolution("1122", "2211") + "[0A4B]");
        System.out.println(mySolution("1234", "0111") + "[0A1B]");
        System.out.println(mySolution("11", "10") + "[1A0B]");
    }

    private static String mySolution(String secret, String guess) {
        int[] sCounts = new int[10]; // 存放secret中没消掉的字符
        int[] gCounts = new int[10]; // 存放guess中没消掉的字符
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);
            if (s == g) {
                bulls++;
            } else {
                sCounts[s - '0']++;
                gCounts[g - '0']++;
            }
        }
        for (int i = 0; i < 10; i++) {
            cows += Math.min(sCounts[i], gCounts[i]);
        }
        return bulls + "A" + cows + "B";
    }
}
