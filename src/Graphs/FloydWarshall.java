package Graphs;

public class FloydWarshall {
    /*
     *@Author :Sahil
     * Date : 13 March 2018
     *
     * The Floyd Warshall Algorithm is for solving the All Pairs Shortest Path problem. The problem is to find shortest distances between every pair of vertices in a given edge weighted directed Graph.
     *
     * Example:
     *
     * Input:
     *        graph[][] = { {0,   5,  INF, 10},
     *                     {INF,  0,  3,  INF},
     *                     {INF, INF, 0,   1},
     *                     {INF, INF, INF, 0} }
     * which represents the following graph
     *              10
     *        (0)------->(3)
     *         |         /|\
     *       5 |          |
     *         |          | 1
     *        \|/         |
     *        (1)------->(2)
     *             3
     * Note that the value of graph[i][j] is 0 if i is equal to j
     * And graph[i][j] is INF (infinite) if there is no edge from vertex i to j.
     *
     * Output:
     * Shortest distance matrix
     *       0      5      8      9
     *     INF      0      3      4
     *     INF    INF      0      1
     *     INF    INF    INF      0
     * References :
     * https://www.youtube.com/watch?v=LwJdNfdLF9s&t=154s
     * https://www.geeksforgeeks.org/dynamic-programming-set-16-floyd-warshall-algorithm/
     *
     * Solution:
     * For every pair (i, j) of source and destination vertices respectively, there are two possible cases.
     * 1) k is not an intermediate vertex in shortest path from i to j. We keep the value of dist[i][j] as it is.
     * 2) k is an intermediate vertex in shortest path from i to j.
     *      We update the value of dist[i][j] as dist[i][k] + dist[k][j].
     */

    public static final int INF = 100000;

    public void floydWarshal(int[][] graph) {
        int V = graph.length;

        //Create distance matrix and initialize it with INF , if there is no direct edge
        //Diagonal with 0 & direct edges with actual weights
        int[][] distancMatrix = new int[V][V];

        //Create path matrix and initialize its value such than if there is a direct edge from 1->2
        //   then update path[1][2]=1;
        int[][] pathMatrix = new int[V][V];


        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                distancMatrix[i][j] = graph[i][j];
                if (graph[i][j] != INF && i != j) {
                    pathMatrix[i][j] = i;
                } else {
                    pathMatrix[i][j] = -1;
                }
            }
        }


        for (int k = 0; k < V; k++) {
            for (int j = 0; j < V; j++) {
                for (int i = 0; i < V; i++) {
                    if (distancMatrix[i][k] >= INF || distancMatrix[k][j] >= INF)
                        continue;
                    if (distancMatrix[i][j] > distancMatrix[i][k] + distancMatrix[k][j]) {
                        distancMatrix[i][j] = distancMatrix[i][k] + distancMatrix[k][j];
                        pathMatrix[i][j] = pathMatrix[k][j];
                    }
                }
            }
        }

        //look for negative weight cycle in the graph
        //if values on diagonal of distance matrix is negative
        //then there is negative weight cycle in the graph.
        for (int i = 0; i < V; i++) {
            if (distancMatrix[i][i] < 0) {
                System.out.print("Negative Edge Cycle -> No direct Solution");
                return;
            }
        }

        //Print Distance Matrix
        System.out.println("Distance Matrix \n");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(distancMatrix[i][j] + "\t");
            }
            System.out.print("\n");
        }

    }


    public static void main(String args[]) {
        int[][] graph = {
                {0, 5, INF, 10},
                {INF, 0, 3, INF},
                {INF, INF, 0, 1},
                {INF, INF, INF, 0}
        };

        /* Let us create the following weighted graph
            10
       (0)------->(3)
        |         /|\
      5 |          |
        |          | 1
       \|/         |
       (1)------->(2)
            3           */

        FloydWarshall fw = new FloydWarshall();
        fw.floydWarshal(graph);


    }
}
