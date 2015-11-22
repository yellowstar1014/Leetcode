package src.pal;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yellowstar on 11/13/15.
 */
public class NumberOfPal {
    // dp[i][j] = s(i,j) is pal or not
    // corner cases
    // i == j -> true;
    // i + 1 = j -> s(i) == S(j)
    public static void main(String[] args) {
        String s = "apple";
        count(s.toLowerCase());
    }
    public static int count(String s) {
        int len = s.length();
        if (len == 0) return 0;
        Set<String> pals = new HashSet<>();
        boolean[][] dp = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (i == j) {
                    dp[i][j] = true;
                } else if (i + 1 == j) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);

                } else {
                    dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                }
                if (dp[i][j]) {
                    pals.add(s.substring(i, j + 1));
                }
            }
        }
        return pals.size();
    }
}
