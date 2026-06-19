package vn.com.leetcode.Arrays_Hashing;

import java.util.HashSet;
import java.util.Set;

public class IsValidSudoku {

    /**
     * Level: Medium
     * Start: 19:30 26/04/2026
     * End: 20:00 26/04/2026
     * Pause:
     */
    public boolean isValidSudoku(char[][] board) {
        // Verify the vertical
        for (int i = 0; i < 9; i++) {
            Set<Character> set = new HashSet<>(9);
            for (char character : board[i]) {
                if (character == '.'){
                    continue;
                } else if (set.contains(character)) {
                    return false;
                }
                set.add(character);
            }
        }

        // Verify the horizontal
        for (int i = 0; i < 9; i++) {
            Set<Character> set = new HashSet<>(9);
            for (int j = 0; j < 9; j++) {
                if (board[j][i] == '.'){
                    continue;
                } else if (set.contains(board[j][i])) {
                    return false;
                }
                set.add(board[j][i]);
            }
        }

        // Verify the 3x3
        for (int i = 0; i < 9; i++) {
            Set<Character> set = new HashSet<>(9);
            for (int j = 0; j < 9; j++) {
                int row = 3 * (i/3) + (j/3);
                int col = 3 * (i%3) + (j%3);

                if (board[row][col] == '.') {
                    continue;
                } else if (set.contains(board[row][col])) {
                    return false;
                }
                set.add(board[row][col]);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsValidSudoku object = new IsValidSudoku();
        char[][] board = new char[][]{
                {'1','2','.','.','3','.','.','.','.'},
                {'4','.','.','5','.','.','.','.','.'},
                {'.','9','1','.','.','.','.','.','3'},
                {'5','.','.','.','6','.','.','.','4'},
                {'.','.','.','8','.','3','.','.','5'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','.','.','.','.','.','2','.','.'},
                {'.','.','.','4','1','9','.','.','8'},
                {'.','.','.','.','8','.','.','7','9'}};

        System.out.println(object.isValidSudoku(board));
    }
}
