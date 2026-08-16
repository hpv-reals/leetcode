package vn.com.leetcode.Arrays_Hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int currentSum = 0;

        Map<Integer, Integer> prefixSumCount = new HashMap<>();

        prefixSumCount.put(0, 1);

        for (int num : nums) {
            currentSum += num;
            int neededSum = currentSum - k;
            if (prefixSumCount.containsKey(neededSum)) {
                count += prefixSumCount.get(neededSum);
            }
            prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }

    public int majorityElement(int[] nums) {
        int length = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int current = map.getOrDefault(num, 0);
            current++;
            map.put(num, current);
            if (current > length / 2) {
                return num;
            }
        }
        return 0;
    }

//    public List<Integer> majorityElement(int[] nums) {
//        int length = nums.length;
//        List<Integer> list = new ArrayList<>();
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int num : nums) {
//            map.put(num, map.getOrDefault(num, 0) + 1);
//        }
//
//        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            if (entry.getValue() > length/3) {
//                list.add(entry.getKey());
//            }
//        }
//        return list;
//    }

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        int i = 0;
        while (i < n) {
            int correctIndex = nums[i] - 1;

            if (nums[i] > 0 && nums[i] <= n && nums[i] != nums[correctIndex]) {
                int temp = nums[i];
                nums[i] = nums[correctIndex];
                nums[correctIndex] = temp;
            } else {
                i++;
            }
        }


        for (int j = 0; j < n; j++) {
            if ( nums[j] != j + 1) {
                return j + 1;
            }
        }

        return n + 1;
    }

    public static void main(String[] args) {
        ArraySolution solution = new ArraySolution();
        int[] array  =new int[]{1,2,3,4,5};
        System.out.println(solution.maxProfit(array));
    }
}
