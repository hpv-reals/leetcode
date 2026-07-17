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


}
