package com.yjb.leetcode.medium;

import java.util.Stack;

/**
 * 227. Basic Calculator II
 * <p>
 * Implement a basic calculator to evaluate a simple expression string.
 * <p>
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
 * The integer division should truncate toward zero.
 * <p>
 * You may assume that the given expression is always valid.
 * <p>
 * Some examples:
 * "3+2*2" = 7
 * " 3/2 " = 1
 * " 3+5 / 2 " = 5
 * Note: Do not use the eval built-in library function.
 */
public class No227BasicCalculatorII {

    /**
     * 这道题是之前那道Basic Calculator 基本计算器的拓展，
     * 不同之处在于那道题的计算符号只有加和减，而这题加上了乘除，那么就牵扯到了运算优先级的问题，
     * 好在这道题去掉了括号，还适当的降低了难度，估计再出一道的话就该加上括号了。
     * 不管那么多，这道题先按木有有括号来处理，
     * 由于存在运算优先级，我们采取的措施是使用一个栈保存数字，
     * 如果该数字之前的符号是加或减，那么把当前数字压入栈中，
     * 注意如果是减号，则加入当前数字的相反数，因为减法相当于加上一个相反数。
     * 如果之前的符号是乘或除，那么从栈顶取出一个数字和当前数字进行乘或除的运算，再把结果压入栈中，
     * 那么完成一遍遍历后，所有的乘或除都运算完了，再把栈中所有的数字都加起来就是最终结果了。
     * <p>
     * 84.74%
     */
    private static int solution1(String s) {
        int res = 0;
        int num = 0;
        char operation = '+';
        Stack<Integer> nums = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c >= '0') {
                num = num * 10 + c - '0';
            }
            if ((c < '0' && c != ' ') || i == s.length() - 1) {
                if (operation == '+') {
                    nums.push(num);
                }
                if (operation == '-') {
                    nums.push(-num);
                }
                if (operation == '*' || operation == '/') {
                    int temp = nums.pop();
                    nums.push(operation == '*' ? temp * num : temp / num);
                }
                operation = c;
                num = 0;
            }
        }
        while (!nums.empty()) {
            res += nums.pop();
        }
        return res;
    }

    /**
     * sign保存数字的符号
     * 遇到数字就把数字*sign加入栈
     * 遇到+或-就改变sign
     * 遇到*或/就把弹出栈顶的数字再乘或除以*或/后面的数字然后再入栈
     * <p>
     * 51.05%
     */
    private static int mySolution(String s) {
        s = s.replaceAll(" ", "");
        int sign = 1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0') {
                int num = 0;
                while (i < s.length() && s.charAt(i) >= '0') {
                    num = num * 10 + s.charAt(i++) - '0';
                }
                i--;
                stack.push(sign * num);
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '*' || c == '/') {
                int num = stack.pop();
                int op = 0;
                while (i < s.length() - 1 && s.charAt(i + 1) >= '0') {
                    op = op * 10 + s.charAt(++i) - '0';
                }
                if (c == '*') {
                    stack.push(num * op);
                } else {
                    stack.push(num / op);
                }
            }
        }
        int sum = 0;
        while (!stack.empty()) {
            sum += stack.pop();
        }
        return sum;
    }
}
