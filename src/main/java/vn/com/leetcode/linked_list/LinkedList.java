package vn.com.leetcode.linked_list;

import java.util.List;
import jdk.nashorn.internal.ir.LiteralNode;

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

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        int length = lists.length;
        int step = 1;
        while (step < length) {
            for (int i = 0; i < length - step; i += 2 * step) {
                if (i + step > length - 1) {
                    continue;
                }
                lists[i] = mergeTwoLists(lists[i], lists[i + step]);
            }
            step = step * 2;
        }
        return lists[0];
    }


    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode groupPrev = dummy;
        ListNode current = head;
        while (current != null) {
            ListNode temp = current;
            for (int i = 0; i < k; i++) {
                if (temp == null) {
                    return dummy.next;
                }
                temp = temp.next;
            }

            ListNode preNode = temp;
            ListNode currentTemp = current;
            for (int i = 0; i < k; i++) {
                ListNode nextNode = currentTemp.next;
                currentTemp.next = preNode;
                preNode = currentTemp;
                currentTemp = nextNode;
            }


            ListNode nextGroupPrev = groupPrev.next;
            groupPrev.next = preNode;

            groupPrev = nextGroupPrev;
            current = currentTemp;
        }
        return dummy.next;
    }


    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        int tempVal = 0;
        int tempDiv = 0;
        while (l1 != null || l2 != null) {
            int l1Val = l1 == null ? 0 : l1.val;
            int l2Val = l2 == null ? 0 : l2.val;

            int sum = l1Val + l2Val + tempDiv;
            tempVal = sum % 10;
            tempDiv = sum / 10;
            temp.next = new ListNode(tempVal);
            temp = temp.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (tempDiv > 0) {
            temp.next = new ListNode(tempDiv);
        }

        return dummy.next;
    }

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


    public Node copyRandomList(Node head) {



        return null;
    }

    // --- HÀM MAIN ---
    public static void main(String[] args) {
        LinkedList solution = new LinkedList();

        // Test case 1: Xóa node bình thường ở giữa
        ListNode rmList1 = createList(new int[]{9,9,9,9,9,9,9});
        ListNode rmList2 = createList(new int[]{9,9,9,9});
        printList(solution.addTwoNumbers(rmList1, rmList2));


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
