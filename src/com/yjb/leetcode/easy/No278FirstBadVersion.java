package com.yjb.leetcode.easy;

/**
 * 278. First Bad Version
 * <p>
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * <p>
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 * <p>
 * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 * <p>
 * Credits:
 * Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
 */
public class No278FirstBadVersion {

    public static void main(String[] args) {
        System.out.println(new MySolution(1702766719).firstBadVersion(2126753390) + "[1702766719]");
    }

    /**
     * 二分
     */
    private static class MySolution extends VersionControl {

        MySolution(int firstBadVersion) {
            super(firstBadVersion);
        }

        int firstBadVersion(int n) {
            int start = 1;
            int end = n;
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (isBadVersion(mid)) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }
            return start;
        }
    }

    private static class VersionControl {
        private final int firstBadVersion;

        VersionControl(int firstBadVersion) {
            this.firstBadVersion = firstBadVersion;
        }

        boolean isBadVersion(int version) {
            return version >= firstBadVersion;
        }
    }
}
