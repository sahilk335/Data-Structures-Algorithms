package Trees;

public class BinaryTree{
	
	public static Node root;
	public BinaryTree()
	{
		root=null;
	}
	
	
	
public void makeTree(){	
	BinaryTree tree=new BinaryTree();
	
	
	
	/*
	 
	 				1
	 			/	   \
	 		   2		3
	 		  / \	   / 
	         4   5    6 
	        / \      / 
	       7   8     9
	      /			/
	     10		   11
	     		  /
				12
				  \
				   13
	 */
	
	BinaryTree.root=new Node(1);
	BinaryTree.root.left=new Node(2);
	BinaryTree.root.right=new Node(3);
	BinaryTree.root.left.left=new Node(4);
	BinaryTree.root.left.left.left=new Node(7);
	BinaryTree.root.left.left.left.left=new Node(10);
	BinaryTree.root.left.left.right=new Node(8);
	BinaryTree.root.left.right=new Node(5);
	BinaryTree.root.right.left=new Node(6);
	BinaryTree.root.right.left.left=new Node(9);
	BinaryTree.root.right.left.left.left=new Node(11);
	BinaryTree.root.right.left.left.left.left=new Node(12);
	BinaryTree.root.right.left.left.left.left.right=new Node(13);
	//System.out.println("Preorder Traversal :");
	//PrintTree pt=new PrintTree();
	//pt.preorder(root);
	
	//System.out.println("Print Path :");
	
	
	
}
}