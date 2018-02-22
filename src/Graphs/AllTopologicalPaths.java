package Graphs;

import java.util.ArrayList;

public class AllTopologicalPaths {
    /*
     * Print all Topological paths of DAG
     *
     *@Author : Sahil
     * Date : 22 Feb 2018
     * Refereces : https://www.geeksforgeeks.org/all-topological-sorts-of-a-directed-acyclic-graph/
     *
     * Step 1 : Start visiting all nodes that has indegree 0
     * Step 2 : Mark this node of step 2 as visisted and add it to Topological list
     * Step 3 : Decrement idegree of all Adjacent nodes of curr node
     * Step 4 : Call recursion All topological Sort
     * Step 5 : Backtrack Step 1,2,3
     * Step 6 : flag = true <- means under recursion and adding paths
     */

    static boolean[] visited;
    static ArrayList<Integer> topologicalOrderList;


    public void allTopologicalOrder(Graphs graph) {

        boolean flag = false;

        for (int vertex = 0; vertex < graph.numberOfVertices; vertex++) {
            if (visited[vertex] != true && graph.indegreeVertex[vertex] == 0) {
                visited[vertex] = true;     //1 Mark vertex with 0 indegree as visited
                topologicalOrderList.add(vertex);   //2 Add it in the Result
                for (int v = 0; v < graph.numberOfAdjacentVertices(vertex); v++) { // 3. Traverse adjacent vertices and reduce indegrees
                    int adjVertex = graph.adj[vertex].get(v);
                    graph.indegreeVertex[adjVertex]--;
                }

                //recursion
                allTopologicalOrder(graph);

                //Backtrack step 1,2,3
                visited[vertex] = false;
                topologicalOrderList.remove(topologicalOrderList.indexOf(vertex));
                for (int v = 0; v < graph.numberOfAdjacentVertices(vertex); v++) { // 3. Traverse adjacent vertices and reduce indegrees
                    int adjVertex = graph.adj[vertex].get(v);
                    graph.indegreeVertex[adjVertex]++;
                }
                //flag = true means we are still under process for reducing indegrees and finding nodes that has indegree=0
                flag = true;
            }
        }
        //flag = false means all nodes are visited in topological order and all indegree is reduced to 0
        if (!flag) {
            for (int i = 0; i < topologicalOrderList.size(); i++) {
                System.out.print(topologicalOrderList.get(i));
            }
            System.out.print("\n");
        }
    }

    public static void main(String args[]) {

        Graphs g = new Graphs(6);
        visited = new boolean[g.numberOfVertices]; //Initialise with false values of visited array
        topologicalOrderList = new ArrayList<Integer>();
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        AllTopologicalPaths allTopologicalPaths = new AllTopologicalPaths();
        allTopologicalPaths.allTopologicalOrder(g);
    }
}
