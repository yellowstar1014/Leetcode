package src;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yellowstar on 9/29/15.
 */
public class PerfectNumber {
    public static void main(String[] args) {
        PerfectNumber perfectNumber = new PerfectNumber();
        List<Integer> ret = perfectNumber.numSquares(24);
    }

    public List<Integer> numSquares(int n) {
        int[] dp = new int[n + 1];
        List<Integer>[] rets = new ArrayList[n + 1];
        dp[0] = 0;
        int root = 1;
        for (int i = 1; i <=n; i++) {
            if (root * root == i) {
                dp[i] = 1;
                List<Integer> ret = new ArrayList<>();
                ret.add(root);
                rets[i] = ret;
                root++;
            } else {
                int min = i;
                int last = 1;
                for (int t = root - 1; t > 0; t--) {
                    if (dp[i - t * t] + 1 < min) {
                        min = dp[i - t * t] + 1;
                        last = t;
                    }
                }
                dp[i] = min;
                List<Integer> ret = new ArrayList<>(rets[i - last * last]);
                ret.add(last);
                rets[i] = ret;
            }
        }
        return rets[n];
    }
}
