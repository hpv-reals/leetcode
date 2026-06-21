package vn.com.leetcode.Sliding_Window;

public class BestTimeTradingStock {

    /**
     * Level: Easy
     * Start: 21:49 19/06/2026
     * End: 21:55 19/06/2026
     */
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = prices.length - 1; j > i; j--) {
                int temp = prices[j] - prices[i];
                if (temp > max) {
                    max = temp;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        BestTimeTradingStock object = new BestTimeTradingStock();
        int[] prices = new int[]{10,8,7,5,2};
        System.out.println(object.maxProfit(prices));
    }
}
