package com.yjb.leetcode.medium;

import com.yjb.leetcode.util.RandomUtils;
import com.yjb.util.Timer;

import java.util.Arrays;
import java.util.List;

/**
 * 34. Search for a Range
 * <p>
 * Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * If the target is not found in the array, return [-1, -1].
 * <p>
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 */
public class No034SearchForARange {

    /* ---------------- test -------------- */
    public static void main(String[] args) {
        System.out.println(Arrays.toString(mySolution3(new int[]{1}, 0)));
        System.out.println(Arrays.toString(mySolution3(new int[]{2, 2}, 3)));
        System.out.println(Arrays.toString(mySolution3(new int[]{5, 7, 7, 8, 8, 10}, 8)));
        System.out.println(Arrays.toString(mySolution3(new int[]{1, 2, 2}, 2)));
        System.out.println("大数据测试");
        int len = 10000000;
        int[] a = new int[len];
        List<Integer> randoms = RandomUtils.randomIntegers(len, 0, len / 2);
        for (int i = 0; i < len; i++) {
            a[i] = randoms.get(i);
        }
        Arrays.sort(a);
        System.out.println("sorted");
        Timer.printlnNano("solution1", () -> System.out.println(Arrays.toString(solution1(a, 17))));
        Timer.printlnNano("mySolution1", () -> System.out.println(Arrays.toString(mySolution1(a, 17))));
        Timer.printlnNano("mySolution2", () -> System.out.println(Arrays.toString(mySolution2(a, 17))));
        Timer.printlnNano("mySolution3", () -> System.out.println(Arrays.toString(mySolution3(a, 17))));
        // 速度排名：mySolution3, mySolution2, solution1, mySolution1
    }

    /* ---------------- mySolution3 -------------- */

    /**
     * 61.68%
     * <p>
     * 思路参考自：
     * 剑指offer: No53aNumberOfK
     * <p>
     * 应该是四种写法里最好的写法
     */
    private static int[] mySolution3(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int left = findLeft(nums, target, 0, nums.length - 1);
        if (left == -1) {
            return new int[]{-1, -1};
        }
        return new int[]{left, findRight(nums, target, left, nums.length - 1)};
    }

    private static int findLeft(int[] nums, int target, int l, int r) {
        int temp = r;
        while (l <= r) {
            int mid = l + ((r - l) >>> 1);
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return (l <= temp && nums[l] == target) ? l : -1;
    }

    private static int findRight(int[] nums, int target, int l, int r) {
        int temp = l;
        while (l <= r) {
            int mid = l + ((r - l) >>> 1);
            if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (r >= temp && nums[r] == target) ? r : -1;
    }

    /* ---------------- solution1 -------------- */

    /**
     * https://www.programcreek.com/2014/04/leetcode-search-for-a-range-java/
     * <p>
     * 58.86%
     */
    private static int[] solution1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        int[] arr = new int[2];
        arr[0] = -1;
        arr[1] = -1;

        binarySearch(nums, 0, nums.length - 1, target, arr);

        return arr;
    }

    private static void binarySearch(int[] nums, int left, int right, int target, int[] arr) {
        if (right < left)
            return;

        if (nums[left] == nums[right] && nums[left] == target) {
            arr[0] = left;
            arr[1] = right;
            return;
        }

        int mid = left + (right - left) / 2;

        if (nums[mid] < target) {
            binarySearch(nums, mid + 1, right, target, arr);
        } else if (nums[mid] > target) {
            binarySearch(nums, left, mid - 1, target, arr);
        } else {
            arr[0] = mid;
            arr[1] = mid;

            //handle duplicates - left
            int t1 = mid;
            while (t1 > left && nums[t1] == nums[t1 - 1]) {
                t1--;
                arr[0] = t1;
            }

            //handle duplicates - right
            int t2 = mid;
            while (t2 < right && nums[t2] == nums[t2 + 1]) {
                t2++;
                arr[1] = t2;
            }
        }
    }

    /* ---------------- mySolution1 -------------- */

    /**
     * 22.78%
     */
    private static int[] mySolution1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int s = nums[start];
            int e = nums[end];
            if (s == target && e == target) {
                return new int[]{start, end};
            }
            int mid = (start + end) / 2;
            int m = nums[mid];
            if (m < target) {
                start = mid + 1;
            } else if (m > target) {
                end = mid - 1;
            } else if (s == target) {
                end--;
            } else {
                start++;
            }
        }
        return new int[]{-1, -1};
    }

    /* ---------------- mySolution2 -------------- */

    /**
     * 58.56%
     */
    private static int[] mySolution2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int s = nums[start];
            int e = nums[end];
            if (s == target && e == target) {
                return new int[]{start, end};
            }
            int mid = (start + end) / 2;
            int m = nums[mid];
            if (m < target) {
                start = mid + 1;
            } else if (m > target) {
                end = mid - 1;
            } else {
                int mid1 = mid;
                while (nums[start] != target) {
                    int sMid = (start + mid1) / 2;
                    if (nums[sMid] < target) {
                        start = sMid + 1;
                    } else {
                        mid1 = sMid - 1;
                    }
                }
                while (nums[end] != target) {
                    int eMid = (mid + end) / 2;
                    if (nums[eMid] > target) {
                        end = eMid - 1;
                    } else {
                        mid = eMid + 1;
                    }
                }
                return new int[]{start, end};
            }
        }
        return new int[]{-1, -1};
    }
}
