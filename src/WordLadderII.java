package src;

import java.util.*;

/**
 * Created by yellowstar on 8/25/15.
 */
public class WordLadderII {
    public static void main(String[] args) {
        WordLadderII wordLadderII = new WordLadderII();
        String start = "hot";
        String end = "dog";
        Set<String> dict = new HashSet<>();
        dict.add("hot");
        dict.add("cog");
        dict.add("dog");
        dict.add("tot");
        dict.add("hog");
        dict.add("hop");
        dict.add("pot");
        dict.add("dot");
        List<List<String>> rets = wordLadderII.findLadders(start, end, dict);
    }

    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> rets = new LinkedList<>();
        List<List<String>> paths = new LinkedList<>();
        List<String> path = new LinkedList<>();
        path.add(beginWord);
        paths.add(path);
        bfs(paths, endWord, wordList, rets);
        return rets;
    }

    private void bfs(List<List<String>> paths, String end, Set<String> wordList, List<List<String>> rets) {
        List<List<String>> updates = new LinkedList<>();
        List<String> removes = new LinkedList<>();
        boolean getEnd = false;
        for (List<String> path : paths) {
            String cur = path.get(path.size() - 1);
            List<String> nexts = expand(cur, wordList, end);
            for (String next : nexts) {
                List<String> update = new LinkedList<>(path);
                if (next.equals(end)) {
                    update.add(next);
                    rets.add(update);
                    getEnd = true;
                } else if (!getEnd && wordList.contains(next)) {
                    update.add(next);
                    updates.add(update);
                    removes.add(next);
                }
            }
        }
        if (getEnd || updates.size() == 0) return;
        wordList.removeAll(removes);
        bfs(updates, end, wordList, rets);
    }

    private List<String> expand(String cur, Set<String> wordList, String end) {
        List<String> nexts = new LinkedList<>();
        char[] cs = cur.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (cs[i] == c) continue;
                cs[i] = c;
                String next = new String(cs);
                cs[i] = cur.charAt(i);
                if (cur.equals(end)) {
                    nexts.clear();
                    nexts.add(next);
                    return nexts;
                }
                if (wordList.contains(next)) {
                    nexts.add(next);
                }
            }
        }
        return nexts;
    }
}
