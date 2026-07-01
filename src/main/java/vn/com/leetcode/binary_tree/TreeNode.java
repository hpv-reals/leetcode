package vn.com.leetcode.binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return 1 + Math.max(leftDepth, rightDepth);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Math.max(leftDepth + rightDepth, Math.max(diameterOfBinaryTree(root.left), diameterOfBinaryTree(root.right)));
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Math.abs(leftDepth - rightDepth) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> output = new ArrayList<>();

        if (root == null) {
            return output;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < queueSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            output.add(currentLevel);
        }
        return output;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> output = new ArrayList<>();
        if (root == null) {
            return output;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            int lastValue = -1;

            for (int i = 0; i < queueSize; i++) {
                TreeNode currentNode = queue.poll();
                lastValue = currentNode.val;

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            output.add(lastValue);
        }
        return output;
    }

    // --- HÀM MAIN ---
    public static void main(String[] args) {
        TreeNode solution = new TreeNode();

        testRightSideView(solution);

//        testLevelOrder(solution);

//        testIsBalanced(solution);

//        testDiameter(solution);
//
//        testInvertBinaryTree(solution);
//
//        testMaxDepth(solution);
    }

    private static void testRightSideView(TreeNode solution) {
        System.out.println("\n========== TEST RIGHT SIDE VIEW ==========");

        // Test 1: Example 1 [1,2,3,null,4,null,5] -> Kỳ vọng: [1,3,5]
        TreeNode rootR1 = new TreeNode(1,
            new TreeNode(2, null, new TreeNode(4)),
            new TreeNode(3, null, new TreeNode(5))
        );
        System.out.println("Test 1 - [1,2,3,null,4,null,5]: " + solution.rightSideView(rootR1));

        // Test 2: Example 2 [1,2,3,4,null,null,null,5] -> Kỳ vọng: [1,3,4,5]
        TreeNode rootR2 = new TreeNode(1,
            new TreeNode(2, new TreeNode(4, new TreeNode(5), null), null),
            new TreeNode(3)
        );
        System.out.println("Test 2 - [1,2,3,4,null,null,null,5]: " + solution.rightSideView(rootR2));

        // Test 3: Example 3 [1,null,2] -> Kỳ vọng: [1,2]
        TreeNode rootR3 = new TreeNode(1, null, new TreeNode(2));
        System.out.println("Test 3 - [1,null,2]: " + solution.rightSideView(rootR3));

        // Test 4: Cây rỗng -> Kỳ vọng: []
        System.out.println("Test 4 - Cây rỗng []: " + solution.rightSideView(null));

        // Test 5: Cây lệch trái hoàn toàn [1,2,null,3,null] -> Kỳ vọng: [1,2,3]
        TreeNode rootR5 = new TreeNode(1, new TreeNode(2, new TreeNode(3), null), null);
        System.out.println("Test 5 - Cây lệch trái [1,2,null,3]: " + solution.rightSideView(rootR5));
    }

    private static void testLevelOrder(TreeNode solution) {
        System.out.println("\n========== TEST LEVEL ORDER ==========");

        // Test case 1: Cây đầy đủ 3 tầng
        TreeNode root1 = new TreeNode(1,
            new TreeNode(2, new TreeNode(4), new TreeNode(5)),
            new TreeNode(3, new TreeNode(6), new TreeNode(7))
        );
        System.out.println("Test 1 - [1,2,3,4,5,6,7]: " + solution.levelOrder(root1));

        // Test case 2: Chỉ có 1 node
        TreeNode root2 = new TreeNode(1);
        System.out.println("Test 2 - [1]: " + solution.levelOrder(root2));

        // Test case 3: Cây rỗng
        System.out.println("Test 3 - []: " + solution.levelOrder(null));

        // Test case 4: Cây lệch trái
        TreeNode root4 = new TreeNode(1, new TreeNode(2, new TreeNode(3), null), null);
        System.out.println("Test 4 - [1,2,null,3]: " + solution.levelOrder(root4));

        // Test case 5: Cây có nhánh null xen kẽ
        TreeNode root5 = new TreeNode(1,
            new TreeNode(2, null, new TreeNode(4)),
            new TreeNode(3, new TreeNode(5), null)
        );
        System.out.println("Test 5 - [1,2,3,null,4,5,null]: " + solution.levelOrder(root5));
    }

    private static void testIsBalanced(TreeNode solution) {
        System.out.println("\n========== TEST IS BALANCED ==========");

        // Test case 1 (Giống Example 1): Cây [1, 2, 3, null, null, 4] - Cân bằng (Kỳ vọng: true)
        //      1
        //    /   \
        //   2     3
        //        /
        //       4
        TreeNode rootBal1 = new TreeNode(1,
            new TreeNode(2),
            new TreeNode(3, new TreeNode(4), null)
        );
        System.out.println("Test 1 - Cây [1,2,3,null,null,4]: " + solution.isBalanced(rootBal1) + " (Kỳ vọng: true)");

        // Test case 2 (Giống Example 2): Cây [1, 2, 3, null, null, 4, null, 5] - Không cân bằng (Kỳ vọng: false)
        //      1
        //    /   \
        //   2     3
        //        /
        //       4
        //      /
        //     5
        TreeNode rootBal2 = new TreeNode(1,
            new TreeNode(2),
            new TreeNode(3, new TreeNode(4, new TreeNode(5), null), null)
        );
        System.out.println("Test 2 - Cây [1,2,3,null,null,4,null,5]: " + solution.isBalanced(rootBal2) + " (Kỳ vọng: false)");

        // Test case 3: Cây rỗng - Cân bằng (Kỳ vọng: true)
        TreeNode rootBal3 = null;
        System.out.println("Test 3 - Cây rỗng []: " + solution.isBalanced(rootBal3) + " (Kỳ vọng: true)");

        // Test case 4: Cây hoàn hảo [1,2,3] - Cân bằng (Kỳ vọng: true)
        //      1
        //    /   \
        //   2     3
        TreeNode rootBal4 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println("Test 4 - Cây hoàn hảo [1,2,3]: " + solution.isBalanced(rootBal4) + " (Kỳ vọng: true)");

        // Test case 5 (Bẫy cực hay): Gốc (node 1) thì thỏa mãn lệch 1, nhưng cây con (node 2) lại bị lệch 2 -> Không cân bằng (Kỳ vọng: false)
        //          1
        //        /   \
        //       2     5
        //      /     /
        //     3     6
        //    /
        //   4
        TreeNode rootBal5 = new TreeNode(1,
            new TreeNode(2,
                new TreeNode(3, new TreeNode(4), null),
                null
            ),
            new TreeNode(5, new TreeNode(6), null)
        );
        System.out.println("Test 5 - Cây con bị mất cân bằng: " + solution.isBalanced(rootBal5) + " (Kỳ vọng: false)");
    }

    private static void testDiameter(TreeNode solution) {
        System.out.println("\n========== TEST DIAMETER OF BINARY TREE ==========");

        // Test case 1 (Giống Example 1 của LeetCode): Cây [1,null,2,3,4,5]
        //      1
        //       \
        //        2
        //       / \
        //      3   4
        //     /
        //    5
        TreeNode rootDia1 = new TreeNode(1,
            null,
            new TreeNode(2,
                new TreeNode(3, new TreeNode(5), null),
                new TreeNode(4)
            )
        );
        System.out.println("Test 1 - Cây [1,null,2,3,4,5]: Đường kính = " + solution.diameterOfBinaryTree(rootDia1) + " (Kỳ vọng: 3)");

        // Test case 2 (Giống Example 2 của LeetCode): Cây [1,2,3]
        TreeNode rootDia2 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println("Test 2 - Cây [1,2,3]: Đường kính = " + solution.diameterOfBinaryTree(rootDia2) + " (Kỳ vọng: 2)");

        // Test case 3: Cây chỉ có 1 node (Không có cạnh nào)
        TreeNode rootDia3 = new TreeNode(1);
        System.out.println("Test 3 - Cây 1 node [1]: Đường kính = " + solution.diameterOfBinaryTree(rootDia3) + " (Kỳ vọng: 0)");

        // Test case 4: Cây là một đường thẳng (Giống Linked List)
        //       1
        //      /
        //     2
        //    /
        //   3
        //  /
        // 4
        TreeNode rootDia4 = new TreeNode(1, new TreeNode(2, new TreeNode(3, new TreeNode(4), null), null), null);
        System.out.println("Test 4 - Cây đường thẳng [1,2,3,4]: Đường kính = " + solution.diameterOfBinaryTree(rootDia4) + " (Kỳ vọng: 3)");


        // Test case 5: ĐƯỜNG KÍNH KHÔNG ĐI QUA ROOT GỐC (Trường hợp siêu hay sai)
        //          1
        //         /
        //        2  <-- Đường kính thực sự đi qua node 2 này
        //       / \
        //      3   4
        //     /     \
        //    5       6
        // Đường dài nhất là 5 -> 3 -> 2 -> 4 -> 6 (Tổng cộng 4 cạnh)
        TreeNode rootDia5 = new TreeNode(1,
            new TreeNode(2,
                new TreeNode(3, new TreeNode(5), null),
                new TreeNode(4, null, new TreeNode(6))
            ),
            null
        );
        System.out.println("Test 5 - Đường kính không đi qua Root gốc: Đường kính = " + solution.diameterOfBinaryTree(rootDia5) + " (Kỳ vọng: 4)");
    }


    private static void testMaxDepth(TreeNode solution) {
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

    private static void testInvertBinaryTree(TreeNode solution) {
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
