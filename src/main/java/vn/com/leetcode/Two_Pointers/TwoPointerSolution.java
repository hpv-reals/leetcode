package vn.com.leetcode.Two_Pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointerSolution {

    public void reverseString(char[] s) {
        if (s.length == 1) {
            return;
        }
        char temp;
        int left = 0, right = s.length - 1;

        while (left < right) {
            temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public String mergeAlternately(String word1, String word2) {
        StringBuilder stringBuilder = new StringBuilder();
        int x = word1.length() - 1;
        int y = word2.length() - 1;
        int length = Math.max(x, y);
        for (int i = 0; i <= length; i++) {
            if (i <= x) {
                stringBuilder.append(word1.charAt(i));
            }
            if (i <= y) {
                stringBuilder.append(word2.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) return result;

        int n = nums.length;
        Arrays.sort(nums);

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (sum > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return result;
    }

    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1) return;
        int length = nums.length;
        int start = length - k;
        int[] result = new int[length];
        System.arraycopy(nums, start, result, 0, k);
        System.arraycopy(nums, 0, result, k, start);
        System.arraycopy(result, 0, nums, 0, length);
    }

    public static void main(String[] args) {
        TwoPointerSolution sol = new TwoPointerSolution();


        // Định nghĩa các test case
        int[][] nums = {
            {3, 2, 3, -3, 1, 0},        // Case 1: Tổng quát
            {1, -1, 1, -1, 1, -1},      // Case 2: Nhiều số trùng nhau
            {0, 0, 0, 0},               // Case 3: Bộ tứ giống nhau
            {1000000000, 1000000000, 1000000000, 1000000000} // Case 4: Tràn số
        };
        int[] targets = {3, 2, 0, 400000000};

        // Chạy kiểm thử
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> output = sol.fourSum(nums[i], targets[i]);
            System.out.println("Test " + (i + 1) + ": target = " + targets[i]);
            System.out.println("Input:  " + Arrays.toString(nums[i]));
            System.out.println("Result: " + output);
            System.out.println("-----------------------------------");
        }
    }


}
