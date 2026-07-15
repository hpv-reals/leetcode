package vn.com.leetcode.stack;

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

}
