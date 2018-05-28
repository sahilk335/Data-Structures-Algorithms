package Trees;

public class BinaryTree2DLL {
    /*
     *@Author : Sahil
     * Date : 29 May 2018
     *
     * Convert a given Binary Tree to Doubly Linked List
     *
     * References : https://www.geeksforgeeks.org/convert-given-binary-tree-doubly-linked-list-set-3/
     *
     *
     * Solution :
     * The idea is to do inorder traversal of the binary tree. While doing inorder traversal,
     * keep track of the previously visited node in a variable say prev. For every visited node, make it
     * next of prev and previous of this node as prev.
     *
     */


    static Node head;
    static Node prev;


    void binaryTree2Dll(Node root) {

        if (root == null)
            return;

        binaryTree2Dll(root.left);

        if (prev == null) {
            head = root;
        } else {
            root.left = prev;
            prev.right = root;
        }

        prev = root;

        binaryTree2Dll(root.right);
    }

    void printDLL(Node root) {
        while (root != null) {
            System.out.print(root.data+" ");
            root = root.right;
        }
    }


    public static void main(String[] args) {
        BinaryTree2DLL binaryTree2DLL = new BinaryTree2DLL();
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.makeTree();
        binaryTree2DLL.binaryTree2Dll(binaryTree.root);
        binaryTree2DLL.printDLL(head);

    }
}
