package vn.com.leetcode.Sliding_Window;

import java.util.HashMap;
import java.util.Map;

public class PermutationString {

    /**
     * Level: Medium
     * Start: 19:02 20/06/2026
     * End: 20:00 20/06/2026
     */
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();

        if (len1 > len2) return false;

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len1; i++) {
            Integer count = map.get(s1.charAt(i));
            if (count != null) {
                map.put(s1.charAt(i), ++count);
            } else {
                map.put(s1.charAt(i), 1);
            }
        }

        for (int i = 0; i <= len2 - len1; i++) {
            Map<Character, Integer> mapTemp = new HashMap<>(map);

            for (int j = i; j < i + len1; j++) {
                char temp = s2.charAt(j);

                if (mapTemp.containsKey(temp)) {
                    int countChar = mapTemp.get(temp) - 1;
                    if (countChar > 0) {
                        mapTemp.put(temp, countChar);
                    } else {
                        mapTemp.remove(temp);
                    }
                } else {
                    break;
                }
            }

            if (mapTemp.isEmpty()) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        PermutationString object = new PermutationString();
        System.out.println(object.checkInclusion("adc", "dcda"));
    }
}
