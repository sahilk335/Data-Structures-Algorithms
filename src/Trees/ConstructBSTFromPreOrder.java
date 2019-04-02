package Trees;

public class ConstructBSTFromPreOrder {
    /*
     *@Author : Sahil
     * Date : 02 Apr 2019
     *
     * Construct BST from given preorder traversal
     * Given preorder traversal of a binary search tree, construct the BST.
     *
     * For example, if the given traversal is {10, 5, 1, 7, 40, 50}, then the output should be root of following tree.
     *      10
     *    /   \
     *   5     40
     *  /  \      \
     * 1    7      50
     *
     * References :
     * https://www.geeksforgeeks.org/construct-bst-from-given-preorder-traversa/
     * https://algorithms.tutorialhorizon.com/construct-binary-search-tree-from-a-given-preorder-traversal-using-recursion/
     *
     * Solution
     * Use the isBST min-max approach
     *
     */

    int index = 0;

    public Node toBST(int[] preOrder, int min, int max) {
        if (index >= preOrder.length) {
            return null;
        }
        if (preOrder[index] < min || preOrder[index] >= max) {
            return null;
        }

        Node newNode = new Node(preOrder[index]);
        index++;
        newNode.left = toBST(preOrder, min, newNode.data);
        newNode.right = toBST(preOrder, newNode.data, max);
        return newNode;
    }

    public Node toBST(int[] preOrder) {
        return toBST(preOrder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static void main(String[] args) {
        ConstructBSTFromPreOrder constructBSTFromPreOrder = new ConstructBSTFromPreOrder();
        int preorder[] = {10, 5, 1, 7, 40, 50};
        Node root = constructBSTFromPreOrder.toBST(preorder);
        PrintTree printTree = new PrintTree();
        printTree.inorder(root);

    }
}
