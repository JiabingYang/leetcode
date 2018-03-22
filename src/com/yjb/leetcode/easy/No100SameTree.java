package com.yjb.leetcode.easy;

import java.util.Stack;

/**
 * 100. Same Tree
 * <p>
 * Given two binary trees, write a function to check if they are the same or not.
 * <p>
 * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input:
 * 1         1
 * / \       / \
 * 2   3     2   3
 * <p>
 * [1,2,3],   [1,2,3]
 * <p>
 * Output: true
 * Example 2:
 * <p>
 * Input:
 * 1         1
 * /           \
 * 2             2
 * <p>
 * [1,2],     [1,null,2]
 * <p>
 * Output: false
 * Example 3:
 * <p>
 * Input:
 * 1         1
 * / \       / \
 * 2   1     1   2
 * <p>
 * [1,2,1],   [1,1,2]
 * <p>
 * Output: false
 */
public class No100SameTree {

    /* ---------------- 递归 -------------- */

    /**
     * 参考:
     * http://blog.csdn.net/u012162613/article/details/41212315
     * <p>
     * 递归
     */
    private static boolean solution1(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return solution1(p.left, q.left) && solution1(p.right, q.right);
    }

    /* ---------------- 栈 -------------- */

    /**
     * 参考:
     * http://blog.csdn.net/u012162613/article/details/41212315
     * <p>
     * 栈
     */
    private static boolean solution2(TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(p);
        stack.push(q);
        while (!stack.empty()) {
            p = stack.pop();
            q = stack.pop();
            if (p == null && q == null) {
                continue;
            }
            if (p == null || q == null) {
                return false;
            }
            if (p.val != q.val) {
                return false;
            }
            stack.push(p.left);
            stack.push(q.left);
            stack.push(p.right);
            stack.push(q.right);
        }
        return true;
    }

    /* ---------------- 依赖 -------------- */
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
