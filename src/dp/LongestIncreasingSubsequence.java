package src.dp;

/**
 * Created by yellowstar on 10/22/15.
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        longestIncreasingSubsequence(new int[]{9,3,6,2,7});
    }
    public static int longestIncreasingSubsequence(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            dp[i] = 1;
            for (int k = 0; k < i; k++) {
                if (nums[i] >= nums[k]) {
                    dp[i] = Math.max(dp[i], dp[k] + 1);
                }
            }
        }
        int max = 1;
        for (int i = 0; i < len; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
