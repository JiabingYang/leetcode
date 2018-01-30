package com.yjb.leetcode.hard;

import java.util.*;

/**
 * 301. Remove Invalid Parentheses
 * <p>
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * <p>
 * Note: The input string may contain letters other than the parentheses ( and ).
 * <p>
 * Examples:
 * "()())()" -> ["()()()", "(())()"]
 * "(a)())()" -> ["(a)()()", "(a())()"]
 * ")(" -> [""]
 * Credits:
 * Special thanks to @hpplayer for adding this problem and creating all test cases.
 */
public class No301RemoveInvalidParentheses {

    // ------------------------ solution1 ------------------------
    private static ArrayList<String> result = new ArrayList<>();
    private static int max = 0;

    /**
     * https://discuss.leetcode.com/topic/34875/easy-short-concise-and-fast-java-dfs-3-ms-solution
     * https://www.cnblogs.com/grandyang/p/4944875.html
     * <p>
     * Key Points:
     * 1. Generate unique answer once and only once, do not rely on Set.
     * 2. Do not need preprocess.
     * 3. Runtime 3 ms.
     * <p>
     * Explanation:
     * We all know how to check a string of parentheses is valid using a stack. Or even simpler use a counter.
     * The counter will increase when it is ‘(‘ and decrease when it is ‘)’. Whenever the counter is negative, we have more ‘)’ than ‘(‘ in the prefix.
     * <p>
     * To make the prefix valid, we need to remove a ‘)’. The problem is: which one? The answer is any one in the prefix.
     * However, if we remove any one, we will generate duplicate results, for example: s = ()), we can remove s[1] or s[2] but the result is the same ().
     * Thus, we restrict ourselves to remove the first ) in a series of consecutive )s.
     * <p>
     * After the removal, the prefix is then valid. We then call the function recursively to solve the rest of the string.
     * However, we need to keep another information: the last removal position（）. If we do not have this position,
     * we will generate duplicate by removing two ‘)’ in two steps only with a different order.
     * For this, we keep tracking the last removal position and only remove ‘)’ after that.
     * <p>
     * Now one may ask. What about ‘(‘? What if s = ‘(()(()’ in which we need remove ‘(‘?
     * The answer is: do the same from right to left.
     * However a cleverer idea is: reverse the string and reuse the code!
     * Here is the final implement in Java.
     */
    private static List<String> solution1(String s) {
        List<String> result = new ArrayList<>();
        remove(s, result, 0, 0, new char[]{'(', ')'});
        return result;
    }

    private static void remove(String s, List<String> result, int iStart, int jStart, char[] par) {
        // find 1st invalid par[1] from iStart
        // 为什么stack==0：iStart之前的字符串已经valid
        int stack = 0;
        for (int i = iStart; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) {
                stack++;
            }
            if (s.charAt(i) == par[1]) {
                stack--;
            }
            if (stack >= 0) {
                continue;
            }
            // 从上一个删除位置lastJ开始遍历到i，如果当前是par[1]，且是第一个par[1]，我们删除当前par[1]，并调用递归函数
            // 因为删不同的位置会有不同的结果，不同的结果都要加入result中，所以要尝试删每一个符合上述条件的j
            for (int j = jStart; j <= i; j++) {
                if (s.charAt(j) == par[1] && (j == jStart || s.charAt(j - 1) != par[1])) {
                    // 递归函数中s长度减1，所以将从当前i的下一个字符开始遍历，从当前j的下一个位置开始找删除的位置
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), result, i, j, par);
                }
            }
            return;// par[1]比par[0]多才会走到这个return，通过递归remove已经让整个字符串valid了。所以不需要再反转去删左括号。
        }
        // stack >= 0: try reverse s and re-do DFS; if already reversed, then add to res
        String reversed = new StringBuilder(s).reverse().toString(); // "(()"，反转变成")(("
        if (par[1] == ')') {
            // finished left to right
            remove(reversed, result, 0, 0, new char[]{')', '('}); // 变成找反向括号")("
        } else {
            // finished right to left
            // 如果此时已经是反向括号了，说明之前的左括号已经删掉了变成了")("，然后又反转了一下，变回来了"()"，那么就可以直接加入结果res了
            result.add(reversed);
        }
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4944875.html
     * <p>
     * 31.95%
     * <p>
     * bfs
     * <p>
     * 先把给定字符串排入队中，然后取出检测其是否合法，若合法直接返回，
     * 不合法的话，我们对其进行遍历，对于遇到的左右括号的字符，我们去掉括号字符生成一个新的字符串，如果这个字符串之前没有遇到过，将其排入队中，
     * 我们用哈希集合记录一个字符串是否出现过。
     * 我们对队列中的每个元素都进行相同的操作，直到队列为空还没找到合法的字符串的话，那就返回空集
     * <p>
     * 根据BFS的性质，当首次从队列中发现有效字符串时，其去掉的括号数一定是最小的。
     * 而此时，队列中存在的元素与队头元素去掉的括号数的差值 ≤ 1
     * 并且，只有与队头元素去掉括号数目相同的元素才有可能是候选答案（根据括号匹配的性质可知）。
     */
    private static List<String> solution2(String s) {
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        visited.add(s);
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        boolean found = false;
        while (!queue.isEmpty()) {
            String v = queue.poll();
            if (isValid(v)) {
                // 根据括号匹配的性质可知，即使queue中有多删一次的字符串，这种字符串也不可能valid
                // （上一层能valid，下一层括号数-1了，肯定不会valid）
                result.add(v);
                found = true;
            }
            if (found) {
                continue;
            }
            for (int i = 0; i < v.length(); i++) {
                if (v.charAt(i) != '(' && v.charAt(i) != ')') {
                    continue;
                }
                String w = v.substring(0, i) + v.substring(i + 1);
                if (!visited.contains(w)) {
                    queue.add(w);
                    visited.add(w);
                }
            }
        }
        return result;
    }

    // ------------------------ mySolution1 ------------------------

    /**
     * bfs
     * <p>
     * 对每一个删除一个字符后的s做bfs
     * 实际上直接对s做bfs就行
     * <p>
     * 使用HashSet作为result避免重复
     * <p>
     * 超时：")((())))))()(((l(((("
     */
    private static List<String> mySolution1(String s) {
        HashSet<String> result = new HashSet<>();
        if (isValid(s)) {
            result.add(s);
            return new ArrayList<>(result);
        }
        int min = s.length();
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            System.out.println("i = " + i);
            char temp = s.charAt(i);
            if (temp != '(' && temp != ')') {
                continue;
            }
            sb.deleteCharAt(i);
            String start = sb.toString();
            if (isValid(start)) {
                if (min > 1) {
                    min = 1;
                    result.clear();
                }
                result.add(start);
            } else if (min != 1) {
                // bfs
                boolean found = false;
                int count = 1;
                LinkedList<Node> queue = new LinkedList<>();
                queue.add(new Node(start, 0));
                while (!queue.isEmpty()) {
                    count++;
                    if (count > min) {
                        break;
                    }
                    int size = queue.size();
                    System.out.println("size = " + size);
                    for (int j = 0; j < size; j++) {
                        Node v = queue.poll();
                        String vStr = v.str;
                        StringBuilder sb1 = new StringBuilder(vStr);
                        for (int k = v.k; k < vStr.length(); k++) {
                            char temp1 = vStr.charAt(k);
                            if (temp1 != '(' && temp1 != ')') {
                                continue;
                            }
                            sb1.deleteCharAt(k);
                            String wStr = sb1.toString();
                            if (isValid(wStr)) {
                                if (found) {
                                    result.add(wStr);
                                } else {
                                    found = true;
                                    if (min > count) {
                                        min = count;
                                        result.clear();
                                    }
                                    result.add(wStr);
                                }
                            } else {
                                queue.add(new Node(wStr, k));
                            }
                            sb1.insert(k, temp1);
                        }
                    }
                    if (found) {
                        break;
                    }
                }
            }
            sb.insert(i, temp);
        }
        return new ArrayList<>(result);
    }

    // ------------------------ mySolution2 ------------------------

    /**
     * bfs
     * <p>
     * 直接对s做bfs
     * <p>
     * 使用HashSet作为result避免重复
     * <p>
     * 6.41%
     */
    private static List<String> mySolution2(String s) {
        HashSet<String> result = new HashSet<>();
        if (isValid(s)) {
            result.add(s);
            return new ArrayList<>(result);
        }

        int min = s.length();
        boolean found = false;
        int count = 0;
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(new Node(s, 0));
        while (!queue.isEmpty()) {
            count++;
            if (count > min) {
                break;
            }
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                Node v = queue.poll();
                String vStr = v.str;
                StringBuilder sb = new StringBuilder(vStr);
                for (int k = v.k; k < vStr.length(); k++) { // 只从上次删除位置以后开始删
                    char temp1 = vStr.charAt(k);
                    if (temp1 != '(' && temp1 != ')') {
                        continue;
                    }
                    sb.deleteCharAt(k);
                    String wStr = sb.toString();
                    if (isValid(wStr)) {
                        if (found) {
                            result.add(wStr);
                        } else {
                            found = true;
                            if (min > count) {
                                min = count;
                                result.clear();
                            }
                            result.add(wStr);
                        }
                    } else {
                        queue.add(new Node(wStr, k));
                    }
                    sb.insert(k, temp1);
                }
            }
            if (found) {
                break;
            }
        }
        return new ArrayList<>(result);
    }

    // ------------------------ 测试 ------------------------
    public static void main(String[] args) {
//        System.out.println(solution1("()())()"));
//        System.out.println(solution1("(a)())()"));
//        System.out.println(solution1(")("));
//        System.out.println(solution1(")d))"));
//        System.out.println(solution1(")()("));
//        System.out.println(solution2("))()()p"));
//        System.out.println(solution2("))n((i()")); // ["ni()","n(i)"]
        System.out.println(solution2(")((())))))()(((l((((")); // ["((()))()l"]
    }

    // ------------------------ 依赖 ------------------------

    private static boolean isValid(String s) {
        int stack = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack++;
                continue;
            }
            if (c == ')') {
                if (stack == 0) {
                    return false;
                }
                stack--;
            }
        }
        return stack == 0;
    }

    private static class Node {
        String str;
        int k;

        Node(String str, int k) {
            this.str = str;
            this.k = k;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "str='" + str + '\'' +
                    ", k=" + k +
                    '}';
        }
    }
}
