package vn.com.leetcode.Sliding_Window;

public class LongestRepeating {

    /**
     * Level: Medium
     * Start: 18:08 20/06/2026
     * End:  20/06/2026
     */
    public int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0;
        int maxLength = 0;
        int maxFreq = 0; // Lưu số lần xuất hiện của ký tự nhiều nhất trong cửa sổ

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            count[currentChar - 'A']++;
            maxFreq = Math.max(maxFreq, count[currentChar - 'A']);
            while ((right - left + 1) - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LongestRepeating object = new LongestRepeating();
        System.out.println(object.characterReplacement("AAAABBBBAABABBBAAAA", 3)); // expected: 5 (Lệch đầu)
        System.out.println(object.characterReplacement("ABBBBA", 1)); // expected: 5 (Nằm giữa)

    }
}
