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

    public static void main(String[] args) {
        SolutionStack sol = new SolutionStack();

        System.out.println(sol.carFleet(10, new int[]{1, 4}, new int[]{3, 2}));
        System.out.println(sol.carFleet(10, new int[]{4, 1, 0, 7}, new int[]{2, 2, 1, 1}));
    }

}
