package com.yjb.leetcode.easy;

import java.util.LinkedList;

/**
 * 346. Moving Average from Data Stream
 * <p>
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 * <p>
 * For example,
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */
public class No346MovingAverageFromDataStream {

    public static void main(String[] args) {
        MySolution m = new MySolution(3);
        System.out.println(m.next(1) + "[1.0]");
        System.out.println(m.next(10) + "[5.5]");
        System.out.println(m.next(3) + "[4.666666666666667]");
        System.out.println(m.next(5) + "[6.0]");
    }

    private static class MySolution {

        private LinkedList<Integer> queue = new LinkedList<>();
        private int size;
        private int sum;

        /**
         * Initialize your data structure here.
         */
        public MySolution(int size) {
            this.size = size;
        }

        public double next(int val) {
            int qSize = queue.size();
            if (qSize < size) {
                queue.offer(val);
                sum += val;
                return sum / (qSize + 1.0);
            }
            int removed = queue.poll();
            queue.offer(val);
            sum = sum - removed + val;
            return sum * 1.0 / size;
        }
    }
}
