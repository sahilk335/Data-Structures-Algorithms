package Graphs;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graphs {
    //Total number of Vertices
    int numberOfVertices;
    //Adjacency list
    ArrayList<Integer> adj[];
    //Indegree Array
    int indegreeVertex[];

    Graphs(int vertices){
        numberOfVertices=vertices;
        indegreeVertex=new int[numberOfVertices];
        adj=new ArrayList[numberOfVertices];
        for(int i=0;i<numberOfVertices;i++)
            adj[i]=new ArrayList<Integer>();
    }

    void addEdge(int v ,int w){
        adj[v].add(w);
        indegreeVertex[w]++;
    }

    int getNumberOfVertices(){
        return  numberOfVertices;
    }

    int numberOfAdjacentVertices(int v){
        return adj[v].size();
    }
}
