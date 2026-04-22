package vn.com.leetcode.Arrays_Hashing;

import java.util.*;

public class GroupAnagrams {

    /**
     * Level: Medium
     * Start: 21:45 22/04/2026
     * End: 22:30 22/04/2026
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = Arrays.toString(charArray);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strs = new String[] {"act","pots","tops","cat","stop","hat"};
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        List<List<String>> result = groupAnagrams.groupAnagrams(strs);
        for (List<String> subList : result) {
            for (String str : subList) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }
}
