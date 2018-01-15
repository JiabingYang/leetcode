package com.yjb.leetcode.hard;

import java.util.*;

/**
 * 30. Substring with Concatenation of All Words
 * <p>
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 * <p>
 * For example, given:
 * s: "barfoothefoobarman"
 * words: ["foo", "bar"]
 * <p>
 * You should return the indices: [0,9].
 * (order does not matter).
 */
public class No30SubstringWithConcatenationOfAllWords {

    public static void main(String[] args) {
        System.out.println(mySolution1("barfoothefoobarman", new String[]{"foo", "bar"}));//[0, 9]
        System.out.println(mySolution1("a", new String[]{"a"})); // [0]
        System.out.println(mySolution1("barfoofoobarthe", new String[]{"bar", "foo", "the"}));// [6]
        System.out.println(mySolution1("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"}));// [6,9,12]
        System.out.println(mySolution1("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"}));// [8]
    }

    /**
     * 暴力解法
     * <p>
     * 无论如何都回溯到匹配字符串开始的下一位，然后清空Map重新匹配
     * <p>
     * 超时
     */
    private static List<Integer> mySolutionBruteForce(String s, String[] words) {
        if (s == null || words == null || words.length == 0) {
            return new ArrayList<>();
        }
        int wordLength = words[0].length();
        if (s.length() < wordLength * words.length) {
            return new ArrayList<>();
        }

        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<String> currentWords = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        int offset = (words.length - 1) * wordLength;

        int i = 0;
        int back = -1;
        while (i <= s.length() - wordLength) {
            String str = s.substring(i, i + wordLength);

            if (map.containsKey(str)) {
                if (back == -1) {
                    back = i + 1;
                }
                // add str to currentWords
                currentWords.add(str);
                // delete str from map
                int count = map.get(str);
                if (count > 1) {
                    map.put(str, count - 1);
                } else {
                    map.remove(str);
                }
                if (map.isEmpty()) {
                    // get a valid subStr
                    result.add(i - offset);
                    Iterator<String> it = currentWords.iterator();
                    while (it.hasNext()) {
                        String word = it.next();
                        map.put(word, map.getOrDefault(word, 0) + 1);
                        it.remove();
                    }
                    i = back;
                    back = -1;
                } else {
                    i += wordLength;
                }
                continue;
            }
            Iterator<String> it = currentWords.iterator();
            while (it.hasNext()) {
                String word = it.next();
                map.put(word, map.getOrDefault(word, 0) + 1);
                it.remove();
            }
            if (back != -1) {
                i = back;
                back = -1;
            } else {
                i++;
            }
        }

        return result;
    }

    /**
     * 暴力解法的基础上优化
     * <p>
     * i一直以wordLength的step前进，但是根据当前单词的内容正确改变currentWords和Map中的内容
     * <p>
     * 62.59%
     */
    private static List<Integer> mySolution1(String s, String[] words) {
        if (s == null || words == null || words.length == 0) {
            return new LinkedList<>();
        }
        int wordLength = words[0].length();
        if (s.length() < wordLength * words.length) {
            return new LinkedList<>();
        }

        HashMap<String, Integer> wordsMap = new HashMap<>();// words对应的map
        for (String word : words) {
            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        }

        List<Integer> result = new LinkedList<>();
        int offset = (words.length - 1) * wordLength;

        for (int i = 0; i < wordLength; i++) {
            HashMap<String, Integer> map = new HashMap<>(wordsMap);// 保存未取出的单词的map
            LinkedList<String> currentWords = new LinkedList<>();// 保存所有已取出的单词
            for (int j = i; j <= s.length() - wordLength; j += wordLength) {
                String str = s.substring(j, j + wordLength);

                if (map.containsKey(str)) {// 当前单词在map中
                    currentWords.add(str);
                    // delete str from map
                    int count = map.get(str);
                    if (count > 1) {
                        map.put(str, count - 1);
                    } else {
                        map.remove(str);
                    }
                    if (map.isEmpty()) {
                        // get a valid subStr
                        result.add(j - offset);
                        String word = currentWords.removeFirst();
                        map.put(word, map.getOrDefault(word, 0) + 1);
                    }
                } else if (wordsMap.containsKey(str)) {// 当前单词在currentWords中
                    int index = currentWords.indexOf(str);
                    while (index > 0) {
                        String word = currentWords.removeFirst();
                        map.put(word, map.getOrDefault(word, 0) + 1);
                        index--;
                    }
                    currentWords.removeFirst();
                    currentWords.add(str);
                } else {// 当前单词不在words中
                    Iterator<String> it = currentWords.iterator();
                    while (it.hasNext()) {
                        String word = it.next();
                        map.put(word, map.getOrDefault(word, 0) + 1);
                        it.remove();
                    }
                }
            }
        }
        return result;
    }

    /**
     * https://www.programcreek.com/2014/06/leetcode-substring-with-concatenation-of-all-words-java/
     * <p>
     * This problem is similar (almost the same) to Longest Substring Which Contains 2 Unique Characters.
     * <p>
     * Since each word in the dictionary has the same length, each of them can be treated as a single character.
     */
    public static List<Integer> solution1(String s, String[] words) {
        ArrayList<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        //frequency of words
        HashMap<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) {
            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        }

        int wordLength = words[0].length();

        for (int i = 0; i < wordLength; i++) {
            HashMap<String, Integer> map = new HashMap<>();
            int start = i; // start index of start
            int count = 0; // count total qualified words so far

            for (int j = i; j <= s.length() - wordLength; j = j + wordLength) {
                String str = s.substring(j, j + wordLength);

                if (wordsMap.containsKey(str)) {
                    //set frequency in current map
                    map.put(str, map.getOrDefault(str, 0) + 1);
                    count++;

                    while (map.get(str) > wordsMap.get(str)) {
                        String left = s.substring(start, start + wordLength);
                        map.put(left, map.get(left) - 1);

                        count--;
                        start += wordLength;
                    }

                    if (count == words.length) {
                        result.add(start); //add to result

                        //shift right and reset currentMap, count & start point
                        String left = s.substring(start, start + wordLength);
                        map.put(left, map.get(left) - 1);
                        count--;
                        start += wordLength;
                    }
                } else {
                    map.clear();
                    start = j + wordLength;
                    count = 0;
                }
            }
        }

        return result;
    }
}
