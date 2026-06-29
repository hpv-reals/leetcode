package vn.com.leetcode.binary_search;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return 1 + Math.max(leftDepth, rightDepth);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    // --- HÀM MAIN ---
    public static void main(String[] args) {
        TreeNode solution = new TreeNode();

        System.out.println("========== TEST INVERT BINARY TREE ==========");

        // Test case 1: Cây [1, 2, 3, 4, 5, 6, 7]
        //      1                 1
        //    /   \             /   \
        //   2     3    =>     3     2
        //  / \   / \         / \   / \
        // 4   5 6   7       7   6 5   4
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3, new TreeNode(6), new TreeNode(7))
        );
        System.out.print("Test 1 - Ban đầu:  ");
        printTree(root1);
        solution.invertTree(root1);
        System.out.print("Test 1 - Đã lật:   ");
        printTree(root1); // Kỳ vọng: [1, 3, 2, 7, 6, 5, 4]


        System.out.println("\n-------------------------------------------");

        // Test case 2: Cây [3, 2, 1]
        //    3             3
        //   / \    =>     / \
        //  2   1         1   2
        TreeNode root2 = new TreeNode(3, new TreeNode(2), new TreeNode(1));
        System.out.print("Test 2 - Ban đầu:  ");
        printTree(root2);
        solution.invertTree(root2);
        System.out.print("Test 2 - Đã lật:   ");
        printTree(root2); // Kỳ vọng: [3, 1, 2]

        System.out.println("\n-------------------------------------------");

        // Test case 3: Cây rỗng []
        TreeNode root3 = null;
        System.out.print("Test 3 - Ban đầu:  ");
        printTree(root3);
        solution.invertTree(root3);
        System.out.print("Test 3 - Đã lật:   ");
        printTree(root3); // Kỳ vọng: []

        System.out.println("\n========== TEST MAX DEPTH ==========");

        // Test case 4: Cây [1, 2, 3, null, null, null, 4]
        // (Chú ý: Để tạo đúng cây trong ví dụ 1, node 3 có con phải là 4, con trái null)
        //      1
        //    /   \
        //   2     3
        //          \
        //           4
        TreeNode root4 = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3, null, new TreeNode(4))
        );
        System.out.print("Test 4 - Cây [1, 2, 3, null, null, null, 4]: ");
        System.out.println("Độ sâu = " + solution.maxDepth(root4) + " (Kỳ vọng: 3)");

        // Test case 5: Cây rỗng
        System.out.print("Test 5 - Cây rỗng []: ");
        System.out.println("Độ sâu = " + solution.maxDepth(null) + " (Kỳ vọng: 0)");
    }

    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("[]");
            return;
        }

        // Dùng Queue để in theo từng tầng (Level-order Traversal / BFS)
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current != null) {
                sb.append(current.val).append(",");
                // Thêm 2 con vào hàng đợi để in ở tầng tiếp theo
                queue.offer(current.left);
                queue.offer(current.right);
            } else {
                //sb.append("null,"); // Bỏ comment dòng này nếu muốn in rõ các node null
            }
        }

        // Xóa dấu phẩy thừa ở cuối và đóng ngoặc
        if (sb.length() > 1 && sb.charAt(sb.length() - 1) == ',') {
            sb.setLength(sb.length() - 1);
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
