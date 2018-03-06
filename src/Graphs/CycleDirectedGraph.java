package Graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CycleDirectedGraph {
    /*
     *@Author : Sahil
     * Date: 6 March 2018
     *
     * References :
     * 1.CLRS book
     *
     * Solution 1 DFS:
     *
     * 1. We add all vertex in White Set
     * 2. Note Graph can be forest also, so we do dfs for all vertexes in White Set
     * 3. Start DFS from any vertex from white set and start putting it in grey set
     * 4. while coming back of recursion there ca be 2 cases
     *      4.1 you can get the same vertex which is already there in grey set (CYCLE)
     *      4.2 vertex has no further edges(put in Black set,as this vertex is completely visited and cannot cause cycle)
     */


    ArrayList<Integer> adj[];
    int vertexCount;

    public boolean isCycle(Graphs g) {
        Set<Integer> whiteSetVertices = new HashSet<Integer>();     //Not yet visited
        Set<Integer> greySetVertices = new HashSet<Integer>();      //Visiting under progress from some other node
        Set<Integer> blackSetVertices = new HashSet<Integer>();      //Node is visited completely, do not look inside me
        vertexCount = g.getNumberOfVertices();
        adj = g.adj.clone();


        // Initially add all the vertexes to the white Set
        for (int i = 0; i < vertexCount; i++)
            whiteSetVertices.add(i);

        //Apply DFS till white Set has vetices left for the dfs operation
        while (whiteSetVertices.size() > 0) {
            Integer currVertex = whiteSetVertices.iterator().next();
            if (dfs(currVertex, whiteSetVertices, greySetVertices, blackSetVertices)) {
                return true;
            }
        }
        return false;
    }

    public boolean dfs(Integer currVertex, Set<Integer> whiteSetVertices, Set<Integer> greySetVertices,
                       Set<Integer> blackSetVertices) {
        moveVertex(currVertex, whiteSetVertices, greySetVertices);
        //Iterate through all adjacent vertex(or Edges) of currNode
        for (Integer i = 0; i < adj[currVertex].size(); i++) {
            /*Case 1: Adjacent node is present in black list ,i.e already visited, then skip the loop and check for next
                      adjacent vertex
            */
            Integer adjNode = adj[currVertex].get(i);
            if (blackSetVertices.contains(adjNode)) {
                continue;
            }

            //Case 2 : Cycle Found, as while visiting , it is found again from another adjacent node's adjacent
            if (greySetVertices.contains(adjNode)) {
                return true;
            }

            //Apply DFS from adjNode
            if (dfs(adjNode, whiteSetVertices, greySetVertices, blackSetVertices)) {
                return true;
            }
        }

        //When DFS is finish from the currNode, put it in BlackSet from GreySet
        moveVertex(currVertex, greySetVertices, blackSetVertices);
        return false;
    }

    public void moveVertex(Integer currVertex, Set<Integer> sourceSet, Set<Integer> destinationSet) {
        sourceSet.remove(currVertex);
        destinationSet.add(currVertex);
    }

    public static void main(String args[]) {
        Graphs g = new Graphs(4);
        g.addEdge(0, 2);
        g.addEdge(0, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        //g.addEdge(3,3);
        g.addEdge(1, 2);
        /*

        2>3>4>2 is a cycle
                    0
                  /   \
                 v     V
                 1 --> 2
                 ^     /
                   \  v
                    3
         */
        CycleDirectedGraph cd = new CycleDirectedGraph();
        System.out.print(cd.isCycle(g));
    }
}
