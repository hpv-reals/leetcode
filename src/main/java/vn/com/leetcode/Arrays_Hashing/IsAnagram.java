package vn.com.leetcode.Arrays_Hashing;

import java.util.HashMap;
import java.util.Hashtable;

public class IsAnagram {

    /**
     * Level: Easy
     * Start: 22:10 20/04/2026
     * End: 22:23 20/04/2026
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Integer> sTable = new HashMap<>();
        HashMap<Character, Integer> tTable = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                sTable.put(s.charAt(i), sTable.getOrDefault(s.charAt(i), 0) + 1);
                tTable.put(t.charAt(i), tTable.getOrDefault(t.charAt(i), 0) + 1);
            }
        }
        return tTable.equals(sTable);
    }

    public static void main(String[] args) {
        IsAnagram isAnagram = new IsAnagram();
        System.out.println(isAnagram.isAnagram("anagram", "nagaram"));
    }
}
