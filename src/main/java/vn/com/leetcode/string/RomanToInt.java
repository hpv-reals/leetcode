package vn.com.leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class RomanToInt {

    /**
     * Start: 15:38 12-04-2026
     * End: 16:00 12-04-2026
     */
    public static int romanToInt(String s) {
        int result = 0;
        int preValue = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            if (value(s.charAt(i)) < preValue) {
                result -= value(s.charAt(i));
            } else {
                result += value(s.charAt(i));
            }
            preValue = value(s.charAt(i));
        }
        return result;
    }

    public static int value(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }
}
