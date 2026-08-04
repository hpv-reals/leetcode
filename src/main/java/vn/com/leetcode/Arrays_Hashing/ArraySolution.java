package vn.com.leetcode.Arrays_Hashing;

import java.util.Arrays;
import java.util.PriorityQueue;

public class ArraySolution {

    public String longestCommonPrefix(String[] strs) {
        String commonString = strs[0];
        for (String str : strs) {
            if (commonString.isBlank() || str.isBlank()) {
                return "";
            }
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < commonString.length(); i++) {
                if (i >= str.length()) {
                    commonString = temp.toString();
                    break;
                }
                if (str.charAt(i) == commonString.charAt(i)) {
                    temp.append(str.charAt(i));
                } else {
                    commonString = temp.toString();
                }
            }
        }
        return commonString;
    }


    public int[] getConcatenation(int[] nums) {
        int length = nums.length;
        int[] newNums = new int[length * 2];
        System.arraycopy(nums,0, newNums,0, length);
        System.arraycopy(nums,0, newNums, length, length);
        return newNums;
    }

    public int removeElement(int[] nums, int val) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (val != nums[i]) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }

    public int[] sortArray(int[] nums) {
        int length = nums.length;
        int left = 0, right = length - 1;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        while (left <= right) {
            if (left == right) {
                queue.add(nums[left++]);
            } else {
                queue.add(nums[left++]);
                queue.add(nums[right--]);
            }
        }
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = queue.poll();
        }
        return result;
    }

    public void sortColors(int[] nums) {
        int left = 0, mid = 0, right = nums.length - 1;
        while (mid <= right) {
            if (nums[mid] == 0) {
                int temp = nums[left];
                nums[left] = nums[mid];
                nums[mid] = temp;
                left++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                int temp = nums[right];
                nums[right] = nums[mid];
                nums[mid] = temp;
                right--;
            }
        }
    }

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length - 1;
        if (nums[n] == nums[n/2]) {
            return nums[n];
        }

        return nums[0];
    }

    public int maxProfit(int[] prices) {
        int buy = prices[0];
        int sell = 1;
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] <= buy) {
                buy = prices[i];
                sell = prices[i];
            } else {
                while (i< prices.length && sell <= prices[i]) {
                    sell = prices[i];
                    i++;
                }
                sum += sell - buy;
                if (i > prices.length - 1) {
                    return sum;
                }
                buy = prices[i];
                sell = prices[i];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        ArraySolution solution = new ArraySolution();
        int[] array  =new int[]{1,2,3,4,5};
        System.out.println(solution.maxProfit(array));
    }
}
