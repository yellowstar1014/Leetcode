package src.BST;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yellowstar on 11/4/15.
 */
public class SurroundedRegions {
    private int[] di = {0, -1, 0, 1};
    private int[] dj = {-1, 0, 1, 0};
    public static void main(String[] args) {
        SurroundedRegions surroundedRegions = new SurroundedRegions();
        char[][] board = {{'X', 'O'},{'O', 'X'}};
        surroundedRegions.solve(board);
    }
    public void solve(char[][] board) {
        int row = board.length;
        if (row == 0) return;
        int col = board[0].length;
        // i = 0, row - 1
        for (int j = 0; j < col; j++) {
            if (board[0][j] == 'O') mark(board, 0, j);
            if (board[row - 1][j] == 'O') mark(board, row - 1, j);
        }
        // j = 0
        for (int i = 1; i < row - 1; i++) {
            if (board[i][0] == 'O') mark(board, i, 0);
            if (board[i][col - 1] == 'O') mark(board, i, col - 1);
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == 'T') board[i][j] = 'O';
            }
        }
    }

    private void mark(char[][] board, int i, int j) {
        Queue<Long> queue = new LinkedList<>();
        board[i][j] = 'T';
        long cur = (long)i << 32 | j;
        queue.offer(cur);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            int ni = (int)(cur >> 32);
            int nj = (int)(cur);
            for (int k = 0; k < 4; k++) {
                int ii = ni + di[k];
                int jj = nj + dj[k];
                if (ii >= 0 && ii < board.length && jj >= 0 && jj < board[0].length && board[ii][jj] == 'O') {
                    board[ii][jj] = 'T';
                    queue.offer((long)ii << 32 | jj);
                }
            }
        }
    }
}
