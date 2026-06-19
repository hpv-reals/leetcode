package vn.com.leetcode.Arrays_Hashing;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(1 << nums.length);
        result.add(new ArrayList<>());

        for (int num : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSubset = new ArrayList<>(result.get(i));
                newSubset.add(num);
                result.add(newSubset);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Subsets solution = new Subsets();
        int[] nums = {0};
        List<List<Integer>> result = solution.subsets(nums);
        System.out.println(result);
    }
}
