package Trees;

import java.util.LinkedList;
import java.util.Queue;

public class MirrorTree {
    /*
     * @Author :Sahil
     * Date : 11 March 2018
     *
     * Change a tree so that the roles of the  left and
     * right pointers are swapped at every node.
     * So the tree...
     *       4
     *      / \
     *     2   5
     *    / \
     *   1   3
     *
     * is changed to...
     *       4
     *      / \
     *     5   2
     *        / \
     *       3   1
     */

    //Recursive Solution
    public Node mirrorRecurive(Node root) {
        if (root == null)
            return null;
        Node left = mirrorRecurive(root.left);
        Node right = mirrorRecurive(root.right);

        root.left = right;
        root.right = left;
        return root;
    }

    //Iterative Solution
    public Node mirroIterative(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node top = queue.poll();
            if (top.left != null) {
                queue.add(top.left);
            }
            if (top.right != null) {
                queue.add(top.right);
            }
            Node right = top.right;
            top.right = top.left;
            top.left = right;
        }
        return root;
    }

    public static void main(String args[]) {
        BinaryTree binaryTree = new BinaryTree();
        BinaryTree.root = new Node(4);
        BinaryTree.root.left = new Node(2);
        BinaryTree.root.right = new Node(5);
        BinaryTree.root.left.left = new Node(1);
        BinaryTree.root.left.right = new Node(3);
        BinaryTree.root.right = new Node(5);
        PrintTree pt = new PrintTree();
        pt.inorder(BinaryTree.root);
        MirrorTree mirrorTree = new MirrorTree();
        mirrorTree.mirroIterative(BinaryTree.root);
        pt.inorder(BinaryTree.root);

    }
}
