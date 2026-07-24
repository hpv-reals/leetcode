package vn.com.leetcode.Sliding_Window;

import javax.swing.plaf.InsetsUIResource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlidingWIndowSolution {

    public int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        if (nums[0] >= target || nums[nums.length - 1] >= target) {
            return 1;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            int minLengthTemp = 1;
            int sumTemp = nums[i];
            if (sumTemp >= target) {
                return minLengthTemp;
            }
            for (int j = i + 1; j < nums.length; j++) {
                sumTemp += nums[j];
                minLengthTemp++;
                if (sumTemp >= target) {
                    minLength = Math.min(minLength, minLengthTemp);
                    break;
                }
            }
        }
        if (minLength <= nums.length) {
            return minLength;
        }
        return 0;
    }


    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }

}
