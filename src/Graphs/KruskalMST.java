package Graphs;

import Graphs.Graphs.Edges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class KruskalMST {
    /*
     *@Author : Sahil
     * Date : 8 March 2018
     *
     *         Find minimum spanning tree usinig Kruskals algorithm
     *
     *         Time complexity - O(ElogE)
     *         Space complexity - O(E + V)
     *
     * References :
     * 1. CLRS book
     * 2.https://en.wikipedia.org/wiki/Kruskal%27s_algorithm
     * 3. https://www.youtube.com/watch?v=fAuF0EuZVCk&t=310s
     *
     * Solution :
     * Sort all the edges according to their Weight ,and then keep on adding the lower edge at the same time check, it is not
     * forming any cycle (on adding edge) using disjoint sets (check for cycles)
     */

    int numofVertices;

    //returns the array list of MST
    public ArrayList<Edges> kruskalMST(Graphs g) {
        ArrayList<Edges> solEdgeList = new ArrayList<>();
        EdgeComparator edgeComparator = new EdgeComparator();
        DisjointSets disjointSets = new DisjointSets();
        numofVertices = g.numberOfVertices;


        //Sort the Edges based on their weights
        Collections.sort(g.edgeList, edgeComparator);

       /*
    //Print sorted Edges
       for (Edges edges : g.edgeList) {
           System.out.println(edges.v1 + "->" + edges.v2);
       }*/

        for (int i = 0; i < numofVertices; i++) {
            disjointSets.makeSet(i);
        }
        for (Edges edges : g.edgeList) {
            int vertice1Parent = disjointSets.findSet(edges.v1);
            int vertice2Parent = disjointSets.findSet(edges.v2);
            if (vertice1Parent == vertice2Parent)    //If cycle found then continue , do not take this edge
                continue;
            disjointSets.union(edges.v1, edges.v2);
            solEdgeList.add(edges);
        }

        return solEdgeList;

    }

    public class EdgeComparator implements Comparator<Edges> {
        @Override
        public int compare(Edges o1, Edges o2) {
            if (o1.weight >= o2.weight)
                return 1;
            else
                return -1;
        }
    }

    public static void main(String args[]) {
        Graphs g = new Graphs(5);
        g.addEdge(0, 1, 1);
        g.addEdge(0, 2, 3);
        g.addEdge(2, 1, 4);
        g.addEdge(1, 3, 6);
        g.addEdge(2, 3, 5);
        g.addEdge(1, 4, 7);
        g.addEdge(3, 4, 2);

        KruskalMST mst = new KruskalMST();
        for (Edges edges : mst.kruskalMST(g)) {
            System.out.println(edges.v1 + "->" + edges.v2);
        }

    }
}
