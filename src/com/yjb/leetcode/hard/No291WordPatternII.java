package com.yjb.leetcode.hard;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 291. Word Pattern II
 * <p>
 * Given a pattern and a string str, find if str follows the same pattern.
 * <p>
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
 * <p>
 * Examples:
 * <p>
 * pattern = "abab", str = "redblueredblue" should return true.
 * pattern = "aaaa", str = "asdasdasdasd" should return true.
 * pattern = "aabb", str = "xyzabcxzyabc" should return false.
 * <p>
 * Notes:
 * You may assume both pattern and str contains only lowercase letters.
 */
public class No291WordPatternII {

    public static void main(String[] args) {
        System.out.println(mySolution("abab", "redblueredblue"));
        System.out.println(mySolution("aaaa", "asdasdasdasd"));
        System.out.println(mySolution("aabb", "xyzabcxzyabc"));
    }

    /**
     * https://www.programcreek.com/2014/07/leetcode-word-pattern-ii-java/
     * <p>
     * 自顶向下遍历+回溯+containsValue
     * <p>
     * map存放已经匹配了的映射，
     * 如果当前k的映射匹配成功就加入map再递归地匹配后续的字符串，
     * 如果当前k的映射匹配失败就测试for中的下一个k映射，假如当前map情况下，所有k都失败那么回溯到上一个map。
     * 但是用了containsValue，时间复杂度高
     * <p>
     * 时间 (n^n)^2 跟第二种相比，n^n中的每一步都用了O(n)时间，所以相当于n^n * n^n，即(n^n)^2
     */
    private static boolean solution1(String pattern, String str) {
        if (pattern.length() == 0 && str.length() == 0)
            return true;
        if (pattern.length() == 0)
            return false;

        HashMap<Character, String> map = new HashMap<>();

        return solution1Helper(pattern, str, 0, 0, map);
    }

    private static boolean solution1Helper(String pattern, String str, int i, int j, HashMap<Character, String> map) {
        if (i == pattern.length() && j == str.length()) {
            return true;
        }
        if (i >= pattern.length() || j >= str.length())
            return false;

        char c = pattern.charAt(i);
        for (int k = j + 1; k <= str.length(); k++) {
            String sub = str.substring(j, k);
            if (!map.containsKey(c) && !map.containsValue(sub)) {
                map.put(c, sub);
                if (solution1Helper(pattern, str, i + 1, k, map))
                    return true;
                map.remove(c);
            } else if (map.containsKey(c) && map.get(c).equals(sub)) {
                if (solution1Helper(pattern, str, i + 1, k, map))
                    return true;
            }
        }

        return false;
    }

    /**
     * https://www.programcreek.com/2014/07/leetcode-word-pattern-ii-java/
     * <p>
     * 自顶向下遍历+回溯+HashSet
     * <p>
     * 相比于solution1，用HashSet替代containsValue，优化时间复杂度
     * <p>
     * The time complexity then is f(n) = n*(n-1)*... *1=n^n.
     */
    private static boolean solution2(String pattern, String str) {
        if (pattern.length() == 0 && str.length() == 0)
            return true;
        if (pattern.length() == 0)
            return false;

        HashMap<Character, String> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        return solution2Helper(pattern, str, 0, 0, map, set);
    }

    private static boolean solution2Helper(String pattern, String str, int i, int j, HashMap<Character, String> map, HashSet<String> set) {
        if (i == pattern.length() && j == str.length()) {
            return true;
        }
        if (i >= pattern.length() || j >= str.length())
            return false;

        char c = pattern.charAt(i);
        for (int k = j + 1; k <= str.length(); k++) {
            String sub = str.substring(j, k);
            if (!map.containsKey(c) && !set.contains(sub)) {
                map.put(c, sub);
                set.add(sub);
                if (solution2Helper(pattern, str, i + 1, k, map, set))
                    return true;
                map.remove(c);
                set.remove(sub);
            } else if (map.containsKey(c) && map.get(c).equals(sub)) {
                if (solution2Helper(pattern, str, i + 1, k, map, set))
                    return true;
            }
        }

        return false;
    }

    /**
     * 分治+containsValue
     * <p>
     * 用递归得到取第一个word和letter以后的部分的HashMap。然后看第一个word和letter能否在这个HashMap下匹配。
     * 这种递归方法一般是用递归获取到一个结果再和当前的部分合并得到当前的结果。
     * 这种方法我认为更像链表合并、归并排序那种递归，而不像回溯（回溯应该是正向的遍历，在遍历过程中维护一个参数，solution1、solution2那样）
     */
    private static boolean mySolution(String pattern, String str) {
        if (pattern.length() <= 1) {
            return pattern.length() == str.length();
        }
        return mySolutionCore(pattern, str) != null;
    }

    private static HashMap<Character, String> mySolutionCore(String pattern, String str) {
        if (pattern.length() > str.length()) {
            return null;
        }
        if (pattern.length() == 1) {
            HashMap<Character, String> result = new HashMap<>();
            result.put(pattern.charAt(0), str);
            return result;
        }
        char firstLetter = pattern.charAt(0);
        String pSuffix = pattern.substring(1);
        for (int i = 1; i < str.length(); i++) {
            HashMap<Character, String> suffixMap = mySolutionCore(pSuffix, str.substring(i));
            if (suffixMap == null) {
                continue;
            }
            String firstWord = str.substring(0, i);
            if (suffixMap.containsKey(firstLetter)) {
                if (!suffixMap.get(firstLetter).equals(firstWord)) {
                    continue;
                }
            } else if (suffixMap.containsValue(firstWord)) {
                continue;
            }
            suffixMap.put(firstLetter, firstWord);
            return suffixMap;
        }
        return null;
    }
}
