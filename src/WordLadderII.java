package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yellowstar on 8/25/15.
 */
public class WordLadderII {
    public static void main(String[] args) {
        WordLadderII wordLadderII = new WordLadderII();
        String start = "a";
        String end = "c";
        Set<String> dict = new HashSet<>();
        dict.add("a");
        dict.add("b");
        dict.add("c");
        List<List<String>> rets = wordLadderII.findLadders(start, end, dict);
    }

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> rets = new ArrayList<>();
        List<List<String>> starts = new ArrayList<>();
        List<String> oneStart = new ArrayList<>();
        oneStart.add(start);
        starts.add(oneStart);
        bfs(starts, end, dict, rets);
        return rets;
    }

    public void bfs(List<List<String>> starts, String end, Set<String> dict, List<List<String>> rets) {
        int size = starts.size();
        if (size == 0) return;
        boolean hasRet = false;
        List<String> removes = new ArrayList<>();
        List<List<String>> newStarts = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            List<String> start = starts.get(j);
            int oneSize = start.size();
            String cur = start.get(oneSize - 1);
            char[] cs = cur.toCharArray();
            boolean found = false;
            for (int i = 0; i < cs.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    if (cs[i] == c) continue;
                    cs[i] = c;
                    String next = new String(cs);
                    cs[i] = cur.charAt(i);
                    if (next.equals(end)) {
                        List<String> ret = new ArrayList<>(start);
                        ret.add(next);
                        rets.add(ret);
                        found = true;
                        hasRet = true;
                        break;
                    }
                    else if (!hasRet && dict.contains(next)) {
                        List<String> newStart = new ArrayList<>(start);
                        newStart.add(next);
                        newStarts.add(newStart);
                        removes.add(next);
                    }
                }
                if (found) break;
            }
        }
        if (hasRet) return;
        dict.removeAll(removes);
        bfs(newStarts, end, dict, rets);
    }
}
