package com.yjb.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 179. Largest Number
 * <p>
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * <p>
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * <p>
 * Note: The result may be very large, so you need to return a string instead of an integer.
 * <p>
 * Credits:
 * Special thanks to @ts for adding this problem and creating all test cases.
 * <p>
 * 关键点：
 * 1. 对String排序并连接
 * 2. 处理结果的开头是一串0的情况
 * 3. 比较函数的定义：return (b + a).compareTo(a + b);
 */
public class No179LargestNumber {

    public static void main(String[] args) {
        System.out.println(solution1(new int[]{3, 30, 34, 5, 9}).equals("9534330") + "[9534330]");
        System.out.println(solution1(new int[]{0, 0}).equals("0") + "[0]");
        System.out.println(solution1(new int[]{121, 12}).equals("12121") + "[12121]");
        System.out.println(solution1(new int[]{824, 938, 1399, 5607, 6973, 5703, 9609, 4398, 8247}).equals("9609938824824769735703560743981399") + "[9609938824824769735703560743981399]");
        System.out.println(solution1(new int[]{4704, 6306, 9385, 7536, 3462, 4798, 5422, 5529, 8070, 6241, 9094, 7846, 663, 6221, 216, 6758, 8353, 3650, 3836, 8183, 3516, 5909, 6744, 1548, 5712, 2281, 3664, 7100, 6698, 7321, 4980, 8937, 3163, 5784, 3298, 9890, 1090, 7605, 1380, 1147, 1495, 3699, 9448, 5208, 9456, 3846, 3567, 6856, 2000, 3575, 7205, 2697, 5972, 7471, 1763, 1143, 1417, 6038, 2313, 6554, 9026, 8107, 9827, 7982, 9685, 3905, 8939, 1048, 282, 7423, 6327, 2970, 4453, 5460, 3399, 9533, 914, 3932, 192, 3084, 6806, 273, 4283, 2060, 5682, 2, 2362, 4812, 7032, 810, 2465, 6511, 213, 2362, 3021, 2745, 3636, 6265, 1518, 8398})
                .equals("98909827968595339456944893859149094902689398937839883538183810810780707982784676057536747174237321720571007032685668066758674466986636554651163276306626562416221603859725909578457125682552954605422520849804812479847044453428339323905384638363699366436503636357535673516346233993298316330843021297028227452732697246523622362231322812216213206020001921763154815181495141713801147114310901048") +
                "[98909827968595339456944893859149094902689398937839883538183810810780707982784676057536747174237321720571007032685668066758674466986636554651163276306626562416221603859725909578457125682552954605422520849804812479847044453428339323905384638363699366436503636357535673516346233993298316330843021297028227452732697246523622362231322812216213206020001921763154815181495141713801147114310901048]");
    }

    /**
     * https://www.programcreek.com/2014/02/leetcode-largest-number-java/
     */
    private static String solution1(int[] nums) {
        String[] arr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));

        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }

        // 处理结果的开头是一串0的情况
        while (sb.charAt(0) == '0' && sb.length() > 1) {
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }

    private static String mySolution(int[] nums) {
        String[] ns = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ns[i] = String.valueOf(nums[i]);
        }
        // 插入排序，这里可以直接用Arrays#sort
        for (int i = 1; i < ns.length; i++) {
            String temp = ns[i];
            int j;
            for (j = i - 1; j >= 0 && compare(ns[j], temp) > 0; j--) {
                ns[j + 1] = ns[j];
            }
            ns[j + 1] = temp;
        }
        StringBuilder sb = new StringBuilder();
        // 处理结果的开头是一串0的情况
        int startOfNonZero = -1;
        for (int i = 0; i < ns.length; i++) {
            String n = ns[i];
            if (!n.equals("0") && startOfNonZero == -1) {
                startOfNonZero = i;
            }
            sb.append(ns[i]);
        }
        return startOfNonZero == -1 ? "0" : sb.substring(startOfNonZero);
    }

    private static int compare(String a, String b) {
        return (b + a).compareTo(a + b);
    }
}
