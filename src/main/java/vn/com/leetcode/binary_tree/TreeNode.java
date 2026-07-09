package vn.com.leetcode.binary_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import sun.reflect.generics.tree.Tree;

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

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }

    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }
    public int dfs(TreeNode root, int maxSoFar) {
        if (root == null) {
            return 0;
        }

        int count = 0;
        if (root.val >= maxSoFar) {
            count++;
            maxSoFar = root.val;
        }

        count += dfs(root.left, maxSoFar);
        count += dfs(root.right, maxSoFar);

        return count;
    }

    int maxGlobal = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxGlobal = Integer.MIN_VALUE;
        maxValue(root);
        return maxGlobal;
    }

    public int maxValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftValue = Math.max(0, maxValue(root.left));
        int rightValue = Math.max(0, maxValue(root.right));
        int currentValue = root.val + leftValue + rightValue;
        maxGlobal = Math.max(currentValue, maxGlobal);
        return root.val + Math.max(leftValue, rightValue);
    }

    final String splitter = ",";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    public void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null").append(splitter);
            return;
        }
        sb.append(node.val).append(splitter);
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queues = new LinkedList<>(Arrays.asList(data.split(splitter)));
        return deserializeHelper(queues);
    }

    public TreeNode deserializeHelper(Queue<String> queue) {
        String value = queue.poll();
        if (Objects.equals(value, "null")) {
            return null;
        }

        TreeNode treeNode = new TreeNode(Integer.parseInt(value));
        treeNode.left = deserializeHelper(queue);
        treeNode.right = deserializeHelper(queue);
        return treeNode;
    }

    public boolean isValidBST(TreeNode root) {
        return isValid(root, null, null);
    }

    private boolean isValid(TreeNode node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }

        if ((min != null && node.val <= min) || (max != null && node.val >= max)) {
            return false;
        }

        return isValid(node.left, min, node.val) && isValid(node.right, node.val, max);
    }

    int count = 0;
    int result = -1;
    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return result;
    }

    public void traverse(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        if (count >= k) {
            return;
        }

        traverse(root.left, k);
        count++;
        if (count == k) {
            result = root.val;
            return;
        }
        traverse(root.right, k);
    }


    private int preorderIndex;
    private Map<Integer, Integer> inorderIndexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        inorderIndexMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return build(preorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int inorderStart, int inorderEnd) {
        if (inorderStart > inorderEnd) {
            return null;
        }

        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);
        int inorderRootIndex = inorderIndexMap.get(rootValue);
        root.left = build(preorder, inorderStart, inorderRootIndex - 1);
        root.right = build(preorder, inorderRootIndex + 1, inorderEnd);

        return root;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p != null && q != null){
            if (p.val == q.val) {
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;

        return isSameTree(root, subRoot) ||
                isSubtree(root.left, subRoot) ||
                isSubtree(root.right, subRoot);
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        inorderTraversal(root, result);

        return result;
    }

    public void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, result);
        result.add(root.val);
        inorderTraversal(root.right, result);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        preorderTraversal(root, result);

        return result;
    }

    public void preorderTraversal(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }

        result.add(node.val);
        preorderTraversal(node.left, result);
        preorderTraversal(node.right, result);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        postorderTraversal(root, result);

        return result;
    }

    public void postorderTraversal(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }

        postorderTraversal(node.left, result);
        postorderTraversal(node.right, result);
        result.add(node.val);
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        }

        if (root1 == null) {
            root1 = new TreeNode();
        }
        if (root2 == null) {
            root2 = new TreeNode();
        }

        return new TreeNode(root1.val + root2.val, mergeTrees(root1.left, root2.left), mergeTrees(root1.right, root2.right) );
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        return hasPathSum(root.left, targetSum - root.val)
            || hasPathSum(root.right, targetSum - root.val);
    }

    public int minusSum(TreeNode root, int targetSum) {
        if (root == null) {
            return targetSum;
        }

        targetSum -= root.val;
        int targetSumTempLeft = targetSum;
        int targetSumTempRight = targetSum;

        int left = minusSum(root.left, targetSumTempLeft);
        if (left == 0) {
            return 0;
        }
        return minusSum(root.right, targetSumTempRight);
    }

    int sumRangeSumBST = 0;
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return sumRangeSumBST;
        }

        if (root.val >= low && root.val <= high) {
            sumRangeSumBST += root.val;
        }

        rangeSumBST(root.left, low, high);
        rangeSumBST(root.right, low, high);

        return sumRangeSumBST;
    }

    // --- HÀM MAIN ---
    public static void main(String[] args) {
        TreeNode solution = new TreeNode();

//        testBuildTree(solution);

//        testKthSmallest(solution);

//        testRightSideView(solution);

//        testLevelOrder(solution);

//        testIsBalanced(solution);

//        testDiameter(solution);
//
//        testInvertBinaryTree(solution);
//
//        testMaxDepth(solution);
    }

    private static void testBuildTree(TreeNode solution) {
        System.out.println("\n========== TEST BUILD TREE (PREORDER & INORDER) ==========");

        // Test 1: Cây tiêu chuẩn (Giống Example 1 của đề bài)
        //        3
        //      /   \
        //     9    20
        //         /  \
        //        15   7
        System.out.print("Test 1 - Cây tiêu chuẩn: ");
        TreeNode root1 = solution.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        printTree(root1); // Kỳ vọng: [3, 9, 20, null, null, 15, 7]

        // Test 2: Cây chỉ có 1 node (Example 2)
        System.out.print("Test 2 - Cây 1 node:     ");
        TreeNode root2 = solution.buildTree(new int[]{1}, new int[]{1});
        printTree(root2); // Kỳ vọng: [1]

        // Test 3: Cây rỗng (0 node - Edge case)
        System.out.print("Test 3 - Cây rỗng:       ");
        TreeNode root3 = solution.buildTree(new int[]{}, new int[]{});
        printTree(root3); // Kỳ vọng: []

        // Test 4: Cây lệch hoàn toàn sang TRÁI (Left-skewed)
        //      1
        //     /
        //    2
        //   /
        //  3
        System.out.print("Test 4 - Lệch TRÁI:      ");
        TreeNode root4 = solution.buildTree(new int[]{1, 2, 3}, new int[]{3, 2, 1});
        printTree(root4); // Kỳ vọng: in ra thấy 1 trỏ trái 2, 2 trỏ trái 3

        // Test 5: Cây lệch hoàn toàn sang PHẢI (Right-skewed)
        //  1
        //   \
        //    2
        //     \
        //      3
        // Đặc điểm: Preorder và Inorder giống hệt nhau!
        System.out.print("Test 5 - Lệch PHẢI:      ");
        TreeNode root5 = solution.buildTree(new int[]{1, 2, 3}, new int[]{1, 2, 3});
        printTree(root5);

        // Test 6: Cây hoàn hảo 3 tầng (7 nodes)
        //        1
        //      /   \
        //     2     3
        //    / \   / \
        //   4   5 6   7
        System.out.print("Test 6 - Đầy đủ 3 tầng:  ");
        TreeNode root6 = solution.buildTree(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 2, 5, 1, 6, 3, 7});
        printTree(root6); // Kỳ vọng: [1, 2, 3, 4, 5, 6, 7]

        // Test 7: Cây zigzag (Độ khó cao để test luồng đệ quy)
        //    1
        //     \
        //      2
        //     /
        //    3
        //     \
        //      4
        System.out.print("Test 7 - Cây zigzag:     ");
        TreeNode root7 = solution.buildTree(new int[]{1, 2, 3, 4}, new int[]{1, 3, 4, 2});
        printTree(root7);

    }

    private static void testKthSmallest(TreeNode solution) {
        System.out.println("\n========== TEST KTH SMALLEST IN BST ==========");

        // Test 1 (Example 1): [3, 1, 4, null, 2], k = 1 -> Kỳ vọng: 1
        //      3
        //     / \
        //    1   4
        //     \
        //      2
        TreeNode rootK1 = new TreeNode(3,
            new TreeNode(1, null, new TreeNode(2)),
            new TreeNode(4)
        );
        System.out.println("Test 1 - Tìm số nhỏ thứ 1: " + solution.kthSmallest(rootK1, 1) + " (Kỳ vọng: 1)");

        solution.count = 0;
        solution.result = -1;

        // Test 2 (Example 2): [5, 3, 6, 2, 4, null, null, 1], k = 3 -> Kỳ vọng: 3
        //          5
        //         / \
        //        3   6
        //       / \
        //      2   4
        //     /
        //    1
        TreeNode rootK2 = new TreeNode(5,
            new TreeNode(3, new TreeNode(2, new TreeNode(1), null), new TreeNode(4)),
            new TreeNode(6)
        );
        System.out.println("Test 2 - Tìm số nhỏ thứ 3: " + solution.kthSmallest(rootK2, 3) + " (Kỳ vọng: 3)");

        solution.count = 0;
        solution.result = -1;

        // Test 3: Cây chỉ có nhánh phải [1, null, 2, null, 3], k = 3 -> Kỳ vọng: 3 (Số lớn nhất)
        TreeNode rootK3 = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3)));
        System.out.println("Test 3 - Tìm số nhỏ thứ 3: " + solution.kthSmallest(rootK3, 3) + " (Kỳ vọng: 3)");

        solution.count = 0;
        solution.result = -1;

        // Test 4: Cây chỉ có 1 node [10], k = 1 -> Kỳ vọng: 10
        TreeNode rootK4 = new TreeNode(10);
        System.out.println("Test 4 - Tìm số nhỏ thứ 1 (Cây 1 node): " + solution.kthSmallest(rootK4, 1) + " (Kỳ vọng: 10)");
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
