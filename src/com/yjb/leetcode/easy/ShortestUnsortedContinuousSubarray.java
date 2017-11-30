package com.yjb.leetcode.easy;

import java.util.Arrays;
import java.util.Stack;

/**
 * 581. Shortest Unsorted Continuous Subarray
 * <p>
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray
 * in ascending order, then the whole array will be sorted in ascending order, too.
 * You need to find the shortest such subarray and output its length.
 * <p>
 * Example 1:
 * Input: [2, 6, 4, 8, 10, 9, 15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 * <p>
 * Note:
 * Then length of the input array is in range [1, 10,000].
 * The input array may contain duplicates, so ascending order here means <=.
 */
public class ShortestUnsortedContinuousSubarray {

    /**
     * 思路：这道题目的代码很简单，关键是对于题意的理解，所谓最短需排序子数组是指一个需要排序的最大区域，
     * 即题目中的数组只要找到一个待排序区域而不是分离的几段，如果在一个区域中元素逐步递增，即在遍历这个
     * 区域时，每个当前元素都是最大值max，那么说明这个区域是有序的；如果从某个元素开始，之后存在元素小
     * 于这个元素，那么这段区域就是非有序的，是需要排序的。为什么需要保留最大值max，在遍历时要保留最大
     * 值max，如果后面的每个元素都更大，则max会逐步向后面移动，因此max所在的区域都是有序的，如果在max
     * 后面出现了一个元素小于这个max，即没有替换这个max值，于是这个当前值到前面max所在的区域必然是需要
     * 重新排序的，此时用int right指针记录这个位置，之后再往后面移动，如果下一个元素大于max，则重新替
     * 换max，再往下移动，如果下一个元素小于max，则将right替换为当前值，说明这个元素之前需要重排序；一
     * 直按此进行，即只要值小于max，就应该赋值给right表示之前的部分是无需的，直到全部遍历完成，此时
     * right所在的位置是应该排序的区域的右端点，在right及左边元素的区域是无序的。那么需要排序的区域的起
     * 始位置再哪里呢？应该是第一个小于max的元素对应的max所在的元素，即从某个元素开始如果元素小于了前面
     * 那么元素，说明开始无序，于是记录第一个right的位置start即可，区域开始的位置就是start-1；由于记录
     * 开始减少的第一个元素较麻烦，可以通过逆向遍历的方式来求无序数组的开始位置，以A[n-1]的值作为min,从
     * i=n-2开始向前遍历元素，如果A[i]<min，那么就替换min，如果A[i]>min，说明出现了破坏数组有序性的元
     * 素，因此将该值赋值给left，于是从left所在位置向右的区域是需要重新排序的，以此方式向左遍历数组，当
     * 遍历完成时，left所在的位置就是需要重新排序的区域的第一个元素，于是最终[left,right]区域就是需要重
     * 新排序的区域，于是长度是right-left+1；注意理解，这里不必担心left>=right，因为2次遍历是对同一个
     * 数组进行的，因此结果一定是唯一统一的，如果存在无序的区域，那么一定是[left,right]，如果不存在无序
     * 区域，即整个序列有序，那么必然有left=0；并且同时right=n-1,此时待排序部分长度是0，返回0即可。
     * <p>
     * 时间复杂度O(n)，空间复杂度O(1)
     * <p>
     * http://blog.csdn.net/qq_27703417/article/details/70953905
     */
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        // unsorted array's right bound. nums after right is gte nums[right].
        // Or we can say sorting is needed before right(inclusive).
        int right = 0;
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= max) {
                max = nums[i];
            } else {
                right = i;
            }
        }
        if (right == 0) {
            return 0;
        }
        // unsorted array's left bound. nums before left is lte nums[left].
        // Or we can say sorting is needed after left(inclusive).
        int left = nums.length - 1;
        int min = nums[nums.length - 1];
        for (int i = nums.length - 1; i > -1; i--) {
            if (nums[i] <= min) {
                min = nums[i];
            } else {
                left = i;
            }
        }
        return right - left + 1;
    }

    // ------ https://leetcode.com/articles/shortest-unsorted-continous-subarray/ ------

    /**
     * Approach #1 Brute Force [Time Limit Exceeded]
     * <p>
     * Algorithm
     * <p>
     * In the brute force approach, we consider every possible subarray that can be formed from the given array nums.
     * For every subarray nums[i:j] considered, we need to check whether this is the smallest unsorted subarray or not.
     * Thus, for every such subarray considered, we find out the maximum and minimum values lying in that subarray given
     * by max and min respectively.
     * <p>
     * If the subarrays nums[0:i-1] and nums[j:n-1] are correctly sorted, then only nums[i:j] could be the required subarray.
     * Further, the elements in nums[0:i-1] all need to be lesser than the min for satisfying the required condition.
     * Similarly, all the elements in nums[j:n-1] need to be larger than max.
     * We check for these conditions for every possible i and j selected.
     * <p>
     * Further, we also need to check if nums[0:i-1] and nums[j:n-1] are sorted correctly.
     * If all the above conditions are satisfied, we determine the length of the unsorted subarray as j−i.
     * We do the same process for every subarray chosen and determine the length of the smallest unsorted subarray found.
     * <p>
     * Complexity Analysis
     * Time complexity: O(n^3). Three nested loops are there.
     * Space complexity: O(1)O(1). Constant space is used.
     */
    public int bruteForce(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j <= nums.length; j++) {
                int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, prev = Integer.MIN_VALUE;
                for (int k = i; k < j; k++) {
                    min = Math.min(min, nums[k]);
                    max = Math.max(max, nums[k]);
                }
                if ((i > 0 && nums[i - 1] > min) || (j < nums.length && nums[j] < max))
                    continue;
                int k = 0;
                while (k < i && prev <= nums[k]) {
                    prev = nums[k];
                    k++;
                }
                if (k != i)
                    continue;
                k = j;
                while (k < nums.length && prev <= nums[k]) {
                    prev = nums[k];
                    k++;
                }
                if (k == nums.length) {
                    res = Math.min(res, j - i);

                }
            }
        }
        return res;
    }

    /**
     * Approach #2 Better Brute Force [Time Limit Exceeded]
     * <p>
     * Algorithm
     * In this approach, we make use of an idea based on selection sort. We can traverse over the given nums array
     * choosing the elements nums[i]. For every such element chosen, we try to determine its correct position in
     * the sorted array. For this, we compare nums[i] with every nums[j], such that i < j < n. Here, n refers to
     * the length of nums array.
     * <p>
     * If any nums[j] happens to be lesser than nums[i], it means both nums[i] and nums[j] aren't at their correct
     * position for the sorted array. Thus, we need to swap the two elements to bring them at their correct positions.
     * Here, instead of swapping, we just note the position of nums[i](given by i) and nums[j](given by j). These two
     * elements now mark the boundary of the unsorted subarray(at least for the time being).
     * <p>
     * Thus, out of all the nums[i] chosen, we determine the leftmost nums[i] which isn't at its correct position.
     * This marks the left boundary of the smallest unsorted subarray(l). Similarly, out of all the nums[j]'s
     * considered for all nums[i]'s we determine the rightmost nums[j] which isn't at its correct position. This
     * marks the right boundary of the smallest unsorted subarray(r).
     * <p>
     * Thus, we can determine the length of the smallest unsorted subarray as r - l + 1r−l+1.
     */
    public int betterBruteForce(int[] nums) {
        int l = nums.length, r = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    r = Math.max(r, j);
                    l = Math.min(l, i);
                }
            }
        }
        return r - l < 0 ? 0 : r - l + 1;
    }

    /**
     * Approach #3 Using Sorting [Accepted]
     * <p>
     * Algorithm
     * Another very simple idea is as follows.
     * We can sort a copy of the given array nums, say given by nums_sorted.
     * Then, if we compare the elements of nums and nums_sorted, we can determine the leftmost and rightmost elements which mismatch.
     * The subarray lying between them is, then, the required shorted unsorted subarray.
     * <p>
     * Complexity Analysis
     * Time complexity : O(nlogn). Sorting takes nlogn time.
     * Space complexity : O(n). We are making copy of original array.
     */
    public int usingSorting(int[] nums) {
        int[] snums = nums.clone();
        Arrays.sort(snums);
        int start = snums.length, end = 0;
        for (int i = 0; i < snums.length; i++) {
            if (snums[i] != nums[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        return (end - start >= 0 ? end - start + 1 : 0);
    }

    /**
     * Approach #4 Using Stack [Accepted]:
     * <p>
     * Algorithm
     * <p>
     * The idea behind this approach is also based on selective sorting.
     * <p>
     * We need to determine the correct position of the minimum and the maximum element in the unsorted subarray to
     * determine the boundaries of the required unsorted subarray.
     * To do so, in this implementation, we make use of a stack. We traverse over the nums array starting from the beginning.
     * As we go on facing elements in ascending order(a rising slope), we keep on pushing the elements' indices over the stack.
     * This is done because such elements are in the correct sorted order(as it seems till now).
     * As soon as we encounter a falling slope, i.e. an element nums[j] which is smaller than the element on the top of the stack,
     * we know that nums[j] isn't at its correct position.
     * <p>
     * In order to determine the correct position of nums[j], we keep on popping the elements from the top of the stack
     * until we reach the stage where the element(corresponding to the index) on the top of the stack is lesser than nums[j].
     * Let's say the popping stops when the index on stack's top is k. Now, nums[j] has found its correct position.
     * It needs to lie at an index k + 1.
     * <p>
     * We follow the same process while traversing over the whole array, and determine the value of minimum such k.
     * This marks the left boundary of the unsorted subarray.
     * <p>
     * Similarly, to find the right boundary of the unsorted subarray, we traverse over the nums array backwards.
     * This time we keep on pushing the elements if we see a falling slope.
     * As soon as we find a rising slope, we trace forwards now and determine the larger element's correct position.
     * We do so for the complete array and thus, determine the right boundary.
     * <p>
     * Complexity Analysis
     * Time complexity : O(n). Stack of size n is filled.
     * Space complexity : O(n). Stack size grows up to n.
     */
    public int usingStack(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int l = nums.length, r = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                l = Math.min(l, stack.pop());
            stack.push(i);
        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                r = Math.max(r, stack.pop());
            stack.push(i);
        }
        return r - l > 0 ? r - l + 1 : 0;
    }

    /**
     * Approach #5 Without Using Extra Space [Accepted]:
     * <p>
     * Algorithm
     * <p>
     * The idea behind this method is that the correct position of the minimum element in the unsorted subarray helps to
     * determine the required left boundary.
     * Similarly, the correct position of the maximum element in the unsorted subarray helps to determine the required
     * right boundary.
     * <p>
     * Thus, firstly we need to determine when the correctly sorted array goes wrong. We keep a track of this by observing
     * rising slope starting from the beginning of the array.
     * Whenever the slope falls, we know that the unsorted array has surely started.
     * Thus, now we determine the minimum element found till the end of the array nums, given by min.
     * <p>
     * Similarly, we scan the array nums in the reverse order and when the slope becomes rising instead of falling,
     * we start looking for the maximum element till we reach the beginning of the array, given by max.
     * <p>
     * Then, we traverse over nums and determine the correct position of min and max by comparing these elements with
     * the other array elements.
     * e.g. To determine the correct position of min, we know the initial portion of nums is already sorted.
     * Thus, we need to find the first element which is just larger than min.
     * Similarly, for max's position, we need to find the first element which is just smaller than max
     * searching in nums backwards.
     */
    public int withoutUsingExtraSpace(int[] nums) {
        // min是unsorted array中最小的值（min大于unsorted array左边的所有值）
        // max是unsorted array中最大的值（max小于unsorted array右边的所有值）
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        boolean flag = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1])
                flag = true;
            if (flag)
                min = Math.min(min, nums[i]);
        }
        flag = false;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1])
                flag = true;
            if (flag)
                max = Math.max(max, nums[i]);
        }
        int l, r;
        // 找到第一个大于min的值的位置作为l
        for (l = 0; l < nums.length; l++) {
            if (min < nums[l])
                break;
        }
        // 找到第一个小于max的值的位置作为r
        for (r = nums.length - 1; r >= 0; r--) {
            if (max > nums[r])
                break;
        }
        return r - l < 0 ? 0 : r - l + 1;
    }

}
