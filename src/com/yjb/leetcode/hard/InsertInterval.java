package com.yjb.leetcode.hard;

import java.util.*;

/**
 * 57. Insert Interval
 * <p>
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * <p>
 * You may assume that the intervals were initially sorted according to their start times.
 * <p>
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * <p>
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 * <p>
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {

    public static void main(String[] args) {
        List<InsertInterval.Interval> intervals1 = new LinkedList<>(Arrays.asList(
                new InsertInterval.Interval(1, 3),
                new InsertInterval.Interval(6, 9)
        ));
        List<InsertInterval.Interval> intervals2 = new LinkedList<>(Arrays.asList(
                new InsertInterval.Interval(1, 2),
                new InsertInterval.Interval(3, 5),
                new InsertInterval.Interval(6, 7),
                new InsertInterval.Interval(8, 10),
                new InsertInterval.Interval(12, 16)
        ));
        List<InsertInterval.Interval> intervals3 = new LinkedList<>(Arrays.asList(
                new InsertInterval.Interval(1, 5)
        ));
        List<InsertInterval.Interval> intervals4 = new LinkedList<>(Arrays.asList(
                new InsertInterval.Interval(1, 5)
        ));
        List<InsertInterval.Interval> intervals5 = new LinkedList<>(Arrays.asList(
                new InsertInterval.Interval(3, 5),
                new InsertInterval.Interval(12, 15)
        ));
        System.out.println(new InsertInterval().insert(intervals1, new Interval(2, 5)));
        System.out.println(new InsertInterval().insert(intervals2, new Interval(4, 9)));
        System.out.println(new InsertInterval().insert(intervals3, new Interval(6, 8)));
        System.out.println(new InsertInterval().insert(intervals4, new Interval(0, 0)));
        System.out.println(new InsertInterval().insert(intervals5, new Interval(6, 6)));
    }

    // ------------------------ mySolution ------------------------

    /**
     * 14.18%
     * iterator遍历，重叠就删.
     * 记录第一个重叠的interval的start和最后一个重叠的interval的end。
     * 碰到第一个interval在newInterval右边的就break
     * 插入新的interval后排序（排序太浪费了，但我没找到怎么在合适的位置插入）
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals.isEmpty()) {
            intervals.add(newInterval);
            return intervals;
        }
        Iterator<Interval> it = intervals.iterator();
        Integer start = null;
        Integer end = null;
        while (it.hasNext()) {
            Interval interval = it.next();
            if (interval.start > newInterval.end) {
                break;
            }
            if (interval.end >= newInterval.start) {
                if (start == null) {
                    start = Math.min(interval.start, newInterval.start);
                }
                end = interval.end;
                it.remove();
            }
        }
        intervals.add(new Interval(start == null ? newInterval.start : start, end == null ? newInterval.end : Math.max(end, newInterval.end)));
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        return intervals;
    }

    // ------------------------ solution2 ------------------------

    /**
     * https://www.programcreek.com/2012/12/leetcode-insert-interval/
     * 38.07%(ArrayList换成LinkedList后为28.83%)
     */
    public List<Interval> solution2(List<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<>();

        // 遍历过程中保证待插入的newInterval一直在intervals的右边
        for (Interval interval : intervals) {
            if (interval.end < newInterval.start) {
                // interval在newInterval左边
                // 直接将interval插入，newInterval不变，还差newInterval没插
                result.add(interval);
            } else if (interval.start > newInterval.end) {
                // interval在newInterval右边
                // newInterval插入，interval作为新的newInterval，还差新的newInterval没插
                result.add(newInterval);
                newInterval = interval;
            } else if (interval.end >= newInterval.start || interval.start <= newInterval.end) {
                // interval和newInterval交叉
                // 生成最新的newInterval，还差最新的newInterval没插
                newInterval = new Interval(Math.min(interval.start, newInterval.start), Math.max(newInterval.end, interval.end));
            }
        }

        // 总有一个newInterval没插，所以这里再插入依次newInterval
        result.add(newInterval);

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
