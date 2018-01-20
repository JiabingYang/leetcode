package com.yjb.leetcode.easy;

/**
 * 38. Count and Say
 * <p>
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 * <p>
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth term of the count-and-say sequence.
 * <p>
 * Note: Each term of the sequence of integers will be represented as a string.
 * <p>
 * Example 1:
 * <p>
 * Input: 1
 * Output: "1"
 * Example 2:
 * <p>
 * Input: 4
 * Output: "1211"
 */
public class No038CountAndSay {

    public static void main(String[] args) {
        for (int i = 1; i < 6; i++) {
            System.out.println(mySolution(i));
        }
    }

    private static String mySolution(int n) {
        if (n == 1) {
            return "1";
        }

        String last = mySolution(n - 1);

        StringBuilder sb = new StringBuilder();
        char start = last.charAt(0);
        int count = 1;

        for (int i = 1; i < last.length(); i++) {
            char c = last.charAt(i);
            if (c == start) {
                count++;
            } else {
                sb.append(count).append(start);
                start = c;
                count = 1;
            }
        }
        sb.append(count).append(start);

        return sb.toString();
    }

    private static String countAndSay(int n) {
        if (n <= 0)
            return null;

        String result = "1";
        int i = 1;

        while (i < n) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            for (int j = 1; j < result.length(); j++) {
                if (result.charAt(j) == result.charAt(j - 1)) {
                    count++;
                } else {
                    sb.append(count);
                    sb.append(result.charAt(j - 1));
                    count = 1;
                }
            }

            sb.append(count);
            sb.append(result.charAt(result.length() - 1));
            result = sb.toString();
            i++;
        }

        return result;
    }
}
