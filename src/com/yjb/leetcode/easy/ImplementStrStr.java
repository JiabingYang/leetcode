package com.yjb.leetcode.easy;

/**
 * Implement strStr().
 * <p>
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */
public class ImplementStrStr {

    /**
     * beats 45.51%
     */
    public int bruteForce(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() == 0)
            return 0;
        if (needle.length() > haystack.length())
            return -1;
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            boolean successFlag = true;
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    successFlag = false;
                    break;
                }
            }
            if (successFlag)
                return i;
        }
        return -1;
    }
}
