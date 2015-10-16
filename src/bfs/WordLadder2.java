package src.bfs;

import src.WordLadder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yellowstar on 10/14/15.
 */
public class WordLadder2 {
    public static void main(String[] args) {
        WordLadder2 wordLadder2 = new WordLadder2();
        Set<String> dict = new HashSet<>();
        String[] ss = {"ts","sc","ph","ca","jr","hf","to","if","ha","is","io","cf","ta"};
        for (String s : ss ) {
            dict.add(s);
        }
        wordLadder2.findLadders("ta", "if", dict);
    }

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> rets = new ArrayList<>();
        List<String> ret = new ArrayList<>();
        ret.add(start);
        rets.add(ret);
        return bfs(rets, dict, end);
    }

    private List<List<String>> bfs(List<List<String>> rets, Set<String> dict, String end) {
        List<List<String>> newRets = new ArrayList<>();
        List<String> removes = new ArrayList<>();
        boolean hasFound = false;
        boolean hasNext = false;
        for (List<String> ret : rets) {
            String cur = ret.get(ret.size() - 1);
            char[] cs = cur.toCharArray();
            for (int i = 0; i < cs.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == cs[i]) continue;
                    cs[i] = c;
                    String next = new String(cs);
                    cs[i] = cur.charAt(i);
                    if (next.equals(end)) {
                        List<String> sol = new ArrayList<>(ret);
                        sol.add(next);
                        if (!hasFound) {
                            newRets = new ArrayList<>();
                            hasFound = true;
                        }
                        newRets.add(sol);
                        break;
                    }
                    if (dict.contains(next) && !hasFound) {
                        List<String> sol = new ArrayList<>(ret);
                        sol.add(next);
                        newRets.add(sol);
                        removes.add(next);
                        hasNext = true;
                    }
                }
                if (hasFound) break;
            }
        }
        if (hasFound) return newRets;
        if (hasNext) {
            dict.removeAll(removes);
            return bfs(newRets, dict, end);
        } else {
            return newRets;
        }
    }
}
