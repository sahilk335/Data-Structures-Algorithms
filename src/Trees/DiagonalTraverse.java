package Trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DiagonalTraverse {
    /*
     *@Author : Sahil
     * Date : 18 May 2018
     *
     * References : https://www.geeksforgeeks.org/diagonal-traversal-of-binary-tree/
     *
     * Solution :
     * 1.Create an ArrayList of VerticalDistance ,and each VerticalDistance has its own ArrayList
     * 2.Initialize Root node as level 0
     * 3. Increment VD +1 on its left child and maintain same VD on its right child
     * 4. Print VD arraylist
     *
     */

    public void diagonalTraversal(Node root, HashMap<Integer, ArrayList<Integer>> mapList, int verDis) {
        if (root == null)
            return;

        if (mapList.get(verDis) == null) {
            ArrayList<Integer> currLevel = new ArrayList<>();
            currLevel.add(root.data);
            mapList.put(verDis, currLevel);
        } else {
            ArrayList currLevel = mapList.get(verDis);
            currLevel.add(root.data);
        }

        diagonalTraversal(root.left, mapList, verDis + 1);
        diagonalTraversal(root.right, mapList, verDis);

    }

    public void diagonalTraversalRecursionUtil(Node root) {
        HashMap<Integer, ArrayList<Integer>> mapList = new HashMap<>();
        diagonalTraversal(root, mapList, 0);
        for (Map.Entry<Integer, ArrayList<Integer>> entry : mapList.entrySet()) {
            ArrayList<Integer> currList = entry.getValue();
            for (int i = 0; i < currList.size(); i++) {
                System.out.print(currList.get(i));
            }
        }
    }

    public static void main(String[] args) {
                /*

	 			  1
	 			/  \
	 		   2	3
	 		  /    / \
	         6    4   5

	     */
        BinaryTree.root = new Node(1);
        BinaryTree.root.left = new Node(2);
        BinaryTree.root.left.left = new Node(6);
        BinaryTree.root.right = new Node(3);
        BinaryTree.root.right.left = new Node(4);
        BinaryTree.root.right.right = new Node(5);
        DiagonalTraverse diag = new DiagonalTraverse();
        diag.diagonalTraversalRecursionUtil(BinaryTree.root);
    }
}
