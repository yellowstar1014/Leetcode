package src;

/**
 * Created by yellowstar on 11/1/15.
 */
public class BestTimeStock {
    public static void main(String[] args) {
        int ret = maxProfit(new int[]{1,4,3,10}, 2);
    }
    // dp(n, state)
    public static int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        if (len <= 1) return 0;
        int[][] dp = new int[len][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i -1][0] + prices[i] - fee);
        }
        return dp[len - 1][1];
    }
}
