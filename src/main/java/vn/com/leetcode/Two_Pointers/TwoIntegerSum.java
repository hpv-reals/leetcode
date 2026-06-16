package vn.com.leetcode.Two_Pointers;

public class TwoIntegerSum {

    /**
     * Level: Medium
     * Start: 10:57 16/06/2026
     * End: 11:20 16/06/2026
     */
    public int[] twoSum(int[] numbers, int target) {
        int index1 = 0;
        int index2 = 1;
        do {
            if (numbers[index1] + numbers[index2] == target) {
                return new int[] {index1 + 1, index2 + 1};
            }
            if (index2 == numbers.length - 1) {
                index1++;
                index2 = index1 + 1;
            } else{
                index2++;
            }
        } while (index1 != numbers.length - 1);
        return new int[]{};
    }

    public static void main(String[] args) {
        TwoIntegerSum twoIntegerSum = new TwoIntegerSum();
        int[] number = new int[] {2,3,4};
        int[] result = twoIntegerSum.twoSum(number, 6);
        for (int ele : result) {
            System.out.print(ele + ",");
        }

        System.out.println();
    }

}
