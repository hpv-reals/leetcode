package vn.com.leetcode.heap_priority_queue;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {

    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;
    public MedianFinder() {
        maxHeap = new PriorityQueue<>();
        minHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        minHeap.add(num);
        maxHeap.add(minHeap.poll());

        if (maxHeap.size() > minHeap.size()) {
            minHeap.add(maxHeap.poll());
        }
    }

    public double findMedian() {
        if (minHeap.size() > maxHeap.size()) {
            return (double) minHeap.peek();
        }
        return (double) (minHeap.peek() + maxHeap.peek()) / 2.0;
    }

}
