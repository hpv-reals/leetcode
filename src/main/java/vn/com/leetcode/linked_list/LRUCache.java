package vn.com.leetcode.linked_list;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private Map<Integer, Node> cache;
    private Node head;
    private Node tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node currentNode = cache.get(key);
        if (currentNode == null) {
            return -1;
        }

        // Remove Node
        removeNode(currentNode);
        // Add Node to the Head
        addNodeToHead(currentNode);

        // Return
        return currentNode.value;
    }

    public void put(int key, int value) {
        Node currentNode = cache.get(key);
        if (currentNode != null) {
            currentNode.value = value;
            removeNode(currentNode);
            addNodeToHead(currentNode);
        } else {
            if (capacity == cache.size()) {
                Node lruNode = tail.prev;
                cache.remove(lruNode.key);
                removeNode(lruNode);
            }
            currentNode = new Node(key, value);
            cache.put(key, currentNode);
            addNodeToHead(currentNode);
        }
    }

    private void removeNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    private void addNodeToHead(Node node) {
        Node nextNode = head.next;

        head.next = node;
        node.prev = head;

        node.next = nextNode;
        nextNode.prev = node;
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 10);  // cache: {1=10}
        System.out.println(lRUCache.get(1));      // return 10
        lRUCache.put(2, 20);  // cache: {1=10, 2=20}
        lRUCache.put(3, 30);  // cache: {2=20, 3=30}, key=1 was evicted
        System.out.println(lRUCache.get(2));      // returns 20
        System.out.println(lRUCache.get(1));      // return -1 (not found)
    }

}
