package com.yjb.leetcode.medium;

import java.util.*;

/**
 * 56. Merge Intervals
 * <p>
 * Given a collection of intervals, merge all overlapping intervals.
 * <p>
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */
public class No056MergeIntervals {

    public static void main(String[] args) {
        List<Interval> intervals = new LinkedList<>(Arrays.asList(
                new Interval(1, 3),
                new Interval(2, 6),
                new Interval(8, 10),
                new Interval(15, 18)
        ));
        System.out.println(mySolution(intervals));
    }

    // ------------------------ mySolution ------------------------

    /**
     * 40.09%
     * <p>
     * 排序时只保证start从小到大
     */
    public static List<Interval> mySolution(List<Interval> intervals) {
        // 首先将intervals按start从小到大排序
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        // result中始终保证最后一个元素的start是最大的，
        // 从而merge的时候只需要将result中最后一个元素和intervals中第一个元素合并即可，
        // 每次从intervals中取出第一个元素，直到intervals为空。
        LinkedList<Interval> result = new LinkedList<>();
        while (!intervals.isEmpty()) {
            Interval intervalToMerge = intervals.remove(0);
            if (result.isEmpty()) {
                result.add(intervalToMerge);
                continue;
            }
            Interval lastInterval = result.getLast();
            if (lastInterval.end < intervalToMerge.start) {
                // lastInterval和intervalToMerge不相交
                result.add(intervalToMerge);
                continue;
            }
            if (lastInterval.end >= intervalToMerge.end) {
                // lastInterval包含intervalToMerge
                continue;
            }
            // lastInterval和intervalToMerge相交但不包含
            result.removeLast();
            result.add(new Interval(lastInterval.start, intervalToMerge.end));
        }
        return result;
    }

    // ------------------------ solution2 ------------------------

    /**
     * https://www.programcreek.com/2012/12/leetcode-merge-intervals/
     * <p>
     * 58.72%
     * <p>
     * 排序时保证start从小到大, start相同时, end也从小到大(增加end的排序，应该没有明显区别)
     * 通过设置pre减少了result的元素增加和删除次数
     */
    public static List<Interval> solution2(List<Interval> intervals) {
        List<Interval> result = new ArrayList<Interval>();

        if (intervals == null || intervals.size() == 0)
            return result;

        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                if (i1.start != i2.start)
                    return i1.start - i2.start;
                else
                    return i1.end - i2.end;
            }
        });

        Interval pre = intervals.get(0);
        for (int i = 0; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (curr.start > pre.end) {
                result.add(pre);
                pre = curr;
            } else {
                Interval merged = new Interval(pre.start, Math.max(pre.end, curr.end));
                pre = merged;
            }
        }
        result.add(pre);

        return result;
    }

    // ------------------------ dependency ------------------------
    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
