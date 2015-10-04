package src;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yellowstar on 9/30/15.
 */
public class NQueen {
    public static void main(String[] args) {
        NQueen nQueen = new NQueen();
        int[] queen = {3, 1, 2};
        int[] ret = nQueen.getThreats(queen);
    }
    public int[] getThreats(int[] queens) {
        int len = queens.length;
        int[] ret = new int[len];
        Map<Integer, Integer> digonal = new HashMap<>();
        Map<Integer, Integer> digonal2 = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int sum = i + queens[i];
            int diff = i - queens[i];
            if (digonal.containsKey(sum)) {
                ret[i]++;
                int last = digonal.get(sum);
                ret[last]++;
            }
            digonal.put(sum, i);
            if (digonal2.containsKey(diff)) {
                ret[i]++;
                int last = digonal2.get(diff);
                ret[last]++;
            }
            digonal2.put(diff, i);
        }
        return ret;
    }
}
