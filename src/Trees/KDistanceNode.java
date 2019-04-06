package Trees;

import java.util.*;

public class KDistanceNode {
    /*
     *@Author : Sahil
     * Date : 06 Apr 2019
     *
     * We are given a binary tree (with root node root), a target node, and an integer value K.
     *
     * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
     *


     * Example 1:

     * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

     * Output: [7,4,1]
     *
     * Explanation:
     * The nodes that are a distance 2 from the target node (with value 5)
     * have values 7, 4, and 1.
     *
     * References :
     * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
     *
     * Solution
     * 1. First create an HashMap for parent node
     * 2. Apply Special BFS(null node as a distance seperator) for K distance and add it to the list
     *
     */

    Map<Node, Node> parentMap;

    public List<Integer> distanceK(Node root, Node target, int k) {
        parentMap = new HashMap<>();
        dfs4Parent(root, null);

        //Now apply BFS for K nodes

        Queue<Node> queue = new LinkedList<>();
        queue.add(null);        //Note : V.Imp -> Null add as a Distance Seperator
        queue.add(target);

        //We maintain a Set to avoid Cycles while travelling in a tree
        Set<Node> seenSet = new HashSet<>();
        seenSet.add(target);
        seenSet.add(null);


        int dist = 0;

        while (!queue.isEmpty()) {

            Node currNode = queue.poll();

            if (currNode == null) {
                if (dist == k) {
                    List<Integer> ans = new ArrayList<>();
                    for (Node node : queue) {
                        ans.add(node.data);
                    }
                    return ans;
                }
                queue.add(null);        //Add Distance Seperator
                dist++;
            } else {
                if (!seenSet.contains(currNode.left)) {
                    seenSet.add(currNode.left);
                    queue.add(currNode.left);
                }
                if (!seenSet.contains(currNode.right)) {
                    seenSet.add(currNode.right);
                    queue.add(currNode.right);
                }

                Node parentNode = parentMap.get(currNode);
                if (!seenSet.contains(parentNode)) {
                    seenSet.add(parentNode);
                    queue.add(parentNode);
                }
            }
        }
        return new ArrayList<Integer>();
    }

    public void dfs4Parent(Node root, Node parent) {
        //In preorder way save the parent
        if (root != null) {
            parentMap.put(root, parent);
            dfs4Parent(root.left, root);
            dfs4Parent(root.right, root);
        }
    }

    public static void main(String[] args) {
        KDistanceNode kDistanceNode = new KDistanceNode();

        BinaryTree.root = new Node(1);
        BinaryTree.root.left = new Node(2);
        BinaryTree.root.right = new Node(3);
        BinaryTree.root.left.left = new Node(4);
        BinaryTree.root.left.left.left = new Node(7);
        BinaryTree.root.left.left.left.left = new Node(10);
        BinaryTree.root.left.left.right = new Node(8);
        BinaryTree.root.left.right = new Node(5);
        BinaryTree.root.right.left = new Node(6);
        BinaryTree.root.right.left.left = new Node(9);
        BinaryTree.root.right.left.left.left = new Node(11);
        BinaryTree.root.right.left.left.left.left = new Node(12);
        BinaryTree.root.right.left.left.left.left.right = new Node(13);


        List<Integer> ansList = kDistanceNode.distanceK(BinaryTree.root, BinaryTree.root.right, 4);

        for (int node : ansList) {
            System.out.println(node);
        }

    }
}
