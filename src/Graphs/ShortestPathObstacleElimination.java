package Graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


/**
 * @Author : Sahil Khurana
 * Date : 02-12-2021
 * <p>
 * You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle).
 * You can move up, down, left, or right from and to an empty cell in one step.
 * <p>
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to the
 * lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles.
 * If it is not possible to find such walk return -1.
 * <p>
 * Leetcode : https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/submissions/
 * <p>
 * Solution :
 * <p>
 * - If there is no obstacle, then answer can be calculated in O(1) because the answer is Manhattan distance i.e.
 * |x2-x1| + |y2-y1| - 2 . This distance will be achieved when you only move right and/or down to reach bottom right
 * corner.(draw on pencil paper in any MxN matrix, you will get it)
 * - So,first optimization is check if we have k more than manhattan distance, if yes ,then ans is manhattan distance
 * - Best case - O(1)
 * - If not, then apply BFS, with state management. Keep a hashSet to maintain (row,col,distance,ObstaclesElimnationActionRemaining)
 * - return -1, if it is not possible to reach destination because of too many obstacles.
 */

class StepsState {
    int row;
    int col;
    int steps;
    int remainingObstacles;

    StepsState(int row, int col, int steps, int remainingObstacles) {
        this.row = row;
        this.col = col;
        this.steps = steps;
        this.remainingObstacles = remainingObstacles;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof StepsState)) {
            return false;
        }
        StepsState newState = (StepsState) other;
        return (newState.row == this.row) && (newState.col == this.col) && (newState.remainingObstacles == this.remainingObstacles);
    }

    @Override
    public int hashCode() {
        return (this.row + 1) + (this.col + 1) * (this.remainingObstacles);
    }

    @Override
    public String toString() {
        return String.join(",", Integer.toString(row), Integer.toString(col), Integer.toString(steps), Integer.toString(remainingObstacles));
    }

}

public class ShortestPathObstacleElimination {

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    public int shortestPath(int[][] grid, int k) {

        int rows = grid.length;
        int cols = grid[0].length;

        // if we have sufficient quotas to eliminate the obstacles in the worst case,
        // then the shortest distance is the Manhattan distance.
        if (k >= rows + cols - 2) {
            return rows + cols - 2;
        }

        int[] target = {rows - 1, cols - 1};

        Queue<StepsState> queue = new LinkedList<>();
        Set<StepsState> seenState = new HashSet<>();

        //Start Step contains (CurrRow,CurrCol,DistanceTraversed,ObstaclesElimnationActionRemaining)
        StepsState startStepState = new StepsState(0, 0, 0, k);
        queue.add(startStepState);
        seenState.add(startStepState);

        while (!queue.isEmpty()) {
            StepsState currStepState = queue.poll();

            // we reach the target here
            if (target[0] == currStepState.row && target[1] == currStepState.col) {
                return currStepState.steps;
            }

            for (int z = 0; z < dx.length; z++) {
                int nRow = currStepState.row + dx[z];
                int nCol = currStepState.col + dy[z];
                if (nRow >= rows || nCol >= cols || nRow < 0 || nCol < 0) {
                    continue;
                }
                int nextElimination = currStepState.remainingObstacles - grid[nRow][nCol];
                StepsState newStepState = new StepsState(nRow, nCol, currStepState.steps + 1, nextElimination);

                if (nextElimination >= 0 && !seenState.contains(newStepState)) {
                    seenState.add(newStepState);
                    queue.add(newStepState);
                }

            }
        }

        //Not possible to reach destination

        return -1;

    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0},
                {1, 1, 0},
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 0}
        };

        int k = 1;

        ShortestPathObstacleElimination shortestPathObstacleElimination = new ShortestPathObstacleElimination();
        System.out.println("Shortest path with obstacle elimitation : " + shortestPathObstacleElimination.shortestPath(grid, k));

    }
}
