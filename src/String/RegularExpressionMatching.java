package src.String;

/**
 * Created by yellowstar on 8/16/15.
 */
public class RegularExpressionMatching {
    public static void main(String[] args) {
        RegularExpressionMatching regularExpressionMatching = new RegularExpressionMatching();
        boolean ret = regularExpressionMatching.isMatch("aaba", "ab*a*b*a");
        System.out.println(ret);
    }
    // dp[i][j] -> whether s[0, i) p[0, j) match or not
    // dp[i][j] =
    // p(j - 1) != '*'     dp[i - 1][j - 1] && (p(j - 1) == '.' || s(i - 1) == p(j - 1))
    // p(j - 1) == '*' ->  dp[i][j - 2] ||
    //                      dp[i - 1][j - 1] && (s(i - 1) == p(j - 2) || p(j - 2) == '.') ||
    //                      dp[i - 1][j] && (s(i - 1) == p(j - 2) || p(j - 2) == '.')
    // corner case
    // dp[0][0] = true;
    // i = 0 ->
    //      p(j - 1) == '*' -> if (j == 1) dp[0][1] = true;
    //                      -> else dp[0][j] = dp[0][j - 1] || dp[0][j - 2]
    //      else -> false
    // j = 0 -> dp[i][0] = false (i != 0)
    //
    //
    // optimization Space -> O(plen)
    // dp[j];
    // dp[j] =
    // p(j - 1) != '*'     diag && (p(j - 1) == '.' || s(i - 1) == p(j - 1))
    // p(j - 1) == '*' ->  dp[j - 2] ||
    //                      dp[j] && (s(i - 1) == p(j - 2) || p(j - 2) == '.')
    // corner case
    // i = 0 -> dp[0] = true;
    //      p(j - 1) == '*' -> dp[1] = true;
    //                      -> else dp[j] = dp[j - 1] || dp[j - 2]
    //      else -> dp[j] = false;
    // i > 0 -> dp[0] = false
    public boolean isMatch(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        boolean[] dp = new boolean[plen + 1];
        dp[0] = true;
        for (int j = 1; j < plen + 1; j++) {
            if (p.charAt(j - 1) == '*') {
                if (j == 1) dp[j] = dp[j - 1];
                else dp[j] = dp[j - 1] || dp[j - 2];
            }
        }

        for (int i = 1; i < slen + 1; i++) {
            boolean diag = dp[0];
            dp[0] = false;
            for (int j = 1; j < plen + 1; j++) {
                boolean tmp = dp[j];
                if (p.charAt(j - 1) != '*' ) {
                    dp[j] = diag && (p.charAt(j - 1) == '.' || s.charAt(i - 1) == p.charAt(j - 1));
                } else {
                    dp[j] = dp[j - 2] || dp[j] && p.charAt(j - 2) == '.'  || s.charAt(i - 1) == p.charAt(j - 2);
                }
                diag = tmp;
            }
        }

        return dp[plen];
    }
}
