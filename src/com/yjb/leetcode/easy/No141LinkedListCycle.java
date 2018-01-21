package com.yjb.leetcode.easy;

/**
 * 141. Linked List Cycle
 * <p>
 * Given a linked list, determine if it has a cycle in it.
 * <p>
 * Follow up:
 * Can you solve it without using extra space?
 */
public class No141LinkedListCycle {

    /**
     * https://www.programcreek.com/2012/12/leetcode-linked-list-cycle/
     * <p>
     * Analysis
     * <p>
     * If we have 2 pointers - fast and slow. It is guaranteed that the fast one will meet the slow one if there exists a circle.
     * <p>
     * The problem can be demonstrated in the following diagram:
     * <img src="https://www.programcreek.com/wp-content/uploads/2012/12/linked-list-cycle-300x211.png">
     */
    private static boolean solution1(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    /**
     * From leetcode user
     */
    private static boolean solution2(ListNode head) {
        while (head != null && head.next != null) {
            if (head.val == Integer.MIN_VALUE)
                return true;
            head.val = Integer.MIN_VALUE;
            head = head.next;
        }
        return false;
    }

    /**
     * 快慢指针
     * 注意空表和单元素链表
     */
    private static boolean mySolution(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        do {
            if (fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            if (fast == null) {
                return false;
            }
            slow = slow.next;
        } while (slow != fast);
        return true;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
