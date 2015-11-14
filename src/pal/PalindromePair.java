package src.pal;

import src.TrieTree.Trie;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by yellowstar on 10/25/15.
 */
public class PalindromePair {
    public static void main(String[] args) {
        Set<String> dict = new HashSet<>();
        dict.add("aaa");
        dict.add("a");
        dict.add("abc");
        dict.add("cba");
        dict.add("ba");
        dict.add("cb");
        List<String[]> rets = findPair(dict);
        for (String[] ret : rets) {
            System.out.println(ret[0] + "  " + ret[1]);
        }
        rets = findPair(dict);
        System.out.println();
        for (String[] ret : rets) {
            System.out.println(ret[0] + "  " + ret[1]);
        }
    }

    public static List<String[]> findPair(Set<String> dict) {
        List<String[]> rets = new LinkedList<>();
        for (String s : dict) {
            String re = new StringBuilder(s).reverse().toString();
            if (dict.contains(re) && !re.equals(s)) {
                String[] sol = new String[2];
                sol[0] = s;
                sol[1] = re;
                rets.add(sol);
            }
            for (int i = 1; i < s.length(); i++) {
                String a = s.substring(0, i);
                String b = s.substring(i);
                if (isPal(a)) {
                    re = new StringBuilder(b).reverse().toString();
                    if (dict.contains(re)) {
                        String[] sol = new String[2];
                        sol[0] = s;
                        sol[1] = re;
                        rets.add(sol);
                    }
                }
                if (isPal(b)) {
                    re = new StringBuilder(a).reverse().toString();
                    if (dict.contains(re)) {
                        String[] sol = new String[2];
                        sol[0] = re;
                        sol[1] = s;
                        rets.add(sol);
                    }
                }
            }
        }
        return rets;
    }

    private static boolean isPal(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return true;
    }

}
