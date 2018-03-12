package Graphs;

import Graphs.Graphs.Edges;

import java.util.HashMap;
import java.util.Map;

public class BellmanFord {
    /*
     *@Author : Sahil
     * Date : 12 March 2018
     *
     * BellmanFord algorithm is for finding shortest path to all nodes from Single Source in both directed and undirec
     * weighted Graph
     * Complexity - O(EV)
     *
     * Djikstra algorithm can not find negative weight cycle, but bellman ford can find
     *
     * Algorithm
     * 1.Make a list of all edges and iterate over it V-1 times
     * 2. Make a <Vertex,Distance> Map and initialize it with infinite value that displays the min distance to reach
     *      the vertex
     * 3. Make a <Vertex,parent> map that store parent of the given vertex
     * 4.After V-1 iterations, the map stores the min distance and its parent
     *
     * To check negative weight Cycle
     * 1. iterate through all edges and check if it is able to reduce the weight of any one of the vertex,
     *      then it means it has negative weight cycle;
     *
     *      Note: in this algorithm do not initilize vertex with INT_MAX because while adding values to edge
     *              it might cause integer overflow
     *
     *              Do following |V|-1 times where |V| is the number of vertices in given graph.
     * …..a) Do following for each edge u-v
     * ………………If dist[v] > dist[u] + weight of edge uv, then update dist[v]
     * ………………….dist[v] = dist[u] + weight of edge uv
     *
     * References :
     * https://en.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm
     * https://www.geeksforgeeks.org/dynamic-programming-set-23-bellman-ford-algorithm/
     */

    public int INF = 100000;
    int numOfVertices;

    public void bellmanFordShortestPath(Graphs g, int source) {
        numOfVertices = g.numberOfVertices;
        //Make a <Vertex,Distance> Map and initialize it with infinite value
        Map<Integer, Integer> vertexDistanceMap = new HashMap<>();
        for (int i = 0; i < numOfVertices; i++) {
            vertexDistanceMap.put(i, INF);
        }

        //Make a map for vertex and its parent
        Map<Integer, Integer> vertexParentMap = new HashMap<>();
        for (int i = 0; i < numOfVertices; i++) {
            vertexParentMap.put(i, null);
        }

        //For source vertex ,initialize the map inputs
        vertexDistanceMap.put(0, 0);
        vertexParentMap.put(0, null);

        for (int i = 0; i < numOfVertices - 1; i++) {

            for (Edges edge : g.edgeList) {
                int u = edge.v1;
                int v = edge.v2;
                if (vertexDistanceMap.get(v) > (vertexDistanceMap.get(u) + edge.weight)) {
                    vertexDistanceMap.put(v, vertexDistanceMap.get(u) + edge.weight);
                    vertexParentMap.put(v, u);
                }
            }
        }


        //Check for negative Cycle
        for (Edges edge : g.edgeList) {
            int u = edge.v1;
            int v = edge.v2;
            if (vertexDistanceMap.get(v) > (vertexDistanceMap.get(u) + edge.weight)) {
                System.out.println("Negative Edge Cycle - No correct Solution");
                return;
            }
        }
        //Print Vertex distance map
        for (int i = 0; i < numOfVertices; i++) {
            System.out.println(i + "->" + vertexDistanceMap.get(i));
        }
    }

    public static void main(String args[]) {
        Graphs g = new Graphs(5, false);
        g.addEdge(0, 1, 4);
        g.addEdge(0, 2, 5);
        g.addEdge(0, 3, 8);
        g.addEdge(1, 2, -3);
        g.addEdge(2, 4, 4);
        g.addEdge(3, 4, 2);
        g.addEdge(4, 3, 1);
        BellmanFord bf = new BellmanFord();
        bf.bellmanFordShortestPath(g, 0);
        /*for (Edges edge : g.edgeList) {
            int u = edge.v1;
            int v = edge.v2;
            System.out.println(u + "->" + v+"->"+edge.weight);
        }*/
    }
}
