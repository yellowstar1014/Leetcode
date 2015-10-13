package src;

/**
 * Created by yellowstar on 10/10/15.
 */
public class BitFlip {
    // dp[i] = the max diff (number of zero - number of one) subarray of A[0] to A[i] end at i;
    // dp[i] = Max(0, dp[i - 1]) + A[i] == 0 ? 1 : -1;
    // corner case
    // i = 1 -> dp[0] = A[0] == 0 ? 1 : -1;
    public int maxAfterFlip(int[] A) {
        int len = A.length;
        int[] dp = new int[len];
        dp[0] = A[0] == 0 ? 1 : -1;
        int ret = dp[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(0, dp[i - 1]) + A[i] == 0 ? 1 : -1;
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }
}
