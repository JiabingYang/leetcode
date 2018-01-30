package com.yjb.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 68. Text Justification
 * <p>
 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
 * <p>
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.
 * <p>
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 * <p>
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 * <p>
 * For example,
 * words: ["This", "is", "an", "example", "of", "text", "justification."]
 * L: 16.
 * <p>
 * Return the formatted lines as:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 * Note: Each word is guaranteed not to exceed L in length.
 * <p>
 * Corner Cases:
 * A line other than the last line might contain only one word. What should you do in this case?
 * In this case, that line should be left-justified.
 */
public class No068TextJustification {

    public static void main(String[] args) {
        System.out.println(mySolution(new String[]{"Listen", "to", "many,", "speak", "to", "a", "few."}, 6));
        System.out.println(mySolution(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));
        System.out.println(mySolution(new String[]{"Here", "is", "an", "example", "of", "text", "justification."}, 14));
        System.out.println(mySolution(new String[]{"Don't", "go", "around", "saying", "the", "world", "owes", "you", "a", "living;", "the", "world", "owes", "you", "nothing;", "it", "was", "here", "first."}, 30));
    }

    /**
     * http://blog.csdn.net/mebiuw/article/details/51367947
     */
    public static List<String> solution1(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0; // 当前选择的第一个单词位置
        int j = 1; // 下一个要遍历的单词的位置
        while (i < words.length) {
            int compulsorySpaces = 0; // 必须的空格，为当前选中单词数量-1
            int len = words[i].length();// 当前选择的所有单词的长度和
            while (j < words.length && len + compulsorySpaces + 1 + words[j].length() <= maxWidth) { //试探选择最大的单词数量
                compulsorySpaces++;
                len += words[j].length();
                j++;
            }
            // 下面的len和compulsorySpaces是针对i到j-1的
            if (j == words.length) {
                // j-1是最后一个
                // 加入i到j-1的字符串
                StringBuilder sb = new StringBuilder(words[i]);
                for (int k = i + 1; k < j; k++) {
                    sb.append(" ").append(words[k]);
                }
                for (int k = len + compulsorySpaces; k < maxWidth; k++) {
                    sb.append(" ");
                }
                result.add(sb.toString());
                break;
            }
            // j-1不是最后一个
            if (j - i == 1) {
                // 只选中的一个的特殊处理，因为计算空格会出现除数为0的状况
                StringBuilder sb = new StringBuilder(words[i]);
                for (int k = len; k < maxWidth; k++) {
                    sb.append(" ");
                }
                result.add(sb.toString());
            } else {
                //处理多个空格
                int space = (maxWidth - len) / (j - i - 1); //基本的空格
                int remains = maxWidth - len - (j - i - 1) * space; //因为整除未能分配的空格数量
                StringBuilder sb = new StringBuilder(words[i]);
                for (int k = i + 1; k < j; k++) {
                    for (int l = 0; l < space; l++) {
                        sb.append(" ");
                    }
                    if (remains-- > 0) {
                        sb.append(" "); //在大于0，也就是还需要在左边多加空格的时候，多给一个
                    }
                    sb.append(words[k]);
                }
                result.add(sb.toString());
            }
            i = j++;
        }
        return result;
    }

    /**
     * 21.92%
     */
    private static List<String> mySolution(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        if (words.length == 0) {
            return result;
        }
        int i = 0; // 左边界（include）
        int j = 0; // 右边界（include）
        int len = 0;
        // 最后一个之前的
        while (j < words.length - 1) {
            // 计算i-j的长度
            int newLen = len + words[j].length();
            if (len != 0) {
                newLen++;
            }

            if (newLen < maxWidth) {
                // 继续右移j
                j++;
                len = newLen;
            } else if (newLen == maxWidth) {
                // 加上i到j的字符串
                StringBuilder sb = new StringBuilder();
                for (int k = i; k <= j; k++) {
                    sb.append(words[k]).append(" ");
                }
                result.add(sb.toString().trim());
                // 右移j，重置i到新的j的位置
                i = ++j;
                len = 0;
            } else {
                int spaceCount = maxWidth - len;
                int count = j - i;
                // 加上i到j-1的字符串
                if (count == 1) {
                    StringBuilder sb = new StringBuilder(words[i]);
                    for (int k = 0; k < spaceCount; k++) {
                        sb.append(" ");
                    }
                    result.add(sb.toString());
                } else {
                    int intervalSpaceCount = spaceCount / (count - 1) + 1;
                    int extra = spaceCount - (intervalSpaceCount - 1) * (count - 1);
                    StringBuilder sb = new StringBuilder();
                    for (int k = i; k < i + extra; k++) {
                        sb.append(words[k]);
                        for (int l = 0; l < intervalSpaceCount + 1; l++) {
                            sb.append(" ");
                        }
                    }
                    for (int k = i + extra; k < j - 1; k++) {
                        sb.append(words[k]);
                        for (int l = 0; l < intervalSpaceCount; l++) {
                            sb.append(" ");
                        }
                    }
                    sb.append(words[j - 1]);
                    result.add(sb.toString());
                }
                // 重置i到j的位置
                i = j;
                len = 0;
            }
        }
        // 最后一个
        if (len == 0) {
            // 加上最后一个单词（如果需要的话，补空格）
            StringBuilder sb = new StringBuilder(words[j]);
            for (int k = words[j].length(); k < maxWidth; k++) {
                sb.append(" ");
            }
            result.add(sb.toString());
        } else {
            int newLen = len + words[j].length() + 1;
            if (newLen == maxWidth) {
                // 加上i到j的字符串（不需要补空格）
                StringBuilder sb = new StringBuilder();
                for (int k = i; k <= j; k++) {
                    sb.append(words[k]).append(" ");
                }
                result.add(sb.toString().trim());
            } else if (newLen < maxWidth) {
                // 加上i到j的字符串（需要补空格）
                StringBuilder sb = new StringBuilder();
                for (int k = i; k <= j; k++) {
                    sb.append(words[k]).append(" ");
                }
                for (int k = newLen + 1; k < maxWidth; k++) {
                    sb.append(" ");
                }
                result.add(sb.toString());
            } else {
                // 先加上i到j-1的字符串
                int spaceCount = maxWidth - len;
                int count = j - i;
                if (count == 1) {
                    StringBuilder sb = new StringBuilder(words[i]);
                    for (int k = 0; k < spaceCount; k++) {
                        sb.append(" ");
                    }
                    result.add(sb.toString());
                } else {
                    int intervalSpaceCount = spaceCount / (count - 1) + 1;
                    int extra = spaceCount - (intervalSpaceCount - 1) * (count - 1);
                    StringBuilder sb = new StringBuilder();
                    for (int k = i; k < i + extra; k++) {
                        sb.append(words[k]);
                        for (int l = 0; l < intervalSpaceCount + 1; l++) {
                            sb.append(" ");
                        }
                    }
                    for (int k = i + extra; k < j - 1; k++) {
                        sb.append(words[k]);
                        for (int l = 0; l < intervalSpaceCount; l++) {
                            sb.append(" ");
                        }
                    }
                    sb.append(words[j - 1]);
                    result.add(sb.toString());
                }

                // 再加上j的字符串
                StringBuilder sb = new StringBuilder(words[j]);
                for (int k = words[j].length(); k < maxWidth; k++) {
                    sb.append(" ");
                }
                result.add(sb.toString());
            }
        }
        return result;
    }
}
