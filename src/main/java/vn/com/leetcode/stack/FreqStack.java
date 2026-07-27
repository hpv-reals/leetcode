package vn.com.leetcode.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class FreqStack {

    private Map<Integer, Integer> frequencyMap;
    private Map<Integer, Stack<Integer>> freqGroups;
    private int maxFreq;

    public FreqStack() {
        frequencyMap = new HashMap<>();
        freqGroups = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int val) {
        int freq = frequencyMap.getOrDefault(val, 0) + 1;
        frequencyMap.put(val, freq);

        maxFreq = Math.max(maxFreq, freq);

        freqGroups.computeIfAbsent(freq, k -> new Stack<>()).push(val);
    }

    public int pop() {
        Stack<Integer> maxFreqStack = freqGroups.get(maxFreq);

        int val = maxFreqStack.pop();

        frequencyMap.put(val, frequencyMap.get(val) - 1);

        if (maxFreqStack.isEmpty()) {
            maxFreq--;
        }

        return val;
    }

    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push(5); // The stack is [5]
        freqStack.push(7); // The stack is [5,7]
        freqStack.push(5); // The stack is [5,7,5]
        freqStack.push(7); // The stack is [5,7,5,7]
        freqStack.push(4); // The stack is [5,7,5,7,4]
        freqStack.push(5); // The stack is [5,7,5,7,4,5]
        System.out.println(freqStack.pop()); // return 5
        System.out.println(freqStack.pop()); // return 7
        System.out.println(freqStack.pop()); // return 5
        System.out.println(freqStack.pop()); // return 4
    }
}
