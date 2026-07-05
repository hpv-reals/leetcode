package vn.com.leetcode.heap_priority_queue;

import java.util.Collections;
import java.util.PriorityQueue;

public class KthLargest {
    public KthLargest() {

    }

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    int kTemp;
    public KthLargest(int k, int[] nums) {
        kTemp = k;
        minHeap = new PriorityQueue<>(k);
        for (int num : nums) {
            if (minHeap.isEmpty() || minHeap.size() < k) {
                minHeap.add(num);
            } else {
                if (num > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.add(num);
                }
            }
        }
    }

    public int add(int val) {
        if (minHeap.isEmpty() || minHeap.size() < kTemp) {
            minHeap.add(val);
        } else {
            if (val > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(val);
            }
        }
        return minHeap.peek();
    }

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : stones) {
            pq.add(num);
        }
        while (pq.size() > 1) {
            int y = pq.poll();
            int x = pq.poll();
            if (y > x) {
                pq.add(y-x);
            }
        }
        if (pq.isEmpty()) {
            return 0;
        }
        return pq.poll();
    }

    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest();
        System.out.println(kthLargest.lastStoneWeight(new int[]{2,3,6,2,4}));
    }
}
