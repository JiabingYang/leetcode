package com.yjb.leetcode.medium;

import java.util.Arrays;

/**
 * 165. Compare Version Numbers
 * <p>
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
 * <p>
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.
 * <p>
 * Here is an example of version numbers ordering:
 * <p>
 * 0.1 < 1.1 < 1.2 < 13.37
 * Credits:
 * Special thanks to @ts for adding this problem and creating all test cases.
 */
public class No165CompareVersionNumbers {
    public static void main(String[] args) {
        System.out.println(Arrays.toString("1".split("\\.")));
        String[] strs1 = {"1"};

        strs1 = Arrays.copyOf(strs1, 2);
        for (int i = strs1.length; i < 2; i++) {
            strs1[i] = "0";
        }
        System.out.println(Arrays.toString(strs1));
    }

    /**
     * https://www.programcreek.com/2014/03/leetcode-compare-version-numbers-java/
     */
    private static int solution1(String version1, String version2) {
        String[] arr1 = version1.split("\\.");
        String[] arr2 = version2.split("\\.");
        int i = 0;
        while (i < arr1.length || i < arr2.length) {
            if (i < arr1.length && i < arr2.length) {
                if (Integer.parseInt(arr1[i]) < Integer.parseInt(arr2[i])) {
                    return -1;
                } else if (Integer.parseInt(arr1[i]) > Integer.parseInt(arr2[i])) {
                    return 1;
                }
            } else if (i < arr1.length) {
                if (Integer.parseInt(arr1[i]) != 0) {
                    return 1;
                }
            } else if (i < arr2.length) {
                if (Integer.parseInt(arr2[i]) != 0) {
                    return -1;
                }
            }
            i++;
        }
        return 0;
    }

    private static int mySolution(String version1, String version2) {
        String[] strs1 = version1.split("\\.");
        String[] strs2 = version2.split("\\.");
        int len1 = strs1.length;
        int len2 = strs2.length;
        if (len1 < len2) {
            strs1 = Arrays.copyOf(strs1, len2);
            for (int i = len1; i < len2; i++) {
                strs1[i] = "0";
            }
        }
        if (len1 > len2) {
            strs2 = Arrays.copyOf(strs2, len1);
            for (int i = len2; i < len1; i++) {
                strs2[i] = "0";
            }
        }
        for (int i = 0; i < strs1.length; i++) {
            int i1 = Integer.parseInt(strs1[i]);
            int i2 = Integer.parseInt(strs2[i]);
            if (i1 > i2) {
                return 1;
            }
            if (i1 < i2) {
                return -1;
            }
        }
        return 0;
    }
}
