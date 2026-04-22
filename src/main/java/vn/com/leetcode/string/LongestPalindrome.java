package vn.com.leetcode.string;

public class LongestPalindrome {

//    Given a string s, return the longest palindromic substring in s.
//
//    Example 1:
//    Input: s = "babad"
//    Output: "bab"
//    Explanation: "aba" is also a valid answer.

//    Example 2:
//    Input: s = "cbbd"
//    Output: "bb"
//
//    Constraints:
//    1 <= s.length <= 1000
//    s consist of only digits and English letters.



    /**
     * Start:   20:35 04-04-2026
     * End:     21:40 04-04-2026
     * @param s
     * @return
     */


    public static String longestPalindromeFastest(String s) {
        int n = s.length();
        String res = "";

        for(int i = 0;i<n;i++){
            // Odd length palindrome
            int st = i,end = i;
            while(st>=0&&end<n&&s.charAt(st)==s.charAt(end)){
                st--;
                end++;
            }
            String temp = s.substring(st+1,end);
            if(temp.length()>res.length())res=temp;

            // Even length palindrome
            st = i;
            end = i+1;
            while(st>=0&&end<n&&s.charAt(st)==s.charAt(end)){
                st--;
                end++;
            }
            temp = s.substring(st+1,end);
            if(temp.length()>res.length())res=temp;
        }

        return res;
    }

    public static String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }

        String sub = s.substring(0, 1);
        for (int i = s.length() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (i - j >= sub.length() && isPalindrome(s.substring(j, i+1))) {
                    sub = s.substring(j, i+1);
                }
            }
        }
        return sub;
    }

    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
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
        long start = System.currentTimeMillis();
        System.out.println(longestPalindrome("abcabcabcbacbacbaa"));
        System.out.println(System.currentTimeMillis() - start);
    }
}
