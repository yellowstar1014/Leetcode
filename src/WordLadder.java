package src;

import java.util.*;

/**
 * Created by yellowstar on 8/25/15.
 */
public class WordLadder {
    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder();
        String start = "a";
        String end = "c";
        Set<String> dict = new HashSet<>();
        dict.add("a");
        dict.add("b");
        dict.add("c");
        wordLadder.ladderLength(start, end, dict);
    }

    public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
        int length = 1;
        if (beginWord.equals(endWord)) return length;
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                char[] cs = cur.toCharArray();
                for (int j = 0; j < cs.length; j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (cs[j] == c) continue;
                        cs[j] = c;
                        String next = new String(cs);
                        cs[j] = cur.charAt(j);
                        if (next.equals(endWord)) return ++length;
                        else if (wordDict.contains(next)) {
                            queue.offer(next);
                            wordDict.remove(next);
                        }
                    }
                }
            }
            length++;
        }
        return 0;
    }
}
