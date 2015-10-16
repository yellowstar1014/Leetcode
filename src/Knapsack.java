package src;

import java.util.Arrays;

/**
 * Created by yellowstar on 9/26/15.
 */
public class Knapsack {

    public static void main(String[] args) {
        int[] v = {5, 1, 7, 4};
        int[] u = {6, 4, 8, 6};
        Knapsack knapsack = new Knapsack();
        //int ret = knapsack.knapsack(v, u, 11);
        int[] A = {3,4,8,5};
        int ret = knapsack.knapsack2(10, A);
        System.out.println(ret);
    }
    public int knapsack(int[] v, int[] u, int W) {
        int len = v.length;
        if (len == 0 || W == 0) return 0;
        int[] pre = new int[W + 1];
        int[] cur = new int[W + 1];
        Arrays.fill(pre, 0); // i = 0
        for (int i = 1; i < len + 1; i++) {
            cur[0] = 0;
            for (int w = 1; w <= W; w++) {
                cur[w] = pre[w];
                for (int  h = u[i - 1] / 2; h <= u[i - 1]; h++) {
                    if (h <= w) {
                        cur[w] = Math.max(cur[w], v[i - 1] * h / u[i - 1] + pre[w - h]);
                    } else {
                        break;
                    }
                }
            }
            pre = cur;
        }
        return cur[W];
    }

    public int knapsack2(int m, int[] A) {
        int len = A.length;
        int[][] dp = new int[2][m + 1];
        // i = 0;
        for (int k = 0; k < m + 1; k++) {
            dp[0][k] = A[0] <= k ? A[0] : 0;
        }
        // from i = 1
        for (int i = 1; i < len; i++) {
            dp[i%2][0] = 0;
            for (int k = 1; k < m + 1; k++) {
                if (k - A[i] >= 0) {
                    dp[i%2][k] = Math.max(dp[(i-1)%2][k], dp[(i-1)%2][k - A[i]] + A[i]);
                } else {
                    dp[i%2][k] = dp[(i-1)%2][k];
                }
            }
        }
        return dp[(len - 1)%2][m];
    }
}
