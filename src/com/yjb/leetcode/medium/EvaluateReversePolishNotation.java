package com.yjb.leetcode.medium;

import java.util.Stack;

public class EvaluateReversePolishNotation {

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

    private void operation(String token){
        int num1;
        int num2;
        switch (token){
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
