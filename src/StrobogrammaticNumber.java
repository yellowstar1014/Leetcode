package src;

import java.util.*;

/**
 * Created by yellowstar on 10/26/15.
 */
public class StrobogrammaticNumber {
    public static void main(String[] args) {
        StrobogrammaticNumber strobogrammaticNumber = new StrobogrammaticNumber();
        int ret = strobogrammaticNumber.strobogrammaticInRange("50", "100");
    }

    public int strobogrammaticInRange(String low, String high) {
        int llen = low.length();
        int hlen = high.length();
        if (compare(low, high) > 0) return 0;
        dfs("", low, high);
        return total;
    }

    private void dfs(String sol, String lo, String hi) {
        if (compare(sol, hi) > 0) {
            return;
        }
        if ((sol.length() == 1 || sol.length() != 0 && sol.charAt(0) != '0') && compare(lo, sol) <= 0) {
            total++;
        }

        if (sol.isEmpty()) {
            dfs("0", lo, hi);
            dfs("1", lo, hi);
            dfs("8", lo, hi);
        }
        for (char c : map.keySet()) {
            dfs(c + sol + map.get(c), lo, hi);
        }
    }

    private int compare(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 != len2) return len1 - len2;
        return s1.compareTo(s2);
    }

    private int total = 0;
    private Map<Character, Character> map = new HashMap<>();{
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        map.put('6', '9');
        map.put('9', '6');
    }
}
