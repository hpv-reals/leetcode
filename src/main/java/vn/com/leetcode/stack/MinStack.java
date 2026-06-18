package vn.com.leetcode.stack;

import java.util.Map;

public class MinStack {
    int capacity = 10;
    int[] element = new int[capacity];
    int[] minValues = new int[capacity];
    int length;
    /**
     * Level: Medium
     * Start: 10:40 18/06/2026
     * End: 11:25 18/06/2026
     */
    public MinStack() {
    }
    public void increaseCapacity() {
        capacity += 10;
        int[] newElement = new int[capacity];
        int[] newMinValues = new int[capacity];

        System.arraycopy(element, 0, newElement, 0, length);
        System.arraycopy(minValues, 0, newMinValues, 0, length);

        element = newElement;
        minValues = newMinValues;
    }

    public void push(int val) {
        if (length >= capacity) {
            increaseCapacity();
        }

        element[length] = val;

        if (length == 0) {
            minValues[length] = val;
        } else {
            minValues[length] = Math.min(val, minValues[length - 1]);
        }
        length++;
    }

    public void pop() {
        if (length == 0) {
            return;
        }
        length--;
    }

    public int top() {
        return element[length - 1];
    }

    public int getMin() {
        return minValues[length - 1];
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(2);
        minStack.push(0);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

}
