package vn.com.leetcode.Two_Pointers;

public class ContainerWithMostWater {

    /**
     * Level: Medium
     * Start: 14:27 17/06/2026
     * End: 14:34 17/06/2026
     */
    public int maxArea(int[] heights) {
        int start = 0;
        int end = heights.length - 1;
        int max = 0;
        while (end > 0) {
            int temp = (end - start) * Math.min(heights[start], heights[end]);
            if (max < temp) {
                max = temp;
            }
            start++;
            if (start == end) {
                end--;
                start = 0;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        ContainerWithMostWater object = new ContainerWithMostWater();
        int[] heights = new int[]{1,7,2,5,4,7,3,6};
        System.out.println(object.maxArea(heights));
    }
}
