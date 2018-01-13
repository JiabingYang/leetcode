package com.yjb.leetcode.hard;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 32. Longest Valid Parentheses
 * <p>
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * <p>
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * <p>
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 */
public class LongestValidParentheses {

    public static void main(String[] args) {
        System.out.println(solution1(")()())"));//4
        System.out.println(solution1("(()"));//2
        System.out.println(solution1("("));//0
        System.out.println(solution1("()"));//2
        System.out.println(solution1("()(())"));//6
        System.out.println(solution1("(()()"));//4
        System.out.println(solution1("()(()"));//2
    }

    /**
     * https://www.programcreek.com/2014/06/leetcode-longest-valid-parentheses-java/
     * <p>
     * 14.24%
     * <p>
     * 栈中为未消掉的字符，根据最后一个未消掉的字符的位置（的下一个位置）计算长度
     */
    public static int solution1(String s) {
        // 0: (
        // 1: )
        // 栈中始终为未消掉的字符
        Stack<int[]> stack = new Stack<>();
        int max = 0;

        for (int i = 0; i <= s.length() - 1; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(new int[]{i, 0}); // 加入新的未消掉字符
                continue;
            }
            // )
            if (stack.empty() || stack.peek()[1] == 1) {
                // 栈为空 或 栈顶为)
                stack.push(new int[]{i, 1}); // 加入新的未消掉字符
            } else {
                // 栈顶为(
                stack.pop();// 消掉
                int len;
                if (stack.empty()) {
                    // 没有未消掉的字符，从0到i
                    len = i + 1;
                } else {
                    // 从栈顶（最后一个未消掉的字符）的下一个位置到i
                    len = i - stack.peek()[0];
                }
                max = Math.max(max, len);
            }
        }

        return max;
    }

    /**
     * http://blog.csdn.net/linhuanmars/article/details/20439613
     * <p>
     * 超时
     * <p>
     * 栈中为(
     * 关键在于长度的计算法则
     * <p>
     * 当前为)：
     * (1) 如果当前栈为空，则说明加上当前右括号没有合法序列（有也是之前判断过的）；
     * (2) 否则弹出栈顶元素，
     * 如果弹出后栈为空，则说明当前括号匹配，我们会维护一个合法开始的起点start，合法序列的长度即为当前元素的位置-start+1；
     * 否则如果栈内仍有元素，则当前合法序列的长度为当前栈顶元素的位置下一位到当前元素的距离，因为栈顶元素后面的括号对肯定是合法的，而且左括号出过栈了。
     * <p>
     * 因为只需要一遍扫描，算法的时间复杂度是O(n)，空间复杂度是栈的空间，最坏情况是都是左括号，所以是O(n)
     */
    public static int solution2(String s) {
        if (s == null || s.length() == 0)
            return 0;
        LinkedList<Integer> stack = new LinkedList<>();
        int start = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
                continue;
            }
            // )
            if (stack.isEmpty()) {
                start = i + 1;
            } else {
                stack.pop();
                max = stack.isEmpty() ? Math.max(max, i - start + 1) : Math.max(max, i - stack.peek());
            }
        }
        return max;
    }

    /**
     * 错误，不能处理"(()()"
     * 栈只存(，这样不能正确统计长度
     */
    public static int mySolution(String s) {
        if (s == null) {
            return 0;
        }
        int max = 0;
        int len = 0;
        int leftCount = 0;
        for (int i = 0; i <= s.length() - 1; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftCount++;
                continue;
            }
            if (leftCount == 0) {
                max = Math.max(max, len);
                len = 0;
            } else {
                leftCount--;
                len += 2;
            }
        }
        max = Math.max(max, len);
        return max;
    }
}
