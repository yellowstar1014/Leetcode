package src;

/**
 * Created by yellowstar on 8/23/15.
 */
public class Search2DMatrix2 {
    public static void main(String[] args) {
        Search2DMatrix2 search2DMatrix2 = new Search2DMatrix2();
        int[][] matrix = {{1, 3, 5}};
        boolean result = search2DMatrix2.searchMatrix(matrix, 2);
    }

    // binary partition
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;
        return helper(matrix, 0, 0, m - 1, n - 1, target);
    }

    public boolean helper(int[][] matrix, int si, int sj, int ei, int ej, int target) {
        if (sj >= matrix[0].length || ei < 0 || si >= matrix.length || ej >= matrix[0].length || ej < 0) return false;
        int mid = (si + ei) / 2;
        int j = binarySearchRow(matrix, mid, sj, ej, target);
        if (j >= 0 && matrix[mid][j] == target) return true;
        return helper(matrix, 0, j + 1, mid - 1, matrix[0].length - 1, target) ||
                helper(matrix, mid + 1, 0, matrix.length - 1, j, target);
    }

    private int binarySearchRow(int[][] matrix, int i, int sj, int ej, int target) {
        int left = sj;
        int right = ej;
        while (left < right) {
            int mid = (left + right) / 2;
            if (target > matrix[i][mid]) left = mid + 1;
            else if (target < matrix[i][mid]) right = mid - 1;
            else return mid;
        }
        if (target >= matrix[i][left]) return left;
        return left - 1;
    }

    // view the matrix as a BST
    // O(m+n)
    public boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;
        // search from bottom left
        int i = m - 1;
        int j = 0;
        while (i >= 0 && j < n) {
            if (target > matrix[i][j])
                j++;
            else if (target < matrix[i][j])
                i--;
            else
                return true;
        }
        return false;
    }
}
