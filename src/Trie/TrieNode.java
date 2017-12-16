package Trie;

public class TrieNode {

    TrieNode[] links;
    int R = 26;
    boolean isEnd;

    public TrieNode() {
        links = new TrieNode[R];
    }

    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    public TrieNode getLink(char ch) {
        return links[ch - 'a'];
    }

    public void put(char ch, TrieNode node) {
        links[ch - 'a'] = node;
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public boolean hasChildren(TrieNode node) {
        for (int i = 0; i < node.R; i++) {
            if (node.containsKey((char) (i + 'a'))) {
                return true;
            }
        }
        return false;
    }

}
