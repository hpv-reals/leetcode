package vn.com.leetcode.Two_Pointers;

public class ValidPalindrome {

    /**
     * Level: Easy
     * Start: 10:28 16/06/2026
     * End: 10:45 16/06/2026
     */
    public boolean isPalindrome(String s) {

//        // Ignore all character that are not letters (A-Z, a-z) and numbers (0-9)
//        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
//        System.out.println(s);
//        int start = 0;
////        if (s.length() == 2) {
////            return s.charAt(0) == s.charAt(1);
////        }
//        for (int i = s.length() - 1; i >= s.length()/2; i--) {
//            if (s.charAt(i) != s.charAt(start)) {
//                return false;
//            }
//            start++;
//        }
//        return true;


        int start = 0;
        int end = s.length() -1;
        while (start < end) {
            while (!Character.isLetterOrDigit(s.charAt(start)) && start < end) {
                start++;
            }
            while (!Character.isLetterOrDigit(s.charAt(end)) && start < end) {
                end--;
            }

            if (start > end || Character.toLowerCase(s.charAt(start))
                != Character.toLowerCase(s.charAt(end))) {
                return false;
            }
            start++;
            end--;
        }
        return true;

    }

    public static void main(String[] args) {
        ValidPalindrome validPalindrome = new ValidPalindrome();
        System.out.println(validPalindrome.isPalindrome("ab"));
    }

}
