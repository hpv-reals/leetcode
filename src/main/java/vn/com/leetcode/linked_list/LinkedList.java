package vn.com.leetcode.linked_list;

import java.util.List;

public class LinkedList {

    public ListNode reverseList(ListNode head) {
        ListNode preNode = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextNode = current.next;
            current.next = preNode;
            preNode = current;
            current = nextNode;
        }
        return preNode;
    }

    public void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode list2 = slow.next;
        slow.next = null;

        ListNode preNode = null;
        ListNode current = list2;
        while (current != null) {
            ListNode nextNode = current.next;
            current.next = preNode;
            preNode = current;
            current = nextNode;
        }

        ListNode first = head;
        ListNode second = preNode;
        while (second != null) {
            ListNode temp1 = first.next;
            ListNode temp2 = second.next;

            first.next = second;
            second.next = temp1;

            first = temp1;
            second = temp2;
        }
    }

    /**
     * Level: Easy
     * Start: 10:07 24/06/2026
     * End: 10:30 24/06/2026
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                current.next = list2;
                list2 = list2.next;
            } else {
                current.next = list1;
                list1 = list1.next;
            }
            current = current.next;
        }

        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }
        return dummy.next;
    }

    /**
     * Level: Easy
     * Start: 10:32 24/06/2026
     * End: 10:40 24/06/2026
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * Level: Medium
     * Start: 10:50 24/06/2026
     * End: 11:04 24/06/2026
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    // --- HÀM MAIN ---
    public static void main(String[] args) {
        LinkedList solution = new LinkedList();

        System.out.println("\n========== TEST REMOVE NTH NODE FROM END ==========");

        // Test case 1: Xóa node bình thường ở giữa
        ListNode rmList1 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Test 1 - Xóa thứ 2 từ cuối [1,2,3,4,5]: ");
        printList(solution.removeNthFromEnd(rmList1, 2)); // Kỳ vọng: 1 -> 2 -> 3 -> 5 -> null

        // Test case 2: Danh sách chỉ có 1 phần tử (Trường hợp rất dễ gây NullPointerException)
        ListNode rmList2 = createList(new int[]{5});
        System.out.print("Test 2 - Xóa thứ 1 từ cuối [5]:         ");
        printList(solution.removeNthFromEnd(rmList2, 1)); // Kỳ vọng: null

        // Test case 3: Xóa đúng node đầu tiên (head)
        ListNode rmList3 = createList(new int[]{1, 2});
        System.out.print("Test 3 - Xóa thứ 2 từ cuối [1,2]:       ");
        printList(solution.removeNthFromEnd(rmList3, 2)); // Kỳ vọng: 2 -> null

        // Test case 4: Xóa đúng node cuối cùng (tail)
        ListNode rmList4 = createList(new int[]{1, 2});
        System.out.print("Test 4 - Xóa thứ 1 từ cuối [1,2]:       ");
        printList(solution.removeNthFromEnd(rmList4, 1)); // Kỳ vọng: 1 -> null

        // Test case 5: Danh sách 3 phần tử, xóa phần tử giữa
        ListNode rmList5 = createList(new int[]{1, 2, 3});
        System.out.print("Test 5 - Xóa thứ 2 từ cuối [1,2,3]:     ");
        printList(solution.removeNthFromEnd(rmList5, 2)); // Kỳ vọng: 1 -> 3 -> null
    }

    // 1. Hàm tạo LinkedList từ Array
    public static ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    // 2. Hàm in LinkedList ra màn hình
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + (current.next != null ? " -> " : " -> null\n"));
            current = current.next;
        }
        if (head == null) {
            System.out.println("null");
        }
    }

    public static void createCycle(ListNode head, int pos) {
        if (head == null || pos < 0) return;

        ListNode cycleNode = null;
        ListNode tail = head;
        int index = 0;

        // Chạy đến đuôi danh sách
        while (tail.next != null) {
            if (index == pos) {
                cycleNode = tail; // Lưu lại vị trí cần móc ngược về
            }
            tail = tail.next;
            index++;
        }

        // Bắt trường hợp vòng lặp nối ngay tại node cuối
        if (index == pos) {
            cycleNode = tail;
        }

        // Bẻ mũi tên của đuôi móc ngược lại vào cycleNode
        if (cycleNode != null) {
            tail.next = cycleNode;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {
        }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
