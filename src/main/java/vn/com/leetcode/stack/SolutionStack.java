package vn.com.leetcode.stack;

import java.util.Arrays;
import java.util.Collections;
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

    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        if (n == 0) return 0;

        double[][] cars = new double[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }

        Arrays.sort(cars, (a, b) -> Double.compare(a[0], b[0]));

        double lastTime = 0.0;
        int fleetNumber = 0;
        for (int i = n - 1; i >= 0; i--) {
            double time = ((double) target - cars[i][0])/cars[i][1];
            if (time > lastTime) {
                fleetNumber++;
                lastTime = time;
            }
        }
        return fleetNumber;
    }

    public String simplifyPath(String path) {
        if (path.length() <= 0 || path.equals("/.")) {
            return "/";
        }

        while (path.contains("//") || path.contains("/./") ) {
            path = path.replaceAll("//", "/");
            path = path.replaceAll("/\\./", "//");
        }
        Stack<String> stack  = new Stack<>();
        String[] arrayString = path.substring(1).split("/");
        for (String str : arrayString) {
            if (str.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(str);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        Collections.reverse(stack);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("/");
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop()).append("/");
        }

        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;
        int index = 0;
        while (index < s.length()) {
            char x = s.charAt(index);
            if (x >= 48 && x <= 57) {
                k = k * 10 + (x - '0');
            } else if (x >= 97 && x <= 122) {
                currentString.append(x);
            } else if (x == '[') {
                countStack.push(k);
                k = 0;
                stringStack.push(currentString.toString());
                currentString = new StringBuilder();
            } else if (x == ']') {
                int count = countStack.pop();
                String strPrevious = stringStack.pop();
                StringBuilder temp = new StringBuilder();
                while (count > 0) {
                    temp.append(currentString);
                    count--;
                }
                currentString = new StringBuilder();
                currentString.append(strPrevious).append(temp);
            }
            index++;
        }
        return currentString.toString();
    }

    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= heights.length; i++) {
            int h;
            if (i == heights.length) {
                h = 0;
            } else {
                h = heights[i];
            }

            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int width;
                int currentIndex = stack.pop();
                if (stack.isEmpty()) {
                    width = i;
                } else {
                    width = i - stack.peek() - 1;
                }
                maxArea = Math.max(maxArea, heights[currentIndex] * width);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        SolutionStack sol = new SolutionStack();
        // Test Case 1: Ví dụ cơ bản từ đề bài
        int[] heights1 = {7, 1, 7, 2, 2, 4};
        System.out.println("Test 1 Result: " + sol.largestRectangleArea(heights1)); // Expected output: 8

        // Test Case 2: Mảng tăng dần
        int[] heights2 = {1, 3, 7};
        System.out.println("Test 2 Result: " + sol.largestRectangleArea(heights2)); // Expected output: 7

        // Test Case 3: Mảng giảm dần
        int[] heights3 = {5, 4, 3, 2, 1};
        System.out.println("Test 3 Result: " + sol.largestRectangleArea(heights3)); // Expected output: 9

        // Test Case 4: Các cột có chiều cao bằng nhau
        int[] heights4 = {2, 2, 2, 2};
        System.out.println("Test 4 Result: " + sol.largestRectangleArea(heights4)); // Expected output: 8

        // Test Case 5: Mảng có chứa giá trị 0
        int[] heights5 = {2, 1, 5, 6, 2, 3};
        System.out.println("Test 5 Result: " + sol.largestRectangleArea(heights5)); // Expected output: 10
    }

}
