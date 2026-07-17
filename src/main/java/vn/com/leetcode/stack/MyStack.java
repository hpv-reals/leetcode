package vn.com.leetcode.stack;

public class MyStack {

    int capacity = 10;
    int[] element = new int[capacity];
    int length = 0;
    public MyStack() {

    }

    public void push(int x) {
        if (length == capacity) {
            capacity *= 2;
            element = java.util.Arrays.copyOf(element, capacity);
        }
        element[length++] = x;
    }

    public int pop() {
        if (length == 0) {
            return element[0];
        }
        return element[--length];
    }

    public int top() {
        if (length == 0) {
            return element[0];
        }
        return element[length - 1];
    }

    public boolean empty() {
        return length == 0;
    }
}
