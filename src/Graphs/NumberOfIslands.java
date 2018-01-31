package Graphs;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    /*
     * @Author : Sahil
     * Date : 31 Jan 2018
     *
     * References : https://leetcode.com/problems/number-of-islands/
     *
     * Solution
     *
     * 1.DFS
     * 2.BFS
     *
     * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by
     * water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of
     * the grid are all surrounded by water.
     *
     * Example :
     *
     * 11000
     * 11000
     * 00100
     * 00011
     * Answer: 3
     *
     */

    int dx[] = {0, 1, -1, 0};
    int dy[] = {1, 0, 0, -1};


    public int numIslandsDFS(char[][] grid) {
        int count = 0;

        int R = grid.length;    //total number of rows
        if (R == 0) return 0;
        int C = grid[0].length; //total number of columns

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == '1') {
                    DFS(grid, i, j, R, C);
                    ++count;
                }
            }
        }
        return count;
    }

    /*
     * This is intelligent DFS without use of visited array
     * if you visit '1' then make it as '0'
     *
     * Normally we used to do visited =false;
     * but here we change the input itself to reduce space complexity
     *
     */
    public void DFS(char[][] grid, int x, int y, int R, int C) {
        // DFS Step 1 ,Base case check boundary else return
        if (x < 0 || y < 0 || x >= R || y >= C || grid[x][y] != '1')
            return;

        //visit or change input itself
        grid[x][y] = '0';

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            DFS(grid, nx, ny, R, C);
        }
    }


    //BFS Solution

    public int numIslandsBFS(char[][] grid) {
        int count = 0;
        int R = grid.length;    //total number of rows
        if (R == 0) return 0;
        int C = grid[0].length; //total number of columns

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == '1') {
                    count++;

                    grid[i][j] = '0';     //Mark itself as 0 because we are not using visited array [Intelligent way]

                    Queue<Integer> neighbours = new LinkedList<>();
                    /*
                     * Important observation: Now we can give id to each 2 d matrix using this mathematical formula
                     * r*C+c
                     */
                    neighbours.add(i * C + j);
                    while (!neighbours.isEmpty()) {
                        int id = neighbours.remove();
                        int x = id / C;
                        int y = id % C;
                        if (x - 1 > 0 && grid[x - 1][y] == '1') {
                            grid[x - 1][y] = '0';
                            neighbours.add(((x - 1) * C + y));        //add id of the neighbour in queue
                        }
                        if (x + 1 < R && grid[x + 1][y] == '1') {
                            grid[x + 1][y] = '0';
                            neighbours.add(((x + 1) * C + y));
                        }

                        if (y - 1 > 0 && grid[x][y - 1] == '1') {
                            grid[x][y - 1] = '0';
                            neighbours.add(x * C + (y - 1));
                        }

                        if (y + 1 < C && grid[x][y + 1] == '1') {
                            grid[x][y + 1] = '0';
                            neighbours.add(x * C + (y + 1));
                        }
                    }
                }
            }
        }

        return count;
    }

    public static void main(String args[]) {
        char grid[][] =
                {
                        {'1', '1', '0', '0', '0'},
                        {'1', '1', '0', '0', '0'},
                        {'0', '0', '1', '0', '0'},
                        {'0', '0', '0', '1', '1'}
                };
        NumberOfIslands noi = new NumberOfIslands();
        //System.out.print(noi.numIslandsDFS(grid));
        System.out.print(noi.numIslandsBFS(grid));
    }
}
