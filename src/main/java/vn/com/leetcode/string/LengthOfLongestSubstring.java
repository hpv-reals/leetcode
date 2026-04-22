package vn.com.leetcode.string;

public class LengthOfLongestSubstring {

    // Start:   15:50 04-04-2026
    // End:     17:23 04-04-2026
    public static int lengthOfLongestSubstring(String s) {
        int max = 0, left = 0;
        int[] lastIndex = new int[256];

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            left = Math.max(left, lastIndex[c]);
            max = Math.max(max, right - left + 1);
            lastIndex[c] = right + 1;
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "ckilbkd";
        System.out.println(s + ":" + lengthOfLongestSubstring(s));
        s = "   ";
        System.out.println(s + ":" + lengthOfLongestSubstring(s));
        s = "abcabcbb";
        System.out.println(s + ":" + lengthOfLongestSubstring(s));
        s = "pwwkew";
        System.out.println(s + ":" + lengthOfLongestSubstring(s));
    }
}
