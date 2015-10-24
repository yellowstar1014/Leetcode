package src.dp;

/**
 * Created by yellowstar on 10/21/15.
 */
public class Triangle {
    public static void main(String[] args) {
        int[][] t = {{-1},{2,3},{1,-1,-3}};
        minimumTotal(t);
    }
    public static int minimumTotal(int[][] triangle) {
        int row = triangle.length;
        int col = triangle[row - 1].length;
        int[][] dp = new int[2][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col && j <= i; j++) {
                if (i == 0 && j == 0) dp[0][0] = triangle[0][0];
                else if (j == 0) dp[i%2][j] = dp[(i-1)%2][j] + triangle[i][j];
                else if (j == i) dp[i%2][j] = dp[(i-1)%2][j - 1] + triangle[i][j];
                else {
                    dp[i%2][j] = Math.min(dp[(i-1)%2][j-1], dp[(i-1)%2][j]) + triangle[i][j];
                }
            }
        }
        int min = dp[(row-1)%2][0];
        for (int j = 1; j < col; j++) {
            min = Math.min(min, dp[(row-1)%2][j]);
        }
        return min;
    }
}
