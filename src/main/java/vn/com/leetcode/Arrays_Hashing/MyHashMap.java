package vn.com.leetcode.Arrays_Hashing;

public class MyHashMap {
    private static class Node {
        int key;
        int value;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private final int CAPACITY = 10000;
    private Node[] buckets;

    public MyHashMap() {
        buckets = new Node[CAPACITY];
    }

    private int getHash(int key) {
        return key % CAPACITY;
    }

    public void put(int key, int value) {
        int index = getHash(key);

        if (buckets[index] == null) {
            buckets[index] = new Node(key, value);
            return;
        }

        Node curNode = buckets[index];
        while (true) {
            if (curNode.key == key) {
                curNode.value = value;
                return;
            }
            if (curNode.next == null) {
                curNode.next = new Node(key, value);
                return;
            }
            curNode = curNode.next;
        }

    }

    public int get(int key) {
        int index = getHash(key);
        Node curNode = buckets[index];

        while (curNode != null) {
            if (curNode.key == key) {
                return curNode.value;
            }
            curNode = curNode.next;
        }

        return -1;
    }

    public void remove(int key) {
        int index = getHash(key);
        Node curNode = buckets[index];

        if (curNode == null) {
            return;
        }

        if (curNode.key == key) {
            buckets[index] = curNode.next;
            return;
        }

        Node prevNode = curNode;
        curNode = curNode.next;
        while (curNode != null) {
            if (curNode.key == key) {
                prevNode.next = curNode.next;
                return;
            }
            prevNode = curNode;
            curNode = curNode.next;
        }
    }
}
