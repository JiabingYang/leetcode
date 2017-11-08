package com.yjb.leetcode.easy;

/**
 * 思路：这道题目的代码很简答，关键是对于题意的理解，所谓最短需排序子数组是指一个需要排序的最大区域，
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
public class ShortestUnsortedContinuousSubarray {

    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
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
}
