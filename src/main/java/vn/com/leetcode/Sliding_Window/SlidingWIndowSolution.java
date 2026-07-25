package vn.com.leetcode.Sliding_Window;

import java.util.*;

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

    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        int[] need = new int[128];
        for (char c : t.toCharArray()) {
            need[c]++;
        }

        int left = 0, right = 0;
        int required = t.length();
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        while (right < s.length()) {
            char cRight = s.charAt(right);

            if (need[cRight] > 0) {
                required--;
            }
            need[cRight]--;
            right++;

            while (required == 0) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }

                char cLeft = s.charAt(left);
                need[cLeft]++;

                if (need[cLeft] > 0) {
                    required++;
                }
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        SlidingWIndowSolution solution = new SlidingWIndowSolution();
        System.out.println(solution.minWindow("OUZODYXAZV", "XYZ"));
    }

}
