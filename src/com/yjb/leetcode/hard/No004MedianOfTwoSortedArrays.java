package com.yjb.leetcode.hard;

/**
 * 4. Median of Two Sorted Arrays
 * <p>
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * <p>
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * <p>
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * The median is 2.0
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * The median is (2 + 3)/2 = 2.5
 */
public class No004MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {};
        int[] nums2 = {2, 3};
        System.out.println(solution1(nums1, nums2));
    }

    // ------------------------ solution1Static ------------------------
    private static int s1;
    private static int s2;

    /**
     * 66ms
     * 70.29%
     * 不需要偶数情况下的第二次查找的递归，但是每次递归的工作量都增大了
     */
    public double solution1Static(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        if (total % 2 == 1) {
            s1 = 0;
            s2 = 0;
            return findKthStatic(total / 2 + 1, nums1, nums2);
        }
        s1 = 0;
        s2 = 0;
        int left = findKthStatic(total / 2, nums1, nums2);
        int right;
        if (s1 >= nums1.length) {
            right = nums2[s2];
        } else if (s2 >= nums2.length) {
            right = nums1[s1];
        } else {
            right = Math.min(nums1[s1], nums2[s2]);
        }
        return (left + right) / 2.0;
    }

    private int findKthStatic(int k, int[] nums1, int[] nums2) {
        if (s1 >= nums1.length) {
            s2 = s2 + k;
            return nums2[s2 - 1];
        }
        if (s2 >= nums2.length) {
            s1 = s1 + k;
            return nums1[s1 - 1];
        }
        if (k == 1) {
            if (nums1[s1] < nums2[s2]) {
                s1 = s1 + 1;
                return nums1[s1 - 1];
            } else {
                s2 = s2 + 1;
                return nums2[s2 - 1];
            }
        }
        int m1 = s1 + k / 2 - 1;
        int m2 = s2 + k / 2 - 1;

        int mid1 = m1 < nums1.length ? nums1[m1] : Integer.MAX_VALUE;
        int mid2 = m2 < nums2.length ? nums2[m2] : Integer.MAX_VALUE;

        if (mid1 < mid2) {
            s1 = m1 + 1;
            return findKthStatic(k - k / 2, nums1, nums2);
        } else {
            s2 = m2 + 1;
            return findKthStatic(k - k / 2, nums1, nums2);
        }
    }

    // ------------------------ solution1 ------------------------

    /**
     * 63ms
     * 84.89%
     */
    public static double solution1(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        if (total % 2 == 0) {
            return (findKth(total / 2 + 1, nums1, nums2, 0, 0) +
                    findKth(total / 2, nums1, nums2, 0, 0)) / 2.0;
        } else {
            return findKth(total / 2 + 1, nums1, nums2, 0, 0);
        }
    }

    /**
     * 总结：
     * 1. 从s到e，总共是e-s+1个元素
     * 2. 每次递归都会向第k个元素前进k/2个元素
     */
    private static int findKth(int k, int[] nums1, int[] nums2, int s1, int s2) {

        // 递归的终止情况
        if (s1 >= nums1.length)
            return nums2[s2 + k - 1];
        if (s2 >= nums2.length)
            return nums1[s1 + k - 1];
        if (k == 1)
            return Math.min(nums1[s1], nums2[s2]);

        // 为什么要减1：
        // 如果不减1的话s1到m1就是取k/2 + 1个元素，这时取的元素数量可能超过k，一旦超过k，算法就不对了（每一步都是不断接近k的）
        int m1 = s1 + k / 2 - 1; // s1到m1总共 k/2 个元素
        int m2 = s2 + k / 2 - 1; // s2到m2总共 k/2 个元素

        // 为什么m1和m2不会同时超出各自数组长度：
        // s1到m1总共 k/2 个元素, s2到m2总共 k/2 个元素，加起来正好是k
        // 假如m1和m2同时超出各自数组长度,那么k > 两个数组从s1和s2开始的所有剩余元素数量，这种情况下第k个元素是不存在的
        int mid1 = m1 < nums1.length ? nums1[m1] : Integer.MAX_VALUE;
        int mid2 = m2 < nums2.length ? nums2[m2] : Integer.MAX_VALUE;

        // 取出k/2个元素，向k前进
        if (mid1 < mid2) {
            return findKth(k - k / 2, nums1, nums2, m1 + 1, s2);
        } else {
            return findKth(k - k / 2, nums1, nums2, s1, m2 + 1);
        }
    }

    // ------------------------ solution2 ------------------------

    /**
     * 64ms
     * 79.55%
     */
    public static double solution2(int A[], int B[]) {
        int m = A.length;
        int n = B.length;

        if ((m + n) % 2 != 0) // odd
            return (double) findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1);
        else { // even
            return (findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1)
                    + findKth(A, B, (m + n) / 2 - 1, 0, m - 1, 0, n - 1)) * 0.5;
        }
    }

    public static int findKth(int a[], int b[], int k,
                              int aStart, int aEnd, int bStart, int bEnd) {
        int aLen = aEnd - aStart + 1;
        int bLen = bEnd - bStart + 1;

        // Handle special cases
        if (aLen == 0)
            return b[bStart + k];
        if (bLen == 0)
            return a[aStart + k];
        if (k == 0)
            return a[aStart] < b[bStart] ? a[aStart] : b[bStart];

        int aMid = aLen * k / (aLen + bLen); // a's middle count
        int bMid = k - aMid - 1; // b's middle count

        // make aMid and bMid to be array index
        aMid = aMid + aStart;
        bMid = bMid + bStart;

        if (a[aMid] > b[bMid]) {
            k = k - (bMid - bStart + 1);
            aEnd = aMid;
            bStart = bMid + 1;
        } else {
            k = k - (aMid - aStart + 1);
            bEnd = bMid;
            aStart = aMid + 1;
        }

        return findKth(a, b, k, aStart, aEnd, bStart, bEnd);
    }
}
