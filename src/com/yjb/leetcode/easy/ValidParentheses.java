package com.yjb.leetcode.easy;

import java.util.Stack;

/**
 * 20. Valid Parentheses
 * <p>
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
public class ValidParentheses {

    /**
     * beats 51.51%
     */
    public boolean mySolution(String s) {
        if (s == null || s.equals(""))
            return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }
            // right
            if (stack.empty()) {
                return false;
            }
            if (stack.peek() != getLeft(c)) {
                return false;
            }
            stack.pop();
        }
        return stack.empty();
    }

    public char getLeft(char right) {
        switch (right) {
            case ')':
                return '(';
            case '}':
                return '{';
            case ']':
                return '[';
            default:
                throw new RuntimeException("invalid right char");
        }
    }
}
