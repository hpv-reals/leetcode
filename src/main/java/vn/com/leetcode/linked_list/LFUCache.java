package vn.com.leetcode.linked_list;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LFUCache {

    class Node {
        int key;
        int value;
        int freq;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    class DoublyLinkedList {
        Node head;
        Node tail;
        int size;

        public DoublyLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        public void addNode(Node node) {
            Node nextNode = head.next;
            head.next = node;
            node.prev = head;
            node.next = nextNode;
            nextNode.prev = node;
            size++;
        }

        public void removeNode(Node node) {
            Node prevNode = node.prev;
            Node nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            size--;
        }

        public Node removeTail() {
            if (size > 0) {
                Node node = tail.prev;
                removeNode(node);
                return node;
            }
            return null;
        }
    }

    private int capacity;
    private int minFreq;
    private Map<Integer, Node> keyNodeMap;
    private Map<Integer, DoublyLinkedList> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.keyNodeMap = new HashMap<>();
        this.freqMap = new HashMap<>();
    }

    public int get(int key) {
        if (!keyNodeMap.containsKey(key)) {
            return -1;
        }
        Node node = keyNodeMap.get(key);
        updateFreq(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (keyNodeMap.containsKey(key)) {
            Node node = keyNodeMap.get(key);
            node.value = value;
            updateFreq(node);
            return;
        }

        if (keyNodeMap.size() >= capacity) {
            DoublyLinkedList minFreqList = freqMap.get(minFreq);
            Node nodeToRemove = minFreqList.removeTail();
            if (nodeToRemove != null) {
                keyNodeMap.remove(nodeToRemove.key);
            }
        }

        Node newNode = new Node(key, value);
        keyNodeMap.put(key, newNode);

        minFreq = 1;
        DoublyLinkedList newList = freqMap.getOrDefault(minFreq, new DoublyLinkedList());
        newList.addNode(newNode);
        freqMap.put(minFreq, newList);
    }

    private void updateFreq(Node node) {
        int oldFreq = node.freq;
        DoublyLinkedList oldList = freqMap.get(oldFreq);
        oldList.removeNode(node);

        if (oldFreq == minFreq && oldList.size == 0) {
            minFreq++;
        }

        node.freq++;
        DoublyLinkedList newList = freqMap.getOrDefault(node.freq, new DoublyLinkedList());
        newList.addNode(node);
        freqMap.put(node.freq, newList);
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1 ---");
        LFUCache lfu1 = new LFUCache(2);
        lfu1.put(1, 1);
        lfu1.put(2, 2);
        System.out.println(lfu1.get(1)); // return 1
        lfu1.put(3, 3);                 // xóa key 2 vì freq nhỏ nhất (freq=1)
        System.out.println(lfu1.get(2)); // return -1 (không tìm thấy)
        System.out.println(lfu1.get(3)); // return 3
        lfu1.put(4, 4);                 // cả 1 và 3 cùng freq=2, nhưng 1 dùng lâu hơn (LRU) -> xóa 1
        System.out.println(lfu1.get(1)); // return -1 (không tìm thấy)
        System.out.println(lfu1.get(3)); // return 3
        System.out.println(lfu1.get(4)); // return 4

        // Test Case 2: Capacity = 1 (mọi thao tác put mới sẽ đè lên phần tử cũ)
        System.out.println("\n--- Test Case 2 ---");
        LFUCache lfu2 = new LFUCache(1);
        lfu2.put(1, 10);
        System.out.println(lfu2.get(1)); // return 10
        lfu2.put(2, 20);                 // xóa key 1
        System.out.println(lfu2.get(1)); // return -1
        System.out.println(lfu2.get(2)); // return 20

        // Test Case 3: Cập nhật giá trị của key đã tồn tại (Value Update)
        System.out.println("\n--- Test Case 3 ---");
        LFUCache lfu3 = new LFUCache(2);
        lfu3.put(1, 100);
        lfu3.put(2, 200);
        lfu3.put(1, 150);               // Cập nhật giá trị key 1, tần suất key 1 tăng lên
        lfu3.put(3, 300);               // Xóa key 2 (vì freq nhỏ hơn key 1)
        System.out.println(lfu3.get(2)); // return -1
        System.out.println(lfu3.get(1)); // return 150
        System.out.println(lfu3.get(3)); // return 300

        // Test Case 4: Kiểm tra quy tắc Tie-breaker (LRU trong cùng một tần suất)
        System.out.println("\n--- Test Case 4 ---");
        LFUCache lfu4 = new LFUCache(2);
        lfu4.put(3, 1);
        lfu4.put(4, 2);
        // Cả 3 và 4 đều có freq = 1. Nhưng 3 được put trước (cũ hơn), 4 put sau (mới hơn).
        // Thêm key 5 vào -> phải xóa 3 (vì 3 là LRU trong nhóm freq=1).
        lfu4.put(5, 3);
        System.out.println(lfu4.get(3)); // return -1 (đã bị xóa)
        System.out.println(lfu4.get(4)); // return 2
        System.out.println(lfu4.get(5)); // return 3

        // Test Case 5: Truy vấn liên tục làm thay đổi cấu trúc tần suất
        System.out.println("\n--- Test Case 5 ---");
        LFUCache lfu5 = new LFUCache(3);
        lfu5.put(1, 1);
        lfu5.put(2, 2);
        lfu5.put(3, 3);
        lfu5.get(1); // freq(1) = 2
        lfu5.get(1); // freq(1) = 3
        lfu5.get(2); // freq(2) = 2
        // Lúc này freq: key 3 = 1, key 2 = 2, key 1 = 3.
        // Khi thêm key 4 vào -> key 3 sẽ bị xóa vì có freq nhỏ nhất (freq=1).
        lfu5.put(4, 4);
        System.out.println(lfu5.get(3)); // return -1
        System.out.println(lfu5.get(1)); // return 1
        System.out.println(lfu5.get(2)); // return 2
        System.out.println(lfu5.get(4)); // return 4
    }

}
