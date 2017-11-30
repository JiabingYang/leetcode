package com.yjb.leetcode.easy;

import java.util.HashMap;

/**
 * 13. Roman to Integer
 * <p>
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 */
public class RomanToInteger {

    /**
     * beat 39.60%
     * https://my.oschina.net/Tsybius2014/blog/487325
     */
    public int tsybius2014Answer(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();

        hashMap.put('I', 1);
        hashMap.put('V', 5);
        hashMap.put('X', 10);
        hashMap.put('L', 50);
        hashMap.put('C', 100);
        hashMap.put('D', 500);
        hashMap.put('M', 1000);

        int result = 0;
        int temp = 0; //临时变量，用于判断加减
        int weight = 0; //当前读取到的罗马数字的权重
        for (int i = s.length() - 1; i >= 0; i--) {
            weight = hashMap.get(s.charAt(i));
            if (temp <= weight) {
                result += weight;
                temp = weight;
            } else {
                result -= weight;
                temp = weight;
            }
        }

        return result;
    }

    /**
     * beats 98.16%
     */
    public int leetCodeUserAnswer(String s) {
        int[] chs = new int[s.length()];
        int result = 0;

        for (int i = 0; i < chs.length; i++) {
            switch (s.charAt(i)) {
                case 'I':
                    chs[i] = 1;
                    break;
                case 'V':
                    chs[i] = 5;
                    break;
                case 'X':
                    chs[i] = 10;
                    break;
                case 'L':
                    chs[i] = 50;
                    break;
                case 'C':
                    chs[i] = 100;
                    break;
                case 'D':
                    chs[i] = 500;
                    break;
                case 'M':
                    chs[i] = 1000;
                    break;
            }
        }
        for (int i = 0; i < chs.length; i++) {
            if (i == chs.length - 1) {
                result += chs[i];
            } else if (chs[i] < chs[i + 1]) {
                result = result - chs[i];
            } else result += chs[i];

        }
        return result;
    }
}
