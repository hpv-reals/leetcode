package vn.com.leetcode.stack;

import java.util.Stack;

public class StockSpanner {

    private Stack<int[]> stack;
    public StockSpanner() {
        stack = new Stack<>();
    }
    public int next(int price) {
        int span = 1;

        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1];
        }
        stack.push(new int[]{price, span});
        return stack.peek()[1];
    }



    public static void main(String[] args) {
        StockSpanner stockSpanner = new StockSpanner();
        System.out.println(stockSpanner.next(100)); // return 1
        System.out.println(stockSpanner.next(80)); // return 1
        System.out.println(stockSpanner.next(60)); // return 1
        System.out.println(stockSpanner.next(70)); // return 2
        System.out.println(stockSpanner.next(60)); // return 1
        System.out.println(stockSpanner.next(75)); // return 4
        System.out.println(stockSpanner.next(85)); // return 6
    }

}
