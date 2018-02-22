package Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TopologicalSortBFS {

    /*
     * Topological Sort of DAG using BFS (Kahn's Algorithm)
     * @Author : Sahil
     * Date: : 22 Feb 2018
     * References :
     * https://www.geeksforgeeks.org/topological-sorting/
     * Solution :
     * Step 1 -> Calculate Indegree of all vertices in graphs
     * Step 2 -> Add all indegrees with 0, to a queue
     *       ***** Note Queue will always contain elements that has indegree 0 *********
     * Step 3 -> pop and print element of the queue and repeat the operation until queue is empty
     *            op 1 -> reduce the adjacent vertices indegree with 1
     *            op 2 -> if vertex has indegree -1 , then that means it is visited (no need to maintain visited array)
     *            op 3 -> add vertext with indegree 0 to queue (Step 1)
     *
     * Video tutorial : https://www.youtube.com/watch?v=71eHuQvSwc0&t=181s
     */

    public void topologicalSort(Graphs graph) {

        //Arraylist of topological order
        ArrayList<Integer> topologicalOrder = new ArrayList<Integer>();

        //Step 2
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < graph.numberOfVertices; i++) {
            if (graph.indegreeVertex[i] == 0) {
                queue.add(i);
            }
        }
        int count = 0;
        //Step 3
        while (!queue.isEmpty()) {
            int vertex = queue.poll();  //pop the element with indegree 0 from the queue and retrieve it
            topologicalOrder.add(vertex);

            //reduce the indegree by 1 from its adjacent nodes .i.e delete the adjacent edges
            for (int i = 0; i < graph.numberOfAdjacentVertices(vertex); i++) {
                int adjVertex = graph.adj[vertex].get(i);
                graph.indegreeVertex[adjVertex]--;
                if (graph.indegreeVertex[adjVertex] == 0) {
                    queue.add(adjVertex);
                }
            }
            count++;
        }

        //Note if count is greater than number of vertices than it has cycles
        if(count>graph.numberOfVertices){
            System.out.print("Cycle Detected in DAG");
        }

        for(int i =0;i<topologicalOrder.size();i++){
            System.out.print(topologicalOrder.get(i));
        }

    }

    public static void main(String args[]) {
        Graphs g = new Graphs(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        TopologicalSortBFS topologicalSortBFS = new TopologicalSortBFS();
        topologicalSortBFS.topologicalSort(g);
    }
}
