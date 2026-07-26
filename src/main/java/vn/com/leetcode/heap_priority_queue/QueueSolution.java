package vn.com.leetcode.heap_priority_queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class QueueSolution {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (!deque.isEmpty() && deque.peekFirst() < i - k +1) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }
}
