package Graphs;

import java.util.HashMap;
import java.util.Map;

public class DisjointSets {
    /*
     *@Author : Sahil
     * Date : 6 March 2018
     *
     * References :
     * 1. CLRS Book
     * 2. https://www.geeksforgeeks.org/disjoint-set-data-structures-java-implementation/
     * 3. https://www.youtube.com/watch?v=ID00PMy0-vE
     *
     * Solution :
     * 1. Create a map for (integer,Node)
     * 2. Node contains -> value, Rank & parent of itself
     * 3. Important Step -> Initially make each node as parent of itself
     * 4. Make MakeSet(int data) function
     * 5. Find Set function finds the path to parent and also make it as direct parent [PATH COMPRESSION]
     * 6. Union Function (int data1 , int data2) ->
     *      Case 6.1 Parent of data1 == Parent of data 2 -> return and do nothing
     *      Case 6.2 Parents are different if(Parent1 >Parent2) increase rank of Parent 1 and make parent1 ka parent
     *                  equal to Parent 2
     *
     * 7. NOTE : Only Node that are parent of its are the parent(s) of the SET(s)
     */
    class Node {
        Integer data;
        Node parent;
        int rank;

        Node(int data) {
            this.data = data;
            this.rank = 0;
            this.parent = this;  //Make every Node as parent of itself initially
        }
    }

    Map<Integer, Node> map = new HashMap();  //Make map for O(1) operation to get Node given the data of its Node

    public void makeSet(int data) {
        Node node = new Node(data);
        map.put(data, node);
    }

    public boolean union(Integer data1, Integer data2) {
        //Get the nodes first from the map , given the data
        Node n1 = map.get(data1);
        Node n2 = map.get(data2);

        //Get parents for both nodes from FindSet()
        Node parent1 = findSet(n1);
        Node parent2 = findSet(n2);

        //If both data has same parents already then that means they are already in same set
        if (parent1.equals(parent2)) {
            return false;
        }

        if (parent1.rank >= parent2.rank) {
            if (parent1.rank == parent2.rank)
                parent1.rank++;
            parent2.parent = parent1;   //Connect parents of two nodes
        } else {
            parent1.parent = parent2;
        }
        return true;
    }

    //finds parent of the given Node
    public Node findSet(Node node) {
        if (node.parent == node)        //Node which is the parent of itself is a parent of the set
            return node;
        node.parent = findSet(node.parent);        //This is path compression
        return node.parent;
    }

    public Integer findSet(Integer data) {
        Node node = map.get(data);
        return findSet(node).data;
    }

    public static void main(String args[]) {
        DisjointSets ds = new DisjointSets();
        ds.makeSet(1);
        ds.makeSet(2);
        ds.makeSet(3);
        ds.makeSet(4);
        ds.makeSet(5);
        ds.makeSet(6);
        ds.makeSet(7);


        ds.union(1, 2);
        ds.union(2, 3);
        ds.union(4, 5);
        ds.union(6, 7);
        ds.union(5, 6);
        ds.union(3, 7);

        System.out.println(ds.findSet(1));
        System.out.println(ds.findSet(2));
        System.out.println(ds.findSet(3));
        System.out.println(ds.findSet(4));
        System.out.println(ds.findSet(5));
        System.out.println(ds.findSet(6));
        System.out.println(ds.findSet(7));

    }
}
