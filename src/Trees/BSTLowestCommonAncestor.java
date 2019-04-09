package Trees;

public class BSTLowestCommonAncestor {
    /*
     *@Author : Sahil
     * Date : 09 Apr 2019
     *
     * Lowest Common Ancestor in a Binary Search Tree.
     * Given values of two values n1 and n2 in a Binary Search Tree, find the Lowest Common Ancestor (LCA).
     * You may assume that both the values exist in the tree.
     *
     * References :
     * https://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-search-tree/
     * https://www.youtube.com/watch?v=TIoCCStdiFo
     *
     * Solution
     * 1. let n1 & n2 be the nodes for which you have to find LCA
     * 2. while traversing tree in Post order way. if you find any Node which is less than equal to n1 & greater than n2
     *      then it is LCA
     *
     *      Complexity - O(H) ,where H is the height of the binary search tree
     */

    Node lcaBST(Node root, int n1, int n2) {
        if (root == null)
            return null;
        if (root.data > Math.max(n1, n2)) {
            return lcaBST(root.left, n1, n2);
        } else if (root.data < Math.min(n1, n2)) {
            return lcaBST(root.right, n1, n2);
        } else
            return root;
    }

    public static void main(String[] args) {
        BSTLowestCommonAncestor lcaBST = new BSTLowestCommonAncestor();
        BinarySearchTree.root = new Node(20);

     /*

	 			   20
	 			/	   \
	 		   8		22
	 		  / \	   / \
	         4  12   21  24
	           /  \
              10  14
	 */

        BinarySearchTree.root = new Node(20);

        //left subtree
        BinarySearchTree.root.left = new Node(8);
        BinarySearchTree.root.right = new Node(22);
        BinarySearchTree.root.left.left = new Node(4);
        ;
        BinarySearchTree.root.left.right = new Node(12);
        BinarySearchTree.root.left.right.left = new Node(10);
        BinarySearchTree.root.left.right.right = new Node(14);

        //right subtree
        BinarySearchTree.root.right = new Node(22);
        BinarySearchTree.root.right.left = new Node(21);
        BinarySearchTree.root.right.right = new Node(24);

        System.out.println("LCA for 10,14 : "+lcaBST.lcaBST( BinarySearchTree.root,10,14).data);

    }
}
