package src;

import java.util.*;

/**
 * Created by yellowstar on 11/3/15.
 */
public class WordLadder2 {
    private Map<String, List<String>> graph = new HashMap<>();
    public static void main(String[] args) {
        WordLadder2 wordLadder2 = new WordLadder2();
        Set<String> dict = new HashSet<>();
        //"hot","cog","dog","tot","hog","hop","pot","dot"
        dict.add("hot");
        dict.add("cog");
        dict.add("dog");
        dict.add("tot");
        dict.add("hog");
        dict.add("hop");
        dict.add("pot");
        dict.add("dot");
        wordLadder2.findLadders("hot", "dog", dict);
    }
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> rets = new LinkedList<>();
        bfs(beginWord, endWord, wordList);
        dfs(rets, new LinkedList<>(), endWord, beginWord);
        return rets;
    }

    private void bfs(String start, String end, Set<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<String> allNext = new HashSet<>();
            boolean getEnd = false;
            while (size-- > 0) {
                String cur = queue.poll();
                List<String> nexts = expand(cur, wordList, end);
                for (String next : nexts) {
                    if (next.equals(end)) getEnd = true;
                    List<String> pre = graph.get(next);
                    if (pre == null) {
                        pre = new LinkedList<>();
                        graph.put(next, pre);
                    }
                    pre.add(cur);
                }
                allNext.addAll(nexts);
            }
            for (String s : allNext) {
                queue.offer(s);
            }
            wordList.removeAll(allNext);
            if (getEnd) break;
        }
    }

    private void dfs(List<List<String>> rets, List<String> path, String cur, String end) {
        path.add(cur);
        if (cur.equals(end)) {
            List<String> sol = new LinkedList<>(path);
            Collections.reverse(sol);
            rets.add(sol);
        }
        List<String> nexts = graph.get(cur);
        if (nexts == null) return;
        for (String next : nexts) {
            dfs(rets, path, next, end);
        }
        path.remove(cur);
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
