package vn.com.leetcode.Arrays_Hashing;

public class NumMatrix {

    int[][] prefix2D;
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;

        int rows = matrix.length;
        int cols = matrix[0].length;

        prefix2D = new int[rows + 1][cols + 1];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                prefix2D[r + 1][c + 1] = matrix[r][c]
                    + prefix2D[r + 1][c]
                    + prefix2D[r][c + 1]
                    - prefix2D[r][c];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return prefix2D[row2 + 1][col2 + 1]
            - prefix2D[row1][col2 + 1]
            - prefix2D[row2 + 1][col1]
            + prefix2D[row1][col1];
   }

    public static void main(String[] args) {
        NumMatrix numMatrix = new NumMatrix(new int[][]{{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}});
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3)); // return 8 (i.e sum of the red rectangle)
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2)); // return 11 (i.e sum of the green rectangle)
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4)); // return 12 (i.e sum of the blue rectangle)
    }

}
