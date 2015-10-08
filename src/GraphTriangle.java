package src;

/**
 * Created by yellowstar on 10/4/15.
 */
public class GraphTriangle {
    public static void main(String[] args) {
        int[][] m = {{0, 1, 1, 1}, {1, 0, 1, 1} ,{1, 1, 0, 1}, {1, 1, 1, 0}};
        System.out.println(new GraphTriangle().calTriangle(m));
    }
    public int calTriangle(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = i + 1; j < col - 1; j++) {
                if (matrix[i][j] == 1) {
                    for (int k = j + 1; k < col; k++) {
                        if (matrix[i][k] == 1 && matrix[j][k] == 1) count++;
                    }
                }
            }
        }
        return count;
    }
}
