package Graphs;

import java.util.Stack;

public class TopologicalSort {
    /*
     * @Author : Sahil
     * Date : 31 Jan 2018
     *
     * Note: Topological Sort is for Directed Acyclic graph
     *
     * Cyclic graph can not have topological order
     *
     * References :
     * https://www.geeksforgeeks.org/topological-sorting/
     * https://www.youtube.com/watch?v=ddTC4Zovtbc&t=403s
     *
     * Solution :
     *
     *  1. Start visit from any vertex
     *  2. Apply DFS on that vertex, keep on marking every visited vertex as true
     *  3. After dfs is applied to a vertex, push the vertex in stack
     *  4. After visiting all vertex.
     *  5. Print Stack (Topological order)
     *
     */



    //Apply DFS on the vertex
    public void topologicalSortUtilDFS(int v, boolean visited[], Stack stack, Graphs graphs) {
        visited[v] = true;

        //Apply DFS on given Vertice
        for (int i = 0; i < graphs.numberOfAdjacentVertices(v); i++) {
            int Connectedvertex = graphs.adj[v].get(i);
            if (!visited[Connectedvertex]) {
                topologicalSortUtilDFS(Connectedvertex, visited, stack, graphs);
            }
        }
        // push the vertice after all its linked vertices are traversed
        stack.push(v);
    }

    public void topologicalSort(Graphs graph) {
        Stack stack = new Stack();
        int V = graph.getNumberOfVertices();
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }

        //For every unvisited vertices apply DFS operation
        for (int i = 0; i < V; i++) {
            if (visited[i] == false) {
                topologicalSortUtilDFS(i, visited, stack, graph);
            }
        }

        //Stack contains the Topological order
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }

    }

    public static void main(String args[]) {
        TopologicalSort ts = new TopologicalSort();
        Graphs graphs = new Graphs(6);
        graphs.addEdge(5, 2);
        graphs.addEdge(5, 0);
        graphs.addEdge(4, 0);
        graphs.addEdge(4, 1);
        graphs.addEdge(2, 3);
        graphs.addEdge(3, 1);

        ts.topologicalSort(graphs);
    }
}
