package com.yjb.leetcode.easy;

/**
 * 155. Min Stack
 * <p>
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * Example:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 * <p>
 * 关键点在于pop操作只会pop掉最新的min值，以前的min值不会pop掉，所以只要保存好所有的min值就行
 */
public class No155MinStack {

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     * <p>
     * https://www.programcreek.com/2014/02/leetcode-min-stack-java/
     */
    private static class MinStack {

        private Element top;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
        }

        public void push(int x) {
            top = new Element(x, top, top == null ? x : Math.min(top.val, x));
        }

        public void pop() {
            if (top != null) {
                top = top.next;
            }
        }

        public int top() {
            if (top == null) {
                return -1;
            }
            return top.val;
        }

        public int getMin() {
            if (top == null) {
                return -1;
            }
            return top.min;
        }

        private static class Element {
            int val;
            Element next;
            int min;

            Element(int val, Element next, int min) {
                this.val = val;
                this.next = next;
                this.min = min;
            }
        }
    }
}
