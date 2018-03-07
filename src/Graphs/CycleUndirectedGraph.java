package Graphs;

import Graphs.Graphs.Edges;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CycleUndirectedGraph {
    /*
     *@Author : Sahil
     * Date : 7 March 2018
     *  References :
     *  1. CLRS book
     *  2.https://www.youtube.com/watch?v=n_t0a_8H8VY
     *
     *  Solution 1 [Using Disjoint Sets]:
     *  1.Note here we take Edges one by one [Not Vertices]
     *  2. First apply MakeSet of itself of each node
     *  3.Start by taking Edge  and take union of both vertices of the edge
     *  4. If at any point of time both vertices of a single edge has same Parent, then it has a cycle, else not
     *
     *
     *  Solution 2 [Using DFS]
     *  1. Take a Visited Set [as it is undirected graph , we do not need white, grey and black set from one vertex,
     *     you can go anywhere, unlike directed graph.]
     *  2. Note in this approach we have to keep track of the parent node because
     *     for example
     *     if there is 1---2, then that means 1->2 and 2<-1
     *     so we apply if adjacent node is parent then continue [i.e do not consider it as cycle[
     *  3. Keep on applying the DFS on adjacent vertices , if while visiting ,adjacent vertice is found in visited Set
     *      also then that means there is a CYCLE.
     *
     */

    int numofVertices;

    public static void main(String args[]) {
        Graphs g = new Graphs(4, true);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        CycleUndirectedGraph cdg = new CycleUndirectedGraph();
        System.out.print(cdg.isCycleDisjoint(g));
        System.out.print(cdg.isCycleDFS(g));
/*
       2>3>4>2 is a cycle
                   0
                 /  \
                1    2
                 \  /
                   3
        */
    }

    //Solution 1
    public boolean isCycleDisjoint(Graphs g) {
        DisjointSets ds = new DisjointSets();
        numofVertices = g.numberOfVertices;
        //Add all the vertices in Disjoint Set
        for (int i = 0; i < numofVertices; i++) {
            ds.makeSet(i);
        }
        //Make Uninon of all the edges, if at any point there is same parent , then cycle is detected
        for (Edges edge : g.edgeList) {
            int parentofI = ds.findSet(edge.v1);
            int parentOfAdjacentNode = ds.findSet(edge.v2);
            if (parentofI == parentOfAdjacentNode)
                return true;
            ds.union(edge.v1, edge.v2);
        }
        return false;
    }

    //Solution 2
    public boolean isCycleDFS(Graphs g) {
        Set<Integer> visited = new HashSet<>();
        numofVertices = g.numberOfVertices;
        for (Integer vertices = 0; vertices < numofVertices; vertices++) {
            //Skip the vertices that are already visited
            if (visited.contains(vertices)) {
                continue;
            }
            boolean answer = dfsCycleUtil(g.adj, visited, vertices, null);
            if (answer)
                return true;
        }
        return false;
    }

    public boolean dfsCycleUtil(ArrayList<Integer> adj[], Set<Integer> visited, Integer vertice, Integer parent) {

        visited.add(vertice);

        //If visiting vertices already contained in visited set,then it is a cycle
        for (Integer adjVertexIndex = 0; adjVertexIndex < adj[vertice].size(); adjVertexIndex++) {
            Integer adjVertex = adj[vertice].get(adjVertexIndex);
            if (adjVertex.equals(parent))
                continue;
            if (visited.contains(adjVertex))
                return true;
            boolean dfsAnswer = dfsCycleUtil(adj, visited, adjVertex, vertice);
            if (dfsAnswer)
                return true;
        }
        return false;
    }
}