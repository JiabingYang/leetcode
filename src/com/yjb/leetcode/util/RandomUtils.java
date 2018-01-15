package com.yjb.leetcode.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    public static String randomString(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append((char) (ThreadLocalRandom.current().nextInt(33, 128)));
        }
        return builder.toString();
    }

    public static List<String> randomStrings(int lengthStart, int lengthEnd, int countPerLength) {
        List<String> result = new ArrayList<>();
        for (int length = lengthStart; length < lengthEnd; length++) {
            for (int i = 0; i < countPerLength; i++) {
                result.add(randomString(length));
            }
        }
        return result;
    }

    public static List<Integer> randomIntegers(int count, int origin, int bound) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(ThreadLocalRandom.current().nextInt(origin, bound));
        }
        return result;
    }
}
