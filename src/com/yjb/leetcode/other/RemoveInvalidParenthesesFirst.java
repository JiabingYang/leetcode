package com.yjb.leetcode.other;

/**
 * 301. Remove Invalid Parenthesess的变种
 * <p>
 * 简单版：只输出第一个valid的（301. Remove Invalid Parenthesess中的结果中输出其中一个就行，所以不需要从lastJ开始找删除，直接删除i位置即可）
 */
public class RemoveInvalidParenthesesFirst {

    /**
     * https://github.com/tongzhang1994/Facebook-Interview-Coding/blob/master/301.%20Remove%20Invalid%20Parentheses.java
     * <p>
     * 思路：按照301. Remove Invalid Parentheses判断isValid的思路，只要遇到stack<0就remove，完了之后reverse再来一次。
     * <p>
     * Time: O(n), 2 pass
     */
    private static String solution1(String s) {
        // 保证右括号不多（删")"）
        String r = remove(s, new char[]{'(', ')'});
        // 保证左括号不多（删"("）
        String tmp = remove(new StringBuilder(r).reverse().toString(), new char[]{')', '('});
        return new StringBuilder(tmp).reverse().toString();
    }

    private static String remove(String s, char[] p) {
        int stack = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == p[0]) {
                stack++;
            }
            if (s.charAt(i) == p[1]) {
                stack--;
            }
            if (stack < 0) {
                s = s.substring(0, i) + s.substring(i + 1);
                i--; // 从删除位置下一个字符开始下次遍历
                stack = 0;
            }
        }
        return s;
    }
}
