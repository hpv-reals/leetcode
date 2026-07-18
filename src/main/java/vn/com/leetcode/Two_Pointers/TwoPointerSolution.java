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

    public static void main(String[] args) {
        TwoPointerSolution solution = new TwoPointerSolution();

        System.out.println(solution.validPalindrome("abc"));
    }


}
