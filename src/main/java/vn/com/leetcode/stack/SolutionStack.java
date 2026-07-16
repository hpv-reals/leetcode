package vn.com.leetcode.stack;

import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

public class SolutionStack {



    public int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack<>();
        for (String operation : operations) {
            if (Objects.equals(operation, "D")) {
                if (!stack.isEmpty()) {
                    stack.push(stack.peek() * 2);
                }
            } else if (Objects.equals(operation, "C")) {
                stack.pop();
            } else if (Objects.equals(operation, "+")) {
                if (stack.size() > 1) {
                    int temp = stack.pop();
                    int temp2 = stack.peek() + temp;
                    stack.push(temp);
                    stack.push(temp2);
                }
            } else {
                stack.push(Integer.parseInt(operation));
            }
        }
        int total = 0;
        for (int num : stack) {
            total += num;
        }
        return total;
    }


    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "+": {
                    stack.push(stack.pop() + stack.pop());
                    break;
                }
                case "-": {
                    int x = stack.pop();
                    int y = stack.pop();
                    stack.push(y - x);
                    break;
                }
                case "*": {
                    stack.push(stack.pop() * stack.pop());
                    break;
                }
                case "/": {
                    int x = stack.pop();
                    int y = stack.pop();
                    stack.push(y / x);
                    break;
                }
                default: {
                    stack.push(Integer.parseInt(token));
                    break;
                }
            }
        }
        return stack.pop();
    }

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int asteroid : asteroids) {
            boolean destroyed = false;

            while (!stack.isEmpty() && asteroid < 0 && stack.peek() > 0) {
                if (stack.peek() + asteroid < 0) {
                    stack.pop();
                } else if (stack.peek() + asteroid == 0) {
                    stack.pop();
                    destroyed = true;
                    break;
                } else {
                    destroyed = true;
                    break;
                }
            }

            if(!destroyed) {
                stack.push(asteroid);
            }
        }

        int[] result = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }

    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                result[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        SolutionStack sol = new SolutionStack();
        // Định nghĩa các test case
        int[][] testCases = {
            {73, 74, 75, 71, 69, 72, 76, 73}, // Test 1: Tổng quát
            {30, 40, 50, 60},                 // Test 2: Tăng dần
            {30, 60, 90},                     // Test 3: Nhảy vọt
            {89, 62, 70, 58, 47, 47, 46, 76}, // Test 4: Nhiều giá trị nhỏ
            {55, 38, 53, 81, 61, 93, 97, 34}  // Test 5: Hỗn hợp
        };

        // Chạy kiểm thử và in kết quả
        for (int i = 0; i < testCases.length; i++) {
            int[] result = sol.dailyTemperatures(testCases[i]);
            System.out.println("Test " + (i + 1) + ": " + Arrays.toString(testCases[i]));
            System.out.println("Result: " + Arrays.toString(result));
            System.out.println("-----------------------------------");
        }
    }

}
