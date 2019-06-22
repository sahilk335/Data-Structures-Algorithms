package Trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PreOrderIterative {

    /*
     *@Author : Sahil Khurana
     * Date : 22 June 2019
     *
     * Pre-Order Tree traversal iterative
     *
     * Solution :
     * Using stacks
     */

    public List<Integer> preorderTraversal(Node root) {
        List<Integer> result = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();
        Node p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                result.add(p.data);  // Add before going to children
                p = p.left;
            } else {
                Node node = stack.pop();
                p = node.right;
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
        PreOrderIterative preOrderIterative = new PreOrderIterative();
        List<Integer> res = preOrderIterative.preorderTraversal(BinaryTree.root);
        for (int i : res) {
            System.out.print(i + " ");
        }

    }
}
