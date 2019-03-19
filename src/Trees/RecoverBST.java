package Trees;

public class RecoverBST {
    /*
     *@Author : Sahil
     * Date : 19 Mar 2019
     *
     * Two elements of a binary search tree (BST) are swapped by mistake.
     *
     * Recover the tree without changing its structure.
     *
     * Example 1:
     *
     * Input: [1,3,null,null,2]
     *
     *    1
     *   /
     *  3
     *   \
     *    2
     *
     * Output: [3,1,null,null,2]
     *
     *    3
     *   /
     *  1
     *   \
     *    2
     * Example 2:
     *
     * Input: [3,1,4,null,null,2]
     *
     *   3
     *  / \
     * 1   4
     *    /
     *   2
     *
     * Output: [2,1,4,null,null,3]
     *
     *   2
     *  / \
     * 1   4
     *    /
     *   3
     * Follow up:
     *
     * A solution using O(n) space is pretty straight forward.
     * Could you devise a constant space solution?
     *
     *
     * References :
     *
     * https://leetcode.com/problems/recover-binary-search-tree/description/
     * https://leetcode.com/problems/recover-binary-search-tree/discuss/32535/No-Fancy-Algorithm-just-Simple-and-Powerful-In-Order-Traversal
     *
     * Solution :
     *
     * 1 . find two nodes... that violates this property  prevNode < currentNode
     * Note : first element found incorrect will always be the smallest element... because it is inorder traversal...
     * So, we have to assign firstElement to prevNode
     *
     * Second element found will be the larger element.. so have to assign secondelement to Root.
     *
     * 2. Swap two nodes.
     *
     */

    Node firstElement = null;
    Node secondElement = null;
    Node prevElement = new Node(Integer.MIN_VALUE);

    public void recoverTree(Node root) {
        //Inorder traversal to find two elements
        traverse(root);

        //swap the values of two nodes
        int temp = firstElement.data;
        firstElement.data = secondElement.data;
        secondElement.data = temp;
    }


    public void traverse(Node root) {
        if (root == null)
            return;

        traverse(root.left);

        //if violates the condition of bst then save it
        if (firstElement == null && prevElement.data >= root.data) {
            firstElement = prevElement;
        }

        //similarly find second element as explained above
        if (firstElement != null && prevElement.data >= root.data) {
            secondElement = root;
        }

        prevElement = root;

        traverse(root.right);
    }


    public static void main(String[] args) {
        RecoverBST recoverBST = new RecoverBST();
        BinarySearchTree bst = new BinarySearchTree();
        bst.makeTree();

        PrintTree pt = new PrintTree();


        pt.inorder(BinarySearchTree.root);
        System.out.println("\n After BST recovery \n");
        recoverBST.recoverTree(BinarySearchTree.root);
        pt.inorder(BinarySearchTree.root);

    }
}
