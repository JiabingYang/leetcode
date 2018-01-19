package com.yjb.leetcode.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * 135. Candy
 * <p>
 * There are N children standing in a line. Each child is assigned a rating value.
 * <p>
 * You are giving candies to these children subjected to the following requirements:
 * <p>
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 * <p>
 * 比较是考虑左右两边的增长变化的，因此可以分别从左向右和从右向左遍历，找递增
 */
public class No135Candy {

    public static void main(String[] args) {
        System.out.println(mySolution(new int[]{1, 4, 2, 5, 3}) + "[7]");
        System.out.println(mySolution(new int[]{1, 2, 3, 4, 5}) + "[15]");
        System.out.println(mySolution(new int[]{5, 3, 1}) + "[6]");
    }

    /**
     * https://www.programcreek.com/2014/03/leetcode-candy-java/
     * <p>
     * Analysis
     * <p>
     * This problem can be solved in O(n) time.
     * <p>
     * We can always assign a neighbor with 1 more if the neighbor has higher a rating value.
     * However, to get the minimum total number, we should always start adding 1s in the ascending order.
     * We can solve this problem by scanning the array from both sides.
     * First, scan the array from left to right, and assign values for all the ascending pairs.
     * Then scan from right to left and assign values to descending pairs.
     * <p>
     * This problem is similar to Trapping Rain Water.
     */
    private static int solution1(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }

        int[] candies = new int[ratings.length];
        candies[0] = 1;

        //from let to right 保证了右边和左边比时的正确性
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            } else {
                // if not ascending, assign 1
                candies[i] = 1;
            }
        }

        int result = candies[ratings.length - 1]; // 从左向右算完后，最右边是对的

        //from right to left 保证了左边和右边比时的正确性
        for (int i = ratings.length - 2; i >= 0; i--) {
            int cur = 1;
            if (ratings[i] > ratings[i + 1]) {
                cur = candies[i + 1] + 1; // 因为是在原来的数上加一
            }

            // 因为取了之前从左向右结果和这次从右向左结果的最大值，
            // candies[i]只会增加或不变，不会减少。
            // 假设从右向左时增加了candies[i]的值，那么对i来说肯定不会破坏它跟i-1的比较条件
            // 而i-1跟i-2的比较条件肯定也不会受到i变化影响，i-1跟i的比较条件将在下一步中达成
            // 所以第二趟遍历没有破坏第一趟遍历的比较条件。
            result += Math.max(cur, candies[i]);
            candies[i] = cur;
        }

        return result;
    }

    /**
     * 0.38%
     * <p>
     * 时间nlogn
     */
    private static int mySolution(int[] ratings) {
        // rating => indexes, 按rating顺序排列
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        for (int i = 0; i < ratings.length; i++) {
            int rating = ratings[i];
            List<Integer> indexes = map.getOrDefault(rating, new ArrayList<>());
            indexes.add(i);
            map.put(rating, indexes);
        }

        int count = 0;
        Integer[] candies = new Integer[ratings.length];

        // 把rating最低的孩子全部标上1
        Integer firstRating = map.firstKey();
        List<Integer> firstIndexes = map.get(firstRating);
        for (Integer index : firstIndexes) {
            candies[index] = 1;
            count++;
        }
        map.remove(firstRating);

        // 其他孩子，按rating从低到高依次从左到右标，
        // 如果两边有rating更低且给过糖果的孩子，那么取两边孩子的糖果数的最大值加1
        // 分发糖果时更新count
        for (Integer rating : map.keySet()) {
            for (Integer index : map.get(rating)) {
                Integer sideCandy = null;
                if (index - 1 >= 0 && ratings[index - 1] < rating) {
                    sideCandy = candies[index - 1];
                }
                if (index + 1 < ratings.length && ratings[index + 1] < rating) {
                    int candy = candies[index + 1];
                    sideCandy = sideCandy == null ? candy : Math.max(sideCandy, candy);
                }
                int candy = sideCandy == null ? 1 : sideCandy + 1;
                candies[index] = candy;
                count += candy;
            }
        }

        return count;
    }
}
