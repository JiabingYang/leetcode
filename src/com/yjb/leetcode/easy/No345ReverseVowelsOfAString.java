package com.yjb.leetcode.easy;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 345. Reverse Vowels of a String
 * <p>
 * Write a function that takes a string as input and reverse only the vowels of a string.
 * <p>
 * Example 1:
 * Given s = "hello", return "holle".
 * <p>
 * Example 2:
 * Given s = "leetcode", return "leotcede".
 * <p>
 * Note:
 * The vowels does not include the letter "y".
 */
public class No345ReverseVowelsOfAString {

    private static String mySolution(String s) {
        int i = 0;
        int j = s.length() - 1;
        char[] arr = s.toCharArray();
        HashSet<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        while (i < j) {
            if (!set.contains(arr[i])) {
                i++;
                continue;
            }
            if (!set.contains(arr[j])) {
                j--;
                continue;
            }
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return new String(arr);
    }
}
