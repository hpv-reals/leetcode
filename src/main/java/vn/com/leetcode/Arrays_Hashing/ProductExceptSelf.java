package vn.com.leetcode.Arrays_Hashing;

import java.util.Arrays;

public class ProductExceptSelf {

    /**
     * Level: Medium
     * Start: 17:37 26/04/2026
     * End: 17:48 26/04/2026
     */
    public int[] productExceptSelf2(int[] nums) {
        int multipleAllNums = 1;
        int count = 0;
        for (int num : nums) {
            if (num == 0) {
                count++;
                continue;
            }
            multipleAllNums *= num;
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (count > 0) {
                if (nums[i] == 0 && count == 1) {
                    result[i] = multipleAllNums;
                } else {
                    result[i] = 0;
                }
            } else {
                result[i] = multipleAllNums/nums[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ProductExceptSelf product = new ProductExceptSelf();
        int[] result = product.productExceptSelf2(new int[]{-1,0,1,2,3});
        System.out.println(Arrays.toString(result));
    }
}
