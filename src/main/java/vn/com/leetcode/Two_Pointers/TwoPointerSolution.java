package vn.com.leetcode.Two_Pointers;

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

    public static void main(String[] args) {
        TwoPointerSolution solution = new TwoPointerSolution();

        System.out.println(solution.validPalindrome("abc"));
    }


}
