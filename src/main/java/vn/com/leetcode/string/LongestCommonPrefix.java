package vn.com.leetcode.string;

public class LongestCommonPrefix {

    /**
     * Level: Easy
     * Start: 20:47 20/04/2026
     * End: 21:02 20/04/2026
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        StringBuilder commonPrefix = new StringBuilder(strs[0]);
        for (int i = 1; i < strs.length; i++) {
            commonPrefix.replace(0, commonPrefix.length(), commonString(commonPrefix.toString(), strs[i]));
            if (commonPrefix.toString().isEmpty()) {
                return "";
            }
        }
        return commonPrefix.toString();
    }

    public String commonString(String str1, String str2) {
        int max = Math.min(str1.length(), str2.length());
        int maxIndex = 0;
        for (int i = 0; i < max; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return str1.substring(0, i);
            } else {
                maxIndex++;
            }
        }
        if (maxIndex > 0) {
            return str1.substring(0, maxIndex);
        }
        return "";
    }

    public static void main(String[] args) {
        LongestCommonPrefix obj = new LongestCommonPrefix();
        System.out.println(obj.longestCommonPrefix(new String[]{"dog","racecar","car"}));
    }
}
