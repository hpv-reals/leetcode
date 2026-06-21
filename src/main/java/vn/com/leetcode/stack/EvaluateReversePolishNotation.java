package vn.com.leetcode.stack;


import java.util.Stack;

public class EvaluateReversePolishNotation {

    /**
     * Level: Medium
     * Start: 21:02 19/06/2026
     * End: 21:45 19/06/2026
     */
    public int evalRPN(String[] tokens) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        int temp = 0;
        if (tokens.length == 1) {
            return Integer.parseInt(tokens[0]);
        }
        for (String str : tokens) {
            if (!str.equals("+") && !str.equals("-") && !str.equals("*") && !str.equals("/")) {
                stack.add(Integer.valueOf(str));
            } else {
                if (stack.size() == 1) {
                    switch (str) {
                        case "+": {
                            if (temp != 0) {
                                temp += stack.pop();
                            } else {
                                result += stack.pop();
                            }
                            break;
                        }
                        case "-": {
                            if (temp !=0) {
                                temp -= stack.pop();
                            } else {
                                result -= stack.pop();
                            }
                            break;
                        }
                        case "*": {
                            if (temp != 0) {
                                temp *= stack.pop();
                            } else {
                                result *= stack.pop();
                            }
                            break;
                        }
                        case "/": {
                            if (temp != 0) {
                                temp /= stack.pop();
                            } else {
                                result /= stack.pop();
                            }
                            break;
                        }
                    }
                } else if (stack.size() >= 2) {
                    int number2 = stack.pop();
                    int number1 = stack.pop();
                    switch (str) {
                        case "+": {
                            temp = number1 + number2;
                            break;
                        }
                        case "-": {
                            temp = number1 - number2;
                            break;
                        }
                        case "*": {
                            temp = number1 * number2;
                            break;
                        }
                        case "/": {
                            temp = number1 / number2;
                            break;
                        }
                    }
                    if (stack.isEmpty()) {
                        result += temp;
                        temp = 0;
                    }
                } else {
                    switch (str) {
                        case "+": {
                            result += temp;
                            break;
                        }
                        case "-": {
                            result -= temp;
                            break;
                        }
                        case "*": {
                            result *= temp;
                            break;
                        }
                        case "/": {
                            result /= temp;
                            break;
                        }
                    }
                    temp = 0;
                }
            }
        }
        return result + temp;
    }

    public static void main(String[] args) {
        EvaluateReversePolishNotation evaluate = new EvaluateReversePolishNotation();
        String[] tokens = new String[]{"1","2","+","3","*","4","-"};
        System.out.println(evaluate.evalRPN(tokens));
    }
}
