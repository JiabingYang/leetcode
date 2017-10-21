package com.yjb.leetcode;

public class PalindromeNumber {

    /**
     * beats 51.78%
     */
    public boolean myAnswer(int x) {
        if (x == 0) {
            return true;
        }
        if (x < 0) {
            return false;
        }
        int size = ((int) Math.log10(x)) + 1;// 不能使用转字符串
        for (int i = 0; i <= size / 2; i++) {
            if (getNumAt(x, i) != getNumAt(x, size - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public int getNumAt(int integer, int index) {
        return (integer / ((int) Math.pow(10, index))) % 10;
    }

    /**
     * beats 10.95%
     * http://blog.csdn.net/lisonglisonglisong/article/details/45701629
     */
    public boolean lisongAnswer(int x) {
        if (x < 0 || (x > 0 && x % 10 == 0)) // 负数与整十的情况
            return false;

        int sum = 0;
        while (x > sum)              // 逆转一半
        {
            sum = sum * 10 + x % 10;
            x = x / 10;
        }

        return (x == sum) || (x == sum / 10);
    }

    /**
     * beats 48.76%
     * https://www.cnblogs.com/grandyang/p/4125510.html
     */
    public boolean grandyangAnswer(int x) {
        if (x < 0)
            return false;
        int div = 1;
        while (x / div >= 10)
            div *= 10;
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right)
                return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

}
