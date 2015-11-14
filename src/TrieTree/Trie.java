package src.TrieTree;

import java.util.*;

/**
 * Created by yellowstar on 11/2/15.
 */
public class Trie {
    TrieNode root;
    public Trie() {
        root = new TrieNode(false);
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] words = {"abcd", "abef", "abefg", "bbef", "bbbef"};
        trie.build(words);
        System.out.println(trie.search("abc"));
        System.out.println(trie.search("bbbef"));
        System.out.println(trie.search("bbef"));
        System.out.println(trie.searchWithDot(".bbef"));
        System.out.println(trie.searchWithDot("ab.f"));
        List<String> rets = trie.collect("bb*****ef");
        for (String ret : rets) {
            System.out.println(ret);
        }
    }

    public void build(String[] words) {
        for (String word : words) {
            insert(word);
        }
    }

    public void build(Collection<String> col) {
        for (String word : col) {
            insert(word);
        }
    }

    public void insert(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            TrieNode next = cur.getNext(c);
            if (next == null) {
                next = new TrieNode(false);
                cur.addNext(c, next);
            }
            cur = next;
        }
        cur.isWord = true;
    }

    public boolean searchWithDot(String word) {
        return searchHelper(root, word, 0);
    }

    private boolean searchHelper(TrieNode cur, String word, int i) {
        if (i == word.length()) return cur.isWord;
        char c = word.charAt(i);
        if (c == '.') {
            for (TrieNode next : cur.children) {
                if (next != null) {
                    if (searchHelper(next, word, i + 1)) return true;
                }
            }
            return false;
        } else {
            TrieNode next = cur.getNext(word.charAt(i));
            if (next == null) return false;
            return searchHelper(next, word, i + 1);
        }
    }

    public boolean search(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            TrieNode next = cur.getNext(c);
            if (next == null) return false;
            cur = next;
        }
        return cur.isWord;
    }

    public List<String> collect(String pattern) {
        List<String> rets = new LinkedList<>();
        Set<String> set = new HashSet<>();
        collect(set, new StringBuilder(), pattern, 0, root);
        rets.addAll(set);
        return rets;
    }

    private void collect(Set<String> rets, StringBuilder prefix, String pattern, int i, TrieNode cur) {
        if (cur == null) return;
        if (i == pattern.length() && cur.isWord) {
            rets.add(prefix.toString());
            return;
        }
        if (i == pattern.length()) return;
        char c = pattern.charAt(i);
        if (c == '.') {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                prefix.append(ch);
                collect(rets, prefix, pattern, i + 1, cur.getNext(ch));
                prefix.deleteCharAt(prefix.length() - 1);
            }
        } else if (c == '*') {
            collect(rets, prefix, pattern, i + 1, cur);
            for (char ch = 'a'; ch <= 'z'; ch++) {
                prefix.append(ch);
                collect(rets, prefix, pattern, i, cur.getNext(ch));
                collect(rets, prefix, pattern, i + 1, cur.getNext(ch));
                prefix.deleteCharAt(prefix.length() - 1);
            }
        } else {
            prefix.append(c);
            collect(rets, prefix, pattern, i + 1, cur.getNext(c));
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    public void getAllPath(TrieNode cur, StringBuilder sub, List<String> rets) {
        if (cur.isWord) {
            rets.add(sub.toString());
        }
        for (char c = 'a'; c <= 'z'; c++) {
            TrieNode next = cur.getNext(c);
            if (next != null) {
                sub.append(c);
                getAllPath(next, sub, rets);
                sub.deleteCharAt(sub.length() - 1);
            }
        }
    }
}
