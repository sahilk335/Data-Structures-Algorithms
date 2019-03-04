package Trees;



public class BinarySearchTree {

    public static Node root;

    public BinarySearchTree() {
        root = null;
    }


    public void makeTree() {
        BinarySearchTree tree = new BinarySearchTree();



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

  /*      System.out.println("Inorder Traversal :");
        PrintTree pt=new PrintTree();
        pt.inorder(root);
*/

    }


}