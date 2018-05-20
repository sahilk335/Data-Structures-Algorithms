package Backtracking_DFS_BFS;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumTimeRotOranges {
    /*
     *@Author : Sahil
     * Date : 20 May 2018
     *
     * Minimum time required to rot all oranges
     *
     * Given a matrix of dimension m*n where each cell in the matrix can have values 0, 1 or 2 which has the following
     * meaning:
     *
     * 0: Empty cell
     *
     * 1: Cells have fresh oranges
     *
     * 2: Cells have rotten oranges
     * So we have to determine what is the minimum time required so that all the oranges become rotten.
     * A rotten orange at index [i,j] can rot other fresh orange at indexes [i-1,j], [i+1,j], [i,j-1], [i,j+1]
     * (up, down, left and right). If it is impossible to rot every orange then simply return -1.
     *
     * Examples:
     *
     * Input:  arr[][C] = { {2, 1, 0, 2, 1},
     *                      {1, 0, 1, 2, 1},
     *                      {1, 0, 0, 2, 1}};
     * Output:
     * All oranges can become rotten in 2 time frames.
     *
     *
     * Input:  arr[][C] = { {2, 1, 0, 2, 1},
     *                      {0, 0, 1, 2, 1},
     *                      {1, 0, 0, 2, 1}};
     * Output:
     * All oranges cannot be rotten.
     *
     * References :
     * https://www.geeksforgeeks.org/minimum-time-required-so-that-all-oranges-become-rotten/
     *
     * Solution :
     *
     * The solution of this problem is similar to BFS (or level order traversal of Binary Tree).
     * We use a Queue data structure and insert cell in queue.
     * The structure that gets stored in the queue (the data part of queue) is
     *
     * class DataElement
     * {
     *     int i;
     *     int j;
     *     int time;
     * };
     * The time in this structure is the time at which orange in this cell will rot.
     * Initially time is zero and all the cells with rotten oranges are inserted in the Queue (with their time as zero)
     * After the the below algorithm is used
     *
     * WHILE (Queue is not empty)
     *   Remove cell from Queue and check all the nearby cells of this removed cell.
     *      IF the nearby cell has Fresh orange
     *          Insert it in the Queue with time = time of removed cell +1
     * Time of the last cell removed from the queue represent the time to rot all oranges in the queue.
     * After this loop we also need to check if there is some cell in the matrix that still has 1.
     * If such cell is present, then this cell is not reachable from any of the rotten orange’s cell.
     * In this case we need to return -1.
     *
     */

    int R = 3;
    int C = 5;

    int dx[] = {0, 0, -1, 1};
    int dy[] = {1, -1, 0, 0};

    class TimePos {
        int x;
        int y;
        int time;

        TimePos(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    boolean isValid(int x, int y) {
        return (x >= 0 && x < R && y >= 0 && y < C);
    }

    //Checks if any fresh orange is still present or not
    boolean isFreshStillLeft(int[][] arr) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }


    public int rotOranges(int arr[][]) {

        int maxTime = Integer.MIN_VALUE;
        Queue<TimePos> queue = new LinkedList<>();

        //STEP 1 : Add already rotten oranges to queue

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                //If Rotten Orange . Add it to the queue with its time to 0
                if (arr[i][j] == 2) {
                    TimePos timePos = new TimePos(i, j, 0);
                    queue.add(timePos);
                }
            }
        }

        //STEP 2 : WHILE QUEUE is not empty ,keep on adding all its adjacent oranges to queue
        while (!queue.isEmpty()) {
            TimePos currTimePos = queue.remove();
            int currTime = currTimePos.time;
            int currX = currTimePos.x;
            int currY = currTimePos.y;
            for (int k = 0; k < 4; k++) {
                int nx = currX + dx[k];
                int ny = currY + dy[k];
                if (isValid(nx, ny) && arr[nx][ny] == 1) {
                    //Mark current node as rotten
                    arr[nx][ny] = 2;
                    TimePos timePos = new TimePos(nx, ny, currTime + 1);
                    queue.add(timePos);
                    //update the answer
                    maxTime = Math.max(maxTime, currTime + 1);
                }
            }
        }

        //Step 3 Check if any further Fresh orange is left
        boolean fresh = isFreshStillLeft(arr);
        if (fresh)
            return -1;

        return maxTime;
    }


    public static void main(String[] args) {
        MinimumTimeRotOranges minimumTimeRotOranges = new MinimumTimeRotOranges();
        int arr[][] =
                {
                        {2, 1, 0, 2, 1},
                        {0, 0, 1, 2, 1},
                        {1, 0, 0, 2, 1}
                };

        int ans = minimumTimeRotOranges.rotOranges(arr);
        if (ans == -1)
            System.out.println("All oranges cannot rot");
        else
            System.out.println("Time required for all oranges to rot = " + ans);
    }
}

