package vn.com.leetcode.linked_list;

public class DoubleLinkedList {
    private Node head;
    private Node tail;
    private static class Node {
        int data;
        Node next;
        Node prev;

        Node() {
        }
        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    // 1. Add a node to the end of the list (Append)
    public void append(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }

    // 2. Add a node to the front of the list (Prepend)
    public void prepend(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    // 3. Delete the first node matching the given value
    public void delete(int data) {
        if (head == null) return;

        Node current = head;
        while (current != null && current.data != data) {
            current = current.next;
        }

        if (current == null) return;

        if (current == head) {
            head = current.next;
        }

        if (current == tail) {
            tail = current.prev;
        }

        if (current.next != null) {
            current.next.prev = current.prev;
        }
        if (current.prev != null) {
            current.prev.next = current.next;
        }
    }

    public int deleteHead() {
        int data = -1;
        if (head == null) {
            return data;
        }
        data = head.data;
        Node newHead = head.next;
        if (newHead == null) {
            head = null;
            tail = null;
            return data;
        }
        newHead.prev = null;
        head = newHead;
        return data;
    }

    public void deleteTail() {
        if (tail == null) {
            return;
        }

        if (tail.prev == null) {
            head = null;
            tail = null;
            return;
        }

        Node newTail = tail.prev;
        newTail.next = null;
        tail = newTail;
    }

    public void moveToTail(int data) {
        delete(data);
        append(data);
    }
}
