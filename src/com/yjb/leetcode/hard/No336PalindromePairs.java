package com.yjb.leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 336. Palindrome Pairs
 * <p>
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 * <p>
 * Example 1:
 * Given words = ["bat", "tab", "cat"]
 * Return [[0, 1], [1, 0]]
 * The palindromes are ["battab", "tabbat"]
 * Example 2:
 * Given words = ["abcd", "dcba", "lls", "s", "sssll"]
 * Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 * The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 * Credits:
 * Special thanks to @dietpepsi for adding this problem and creating all test cases.
 */
public class No336PalindromePairs {

    public static void main(String[] args) {
        System.out.println(mySolution(new String[]{"bat", "tab", "cat"}));
        System.out.println(mySolution(new String[]{"abcd", "dcba", "lls", "s", "sssll"}));
    }

    /**
     * 84.69%
     * <p>
     * 先用map做word和index的映射
     * <p>
     * 然后遍历每一个word，找出它的前缀和后缀，如果得到的前缀或后缀和原字符串不一样且map中存在一样的前缀或后缀就得到一组结果
     */
    private static List<List<Integer>> mySolution(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words.length < 2) {
            return result;
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            map.put(word, i);
        }
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            // 找前缀
            for (int j = word.length(); j >= 0; j--) {
                if (isPalindrome(word.substring(0, j))) {
                    String prefix = new StringBuilder(word.substring(j)).reverse().toString();
                    if (!word.equals(prefix) && map.containsKey(prefix)) {
                        List<Integer> pair = new ArrayList<>();
                        pair.add(map.get(prefix));
                        pair.add(i);
                        result.add(pair);
                    }
                }
            }
            // 找后缀
            for (int j = 0; j < word.length(); j++) { // 没有加入j==word.length()的情况，因为这种情况在前缀的j==0的情况中考虑了
                if (isPalindrome(word.substring(j))) {
                    String suffix = new StringBuilder(word.substring(0, j)).reverse().toString();
                    if (!word.equals(suffix) && map.containsKey(suffix)) {
                        List<Integer> pair = new ArrayList<>();
                        pair.add(i);
                        pair.add(map.get(suffix));
                        result.add(pair);
                    }
                }
            }
        }
        return result;
    }

    /**
     * https://www.programcreek.com/2014/05/leetcode-palindrome-pairs-java/
     */
    private static List<List<Integer>> solution(String[] words) {
        List<List<Integer>> result = new ArrayList<>();

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            String s = words[i];

            //if the word is a palindrome, get index of ""
            if (isPalindrome(s)) {
                if (map.containsKey("")) {
                    if (map.get("") != i) {
                        ArrayList<Integer> l = new ArrayList<>();
                        l.add(i);
                        l.add(map.get(""));
                        result.add(l);

                        l = new ArrayList<>();

                        l.add(map.get(""));
                        l.add(i);
                        result.add(l);
                    }

                }
            }

            //if the reversed word exists, it is a palindrome
            String reversed = new StringBuilder(s).reverse().toString();
            if (map.containsKey(reversed)) {
                if (map.get(reversed) != i) {
                    ArrayList<Integer> l = new ArrayList<>();
                    l.add(i);
                    l.add(map.get(reversed));
                    result.add(l);
                }
            }

            for (int k = 1; k < s.length(); k++) {
                String left = s.substring(0, k);
                String right = s.substring(k);

                //if left part is palindrome, find reversed right part
                if (isPalindrome(left)) {
                    String reversedRight = new StringBuilder(right).reverse().toString();
                    if (map.containsKey(reversedRight)) {
                        if (map.get(reversedRight) != i) {
                            ArrayList<Integer> l = new ArrayList<>();
                            l.add(map.get(reversedRight));
                            l.add(i);
                            result.add(l);
                        }
                    }
                }

                //if right part is a palindrome, find reversed left part
                if (isPalindrome(right)) {
                    String reversedLeft = new StringBuilder(left).reverse().toString();
                    if (map.containsKey(reversedLeft)) {
                        if (map.get(reversedLeft) != i) {

                            ArrayList<Integer> l = new ArrayList<>();
                            l.add(i);
                            l.add(map.get(reversedLeft));
                            result.add(l);
                        }
                    }
                }
            }
        }

        return result;
    }

    private static boolean isPalindrome(String s) {
        if (s.length() <= 1) {
            return true;
        }
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
