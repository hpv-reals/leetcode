package vn.com.leetcode.Sliding_Window;

import java.util.*;

public class LongestSubstring {
    /**
     * Level: Easy
     * Start: 21:57 19/06/2026
     * End: 22:40 19/06/2026
     */
    public int lengthOfLongestSubstring(String s) {
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                if (max < set.size()) {
                    max = set.size();
                }
                set = newSet(set, s.charAt(i));
            } else {
                set.add(s.charAt(i));
            }
        }
        return Math.max(max, set.size());
    }

    public LinkedHashSet<Character> newSet (LinkedHashSet<Character> linkedSet, Character ch) {
        LinkedHashSet<Character> newSet = new LinkedHashSet<>();
        boolean start = false;
        for (Character c : linkedSet){
            if (start) {
                newSet.add(c);
            } else if (c.equals(ch)) {
                start = true;
            }
        }
        newSet.add(ch);
        return newSet;
    }

    public static void main(String[] args) {
        LongestSubstring object = new LongestSubstring();
        System.out.println(object.lengthOfLongestSubstring("1a2b3c4d5e6f7g8h9i0j1k2l3m4n5o6p7q8r9s0t1u2v3w4x5y6z7A8B9C0D1E2F3G4H5I6J7K8L9M0N1O2P3Q4R5S6T7U8V9W0X1Y2Z3"));
    }
}
