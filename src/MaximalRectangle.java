package src;

import java.util.Arrays;

/**
 * Created by yellowstar on 8/28/15.
 */
public class MaximalRectangle {
    public static void main(String[] args) {
        MaximalRectangle maximalRectangle = new MaximalRectangle();
        char[][] matr = {{'0','1','0','0'},{'1','0','0','1'},{'1','1','1','1'},{'0','1','0','1'}};
        int ret = maximalRectangle.maximalRectangle(matr);
    }
    // 1 0 1 0
    // 1 1 1 0
    // 1 1 1 0
    // 0 1 0 1
    // to calculte area of a rect we need left, right, height
    // left[i][j] -> the left index of the max rectangle having matrix[i][j] in it
    // left[i][j] =
    //              1. matrix[i][j] == '0' -> -1;
    //              2. maxtrix[i][j] == '1' -> max(left[i - 1][j], curleft);
    // right[i][j] =
    //              1. -> n
    //              2. -> min(right[i - 1][j], curright)
    // height[i][j] =
    //              1. -> 0;
    //              2. -> height[i - 1][j];
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] height = new int[n];
        int max = 0;
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Arrays.fill(height, 0);

        for (int i = 0; i < m; i++) {
            // calculate height
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') height[j]++;
                else height[j] = 0;
            }
            // calculate left;
            int curleft = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') left[j] = Math.max(left[j], curleft);
                else {
                    left[j] = -1;
                    curleft = j + 1;
                }
            }
            // calculate right
            int curright = n;
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') right[j] = Math.min(right[j], curright);
                else {
                    right[j] = n;
                    curright = j;
                }
            }

            // compute max area
            for (int j = 0; j < n; j++) {
                max = Math.max(max, (right[j] - left[j]) * height[j]);
            }
        }
        return max;
    }
}
