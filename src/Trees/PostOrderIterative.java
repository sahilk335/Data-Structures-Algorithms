package Trees;

import java.util.*;

public class PostOrderIterative {
    /*
     *@Author : Sahil Khurana
     * Date : 22 June 2019
     *
     * Post-Order Tree traversal iterative
     *
     * Solution :
     * Using stacks
     */

    public List<Integer> postorderTraversal(Node root) {
        LinkedList<Integer> result = new LinkedList<>();
        Deque<Node> stack = new ArrayDeque<>();
        Node p = root;
        while(!stack.isEmpty() || p != null) {
            if(p != null) {
                stack.push(p);
                result.add(0,p.data);  // Reverse the process of preorder
                p = p.right;             // Reverse the process of preorder
            } else {
                Node node = stack.pop();
                p = node.left;           // Reverse the process of preorder
            }
        }
        return result;
    }

    public static void main(String[] args) {


	/*

	 				1
	 			/	   \
	 		   2		3
	 		  / \	   /
	         4   5    6
	          \
                8
	 */

        BinaryTree.root = new Node(1);
        BinaryTree.root.left = new Node(2);
        BinaryTree.root.right = new Node(3);
        BinaryTree.root.left.left = new Node(4);
        BinaryTree.root.left.left.right = new Node(8);
        BinaryTree.root.left.right = new Node(5);
        BinaryTree.root.right.left = new Node(6);
        PostOrderIterative postOrderIterative = new PostOrderIterative();
        List<Integer> res = postOrderIterative.postorderTraversal(BinaryTree.root);
        for (int i : res) {
            System.out.print(i + " ");
        }

    }
}
