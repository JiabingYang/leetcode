package com.yjb.leetcode.medium;

import java.util.Stack;

/**
 * 150. Evaluate Reverse Polish Notation
 * <p>
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * <p>
 * Some examples:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
public class No150EvaluateReversePolishNotation {

    private Stack<Integer> stack = new Stack<>();

    /**
     * beats 96.98%
     */
    public int mySolution(String[] tokens) {
        for (String token : tokens) {
            operation(token);
        }
        return stack.pop();
    }

    private void operation(String token) {
        int num1;
        int num2;
        switch (token) {
        case "+":
            num1 = stack.pop();
            num2 = stack.pop();
            stack.push(num2 + num1);
            break;
        case "-":
            num1 = stack.pop();
            num2 = stack.pop();
            stack.push(num2 - num1);
            break;
        case "*":
            num1 = stack.pop();
            num2 = stack.pop();
            stack.push(num2 * num1);
            break;
        case "/":
            num1 = stack.pop();
            num2 = stack.pop();
            stack.push(num2 / num1);
            break;
        default:
            stack.push(Integer.valueOf(token));
            break;
        }
    }
}
