package vn.com.leetcode.binary_tree;

public class NodeSolution {

    public Node construct(int[][] grid) {
        return dfs(grid, 0, 0, grid.length);
    }

    private Node dfs(int[][] grid, int r, int c, int n) {
        if (isAllSame(grid, r, c, n)) {
            return new Node(grid[r][c] == 1, true);
        }

        int half = n / 2;
        Node topLeft = dfs(grid, r, c, half);
        Node topRight = dfs(grid, r, c + half, half);
        Node bottomLeft = dfs(grid, r + half, c, half);
        Node bottomRight = dfs(grid, r + half, c + half, half);

        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }

    private boolean isAllSame(int[][] grid, int r, int c, int n) {
        int firstVal = grid[r][c];
        for (int i = r; i < r + n; i++) {
            for (int j = c; j < c + n; j++) {
                if (grid[i][j] != firstVal) {
                    return false;
                }
            }
        }
        return true;
    }



    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}
