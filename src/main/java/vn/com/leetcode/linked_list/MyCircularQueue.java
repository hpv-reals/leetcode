package vn.com.leetcode.linked_list;

public class MyCircularQueue {

    int[] array;
    int lastIndex = -1;

    public MyCircularQueue(int k) {
        array = new int[k];
    }

    public boolean enQueue(int value) {
        if (lastIndex >= array.length - 1) {
            return false;
        }
        lastIndex++;
        array[lastIndex] = value;
        return true;
    }

    public boolean deQueue() {
        if (lastIndex < 0) {
            return false;
        }
        int[] newArray = new int[array.length];
        System.arraycopy(array, 1, newArray, 0, array.length - 1);
        array = newArray;
        lastIndex--;
        return true;

    }

    public int Front() {
        if (lastIndex < 0) {
            return -1;
        }
        return array[0];
    }

    public int Rear() {
        if (lastIndex < 0) {
            return -1;
        }
        return array[lastIndex];
    }

    public boolean isEmpty() {
        return lastIndex < 0;
    }

    public boolean isFull() {
        return lastIndex == array.length - 1;
    }

    public static void main(String[] args) {
        // Test Case 1: Ví dụ cơ bản theo đề bài
        System.out.println("--- Test Case 1 ---");
        MyCircularQueue q1 = new MyCircularQueue(3);
        System.out.println(q1.enQueue(1)); // true
        System.out.println(q1.enQueue(2)); // true
        System.out.println(q1.enQueue(3)); // true
        System.out.println(q1.enQueue(4)); // false (Full)
        System.out.println(q1.Rear());     // 3
        System.out.println(q1.isFull());   // true
        System.out.println(q1.deQueue());  // true
        System.out.println(q1.enQueue(4)); // true
        System.out.println(q1.Rear());     // 4

        // Test Case 2: Kiểm tra hàng đợi rỗng và các thao tác Front/Rear biên
        System.out.println("\n--- Test Case 2 ---");
        MyCircularQueue q2 = new MyCircularQueue(2);
        System.out.println(q2.isEmpty());  // true
        System.out.println(q2.Front());    // -1
        System.out.println(q2.Rear());     // -1
        System.out.println(q2.enQueue(10));// true
        System.out.println(q2.Front());    // 10
        System.out.println(q2.Rear());     // 10

        // Test Case 3: Vòng lặp xoay vòng (Circular Behavior)
        System.out.println("\n--- Test Case 3 ---");
        MyCircularQueue q3 = new MyCircularQueue(3);
        System.out.println(q3.enQueue(5)); // true
        System.out.println(q3.enQueue(10));// true
        System.out.println(q3.deQueue());  // true (xóa 5)
        System.out.println(q3.enQueue(15));// true (vòng về tận dụng chỗ trống)
        System.out.println(q3.Front());    // 10
        System.out.println(q3.Rear());     // 15

        // Test Case 4: Kích thước k = 1
        System.out.println("\n--- Test Case 4 ---");
        MyCircularQueue q4 = new MyCircularQueue(1);
        System.out.println(q4.enQueue(7)); // true
        System.out.println(q4.isFull());   // true
        System.out.println(q4.enQueue(8)); // false
        System.out.println(q4.deQueue());  // true
        System.out.println(q4.isEmpty());  // true
        System.out.println(q4.Front());    // -1

        // Test Case 5: Hỗn hợp nhiều thao tác liên tục
        System.out.println("\n--- Test Case 5 ---");
        MyCircularQueue q5 = new MyCircularQueue(4);
        System.out.println(q5.enQueue(1)); // true
        System.out.println(q5.enQueue(2)); // true
        System.out.println(q5.enQueue(3)); // true
        System.out.println(q5.deQueue());  // true
        System.out.println(q5.enQueue(4)); // true
        System.out.println(q5.enQueue(5)); // true
        System.out.println(q5.isFull());   // true
        System.out.println(q5.Front());    // 2
        System.out.println(q5.Rear());     // 5
    }

}
