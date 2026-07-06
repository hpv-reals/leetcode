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

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]));

        for (int[] point : points) {
            priorityQueue.add(point);

            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }

        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = priorityQueue.poll();
        }
        return result;
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k);
        for (int num : nums) {
            if (priorityQueue.isEmpty() || priorityQueue.size() < k) {
                priorityQueue.add(num);
            } else {
                if (num > priorityQueue.peek()) {
                    priorityQueue.poll();
                    priorityQueue.add(num);
                }
            }
        }
        return priorityQueue.isEmpty() ? -1 : priorityQueue.poll();
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
