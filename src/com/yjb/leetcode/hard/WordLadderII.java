package com.yjb.leetcode.hard;

import java.util.*;

/**
 * 126. Word Ladder II
 * <p>
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 * <p>
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * For example,
 * <p>
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * Return
 * [
 * ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 * <p>
 * Note:
 * Return an empty list if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * <p>
 * UPDATE (2017/1/20):
 * The wordList parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
 */
public class WordLadderII {

    public static void main(String[] args) {
        String beginWord = "hot";
        String endWord = "dog";
        List<String> wordList = new LinkedList<>(Arrays.asList("hot", "dog"));
        System.out.println(new WordLadderII().mySolution(beginWord, endWord, wordList));
    }

    /**
     * 336ms
     * beats 23.6%
     */
    public List<List<String>> mySolution(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new LinkedList<>();
        if (!wordList.contains(endWord)) {
            return result;
        }

        Set<String> wordSet = new HashSet<>(wordList);
        Queue<WordNode> queue = new LinkedList<>();
        queue.add(new WordNode(beginWord, null));

        Set<String> wStrs = new HashSet<>();
//        wStrs.add(beginWord);

        boolean found = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            wordSet.removeAll(wStrs); // Queue中已访问的点不会再作为下一个邻接点了
            wStrs.clear();
            for (int i = 0; i < size; i++) {
                WordNode v = queue.remove();
                String vStr = v.word;
                char[] vChars = vStr.toCharArray();

                outer:
                for (int j = 0; j < vChars.length; j++) {
                    char temp = vChars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (temp == c) {
                            continue;
                        }
                        vChars[j] = c;
                        String wStr = new String(vChars);
                        if (wordSet.contains(wStr)) {
                            if (wStr.equals(endWord)) {
                                result.add(getPath(wStr, v));
                                found = true;
                                vChars[j] = temp;
                                continue outer;
                            }
                            if (!found) {
                                queue.add(new WordNode(wStr, v));
                                wStrs.add(wStr);
                            }
                        }
                    }
                    vChars[j] = temp;
                }
            }
            if (found) {
                break;
            }
        }
        return result;
    }

    private List<String> getPath(String word, WordNode pre) {
        LinkedList<String> path = new LinkedList<>();
        path.add(word);
        while (pre != null) {
            path.addFirst(pre.word);
            pre = pre.pre;
        }
        return path;
    }

    private class WordNode {
        String word;
        WordNode pre;

        WordNode(String word, WordNode pre) {
            this.word = word;
            this.pre = pre;
        }

        @Override
        public String toString() {
            LinkedList<String> path = new LinkedList<>();
            WordNode endNode = this;
            while (endNode != null) {
                path.addFirst(endNode.word);
                endNode = endNode.pre;
            }
            return path.toString();
        }
    }
}
