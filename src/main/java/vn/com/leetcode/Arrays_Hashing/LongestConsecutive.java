package vn.com.leetcode.Arrays_Hashing;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class LongestConsecutive {


    /**
     * Level: Medium
     * Start: 20:38 26/04/2026
     * End: 21:00 26/04/2026
     */
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        int max = 0;
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i-1] == 1) {
                count++;
            } else if (nums[i] != nums[i-1]) {
                max = Math.max(max, count);
                count = 1;
            }
        }

        return Math.max(count, max);
    }


    public static void main(String[] args) {
        LongestConsecutive object = new LongestConsecutive();
        System.out.println(object.longestConsecutive(new int[]{9,1,4,7,3,-1,0,5,8,-1,6}));
    }
}
