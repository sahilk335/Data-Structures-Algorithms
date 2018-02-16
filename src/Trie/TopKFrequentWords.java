package Trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopKFrequentWords {
    /*
     * @Author : Sahil
     * Date : 14 Feb 2018 (Valentines day) 😀
     *
     * Refernces : https://leetcode.com/problems/top-k-frequent-words/description/
     *
     *
     * Given a list of words and an integer k, return the top k frequent words in the list.
     * Example
     * Given
     * [
     *     "yes", "lint", "code",
     *     "yes", "code", "baby",
     *     "you", "baby", "chrome",
     *     "safari", "lint", "code",
     *     "body", "lint", "code"
     * ]
     * for k = 3, return ["code", "lint", "baby"].
     * for k = 4, return ["code", "lint", "baby", "yes"],
     * Note
     * You should order the words by the frequency of them in the return list, the most frequent one comes first.
     * If two words has the same frequency, the one with lower alphabetical order come first.
     *
     *
     * Solution :
     * 1. Create a modified Trie Node that has word frequency with every isEnd = true node
     * 2. Create a modified Heap Array that store word and word frequency
     *      2.1 Heap Array is created after completing the Trie insertion
     *      2.2 And then travel all unique Trie Strings
     * 3. Apply Heapify Operation k times
     *
     */

    //This is modified Trie class that stores frequency of each word also
    int totalDistinctWordsInTrie = 0;
    int heapIndex = 0;
    List<String> solution = new ArrayList<String>();

    private class HeapArray {
        String word;
        int wordFrequency;

    }

    private class Tries {
        TrieNode root;

        Tries() {
            root = new TrieNode();
        }

        public void insert(String s) {
            TrieNode node = root;
            for (int i = 0; i < s.length(); i++) {
                char currWord = s.charAt(i);
                if (!node.containsKey(currWord)) {
                    node.put(currWord, new TrieNode());
                }
                node = node.getLink(currWord);
            }

            //If word alredy exist then increment its frequency
            if (node.isEnd()) {
                node.wordFrequency++;
            } else {
                //else set the word end and initialize its frequency from 1
                node.setEnd();
                node.wordFrequency = 1;
                //This is unique word always
                totalDistinctWordsInTrie++;
            }
        }
    }


    public List<String> topKFrequent(String[] words, int k) {
        List<String> topKWordsList = new ArrayList<String>();
        Tries trie = new Tries();
        //Insert words in trie node
        for (String word : words) {
            trie.insert(word);
        }

        //Build Heap of TrieNodes
        buildHeap(trie, topKWordsList, k);
        Collections.reverse(solution);

        return solution;
    }

    //Traverse Trie and and add each unique string in a heap array
    public void traverseTrie(TrieNode node, HeapArray[] heapArrays, ArrayList<Character> charArray) {
        if (node.isEnd) {
            StringBuilder builder = new StringBuilder(charArray.size());
            for (Character ch : charArray) {
                builder.append(ch);
            }
            heapArrays[heapIndex].word = builder.toString();
            heapArrays[heapIndex].wordFrequency = node.wordFrequency;
            heapIndex++;
        }
        if (node.isEnd && !node.hasChildren(node)) {
            return;
        }
        for (int i = 0; i < node.R; i++) {
            if (node.containsKey((char) (i + 'a'))) {
                charArray.add((char) (i + 'a'));
                traverseTrie(node.getLink((char) (i + 'a')), heapArrays, charArray);
                charArray.remove(charArray.size() - 1);
            }
        }
    }

    //Build Heap and call heapify K times
    public void buildHeap(Tries trie, List<String> topKWordsList, int k) {

        HeapArray heapArray[] = new HeapArray[totalDistinctWordsInTrie];
        //Initialize
        for (int i = 0; i < totalDistinctWordsInTrie; i++) {
            heapArray[i] = new HeapArray();
            heapArray[i].wordFrequency = 0;
            heapArray[i].word = new String();
        }
        ArrayList<Character> wordList = new ArrayList<Character>();
        traverseTrie(trie.root, heapArray, wordList);
        /*for (int i = 0; i < heapArray.length; i++) {
            System.out.println(heapArray[i].word + " -> " + heapArray[i].wordFrequency);
        }*/

        //Now Actually build the heap,start calling heapify on leaf nodes
        int size = heapArray.length;
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(heapArray, size, i);
        }

        //Call Heapify K times
        for (int i = 0; i < k; i++) {
            solution.add(heapArray[0].word);
            swap(heapArray, size - i - 1, 0);
            heapify(heapArray, size - i, 0);

        }
    }

    public void heapify(HeapArray heapArray[], int size, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < size && heapArray[left].wordFrequency >= heapArray[largest].wordFrequency)
            largest = left;
        if (right < size && heapArray[right].wordFrequency >= heapArray[largest].wordFrequency)
            largest = right;
        if (largest != i) {
            swap(heapArray, largest, i);
            heapify(heapArray, size, largest);
        }
    }

    public void swap(HeapArray heapArray[], int j, int i) {
        HeapArray tempHeapElement = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = tempHeapElement;
    }

    public static void main(String args[]) {
        TopKFrequentWords topKwords = new TopKFrequentWords();
        List<String> topKWordsList = new ArrayList<String>();
        int k = 2;
        String words[] = {"the", "day", "is", "sunny", "sunny", "is", "is", "is", "is", "day", "day"};
        topKwords.topKFrequent(words, k);
    }
}