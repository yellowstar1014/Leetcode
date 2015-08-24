package src;

/**
 * Created by yellowstar on 8/16/15.
 */
public class RegularExpressionMatching {
    // match[i][j] -> whether s(0, i), p(0, j) can match or not
    // match[i][j] =
    // 1. s(i - 1) == p(j - 1) || p(j - 1) == '.' -> match[i - 1][j - 1]
    // 2. p(j - 1) == '*' ->
    //      remove preceding char -> match[i][j - 2]
    //      match zero preceding char -> match[i][j - 1]
    //      match one preceding char -> s(i - 1) == p(j - 2)  && match[i - 1][j - 1]
    //      match one or more preceding char -> s(x) == p(j - 2)  k <= x < i  && match[k][j - 1] 0 <= k < i
    //      match[i - 1][j] -> s(x) == p(j - 2) k <= x < i - 1 && match[k][j - 1] 0 <= k < i - 1
    //   -> match[i][j] = s(i - 1) == p(j - 2) && match[i - 1][j] || match[i - 1][j - 1]
    // corner case
    // p(0) == '*' -> remove *
    // j == 1 match[1, 1] = s(i - 1) == p(j - 1)  match[i, 1] = false (i > 1); match[0][1] = false;
    // match[0][0] = true;
    // macth[0][j] = p(j - 1) == '*' && match[0][j - 1] (j != 0) -> p(0) != '*' -> match[0][j] = false;
    // match[i][0] = false; (i != 0)
    // optimization 2D->1D
    // save match[j - 1] and match[j - 2] in last row
    public static void main(String[] args) {
        RegularExpressionMatching regularExpressionMatching = new RegularExpressionMatching();
        regularExpressionMatching.isMatch("aaba", "ab*a*c*a");
    }
    public boolean isMatch(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        int x = 0;
        while (x < plen) {
            if (p.charAt(x) == '*') x++;
            else break;
        }
        p = p.substring(x);
        plen = p.length();

        if (plen == 0 && slen == 0) return true;
        else if (plen == 0 && slen != 0) return false;

        boolean[] match = new boolean[plen + 1];

        for (int i = 0; i <= slen; i++) {
            boolean diag = match[1];
            match[0] = i == 0 ? true : false;
            match[1] = i == 1 ? s.charAt(0) == p.charAt(0) : false;
            for (int j = 2; j <= plen; j++) {
                boolean tmp = match[j];
                if (i == 0) {
                    match[j] = p.charAt(j - 1) == '*' && (match[j - 2] || match[j - 1]);
                }
                else if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    match[j] = diag;
                }
                else if (p.charAt(j - 1) == '*') {
                    match[j] = match[j - 2] || match[j - 1] || (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')
                            && (match[j] || diag);
                }
                else {
                    match[j] = false;
                }
                diag = tmp;
            }
        }
        return match[plen];
    }
}
