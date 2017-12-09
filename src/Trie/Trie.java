package Trie;

import java.util.ArrayList;
import java.util.Scanner;

public class Trie {

    private TrieNode root;
    ArrayList<Character> prefixStringCollection;
    int matchingStringCount;

    public Trie() {
        root = new TrieNode();
        prefixStringCollection = new ArrayList<Character>();
        matchingStringCount = 0;
    }

    //Insert a word in a Trie
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currWord = word.charAt(i);
            if (!node.containsKey(currWord)) {
                node.put(currWord, new TrieNode());
            }
            node = node.getLink(currWord);
        }
        node.setEnd();
    }

    // search a prefix or whole key in trie and
    // returns the node where search ends
    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currWord = word.charAt(i);
            if (node.containsKey(currWord)) {
                node = node.getLink(currWord);
            } else {
                return null;
            }
        }
        return node;
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    //AutoTextComplete feature implemeted like in chrome string search

    public void autoTextComplete(String prefix, TrieNode node) {
        boolean moreBranching = false;
        for (int i = 0; i < node.R; i++) {
            if (node.containsKey((char) (i + 'a'))) {
                moreBranching = true;                 //means node still contains some char going through it
                break;
            }
        }
        if (node.isEnd == true) {
            matchingStringCount++;
            for (int i = 0; i < prefixStringCollection.size(); i++) {
                System.out.print(prefixStringCollection.get(i));
            }
            System.out.print("\n");
        }
        //Termination condition when node End is true and no moreBranching = false
        if (node.isEnd & !moreBranching) {
            return;
        }
        for (int i = 0; i < node.R; i++) {
            if (node.containsKey((char) (i + 'a'))) {
                prefixStringCollection.add((char) (i + 'a'));
                autoTextComplete(prefix, node.getLink((char) (i + 'a')));
                prefixStringCollection.remove(prefixStringCollection.size() - 1);
            }
        }
    }

    public void printPrefixStringUtil(String prefix) {
        TrieNode node = searchPrefix(prefix);
        //Append the prefix first that will remain constant
        for (int i = 0; i < prefix.length(); i++) {
            prefixStringCollection.add(prefix.charAt(i));
        }
        autoTextComplete(prefix, node);
    }

    public static void main(String args[]) {
        Trie trie = new Trie();
        trie.insert("sahil");
        trie.insert("pranshu");
        trie.insert("zoo");
        trie.insert("zoomba");
        trie.insert("prince");
        trie.insert("samsung");
        trie.insert("sam");
        System.out.print("Search Available :" + trie.search("sams"));
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nFind all Strings that starts with prefix : ");
        String prefix = scanner.next();
        trie.printPrefixStringUtil(prefix);
        System.out.println("\n Number of prefixString Match = " + trie.matchingStringCount);
    }
}