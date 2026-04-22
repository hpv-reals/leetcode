package vn.com.leetcode.Arrays_Hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HasDuplicate {

    /**
     * Level: Easy
     * Start: 21:24 20/04/2026
     * End: 21:27 20/04/2026
     */
    public boolean hasDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                return true;
            } else {
                map.put(num, null);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        HasDuplicate hasDuplicate = new HasDuplicate();
        System.out.println(hasDuplicate.hasDuplicate(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }
}
