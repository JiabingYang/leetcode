package com.yjb.leetcode.easy;

/**
 * 67. Add Binary
 * <p>
 * Given two binary strings, return their sum (also a binary string).
 * <p>
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 */
public class No067AddBinary {

    public static void main(String[] args) {
        System.out.println(mySolution1("11", "1"));// 100
        System.out.println(mySolution1("0", "0")); //0
        System.out.println(mySolution1("1", "1")); //10
        System.out.println(mySolution1("11", "0")); //11
        System.out.println(mySolution1("1", "0")); //10
        System.out.println(mySolution1("100", "110010")); //110110
    }

    /**
     * faster
     */
    public static String mySolution1(String a, String b) {
        if (a.length() < b.length()) {
            String c = a;
            a = b;
            b = c;
        }
        char carry = '0';
        int offset = a.length() - b.length();
        StringBuilder sb = new StringBuilder();
        for (int i = b.length() - 1; i >= 0; i--) {
            int count = 0;
            if (a.charAt(offset + i) == '1') {
                count++;
            }
            if (b.charAt(i) == '1') {
                count++;
            }
            if (carry == '1') {
                count++;
            }
            switch (count) {
                case 0:
                    sb.insert(0, '0');
                    carry = '0';
                    break;
                case 1:
                    sb.insert(0, '1');
                    carry = '0';
                    break;
                case 2:
                    sb.insert(0, '0');
                    carry = '1';
                    break;
                case 3:
                    sb.insert(0, '1');
                    carry = '1';
                    break;
            }
        }
        for (int i = offset - 1; i >= 0; i--) {
            int count = 0;
            if (a.charAt(i) == '1') {
                count++;
            }
            if (carry == '1') {
                count++;
            }
            switch (count) {
                case 0:
                    sb.insert(0, '0');
                    carry = '0';
                    break;
                case 1:
                    sb.insert(0, '1');
                    carry = '0';
                    break;
                case 2:
                    sb.insert(0, '0');
                    carry = '1';
                    break;
            }
        }
        if (carry == '1') {
            sb.insert(0, '1');
        }
        return sb.toString();
    }

    /**
     * shorter
     */
    public static String mySolution2(String a, String b) {
        if (a.length() < b.length()) {
            String c = a;
            a = b;
            b = c;
        }
        int carry = 0;
        int offset = a.length() - b.length();
        StringBuilder sb = new StringBuilder();
        for (int i = b.length() - 1; i >= 0; i--) {
            int count = 0;
            if (a.charAt(offset + i) == '1') {
                count++;
            }
            if (b.charAt(i) == '1') {
                count++;
            }
            count += carry;
            carry = count >= 2 ? 1 : 0;
            sb.insert(0, count % 2);
        }
        for (int i = offset - 1; i >= 0; i--) {
            int count = 0;
            if (a.charAt(i) == '1') {
                count++;
            }
            count += carry;
            carry = count >= 2 ? 1 : 0;
            sb.insert(0, count % 2);
        }
        if (carry == 1) {
            sb.insert(0, '1');
        }
        return sb.toString();
    }
}
