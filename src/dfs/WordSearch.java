package src.dfs;

/**
 * Created by yellowstar on 10/14/15.
 */
public class WordSearch {
    public static void main (String[] args) {
        char[][] board = {{'a', 'b', 'c'}, {'a', 'e', 'd'}, {'a', 'f', 'g'}}; //"abc","aed","afg"
        WordSearch wordSearch = new WordSearch();
        boolean ret = wordSearch.exist(board, "abcdefg");
    }
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        if (row == 0) return word.isEmpty();
        int col = board[0].length;
        boolean[][] isVisited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dfs(board, isVisited, word, 0, i, j)) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, boolean[][] isVisited, String word, int cur, int i, int j) {
        if (cur == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || isVisited[i][j]
                || word.charAt(cur) != board[i][j]) {
            return false;
        }
        boolean ret = false;
        isVisited[i][j] = true;
        ret = dfs(board, isVisited, word, cur + 1, i + 1, j) || dfs(board, isVisited, word, cur + 1, i - 1, j)
                || dfs(board, isVisited, word, cur + 1, i, j + 1) || dfs(board, isVisited, word, cur + 1, i, j - 1);
        return ret;
    }
}
