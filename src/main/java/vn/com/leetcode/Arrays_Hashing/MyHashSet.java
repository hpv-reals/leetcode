package vn.com.leetcode.Arrays_Hashing;

public class MyHashSet {
    private static class Node {
        int key;
        Node next;

        public Node(int key) {
            this.key = key;
            this.next = null;
        }
    }

    private final int CAPACITY = 10000;
    private Node[] buckets;

    public MyHashSet() {
        buckets = new Node[CAPACITY];
    }

    private int getHash(int key) {
        return key % CAPACITY;
    }

    public void add(int key) {
        int index = getHash(key);

        if (buckets[index] == null) {
            buckets[index] = new Node(key);
            return;
        }

        Node curNode = buckets[index];
        while (true) {
            if (curNode.key == key) {
                return;
            }
            if (curNode.next == null) {
                curNode.next = new Node(key);
                return;
            }
            curNode = curNode.next;
        }
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

    public boolean contains(int key) {
        int index = getHash(key);
        Node curNode = buckets[index];

        while (curNode != null) {
            if (curNode.key == key) {
                return true;
            }
            curNode = curNode.next;
        }

        return false;
    }
}