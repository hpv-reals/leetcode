package vn.com.leetcode.Arrays_Hashing;

import java.util.*;

public class EncodeAndDecodeString {


    private static final String SPLITTER = "#";

    /**
     * Level: Medium
     * Start: 09:08 25/04/2026
     * End: 09:46 25/04/2026
     */
    public String encode(List<String> strs) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strs) {
            stringBuilder.append(str.length())
                    .append(SPLITTER)
                    .append(str);
        }
        return stringBuilder.toString();
    }

    public List<String> decode(String str) {
        int i = 0;
        List<String> result = new ArrayList<>();
        while (i < str.length()) {
            int startIndexStr = str.indexOf(SPLITTER, i);
            int length = Integer.parseInt(str.substring(i, startIndexStr));
            String element = str.substring(startIndexStr + 1, startIndexStr + length + 1);
            result.add(element);
            i = element.length() + startIndexStr + 1;
        }
        return result;
    }

    public static void main(String[] args) {
        EncodeAndDecodeString object = new EncodeAndDecodeString();
        String encodeString = object.encode(Arrays.asList("Hello", "World", "", "abcdefghijklmnopqrstabcdefghijklmnopqrstabcdefghijklmnopqrstabcdefghijklmnopqrst", "a", "rrr"));
        List<String> decodeList = object.decode(encodeString);
        for (String str : decodeList) {
            System.out.println(str);
        }
    }
}
