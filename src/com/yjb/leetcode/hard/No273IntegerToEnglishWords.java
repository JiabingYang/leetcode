package com.yjb.leetcode.hard;

import java.util.HashMap;

/**
 * 273. Integer to English Words
 * <p>
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 * <p>
 * For example,
 * 123 -> "One Hundred Twenty Three"
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */
public class No273IntegerToEnglishWords {

    public static void main(String[] args) {
        System.out.println(mySolution(19).equals("Nineteen") + "[Nineteen]");
        System.out.println(mySolution(100).equals("One Hundred") + "[One Hundred]");
        System.out.println(mySolution(123).equals("One Hundred Twenty Three") + "[One Hundred Twenty Three]");
        System.out.println(mySolution(12345).equals("Twelve Thousand Three Hundred Forty Five") + "[Twelve Thousand Three Hundred Forty Five]");
        System.out.println(mySolution(1234567).equals("One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven") + "[One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven]");
    }

    // ------------------------ mySolution ------------------------

    private static final String[] ARR_1 = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] ARR_2 = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] ARR_3 = {"Thousand", "Million", "Billion", "Trillion"};

    /**
     * 归并
     * <p>
     * 以3位为间隔，把数字分为最高的间隔（high）和后面所有间隔组成的数字（low）。
     * 基本情况是数字位数<=3（num < 1000）的情况。
     * 连接字符串的时候加上空格。
     * <p>
     * 93.29%
     */
    private static String mySolution(int num) {
        // 0-19
        if (num < 20) {
            return ARR_1[num];
        }
        // 20-99
        if (num < 100) {
            if (num % 10 == 0) {
                return ARR_2[num / 10 - 2];
            }
            return ARR_2[num / 10 - 2] + " " + ARR_1[num % 10];
        }
        // 100-999
        if (num < 1000) {
            String result = ARR_1[num / 100] + " Hundred";
            if (num % 100 != 0) {
                result += " " + mySolution(num % 100);
            }
            return result;
        }
        // >999
        // 计算数字长度
        int len = 0;
        int temp = num;
        do {
            len++;
            temp /= 10;
        } while (temp > 0);
        // 计算数字高位部分和低位部分
        int level = (len - 1) / 3;
        int base = (int) Math.pow(1000, level);
        int high = num / base;
        int low = num - base * high;
        // 归并得到结果
        String result = mySolution(high) + " " + ARR_3[level - 1];
        if (low != 0) {
            result += " " + mySolution(low);
        }
        return result;
    }

    // ------------------------ solution1 ------------------------
    private static final HashMap<Integer, String> map = new HashMap<>();

    /**
     * https://www.programcreek.com/2012/11/top-10-algorithms-for-coding-interview/
     */
    private static String solution1(int num) {
        fillMap();
        StringBuilder sb = new StringBuilder();

        if (num == 0) {
            return map.get(0);
        }

        if (num >= 1000000000) {
            int extra = num / 1000000000;
            sb.append(convert(extra)).append(" Billion");
            num = num % 1000000000;
        }

        if (num >= 1000000) {
            int extra = num / 1000000;
            sb.append(convert(extra)).append(" Million");
            num = num % 1000000;
        }

        if (num >= 1000) {
            int extra = num / 1000;
            sb.append(convert(extra)).append(" Thousand");
            num = num % 1000;
        }

        if (num > 0) {
            sb.append(convert(num));
        }

        return sb.toString().trim();
    }

    private static String convert(int num) {

        StringBuilder sb = new StringBuilder();

        if (num >= 100) {
            int numHundred = num / 100;
            sb.append(" ").append(map.get(numHundred)).append(" Hundred");
            num = num % 100;
        }

        if (num > 0) {
            if (num <= 20) {
                sb.append(" ").append(map.get(num));
            } else {
                int numTen = num / 10;
                sb.append(" ").append(map.get(numTen * 10));

                int numOne = num % 10;
                if (numOne > 0) {
                    sb.append(" ").append(map.get(numOne));
                }
            }
        }

        return sb.toString();
    }

    private static void fillMap() {
        map.put(0, "Zero");
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        map.put(10, "Ten");
        map.put(11, "Eleven");
        map.put(12, "Twelve");
        map.put(13, "Thirteen");
        map.put(14, "Fourteen");
        map.put(15, "Fifteen");
        map.put(16, "Sixteen");
        map.put(17, "Seventeen");
        map.put(18, "Eighteen");
        map.put(19, "Nineteen");
        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");
        map.put(60, "Sixty");
        map.put(70, "Seventy");
        map.put(80, "Eighty");
        map.put(90, "Ninety");
    }
}
