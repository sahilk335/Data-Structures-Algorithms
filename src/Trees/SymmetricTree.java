package Trees;

public class SymmetricTree {
    /*
     * @Author : Sahil
     * Date : 8 Feb 2018
     *
     * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
     *
     * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
     *
     *     1
     *   / \
     *  2   2
     *  / \ / \
     * 3  4 4  3
     * But the following [1,2,2,null,3,null,3] is not:
     *     1
     *    / \
     *   2   2
     *    \   \
     *    3    3
     *
     * References : https://leetcode.com/problems/symmetric-tree/description/
     * Solution : Submitted on Leetcode
     * 1. consider 1 tree as two trees
     * first tree starts from root.left
     * Second tree starts from root.right
     *
     */

    public boolean isSymmetric(Node root) {
        return isSymmetricCheck(root, root);
    }

    public boolean isSymmetricCheck(Node root1, Node root2) {
        //both have leaf node at same time
        if (root1 == null && root2 == null)
            return true;
        //above condition already failed and if either one of it is null,means it is not equal hence return false
        if (root1 == null || root2 == null)
            return false;

        return (root1.data == root2.data) &&
                isSymmetricCheck(root1.left, root2.right) &&
                isSymmetricCheck(root1.right, root2.left);
    }

    public static void main(String args[]) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.makeTree();
        SymmetricTree symmetricTree = new SymmetricTree();
        System.out.print(symmetricTree.isSymmetric(binaryTree.root));
    }
}
