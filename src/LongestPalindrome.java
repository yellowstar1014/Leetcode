package src;

/**
 * Created by yellowstar on 10/7/15.
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        String ret = longestPalindrome.longestPalindrome("ccc");
    }
    public String longestPalindrome(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (i == j) dp[i][j] = true;
                else if (i + 1 == j) dp[i][j] = s.charAt(i) == s.charAt(j);
                else dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
            }
        }

        String ret = "";
        for (int l = len; l > 0; l--) {
            for (int i = 0; i < len - l + 1; i++) {
                if (dp[i][i + l - 1]) {
                    return s.substring(i, i + l);
                }
            }
        }
        return ret;
    }
}
