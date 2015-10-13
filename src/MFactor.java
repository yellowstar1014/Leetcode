package src;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yellowstar on 10/11/15.
 */
//Given two numbers n and m, represent n as a product of m of its factors
// E.g. n = 5, m = 2 => 1 * 5
// m = 3, 1 * 1 * 5
// E.g. n = 64, m = 3 => 1 * 1 * 64, 1 * 2 * 32, ..., 4 * 4 * 4)
public class MFactor {
    public static void main(String[] args) {
        MFactor mFactor = new MFactor();
        List<List<Integer>> rets = mFactor.calculate(3, 64);
    }
    public List<List<Integer> > calculate(int m, int n) {
        List<List<Integer>> rets = new ArrayList<>();
        helper(rets, new ArrayList<>(), 1, n, m);
        return rets;
    }

    private void helper(List<List<Integer>> rets, List<Integer> sol, int cur, int target, int size) {
        if (sol.size() == size) {
            if (target == 1) {
                rets.add(new ArrayList<>(sol));
            }
            return;
        }

        for (int i = cur; i <= target; i++) {
            if (target % i == 0) {
                sol.add(i);
                helper(rets, sol, i, target / i, size);
                sol.remove(sol.size() - 1);
            }
        }
    }
}
