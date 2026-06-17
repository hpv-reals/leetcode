package vn.com.leetcode.Two_Pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ThreeSum {

    /**
     * Level: Medium
     * Start: 13:41 17/06/2026
     * End: 14:21 17/06/2026
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int end = nums.length -1;
        int mid = end -1;
        int start = 0;
        Arrays.sort(nums);
        while (end > 1) {
            if (nums[start] + nums[mid] + nums[end] == 0) {
                List<Integer> element = new ArrayList<>();
                element.add(nums[start]);
                element.add(nums[mid]);
                element.add(nums[end]);
                element = element.stream().sorted().collect(Collectors.toList());
                addToList(result, element);
            }
            start++;
            if (mid == start) {
                mid--;
                start = 0;
            }
            if (mid == 0){
                end--;
                mid = end - 1;
                start = 0;
            }
        }
        return result;
    }

    public void addToList(List<List<Integer>> result, List<Integer> list) {
        for (List<Integer> element : result) {
            if (element.equals(list)){
                return;
            }
        }
        result.add(list);
    }

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        List<List<Integer>> result = threeSum.threeSum(nums);
        for (List<Integer> element : result) {
            for (Integer number : element) {
                System.out.print(number + ", ");
            }
            System.out.println();
        }
    }

}
