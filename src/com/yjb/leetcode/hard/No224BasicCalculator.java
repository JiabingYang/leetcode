package com.yjb.leetcode.hard;

import java.util.Stack;

/**
 * 224. Basic Calculator
 * <p>
 * Implement a basic calculator to evaluate a simple expression string.
 * <p>
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 * <p>
 * You may assume that the given expression is always valid.
 * <p>
 * Some examples:
 * "1 + 1" = 2
 * " 2-1 + 2 " = 3
 * "(1+(4+5+2)-3)+(6+8)" = 23
 * Note: Do not use the eval built-in library function.
 */
public class No224BasicCalculator {

    public static void main(String[] args) {
        System.out.println(solution1("4+5+2") + "[11]");
        System.out.println(solution1("1 + 1") + "[2]");
        System.out.println(solution1(" 2-1 + 2 ") + "[3]");
        System.out.println(solution1("(1+(4+5+2)-3)+(6+8)") + "[23]");
        System.out.println(solution1("2-(5-6)") + "[3]");
        System.out.println(solution1("(7)-(0)+(4)") + "[11]");
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4570699.html
     * <p>
     * 我们需要一个栈来辅助计算，用个变量sign来表示当前的符号，
     * 我们遍历给定的字符串s，如果遇到了数字，由于可能是个多位数，所以我们要用while循环把之后的数字都读进来，
     * 然后用sign*num来更新结果res；如果遇到了加号，则sign赋为1，如果遇到了符号，则赋为-1；
     * 如果遇到了左括号，则把当前结果res和符号sign压入栈，res重置为0，sign重置为1；
     * 如果遇到了右括号，结果res乘以栈顶的符号，栈顶元素出栈，结果res加上栈顶的数字，栈顶元素出栈。
     * <p>
     * 69.68%
     */
    private static int solution1(String s) {
        int result = 0;
        int sign = 1;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0') {
                int num = 0;
                while (i < s.length() && s.charAt(i) >= '0') {
                    num = 10 * num + s.charAt(i++) - '0';
                }
                result += sign * num;
                i--;
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (c == ')') {
                result *= stack.pop();
                result += stack.pop();
            }
        }

        return result;
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4570699.html
     * <p>
     * 下面这种方法和上面的基本一样，只不过对于数字的处理略微不同，
     * 上面的方法是连续读入数字，而这种方法是使用了一个变量来保存读入的num，
     * 所以在遇到其他字符的时候，都要用sign*num来更新结果res。
     */
    private static int solution2(String s) {
        int result = 0;
        int sign = 1;
        Stack<Integer> stack = new Stack<>();
        int num = 0;

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c >= '0') {
                num = 10 * num + (c - '0');
            } else if (c == '+' || c == '-') {
                result += sign * num;
                num = 0;
                sign = (c == '+') ? 1 : -1;
            } else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (c == ')') {
                result += sign * num;
                num = 0;
                result *= stack.pop();
                result += stack.pop();
            }
        }
        result += sign * num;
        return result;
    }

    /**
     * 18.70%
     */
    private static int mySolution(String s) {
        Stack<Integer> stack = new Stack<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                int start = stack.pop();
                String sum = String.valueOf(basicCalculate(arr, start + 1, i - 1));
                for (int j = 0; j < sum.length(); j++) {
                    arr[start + j] = sum.charAt(j);
                }
                arr[i] = ' ';
            }
        }
        return basicCalculate(arr, 0, arr.length - 1);
    }

    private static int basicCalculate(char[] charArray, int start, int end) {
        int result = 0;
        Boolean plus = true;
        Integer num = null;
        for (int i = start; i <= end; i++) {
            char c = charArray[i];
            if (c == ' ') {
                continue;
            }
            if (c == '+' || c == '-') {
                if (num == null) {
                    plus = plus == (c == '+');
                } else {
                    if (plus) {
                        result += num;
                    } else {
                        result -= num;
                    }
                    plus = c == '+';
                    num = null;
                }
            } else {
                if (num == null) {
                    num = c - '0';
                } else {
                    num = num * 10 + (c - '0');
                }
            }
            charArray[i] = ' ';
        }
        if (plus) {
            result += num;
        } else {
            result -= num;
        }
        return result;
    }
}
