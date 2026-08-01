package vn.com.leetcode.Arrays_Hashing;

import java.util.PriorityQueue;
import java.util.Queue;

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

    public static void main(String[] args) {
        ArraySolution solution = new ArraySolution();
        int[] array  =new int[]{5,10,2,1,3};
        int[] array2 = solution.sortArray(array);
        System.out.println("");
    }
}
