package src.TrieTree;

/**
 * Created by yellowstar on 11/2/15.
 */
public class TrieNode {
    boolean isWord;
    TrieNode[] children;
    public TrieNode() {
        this.children = new TrieNode[26];
    }
    public TrieNode(boolean isWord) {
        this.isWord = isWord;
        this.children = new TrieNode[26];
    }

    public TrieNode getNext(char c) {
        return children[c - 'a'];
    }

    public void addNext(char c, TrieNode node) {
        children[c - 'a'] = node;
    }
}
