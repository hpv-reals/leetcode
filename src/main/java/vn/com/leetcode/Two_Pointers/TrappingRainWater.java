package vn.com.leetcode.Two_Pointers;

public class TrappingRainWater {
    /**
     * Level: Hard
     * Start: 11:28 18/06/2026
     * End: 11:50 18/06/2026
     */
    public int trap(int[] height) {
        int total = 0;
        int currentIndex = 0;

        int startIndexSum = 0;
        int maxEndIndexSum = 0;

        boolean startSum = false;
        boolean isDraft = true;

        int minus = 0;

        while (currentIndex < height.length - 1) {
            int currentNumber = height[currentIndex];
            if (currentNumber == 0 && isDraft) {
                continue;
            } else {
               if (currentNumber == 0) {
                    minus += currentNumber;
               } else {
                   if (!startSum && height[currentIndex + 1] == 0) {
                       startSum = true;
                       startIndexSum = currentIndex;
                       continue;
                   }
                   if (startSum) {
                       currentInde
                   }
                   isDraft = false;
               }
            }

            currentIndex++;
        }
        return total;
    }

    public static void main(String[] args) {
        TrappingRainWater trappingRainWater = new TrappingRainWater();
        int[] height = new int[]{0,2,0,3,1,0,1,3,2,1};
        System.out.println(trappingRainWater.trap(height));
    }

}
