package src;

import java.util.*;

/**
 * Created by yellowstar on 9/30/15.
 */
public class LongestChain {
    public static void main(String[] args) {
        LongestChain longestChain = new LongestChain();
        List<String> dict = new ArrayList<>();
        dict.add("aaaa");
        dict.add("aa");
        dict.add("bdcbb");
        dict.add("bdcb");
        dict.add("a");
        dict.add("");
        dict.add("bbb");
        dict.add("bcb");
        dict.add("bb");
        dict.add("b");
        int ret = longestChain.longestChain(dict);
        System.out.println(ret);
    }
    public int longestChain(List<String> dict) {
        Collections.sort(dict, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });

        Set<String> set = new HashSet<>();
        set.addAll(dict);
        int ret = 0;
        for (String s : dict) {
            if (set.contains(s)) {
                set.remove(s);
                List<String> sol = new ArrayList<>();
                sol.add(s);
                ret = Math.max(ret, bfs(sol, set, 1));
            }
        }
        return ret;
    }

    private int bfs(List<String> ret, Set<String> set, int length) {
        List<String> tmp = new ArrayList<>();
        for (String s : ret) {
            for (int i = 0; i < s.length(); i++) {
                String next = null;
                if (i == 0) next = s.substring(i + 1);
                else if (i == s.length() - 1) next = s.substring(0, i);
                else {
                    next = s.substring(0, i) + s.substring(i + 1);
                }
                if (set.contains(next)) {
                    tmp.add(next);
                    set.remove(next);
                }
            }
        }
        if (tmp.size() == 0) return length;
        return bfs(tmp, set, length + 1);
    }
}