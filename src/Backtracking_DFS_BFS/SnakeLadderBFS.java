package Backtracking_DFS_BFS;

import java.util.LinkedList;
import java.util.Queue;

public class SnakeLadderBFS {
    /*
     *@Author : Sahil
     * Date : 20 May 2018
     *
     * Snake & Ladder Problem
     *
     * Given a snake and ladder board, find the minimum number of dice throws required to reach the destination or last
     * cell from source or 1st cell. Basically, the player has total control over outcome of dice throw and wants to
     * find out minimum number of throws required to reach last cell.
     *
     * If the player reaches a cell which is base of a ladder, the player has to climb up that ladder and if reaches a
     * cell is mouth of the snake, has to go down to the tail of snake without a dice throw.
     *
     * References :
     * https://www.hackerrank.com/challenges/the-quickest-way-up/problem
     * https://www.geeksforgeeks.org/snake-ladder-problem-2/
     *
     */

    // Complete the quickestWayUp function below.

    static boolean[] visited = new boolean[110];
    static int[] goImmediatelyTo = new int[110];

    static class Pair {
        int squarePos;
        int minDistToReach;

        Pair(int squarePos, int minDistToReach) {
            this.squarePos = squarePos;
            this.minDistToReach = minDistToReach;
        }
    }

    static boolean isValid(int squarePos) {
        if (squarePos < 1 || squarePos > 100 || visited[squarePos])
            return false;
        return true;
    }

    static void goImmediatelyTo(int[][] ladders, int[][] snakes) {
        for (int i = 1; i <= 100; i++) {
            goImmediatelyTo[i] = i;
        }
        //LADDER CHECK
        for (int j = 0; j < ladders.length; j++) {
            goImmediatelyTo[ladders[j][0]] = ladders[j][1];
        }
        //SNAKE CHECK
        for (int k = 0; k < snakes.length; k++) {
            goImmediatelyTo[snakes[k][0]] = snakes[k][1];
        }
    }

    static void refresh(){
        for(int i=0;i<110;i++){
            visited[i]=false;
            goImmediatelyTo[i]=i;
        }
    }

    static int quickestWayUp(int[][] ladders, int[][] snakes) {
        refresh();
        Queue<Pair> queue = new LinkedList<Pair>();
        Pair currPair = new Pair(1, 0);

        //Add GoImmediately to Concept
        goImmediatelyTo(ladders, snakes);

        //Mark first node as visited
        visited[currPair.squarePos] = true;
        queue.add(currPair);

        while (!queue.isEmpty()) {
            currPair = queue.remove();
            int currPos = currPair.squarePos;
            for (int i = 1; i <= 6; i++) {

                int nextPos = goImmediatelyTo[i + currPos];

                if(nextPos==100)
                    return currPair.minDistToReach+1;

                if (isValid(nextPos)) {
                    visited[nextPos] = true;
                    Pair pair = new Pair(nextPos, currPair.minDistToReach + 1);
                    queue.add(pair);
                }
            }
        }
        return -1;
    }

}
