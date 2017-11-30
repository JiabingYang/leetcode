package com.yjb.leetcode.easy;


/**
 * 21. Merge Two Sorted Lists
 * <p>
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 */
public class MergeTwoSortedLists {

    /**
     * beats 48.07%
     */
    public ListNode mySolution(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode first = null;
        ListNode last = null;
        while (true) {
            boolean l1LeL2 = l1.val <= l2.val;
            if (l1LeL2) {
                if (first == null) {
                    first = last = l1;
                } else {
                    last.next = l1;
                    last = last.next;
                }
                if (l1.next == null) {
                    last.next = l2;
                    break;
                }
                l1 = l1.next;
            } else {
                if (first == null) {
                    first = last = l2;
                } else {
                    last.next = l2;
                    last = last.next;
                }
                if (l2.next == null) {
                    last.next = l1;
                    break;
                }
                l2 = l2.next;
            }
        }
        return first;
    }

    /**
     * beats 26.79%
     */
    public ListNode leetCodeUserAnswer(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode head = null;
        if (l1.val < l2.val) {
            head = l1;
            head.next = leetCodeUserAnswer(l1.next, l2);
        } else {
            head = l2;
            head.next = leetCodeUserAnswer(l1, l2.next);
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
