package Strings;

import java.util.*;

public class WordLadder {
    /*
     *@Author : Sahil
     * Date : 06 May 2019
     *
     * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
     *
     * Only one letter can be changed at a time.
     * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
     * Note:
     *
     * Return 0 if there is no such transformation sequence.
     * All words have the same length.
     * All words contain only lowercase alphabetic characters.
     * You may assume no duplicates in the word list.
     * You may assume beginWord and endWord are non-empty and are not the same.
     * Example 1:
     *
     * Input:
     * beginWord = "hit",
     * endWord = "cog",
     * wordList = ["hot","dot","dog","lot","log","cog"]
     *
     * Output: 5
     *
     * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     * return its length 5.
     * Example 2:
     *
     * Input:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log"]
     *
     * Output: 0
     *
     * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
     *
     *
     * References : https://leetcode.com/problems/word-ladder/
     * https://leetcode.com/problems/word-ladder/solution/
     *
     *
     * Solution :
     *
     * Example :
     *
     * 1. Create map for all combinations and its corresponding words
     *
     *      d*g -> [dog]
     *      c*g -> [cog]
     *      ho* -> [hot]
     *      *og -> [dog, log, cog]
     *      h*t -> [hot]
     *      lo* -> [lot, log]
     *      l*t -> [lot]
     *      l*g -> [log]
     *      do* -> [dot, dog]
     *      *ot -> [hot, dot, lot]
     *      d*t -> [dot]
     *      co* -> [cog]
     *
     * 2. Create a new map <String,Boolean> that contains if the currString is visited or not
     *
     * 3. Apply BFS. add all adjacent words to the queue (get from the map)
     *
     * 4. Maintain level, return the level where endString is found.
     *
     *
     */

    class Word {
        String currWord;
        int level;

        Word(String currWord, int level) {
            this.level = level;
            this.currWord = currWord;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        //Map of comb Word * with its original mapping
        Map<String, ArrayList<String>> mapWordComb = new HashMap<>();
        int L = beginWord.length();
        for (int i = 0; i < wordList.size(); i++) {
            String currWord = wordList.get(i);

            //generate all word combinations with * in the map
            for (int j = 0; j < L; j++) {
                String newCombWord = currWord.substring(0, j) + '*' + currWord.substring(j + 1, L);
                ArrayList<String> correspondingWordList = mapWordComb.getOrDefault(newCombWord, new ArrayList<String>());
                correspondingWordList.add(currWord);
                mapWordComb.put(newCombWord, correspondingWordList);
            }
        }

        //Queue for BFS
        Queue<Word> queue = new LinkedList<>();
        Word first = new Word(beginWord, 1);
        queue.add(first);

        //Visited set to make sure no cycle
        Set<String> set = new HashSet<String>();
        set.add(beginWord);

        while (!queue.isEmpty()) {
            Word currElement = queue.remove();
            String currWord = currElement.currWord;
            int currLevel = currElement.level;

            for (int i = 0; i < L; i++) {
                //get the  transformation of the word
                String newWord = currWord.substring(0, i) + '*' + currWord.substring(i + 1, L);

                //get all its corresponding words from the map and check if it has endword , else keep on adding
                ArrayList<String> correspondingWordList = mapWordComb.getOrDefault(newWord, new ArrayList<String>());
                for (String adjacentWord : correspondingWordList) {

                    //if endWord Found then return level+1
                    if (adjacentWord.equals(endWord)) {
                        return currLevel + 1;
                    }

                    //otherwise, add it to bfs queue. also mark it visited
                    if (!set.contains(adjacentWord)) {
                        set.add(adjacentWord);
                        queue.add(new Word(adjacentWord, currLevel + 1));
                    }
                }

            }
        }

        return 0;
    }


    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder();
        ArrayList<String> list = new ArrayList<>();
        list.add("hot");
        list.add("dot");
        list.add("dog");
        list.add("lot");
        list.add("log");
        list.add("cog");
        System.out.println(wordLadder.ladderLength("hit", "cog", list));
    }
}
