package Trees;

/*
 *@Author : Sahil
 * Date : 12 May 2019
 *
 * Given a BST , replace all its node with its next greater element (i.e successor )
 *
 * Example :
 *         20                        22
 *       /   \                     /   \
 *      8     22                  12     24
 *     /\     /\      ->>        / \    / \
 *    4  12  21 24              8   14 22  24
 *      /  \                       / \
 *     10  14                     12  20
 *
 * Solution :
 *
 * 1. Keep global prev Node
 * 2. replace prev with current
 *
 */

public class BSTNextGreaterElement {
    Node prev = null;

    public void BSTNextGreater(Node root) {
        if (root == null)
            return;

        BSTNextGreater(root.left);

        if (prev != null)
            prev.data = root.data;
        prev = root;

        BSTNextGreater(root.right);
    }

    public static void main(String[] args) {
        BinarySearchTree.root = new Node(20);

        /*
         20
       /   \
      8     22
     /\     /\
    4  12  21 24
      /  \
     10  14
         */

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

        PrintTree printTree = new PrintTree();

        printTree.inorder(BinarySearchTree.root);

        BSTNextGreaterElement bstNge = new BSTNextGreaterElement();
        bstNge.BSTNextGreater(BinarySearchTree.root);

        System.out.println("\n\n");
        printTree.inorder(BinarySearchTree.root);
    }
}
