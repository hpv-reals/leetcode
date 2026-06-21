package vn.com.leetcode.binary_search;

public class BinarySearch {

    /**
     * Level: Easy
     * Start: 16:37 19/06/2026
     * End: 16:48 19/06/2026
     */
    public int search(int[] nums, int target) {
        int result = -1;

        int left = 0,  right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int value = nums[mid];
            if (value == target) {
                return mid;
            } else if (target > value) {
                left = ++mid;
            } else {
                right = --mid;
            }
        }

        return result;
    }

    /**
     * Level: Medium
     * Start: 16:50 19/06/2026
     * End: 17:13 19/06/2026
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        boolean result = false;

        int left = 0, right = matrix.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            int[] numbs = matrix[mid];
            int firstValue = numbs[0];
            int lastValue = numbs[numbs.length - 1];
            if (target >= firstValue && target <= lastValue) {
                return search(numbs, target) != -1;
            } else if (target > lastValue) {
                left = mid + 1;
            } else  {
                right = mid - 1;
            }
        }

        return result;
    }

    public int findMaxEle(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (max < num) {
                max = num;
            }
        }
        return  max;
    }

    public int totalHours(int[] piles, int speed) {
        int total = 0;
        for (int pile : piles) {
            total += (pile + speed - 1) / speed;
        }
        return total;
    }

    /**
     * Level: Medium
     * Start: 17:24 19/06/2026
     * End:  19/06/2026
     */
    public int minEatingSpeed(int[] piles, int h) {
        int lastValue = 0;
        int left = 0, right = findMaxEle(piles);

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid == 0) {
                return lastValue;
            }
            if (totalHours(piles, mid) <= h) {
                lastValue = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }


        }

        return lastValue;
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[] nums = {25,10,23,4};

        System.out.println(binarySearch.minEatingSpeed(nums, 4));
    }
}
