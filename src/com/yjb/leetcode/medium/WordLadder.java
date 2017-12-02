package com.yjb.leetcode.medium;

import java.util.*;

/**
 * 127. Word Ladder
 * <p>
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest
 * transformation sequence from beginWord to endWord, such that:
 * <p>
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * For example,
 * <p>
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * <p>
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * <p>
 * UPDATE (2017/1/20):
 * The wordList parameter had been changed to a list of strings (instead of a set of strings). Please
 * reload the code definition to get the latest changes.
 *
 * 总结：
 * 1. 本题求的是最短路径问题，应该采用bfs而不是dfs
 * 2. 查找邻接点方式的速度：ac+HashSet > isTransformable > ac
 * 如果循环的话，比较次数可能isTransfromable比ac更少，但是ac的方式如果结合HashSet的话通过散列可以少做一个循环，会快很多
 * 3. 本题不是求单源最短路径，而是求单源到一个点的最短路径，因此不用记录所有点的最短路径，而只要记录当前队列的路径值，而
 * 该路径值要想维持准确的关键在于，取队列的时候只取当前队列长度的元素，而新加入的邻接点则到下一个循环再取。通过减少不必要
 * 的路径值存储也可以加快速度
 * 4. 对循环而不用HashSet来说，HashMap和WordNode的速度差不多。但是WordNode可以有多个word相同的node,而HashMap假如WordList
 * 含有beginWord，那么WordList中的beginWord得删了或者循环的时候跳过WordList中的beginWord，因为HashMap对于beginWord和WordList
 * 中的beginWord两个只能存一个的路径值
 */
public class WordLadder {

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        String[] wordArray = {"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> wordList = new ArrayList<>(Arrays.asList(wordArray));
        System.out.println(new WordLadder().transformableWordNode(beginWord, endWord, wordList));
    }

    // ------------------------ 依赖的方法和内部类 ------------------------
    private boolean isTransfromable(String word1, String word2) {
        boolean differentAppears = false;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) == word2.charAt(i)) {
                continue;
            }
            if (differentAppears) {
                return false;
            }
            differentAppears = true;
        }
        return differentAppears;
    }

    private class WordNode {
        String word;
        int numSteps;

        WordNode(String word, int numSteps) {
            this.word = word;
            this.numSteps = numSteps;
        }
    }

    // ------------------------ acSizeHashSet ------------------------
    /**
     * beats 70.86%
     */
    public int acSizeHashSet(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(); // 使用HashSet结合ac更快，将一层循环转为散列
        set.addAll(wordList);
        int n = 0;
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            n++;
            for (int i = 0; i < size; i++) { // 保证只取当前路径值的队列中的点, 因此不需要再维护每个点的路径值
                String vStr = queue.poll();
                if (vStr.equals(endWord)) {
                    return n;
                }
                char[] vChars = vStr.toCharArray();
                for (int j = 0; j < vChars.length; j++) {
                    char temp = vChars[j];
                    for (int k = 0; k < 26; k++) {
                        char c = (char) ('a' + k);
                        if (temp == c)
                            continue;
                        vChars[j] = c;
                        String wStr = new String(vChars);
                        if (set.contains(wStr)) {
                            queue.add(wStr);
                            set.remove(wStr);
                        }
                    }
                    vChars[j] = temp;
                }
            }
        }
        return 0;
    }

    // ------------------------ acWordNodeHashSet ------------------------
    /**
     * beats 61.76%
     */
    public int acWordNodeHashSet(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>();
        set.addAll(wordList);
        Queue<WordNode> queue = new LinkedList<>();
        queue.add(new WordNode(beginWord, 1));

        while (!queue.isEmpty()) {
            WordNode v = queue.remove();
            String word = v.word;

            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char temp = arr[i];
                    if (arr[i] != c) {
                        arr[i] = c;
                    }

                    String w = new String(arr);
                    if (set.contains(w)) {
                        if (w.equals(endWord)) {
                            return v.numSteps + 1;
                        }
                        queue.add(new WordNode(w, v.numSteps + 1));
                        set.remove(w);
                    }

                    arr[i] = temp;
                }
            }
        }

        return 0;
    }

    // ------------------------ transformableWordNode ------------------------
    /**
     * beats 22.7%
     */
    public int transformableWordNode(String beginWord, String endWord, List<String> wordList) {
        Queue<WordNode> queue = new LinkedList<>();
        queue.add(new WordNode(beginWord, 1));
        while (!queue.isEmpty()) {
            WordNode v = queue.remove();
            Iterator<String> it = wordList.iterator();
            while (it.hasNext()) {
                String w = it.next();
                if (isTransfromable(v.word, w)) {
                    if (w.equals(endWord)) {
                        return v.numSteps + 1;
                    }
                    queue.add(new WordNode(w, v.numSteps + 1));
                    it.remove();
                }
            }
        }
        return 0;
    }

    // ------------------------ transformableHashMap ------------------------
    /**
     * beats 21.2%
     */
    public int transformableHashMap(String beginWord, String endWord, List<String> wordList) {
        HashMap<String, Integer> map = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        map.put(beginWord, 1);
        queue.add(beginWord);
        while (!queue.isEmpty()) {
            String v = queue.remove();
            Iterator<String> it = wordList.iterator();
            while (it.hasNext()) {
                String w = it.next();
                if (isTransfromable(v, w)) {
                    if (w.equals(endWord)) {
                        return map.get(v) + 1;
                    }
                    map.put(w, map.get(v) + 1);
                    queue.add(w);
                    it.remove();
                }
            }
        }
        return 0;
    }

    // ------------------------ transformableHashMapHashSet ------------------------
    /**
     * beats 9.91%
     */
    public int transformableHashMapHashSet(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>();// HashSet结合isTransfromable，因为是循环HashSet，反而会比循环List更慢
        set.addAll(wordList);
        HashMap<String, Integer> map = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        map.put(beginWord, 1);
        queue.add(beginWord);
        while (!queue.isEmpty()) {
            String v = queue.remove();
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                String w = it.next();
                if (isTransfromable(v, w)) {
                    if (w.equals(endWord)) {
                        return map.get(v) + 1;
                    }
                    map.put(w, map.get(v) + 1);
                    queue.add(w);
                    it.remove();
                }
            }
        }
        return 0;
    }

    // ------------------------ acWordNode ------------------------
    /**
     * 超时（ac不结合HashSet，而是循环的话会比isTransfromable慢）
     */
    public int acWordNode(String beginWord, String endWord, List<String> wordList) {
        Queue<WordNode> queue = new LinkedList<>();
        queue.add(new WordNode(beginWord, 1));

        while (!queue.isEmpty()) {
            WordNode v = queue.remove();
            String word = v.word;

            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char temp = arr[i];
                    if (arr[i] != c) {
                        arr[i] = c;
                    }

                    String w = new String(arr);
                    if (wordList.contains(w)) {
                        if (w.equals(endWord)) {
                            return v.numSteps + 1;
                        }
                        queue.add(new WordNode(w, v.numSteps + 1));
                        wordList.remove(w);
                    }

                    arr[i] = temp;
                }
            }
        }

        return 0;
    }

    // ------------------------ acHashMap ------------------------
    /**
     * 超时（ac不结合HashSet，而是循环的话会比isTransfromable慢）
     */
    public int acHashMap(String beginWord, String endWord, List<String> wordList) {
        // Map中存储每一个已知点的路径值
        HashMap<String, Integer> map = new HashMap<>();
        // 队列中存储所有新加入的邻接点
        Queue<String> queue = new LinkedList<>();
        // beginWord是第一个点，先将beginWord加入队列，并置其路径值为1
        map.put(beginWord, 1);
        queue.add(beginWord);
        // wordList中存储所有未知点，所以这里先将beginWord从wordList中删除
        if (wordList.contains(beginWord)) {
            wordList.remove(beginWord);
        }
        while (!queue.isEmpty()) {
            String vStr = queue.remove();
            char[] vChars = vStr.toCharArray();
            for (int i = 0; i < vChars.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char temp = vChars[i];
                    if (temp == c) {
                        // 如果替换的字母和原字母相同则continue，因为和原字符串相同的字符串肯定是已知的，
                        // 已从wordList中删除，不会存在于wordList，所以不用再从wordList中找该邻接点了。
                        // 这句如果不写也不会出问题，因为wordList中如果找也找不到，只是多费点时间而已。
                        continue;
                    }
                    vChars[i] = c;
                    String wStr = new String(vChars);
                    if (wordList.contains(wStr)) {
                        // 找到一个邻接点
                        if (wStr.equals(endWord)) {
                            // 如果邻接点就是目的点，那么返回当前路径值加1（1为目的点）
                            return map.get(vStr) + 1;
                        }
                        // 否则将找到的邻接点加入队列，置邻接点的路径值为当前路径值+1，并从wordList中删除（不再未知）
                        queue.add(wStr);
                        map.put(wStr, map.get(vStr) + 1);
                        wordList.remove(wStr);
                    }
                    // 恢复原字符串，用于下一轮换字母
                    vChars[i] = temp;
                }
            }
        }
        // 如果bfs完还是没有找到endWord，就返回0
        return 0;
    }

    // ------------------------ dfs ------------------------
    /**
     * Wrong solution. Must use bfs.
     */
    private int dfs(String beginWord, String endWord, List<String> wordList, List<String> ladder) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        if (isTransfromable(beginWord, endWord)) {
            System.out.println(ladder);
            return ladder.size() + 1;
        }
        for (String word : wordList) {
            if (ladder.contains(word)) {
                continue;
            }
            if (isTransfromable(beginWord, word)) {
                ladder.add(word);
                int length = dfs(word, endWord, wordList, ladder);
                if (length > 0) {
                    return length;
                }
                ladder.remove(word);
            }
        }
        return 0;
    }
}
