package vn.com.leetcode.binary_search;

public class BinarySearch {

    /**
     * Level: Easy
     * Start: 16:37 21/06/2026
     * End: 16:48 21/06/2026
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
     * Start: 16:50 21/06/2026
     * End: 17:13 21/06/2026
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
     * Start: 17:24 21/06/2026
     * End: 17:54 21/06/2026
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

    /**
     * Level: Medium
     * Start: 10:03 22/06/2026
     * End: 10:50 22/06/2026
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }

    /**
     * Level: Medium
     * Start: 10:55 22/06/2026
     * End:  22/06/2026
     */
    public int search2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // Trường hợp 1: Nửa TỪ LEFT ĐẾN MID đã sắp xếp chuẩn
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // Trường hợp 2: Nửa TỪ MID ĐẾN RIGHT đã sắp xếp chuẩn
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * Level: Medium
     * Start: 23/06/2026
     * End:  23/06/2026
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result = 0.0;



        return result;
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[] nums1 = {1,2};
        int[] nums2 = {3};

        System.out.println(binarySearch.findMedianSortedArrays(nums1, nums2));
    }
}
