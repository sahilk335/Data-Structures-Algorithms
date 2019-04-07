package Trees;

import java.util.*;

public class NearestLeaf {
    /*
     *@Author : Sahil
     * Date : 07 Apr 2019
     *
     * Given a binary tree where every node has a unique value, and a target key k, find the value of the nearest leaf
     * node to target k in the tree.
     *
     * Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the
     * tree. Also, a node is called a leaf if it has no children.
     *
     * In the following examples, the input tree is represented in flattened form row by row. The actual root tree given
     * will be a TreeNode object.
     *
     * Example 1:
     *
     * Input:
     * root = [1, 3, 2], k = 1
     * Diagram of binary tree:
     *           1
     *          / \
     *         3   2
     *
     * Output: 2 (or 3)
     *
     * Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.
     * Example 2:
     *
     * Input:
     * root = [1], k = 1
     * Output: 1
     *
     * Explanation: The nearest leaf node is the root node itself.
     * Example 3:
     *
     * Input:
     * root = [1,2,3,4,null,null,null,5,null,6], k = 2
     * Diagram of binary tree:
     *              1
     *             / \
     *            2   3
     *           /
     *          4
     *         /
     *        5
     *       /
     *      6
     *
     * Output: 3
     * Explanation: The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.
     * Note:
     * root represents a binary tree with at least 1 node and at most 1000 nodes.
     * Every node has a unique node.val in range [1, 1000].
     * There exists some node in the given binary tree for which node.val == k.
     *
     *
     * Solution :
     * 1. Create a parent Map
     * 2. Apply BFS , the first Node found with no left & right child is the nearest leaf found
     */

    Map<Node, Node> parentMap;

    public int findClosestLeaf(Node root, Node target) {

        //Check if target is itself a leaf node
        if (target == null || isLeaf(target))
            return target.data;


        parentMap = new HashMap<>();
        dfs4Parent(root, null);


        Queue<Node> queue = new LinkedList<>();
        queue.add(null);        //Note : V.Imp -> Null add as a Distance Seperator
        queue.add(target);

        //We maintain a Set to avoid Cycles while travelling in a tree
        Set<Node> seenSet = new HashSet<>();
        seenSet.add(target);
        seenSet.add(null);


        //Now apply BFS till leaf node is found
        while (!queue.isEmpty()) {

            Node currNode = queue.poll();

            if (currNode != null && isLeaf(currNode)) {         //Nearest Leaf Node found
                return currNode.data;
            }
            if (currNode == null) {
                queue.add(null);        //Add Distance Seperator
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
        return -1;
    }

    boolean isLeaf(Node root) {
        return root.left == null && root.right == null;
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
        NearestLeaf nearestLeaf = new NearestLeaf();
	/*

	 				1
	 			/	   \
	 		   2		3
	 		  / \	   / \
	         4   5    6   14
	        / \      /
	       7   8     9
	      /			/
	     10		   11
	     		  /
				12
				  \
				   13
	 */

        BinaryTree.root = new Node(1);
        BinaryTree.root.left = new Node(2);
        BinaryTree.root.right = new Node(3);
        BinaryTree.root.right.right = new Node(14);
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


        System.out.println("Nearest Leaf node " + nearestLeaf.findClosestLeaf(BinaryTree.root, BinaryTree.root.right.left));


    }
}
