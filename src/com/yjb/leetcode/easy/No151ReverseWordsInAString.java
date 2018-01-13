package com.yjb.leetcode.easy;

/**
 * 151. Reverse Words in a String
 * <p>
 * Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
 * <p>
 * The input string does not contain leading or trailing spaces and the words are always separated by a single space.
 * <p>
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * <p>
 * Could you do it in-place without allocating extra space?
 */
public class No151ReverseWordsInAString {

    public static void main(String[] args) {
        char[] s = "the sky is blue".toCharArray();
        new No151ReverseWordsInAString().mySolution(s);
    }

    // ---- https://www.programcreek.com/2014/05/leetcode-reverse-words-in-a-string-ii-java/ ----
    /**
     * Much better than my solution!
     * <p>
     * eht sky is blue
     * eht yks is blue
     * eht yks si blue
     * eht yks si eulb
     * blue is sky the
     */
    public void reverseWords(char[] s) {
        int i = 0;
        for (int j = 0; j < s.length; j++) {
            if (s[j] == ' ') {
                reverse(s, i, j - 1);
                System.out.println(new String(s));
                i = j + 1;
            }
        }
        reverse(s, i, s.length - 1);
        System.out.println(new String(s));
        reverse(s, 0, s.length - 1);
        System.out.println(new String(s));
    }

    // ---- mySolution ----
    /**
     * sky is blue the
     * is blue sky the
     * blue is sky the
     * blue is sky the
     */
    public void mySolution(char[] s) {
        reverseWords(s, 0, s.length - 1);
    }

    public void reverseWords(char[] s, int left, int right) {
        for (int i = 0; i <= right; i++) {
            if (s[i] == ' ') {
                reverse(s, 0, i - 1);
                reverse(s, i + 1, right);
                reverse(s, left, right);
                System.out.println(new String(s));
                reverseWords(s, 0, right - i - 1);
                break;
            }
        }
    }

    private void reverse(char[] s, int left, int right) {
        if (s == null || s.length == 1)
            return;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
