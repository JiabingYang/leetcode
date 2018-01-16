package com.yjb.leetcode.easy;

public class No002AddTwoNumbers {

    public static void main(String[] args) {

    }

    /**
     * 小学数学算法
     * 其实没有使用carry的必要（carry可以直接存在sum中），另外使用dummy节点可以减少头节点判断
     */
    private static ListNode mySolution1(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = null;
        ListNode current = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            if (sum >= 10) {
                sum -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            if (current == null) {
                current = new ListNode(sum);
                head = current;
            } else {
                current.next = new ListNode(sum);
                current = current.next;
            }
        }
        if (carry != 0) {
            current.next = new ListNode(carry);
        }
        return head;
    }

    /**
     * 小学数学算法
     * carry可以直接存在sum中
     * 使用dummy节点减少头节点判断
     */
    private static ListNode mySolution2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int sum = 0;
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            current.next = new ListNode(sum % 10);
            current = current.next;
            sum = sum / 10;
        }
        if (sum != 0) {
            current.next = new ListNode(1);
        }
        return dummy.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
