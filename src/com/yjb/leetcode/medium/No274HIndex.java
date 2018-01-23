package com.yjb.leetcode.medium;

import java.util.Arrays;

/**
 * 274. H-Index
 * <p>
 * Given an array of citations (each citation is a non-negative integer) of a researcher,
 * write a function to compute the researcher's h-index.
 * <p>
 * According to the definition of h-index on Wikipedia:
 * "A scientist has index h if h of his/her N papers have at least h citations each,
 * and the other N − h papers have no more than h citations each."
 * <p>
 * For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total
 * and each of them had received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each
 * and the remaining two with no more than 3 citations each, his h-index is 3.
 * <p>
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 * <p>
 * Credits:
 * Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
 */
public class No274HIndex {

    /**
     * https://www.programcreek.com/2012/11/top-10-algorithms-for-coding-interview/
     * https://segmentfault.com/a/1190000003794831
     * 排序
     * <p>
     * 先将数组排序，我们就可以知道对于某个引用数，有多少文献的引用数大于这个数。
     * 对于引用数citations[i]，大于该引用数文献的数量是citations.length - i，
     * 而<b>当前的H-Index则是Math.min(citations[i], citations.length - i)</b>，
     * 我们将这个当前的H指数和全局最大的H指数来比较，得到最大H指数。
     * <p>
     * 时间 O(NlogN) 空间 O(1)
     */
    private static int solution1(int[] citations) {
        Arrays.sort(citations);
        int result = 0;
        for (int i = 0; i < citations.length; i++) {
            int smaller = Math.min(citations[i], citations.length - i);
            result = Math.max(result, smaller);
        }
        return result;
    }

    /**
     * http://smartyi8979.com/2016/03/18/H-index/
     * <p>
     * 排序 + 二分查找
     * <p>
     * 因为已经排序，那么每次选取数组中间元素和它之后的元素的数量相比较(包括它本身)，那么就有两种情况：
     * <p>
     * 假设数组长度n，搜索起始元素start，终点元素end，中间元素mid；那么包括中间元素本身的后面元素的数量是\(n-mid\).
     * <p>
     * 当前元素的值和它之后元素的数量相同，那么证明至少有\(n-mid\)个元素是大于\(n-mid\)的，那么直接返回就好；
     * 当前元素小于它之后的元素的数量，那么证明后面元素数量过多或者本身元素值太小，甭管什么原因，出现在它之前的那些元素都不可能是答案了，所以start＋1；
     * 当前元素大于它之后的元素的数量，那么证明后面元素数量过少或者本身元素值太大，甭管什么原因，出现在它之后的那些元素都不可能是答案了，所以end－1；
     */
    private static int solution2(int[] citations) {
        Arrays.sort(citations);

        int len = citations.length;
        int start = 0;
        int end = len - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (citations[mid] == len - mid) {
                return len - mid;
            } else if (citations[mid] < len - mid) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return len - start;
    }

    /**
     * https://segmentfault.com/a/1190000003794831
     * <p>
     * 数组统计表
     * <p>
     * 思路
     * 也可以不对数组排序，我们额外使用一个大小为N+1的数组stats。
     * stats[i]表示有多少文章被引用了i次，这里如果一篇文章引用大于N次，我们就将其当为N次，因为H指数不会超过文章的总数。
     * <p>
     * 为了构建这个数组，我们需要先将整个文献引用数组遍历一遍，对相应的格子加一。
     * <p>
     * 统计完后，我们从N向1开始遍历这个统计数组。
     * 如果遍历到某一个引用次数时，(>=该引用次数的文章数量) >= 该引用次数 时，我们可以认为这是H指数。
     * <p>
     * 之所以不用再向下找，因为我们要取最大的H指数。
     * <p>
     * 那如何求大于或等于某个引用次数的文章数量呢？
     * 我们可以用一个变量，从高引用次的文章数累加下来。
     * 因为我们知道，如果有x篇文章的引用大于等于3次，那引用大于等于2次的文章数量一定是x加上引用次数等于2次的文章数量。
     * <p>
     * 时间 O(N) 空间 O(N)
     */
    private static int solution3(int[] citations) {
        int n = citations.length;

        int[] stats = new int[citations.length + 1];
        // 统计各个引用次数对应多少篇文章
        for (int citation : citations) {
            stats[citation <= n ? citation : n]++;
        }

        int sum = 0;
        // 找出最大的H指数
        for (int i = n; i > 0; i--) {
            // 引用大于等于i次的文章数量，等于引用大于等于i+1次的文章数量，加上引用等于i次的文章数量
            sum += stats[i];
            // (>=该引用次数(i)的文章数量(sum) >= (该引用次数(i))，说明i是H指数
            if (sum >= i) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 排序
     * <p>
     * 严格按照题目描述的写法
     * <p>
     * 时间 O(NlogN) 空间 O(1)
     */
    private static int mySolution(int[] citations) {
        Arrays.sort(citations);
        int len = citations.length;
        for (int i = len - 1; i >= 0; i--) { // 或 int i = 0; i < citations.length; i++
            int h = len - i; // 至少有h(即len - i)个>=citations[i]
            int left = i == 0 ? 0 : citations[i - 1]; // 有i(即N-h)个<=left
            // h <= citations[i] => 至少有h个>= h
            // h >= left => 有i(即N-h)个 <= h
            if (h >= left && h <= citations[i]) {
                return h;
            }
        }
        // []
        return 0;
    }
}
