package Graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BipartiteGraph {
    /*
     *@Author : Sahil
     * Date : 13 March 2018
     *
     * Bipartite Graph :
     * A Bipartite Graph is a graph whose vertices can be divided into two independent sets, U and V such
     * that every edge (u, v) either connects a vertex from U to V or a vertex from V to U. In other words, for every
     * edge (u, v), either u belongs to U and v to V, or u belongs to V and v to U. We can also say that there is no
     * edge that connects vertices of same set.
     *
     * A bipartite graph is possible if the graph coloring is possible using two colors such that vertices in a set
     * are colored with the same color. Note that it is possible to color a cycle graph with even cycle
     * using two colors
     *
     * References :
     * https://www.geeksforgeeks.org/bipartite-graph/
     *
     * Note: Graph with negative cycle is always NOT A BIPARTITE GRAPH
     *
     * Solution 1 [Using BFS] 2 Coloring-
     *
     * 1.Make two Sets RedSet and BlueSet and a queue
     * 2.Push it in blue color and add it in a queue
     * 3.Repeat the below operations while queue is not empty
     *      3.1 push the adjacent vertex in a queue
     *      3.2 check if the adjacentVertex is not already colored.
     *              3.2.1 if it is already colored check with the color of currVertexColor, if same return false
     *              3.2.2 if it is not colored,add it in the opposite color Set
     * 4. if step 3 is completed then return true, means it is Bipartite
     *
     * Note it is possible that Graph is not strongly Connected, for that visit all the nodes.forcefully
     *
     * Solution 2 [Using DFS]
     *
     * 1. start with given vertex and send opp color in recursion and solve accordingly
     */

    int numOfVertices;

    public boolean bipartiteCheckBFS(Graphs g) {

        Set<Integer> blueSet = new HashSet<>();
        Set<Integer> redSet = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        //Since graph can be disconnected , check for all vertex
        numOfVertices = g.numberOfVertices;
        for (int vertex = 0; vertex < numOfVertices; vertex++) {
            if (!blueSet.contains(vertex) && !redSet.contains(vertex)) {
                blueSet.add(vertex);
                queue.add(vertex);
                while (!queue.isEmpty()) {
                    int currVertex = queue.poll();
                    for (int adjVertex : g.adj[currVertex]) {
                        if (blueSet.contains(currVertex) && blueSet.contains(adjVertex)) {
                            return false;
                        }
                        if (redSet.contains(currVertex) && redSet.contains(adjVertex)) {
                            return false;
                        }
                        if (blueSet.contains(currVertex) && !redSet.contains(adjVertex)) {
                            redSet.add(adjVertex);
                            queue.add(adjVertex);
                        }
                        if (redSet.contains(currVertex) && !blueSet.contains(adjVertex)) {
                            blueSet.add(adjVertex);
                            queue.add(adjVertex);
                        }
                    }
                }
            }
        }

        return true;

    }

    public boolean isBipartiteDFS(Graphs g) {
        numOfVertices = g.numberOfVertices;
        Set<Integer> blueSet = new HashSet<>();
        Set<Integer> redSet = new HashSet<>();
        for (int vertex = 0; vertex < numOfVertices; vertex++) {
            if (blueSet.contains(vertex) || redSet.contains(vertex))
                continue;
            boolean flag = isBipartiteDFS(vertex, blueSet, redSet, g, true);
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    private boolean isBipartiteDFS(int vertex, Set<Integer> blueSet, Set<Integer> redSet, Graphs g, boolean wasBlue) {
        if (wasBlue) {
            if (blueSet.contains(vertex)) {
                return false;
            }
            if (redSet.contains(vertex)) {
                return true;        //vertex is adjVertex
            }

            //means it is neither in blue and red till yet, then add it in the blue set
            redSet.add(vertex);
        } else if (!wasBlue) {
            if (redSet.contains(vertex))
                return false;
            if (blueSet.contains(vertex)) {
                return true;
            }

            blueSet.add(vertex);

        }
        for (int adjVertex : g.adj[vertex]) {
            boolean flag = isBipartiteDFS(adjVertex, blueSet, redSet, g, !wasBlue); //Send opposite of currVertex Color
            if (!flag) {
                return false;
            }
        }
        return true;

    }


    public static void main(String args[]) {
        Graphs g = new Graphs(5, true);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 0);
        //g.addEdge(5, 0);
        BipartiteGraph bpGraph = new BipartiteGraph();
        System.out.print(bpGraph.isBipartiteDFS(g));

    }
}
