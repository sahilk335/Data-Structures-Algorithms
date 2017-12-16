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

    public void autoTextComplete(TrieNode node) {

        if (node.isEnd == true) {
            matchingStringCount++;
            for (int i = 0; i < prefixStringCollection.size(); i++) {
                System.out.print(prefixStringCollection.get(i));
            }
            System.out.print("\n");
        }
        //Termination condition when node End is true and no moreBranching = false
        if (node.isEnd && !node.hasChildren(node)) {
            return;
        }
        for (int i = 0; i < node.R; i++) {
            if (node.containsKey((char) (i + 'a'))) {
                prefixStringCollection.add((char) (i + 'a'));
                autoTextComplete(node.getLink((char) (i + 'a')));
                prefixStringCollection.remove(prefixStringCollection.size() - 1);
            }
        }
    }

    public void printPrefixStringUtil(String prefix) {
        TrieNode node = searchPrefix(prefix);
        //Append the prefix first that will remain constant
        prefixStringCollection.clear();
        for (int i = 0; i < prefix.length(); i++) {
            prefixStringCollection.add(prefix.charAt(i));
        }
        autoTextComplete(node);
    }

    public void deleteString(String word) {

        //Case when word does not exist in Trie
        if (!search(word)) {
            System.out.print("Word does not exist in Trie");
            return;
        }
        TrieNode node = root;
        deleteUtil(node, word, 0);

    }

    public boolean deleteUtil(TrieNode node, String word, int level) {
        if (level == word.length()) {
            node.isEnd = false;
            if (node.hasChildren(node))             //means it has branches and it is not the last word
                return false;
            else                                    //means it is a last word in its branch, so delete itself
                return true;
        }
        boolean nodeShouldBeDeleteted = deleteUtil(node.getLink(word.charAt(level)), word, level + 1);
        if (nodeShouldBeDeleteted) {                  // Note : For example you are deleting I in SAHIL.. and there is one
            node.isEnd = false;                       // one more word SAHIB .. now you get true from L.. now you are at I
            node.put(word.charAt(level - 1), null); //we will put NULL in position of L ,
            if (node.hasChildren(node)) {             // you check at I that if it has other branches..or not..
                return false;                       // In this case it has.. so it returns false..
            }
            return true;
        }
        return false;
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
        String wordtoDelete = scanner.next();
        System.out.println("\nWord deleted : " + wordtoDelete);
        trie.deleteString(wordtoDelete);
        System.out.println("\nFind all Strings that starts with prefix : ");
        String prefixs = scanner.next();
        trie.printPrefixStringUtil(prefixs);
    }
}