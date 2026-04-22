package vn.com.leetcode.Arrays_Hashing;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] result = twoSun(new int[]{5,5}, 10);
        System.out.println(result[0] + "-" + result[1]);
    }

    public static int[] twoSun(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int leftNumber = target - nums[i];
            if (map.containsKey(leftNumber)) {
                return new int[] {map.get(leftNumber), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
