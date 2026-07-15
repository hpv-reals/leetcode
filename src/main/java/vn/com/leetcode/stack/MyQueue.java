package vn.com.leetcode.stack;

public class MyQueue {
    int capacity = 10;
    int[] element = new int[capacity];
    int length = 0;
    public MyQueue() {

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
        int result = element[0];
        int[] newElement = new int[capacity];
        System.arraycopy(element, 1, newElement, 0, element.length - 1);
        element = newElement;
        length--;
        return result;
    }

    public int peek() {
        return element[0];
    }

    public boolean empty() {
        return length == 0;
    }
}
