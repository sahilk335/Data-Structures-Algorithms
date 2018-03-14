package Trie;

import java.util.ArrayList;
import java.util.List;

public class WordAmentSolution {
    /*
     *@Author : Sahil
     * Date : 14 March 2018
     * References :
     * Given a 2D board and a list of words from the dictionary, find all words in the board.

     * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
     * horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
     *
     *For example,
     *Given words = ["oath","pea","eat","rain"] and board =
     *
     *[
     *  ['o','a','a','n'],
     *  ['e','t','a','e'],
     *  ['i','h','k','r'],
     *  ['i','f','l','v']
     *]
     *Return ["eat","oath"].
     */
    List<String> res;
    int dx[];
    int dy[];

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        res = new ArrayList<>();
        dx = new int[]{0, 0, -1, 1};
        dy = new int[]{1, -1, 0, 0};

        //Insert all the words in Trie
        for (String word : words) {
            trie.insert(word);
        }

        int row = board.length;
        int col = board[0].length;
        boolean visited[][] = new boolean[row][col];

        //Apply DFS on every word of the board
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dfs("", i, j, board, visited, trie);
            }
        }

        return res;
    }

    public void dfs(String word, int x, int y, char[][] board, boolean[][] visited, Trie trie) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length)
            return;

        if (visited[x][y])
            return;

        word += board[x][y];

        if (trie.searchPrefix(word) == null) {
            return;
        }

        if (trie.search(word))
            res.add(word);

        visited[x][y] = true;
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            dfs(word, nx, ny, board, visited, trie);
        }
        //backtrack
        visited[x][y] = false;
    }


    public static void main(String args[]) {
        WordAmentSolution boggle = new WordAmentSolution();
        char board[][] = {
                {'o', 'a', 'a', 'h'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String words[] = {"oath", "pea", "eat", "rain"};
        System.out.println(boggle.findWords(board, words));
    }
}
