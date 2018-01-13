package com.yjb.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. Palindrome Partitioning
 * <p>
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * <p>
 * For example, given s = "aab",
 * Return
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 */
public class No131PalindromePartitioning {

    /**
     * 深度优先搜索，回溯
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        addPalindrome(s, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     * dfs
     *
     * @param s           用于搜索的字符串
     * @param pos         从字符串的pos位置开始搜索
     * @param palindromes 临时List，存储当前得到的回文字符串
     * @param result      存储最终结果
     */
    private void addPalindrome(String s, int pos, List<String> palindromes, List<List<String>> result) {
        if (pos == s.length()) {
            result.add(new ArrayList<>(palindromes));
            return;
        }
        for (int i = pos; i < s.length(); i++) {
            String subStr = s.substring(pos, i + 1);
            if (!isPalindrome(subStr)) {
                continue;
            }
            palindromes.add(subStr);
            addPalindrome(s, i + 1, palindromes, result);
            palindromes.remove(palindromes.size() - 1);
        }
    }

    private boolean isPalindrome(String s) {
        if (s.length() < 2) {
            return true;
        }
        for (int i = 0; i <= (s.length() + 1) / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
