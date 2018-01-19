package com.yjb.leetcode.medium;

import java.util.Stack;

/**
 * 71. Simplify Path
 * <p>
 * Given an absolute path for a file (Unix-style), simplify it.
 * <p>
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 */
public class No071SimplifyPath {

    public static void main(String[] args) {
        System.out.println(mySolution("/home/").equals("/home") + "[/home]");
        System.out.println(mySolution("/a/./b/../../c/").equals("/c") + "[/c]");
        System.out.println(mySolution("/../").equals("/") + "[/]");
        System.out.println(mySolution("/home//foo/").equals("/home/foo") + "[/home/foo]");
    }

    private static String mySolution(String path) {
        String[] elements = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String element : elements) {
            if (element.equals(".") || element.equals("")) {
                continue;
            }
            if (element.equals("..")) {
                if (!stack.empty()) {
                    stack.pop();
                }
                continue;
            }
            stack.push(element);
        }
        if (stack.empty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.insert(0, stack.pop()).insert(0, "/");
        }
        return sb.toString();
    }
}
