package src.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yellowstar on 10/24/15.
 */
public class LetterNumber {
    public static void main(String[] args) {
       possibleOutput("2011");
    }
    // 1--a.
    // 2--b
    // 26--z
    //given a digit , return the count of the possible output
    public static List<String> possibleOutput(String num) {
        List<String> rets = new LinkedList<>();
        if (num.length() == 0) return rets;
        dfs(rets, "", num, 0);
        return rets;
    }

    private static void dfs (List<String> rets, String sol, String num, int cur) {
        if (cur == num.length()) {
            rets.add(sol);
            return;
        }
        if (num.charAt(cur) == '0') throw new RuntimeException();
        if (cur + 1 < num.length() && num.charAt(cur + 1) == '0') {
            String sub = num.substring(cur, cur + 2);
            int val = Integer.valueOf(sub);
            if (val > 26) throw new RuntimeException();
            char c = (char)('a' + val - 1);
            dfs(rets, sol + c, num, cur + 2);
        } else {
            String sub = num.substring(cur, cur + 1);
            int val = Integer.valueOf(sub);
            char c = (char)('a' + val - 1);
            dfs(rets, sol + c, num, cur + 1);
            if (cur + 1 < num.length()) {
                sub = num.substring(cur, cur + 2);
                val = Integer.valueOf(sub);
                if (val <= 26) {
                    c = (char) ('a' + val - 1);
                    dfs(rets, sol + c, num, cur + 2);
                }
            }
        }
    }
}
