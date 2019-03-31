package Trees;

public class BSTDeletion {
    /*
     *@Author : Sahil
     * Date : 31 March 2019
     *
     * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
     *
     * Basically, the deletion can be divided into two stages:
     *
     * Search for a node to remove.
     * If the node is found, delete the node.
     * Note: Time complexity should be O(height of tree).
     *
     * Example:
     *
     * root = [5,3,6,2,4,null,7]
     * key = 3
     *
     *     5
     *    / \
     *   3   6
     *  / \   \
     * 2   4   7
     *
     * Given key to delete is 3. So we find the node with value 3 and delete it.
     *
     * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
     *
     *     5
     *    / \
     *   4   6
     *  /     \
     * 2       7
     *
     * Another valid answer is [5,2,6,null,4,null,7].
     *
     *     5
     *    / \
     *   2   6
     *    \   \
     *     4   7
     *
     *
     * References :
     * https://leetcode.com/problems/delete-node-in-a-bst/
     *
     *
     * Solution :
     * 1. Recursively find the node that has the same value as the key, while setting the left/right nodes equal to
     * the returned subtree
     * 2. Once the node is found, have to handle the below 4 cases
     *     a . node doesn't have left or right - return null
     * b . node only has left subtree- return the left subtree
     * c .node only has right subtree- return the right subtree
     * d .node has both left and right - find the minimum value in the right subtree, set that value to the currently
     * found node, then recursively delete the minimum value in the right subtree
     */

    public Node deleteBSTNode(Node root, int data) {
        if (root == null)
            return null;

        if (data < root.data) {
            root.left = deleteBSTNode(root.left, data);
        } else if (data > root.data) {
            root.right = deleteBSTNode(root.right, data);
        } else {
            //Node to be deleted is found handle 4 cases as discussed above
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            Node minNode = findMin(root.right);
            root.data = minNode.data;
            root.right = deleteBSTNode(root.right, root.data);
        }
        return root;
    }

    private Node findMin(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }


    public static void main(String[] args) {
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

        PrintTree printTree = new PrintTree();

        printTree.inorder(BinarySearchTree.root);

        BSTDeletion bstDeletion = new BSTDeletion();
        bstDeletion.deleteBSTNode(BinarySearchTree.root,12);
        System.out.println("\n After Node 12 deletion : \n");

        printTree.inorder(BinarySearchTree.root);

    }
}
