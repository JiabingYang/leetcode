package com.yjb.leetcode.medium;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 134. Gas Station
 * <p>
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * <p>
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * <p>
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * <p>
 * Note:
 * The solution is guaranteed to be unique.
 */
public class No134GasStation {

    /**
     * https://www.programcreek.com/2014/03/leetcode-gas-station-java/
     * <p>
     * Analysis
     * <p>
     * To solve this problem, we need to understand and use the following 2 facts:
     * 1) if the sum of gas >= the sum of cost, then the circle can be completed.(重要！)
     * 2) if A can not reach C in a the sequence of A-->B-->C, then B can not make it either.(重要！)
     * <p>
     * Proof of fact 2:
     * <p>
     * If gas[A] < cost[A], then A can not even reach B.
     * So to reach C from A, gas[A] must >= cost[A].
     * Given that A can not reach C, we have gas[A] + gas[B] < cost[A] + cost[B],
     * and gas[A] >= cost[A],
     * Therefore, gas[B] < cost[B], i.e., B can not reach C.
     * In the following solution, sumRemaining tracks the sum of remaining to the current index.
     * If sumRemaining < 0, then every index between old start and current index is bad,
     * and we need to update start to be the current index.
     * You can use the following example to visualize the solution.
     * <p>
     * <img src="https://www.programcreek.com/wp-content/uploads/2014/03/leetcode-gas-station-java-400x207.png">
     */
    private static int solution1(int[] gas, int[] cost) {
        int sumRemaining = 0; // track current remaining
        int total = 0; // track total remaining
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            int remaining = gas[i] - cost[i];

            //if sum remaining of (i-1) >= 0, continue
            if (sumRemaining >= 0) {
                sumRemaining += remaining;
                //otherwise, reset start index to be current
            } else {
                sumRemaining = remaining;
                start = i;
            }
            total += remaining;
        }

        if (total >= 0) {
            return start;
        } else {
            return -1;
        }
    }

    /**
     * 暴力解法
     */
    public static int mySolution1BruteForce(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            if (mySolution1BruteForce(gas, cost, i)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean mySolution1BruteForce(int[] gas, int[] cost, int start) {
        int tank = 0;
        for (int i = 0; i < gas.length; i++) {
            int station = (start + i) % gas.length;
            tank = tank + gas[station] - cost[station];
            if (tank < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 超时
     * <p>
     * 维持一个list，一直合并。（结果不一定正确，leetcode运行超时）
     */
    private static int mySolution2(int[] gas, int[] cost) {
        if (gas.length == 0) {
            return -1;
        }
        LinkedList<Station> stations = new LinkedList<>();
        for (int i = 0; i < gas.length; i++) {
            stations.add(new Station(i, gas[i] - cost[i]));
        }
        while (stations.size() > 1) {
            Iterator<Station> it = stations.iterator();
            Station toMerge = null;
            while (it.hasNext()) {
                Station s = it.next();
                if (s.balance >= 0 || toMerge != null) {
                    if (toMerge != null) {
                        toMerge.add(s.balance);
                        it.remove();
                    } else {
                        toMerge = s;
                    }
                }
            }
        }
        Station merged = stations.getFirst();
        if (merged.balance >= 0) {
            return merged.index;
        }
        return -1;
    }

    private static class Station {
        int index;
        int balance;

        Station(int index, int balance) {
            this.index = index;
            this.balance = balance;
        }

        void add(int balance) {
            this.balance += balance;
        }
    }
}
