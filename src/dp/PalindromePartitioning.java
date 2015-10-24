package src.dp;

/**
 * Created by yellowstar on 10/22/15.
 */
public class PalindromePartitioning {
    public static void main(String[] args) {
        minCut("efe");
    }
    public static int minCut(String s) {
        int len = s.length();
        if (len == 0) return 0;
        boolean[][] isPal = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (i == j) isPal[i][j] = true;
                else if (i + 1 == j) isPal[i][j] = s.charAt(i) == s.charAt(j);
                else {
                    isPal[i][j] = s.charAt(i) == s.charAt(j) && isPal[i + 1][j - 1];
                }
            }
        }
        int[] dp = new int[len];
        dp[0] = 0;
        for (int i = 1; i < len; i++) {
            if (isPal[0][i]) {
                dp[i] = 0;
                continue;
            }
            int min = i;
            for (int k = 0; k < i; k++) {
                if (isPal[k+1][i]) {
                    min = Math.min(min, dp[k] + 1);
                }
            }
            dp[i] = min;
        }
        return dp[len - 1];
    }
}
