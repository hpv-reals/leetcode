package vn.com.leetcode.Arrays_Hashing;

import java.util.*;

public class TopKFrequent {

    /**
     * Suggestion: True
     * Level: Medium
     * Start: 20:10 23/04/2026
     * End:
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

//        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
//        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
//
//        list.sort((a,b) -> b.getValue() - a.getValue());
//
//        int[] result = new int[k];
//        for (int i = 0; i < k; i++) {
//            result[i] = list.get(i).getKey();
//        }
//        return result;

        return map.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(k)
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }

    public int[] topKFrequent1(int[] nums, int k) {
        int[] result = new int[k];

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> map.get(n1) - map.get(n2));

        for (int n : map.keySet()) {
            heap.add(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        for (int i = k - 1; i >= 0; --i) {
            result[i] = heap.poll();
        }

        return result;
    }

    public int[] topKFrequent2(int[] nums, int k) {
        int[] result = new int[k];

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] bucket = new List[nums.length + 1];
        for (int key : map.keySet()) {
            int frequency = map.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        int count = 0;
        for (int i = bucket.length - 1; i >= 0 & count < k; i--) {
            if (bucket[i] != null) {
                for (int num : bucket[i]) {
                    result[count++] = num;
                    if (count == k) break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TopKFrequent topKFrequent = new TopKFrequent();
        System.out.println(Arrays.toString(topKFrequent.topKFrequent2(new int[]{1,2,2,3,3,3}, 2)));
    }
}
