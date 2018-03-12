package Graphs;

import Graphs.Graphs.Edges;

import java.util.HashMap;
import java.util.Map;

public class DjikstraShortesPath {
    /*
     * @Author:Sahil
     * Date : 12 March 2018
     *
     * *Find single source shortest path using Dijkstra's algorithm
     *
     * for both DIRECTED & Undirected GRAPH
     *
     * Note: Not for Negative Edges
     *
     * Space complexity - O(E + V)
     * Time complexity - O(ElogV)
     *
     * References
     * https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
     * CLRS book
     *
     * Solution :
     * Step 1 : Create Heap + Map Structure of vertex and distance and initialize all vertex weight  to infinite
     * Step 2 : Create Vertex,Distance Map -> that store minimum distance required to reach tha vertex
     * Step 3 : Create Destination vertex, parent vertex-> that tells you are coming from which vertex
     * Step 4: Like Prim's Algorithm, Extract Min and update its adjacent vertex distances in vertex,distance max
     *          and also update the vertex,vertex map
     * Step 5 : Repeat the process untill Heap+Map is empty
     * Step 6 : from vertex->parent vertex map -> print the source to destination path
     * Step 7 : from vertex-> distance print the distance
     */

    int numOfVertices;

    public void djikstraPath(Graphs g, int source) {
        numOfVertices = g.numberOfVertices;

        //Create Heap + Map Structure and initialize all vertex weight  to infinite
        BinaryMinHeap<Integer> minHeap = new BinaryMinHeap<Integer>();
        for (int i = 0; i < numOfVertices; i++) {
            minHeap.add(Integer.MAX_VALUE, i);
        }

        //Create Vertex,Distance Map -> that store minimum distance required to reach tha vertex
        Map<Integer, Integer> vertexDistanceMap = new HashMap<>();

        //Create Destination vertex, parent vertex-> that tells you are coming from which vertex
        Map<Integer, Integer> vertexParentMap = new HashMap<>();

        minHeap.decrease(0, source);//start from source vertex and put it in heap +map strucutre with 0 distance
        vertexDistanceMap.put(source, 0); //Distance from source to source is 0
        vertexParentMap.put(source, null);   //Parent of the source vertex is null

        while (!minHeap.empty()) {
            BinaryMinHeap<Integer>.Node currVertexNode = minHeap.extractMinNode();
            int currVertex = currVertexNode.key;

            //put the vertex in vertex DIstance map as it is the shortest distance of currNode
            vertexDistanceMap.put(currVertex, currVertexNode.weight);

            for (Edges edge : g.verticeToEdge[currVertex]) {
                int adjVertex = edge.v2;

                if (!minHeap.containsData(adjVertex))
                    continue;

                int newDistance = vertexDistanceMap.get(currVertex) + edge.weight;
                int oldDistance = minHeap.getWeight(adjVertex);

                if (newDistance < oldDistance) {
                    minHeap.decrease(newDistance, adjVertex);
                    vertexParentMap.put(adjVertex, currVertex);
                }
            }
        }


        //Print the Shortest DIstance from source to all vertices
        for (int i = 0; i < vertexDistanceMap.size(); i++) {
            System.out.println(i + "->" + vertexDistanceMap.get(i));
        }
    }


    public static void main(String args[]) {
        Graphs g = new Graphs(6, true);
        g.addEdge(0, 1, 5);
        g.addEdge(0, 5, 2);
        g.addEdge(0, 3, 9);
        g.addEdge(1, 2, 2);
        g.addEdge(2, 3, 3);
        g.addEdge(3, 4, 2);
        g.addEdge(5, 4, 3);
        DjikstraShortesPath dpath = new DjikstraShortesPath();
        dpath.djikstraPath(g, 0);


    }
}
