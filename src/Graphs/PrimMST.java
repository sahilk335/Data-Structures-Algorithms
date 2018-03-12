package Graphs;

import Graphs.Graphs.Edges;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrimMST {
    /*
     *@Author : Sahil Khurana
     * Date : 11 March 2018
     *
     * Prim Algorithm[Undirected Weighed Graph] :-
     *
     * 1. Make Heap + Map data structure and store all vertices with infinite weights
     * 2. Start with any vertex and apply Extract Min operation on this vertex from heap+map data structure.
     * 3. Visit all the vertices adjacent to the (extract min) vertice ,which are available in heap+map structure and
     *    update its weight,if previous stored weight was greater than this weight from current vertice.
     * 4.Select Extract Min operation and store its value in Vertice|edge datastructure and store it in result
     * 5.Repeat the Step 3 and 4 untill Heap+Map Data structure is empty
     * 6.Print result.
     *
     */

    int numOfVertices;

    public List<Edges> primMST(Graphs g) {
        numOfVertices = g.numberOfVertices;
        //Step 1 :Make Heap + Map data structure and store all vertices with infinite weights initially
        BinaryMinHeap<Integer> minHeap = new BinaryMinHeap<Integer>();
        for (int i = 0; i < numOfVertices; i++) {
            minHeap.add(Integer.MAX_VALUE, i);
        }


        //Create List of Edges to store results
        List<Edges> result = new ArrayList<>();

        //Create Vertex to Edge Map which stores Minimum weight Edge corresponding to given Vertex
        Map<Integer, Edges> vertexToEdgeMap = new HashMap<>();

        int startVertex = 0;
        minHeap.decrease(0, startVertex);    //Decrease the keyValue to 0 for the first vertex;

        while (!minHeap.empty()) {
            Integer currVertex = minHeap.extractMin();

            //Get the minimumEdge from current Vertex
            //For first Vertex it is null
            Edges minSpanningTreeEdge = vertexToEdgeMap.get(currVertex);
            if (minSpanningTreeEdge != null) {
                result.add(minSpanningTreeEdge);
            }
            //Visit adjacent nodes of the currVertex
            for (Edges edge : g.verticeToEdge[currVertex]) {
                Integer adjVertex = edge.v2;
                //if the adjacentVertex is present in the Heap+Map strucure and its value is lesser then update
                if (minHeap.containsData(adjVertex) && minHeap.getWeight(adjVertex) > edge.weight) {
                    minHeap.decrease(edge.weight, adjVertex);
                    vertexToEdgeMap.put(adjVertex, edge);
                }
            }
        }
        return result;
    }

    public static void main(String args[]) {
        Graphs g = new Graphs(5, true);
        g.addEdge(0, 1, 1);
        g.addEdge(0, 2, 3);
        g.addEdge(2, 1, 4);
        g.addEdge(1, 3, 6);
        g.addEdge(2, 3, 5);
        g.addEdge(1, 4, 7);
        g.addEdge(3, 4, 2);
        PrimMST primMST = new PrimMST();
        List<Edges> minimumSpanningEdge = primMST.primMST(g);
        for (Edges edge : minimumSpanningEdge) {
            System.out.println(edge.v1 + "->" + edge.v2);
        }
 /*   for(Edges edge:g.verticeToEdge[1]){
           System.out.println(edge.v1+"->"+edge.v2+"->"+edge.weight);
       }*/
    }
}
