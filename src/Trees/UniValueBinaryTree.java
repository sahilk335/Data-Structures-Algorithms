package Trees;


public class UniValueBinaryTree {
    /**
     * Univalue binary tree
     * Given the root of a binary tree, return the number of uni-value subtrees.
     * <p>
     * A uni-value subtree means all nodes of the subtree have the same value.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: root = [5,1,5,5,5,null,5]
     * Output: 4
     * Example 2:
     * <p>
     * Input: root = []
     * Output: 0
     * Example 3:
     * <p>
     * Input: root = [5,5,5,5,5,null,5]
     * Output: 6
     * <p>
     * <p>
     * Constraints:
     * <p>
     * The number of the node in the tree will be in the range [0, 1000].
     * -1000 <= Node.val <= 1000
     * <p>
     * Reference : https://leetcode.com/problems/count-univalue-subtrees/
     * <p>
     * 1. Traverse Binary tree in postorder
     * 2. leaf node is univalue, then add +1 count
     * 3. if left and right child of parent matches then add +1
     * 4. if right is null and left matches parent then add +1
     * 5. if left is null and right matches parent then add +1
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.left = left;
            this.right = right;
            this.val = val;
        }
    }

    int count = 0;

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        uniValSubTreeHelper(root);
        return count;
    }

    public boolean uniValSubTreeHelper(TreeNode root) {
        if (root == null) {
            return true;
        }
        // Leaf node is uniValSubTree
        if (root.left == null && root.right == null) {
            count++;
            return true;
        }

        boolean leftSubtreeResult = uniValSubTreeHelper(root.left);
        boolean rightSubtreeResult = uniValSubTreeHelper(root.right);

        if(leftSubtreeResult && rightSubtreeResult) {

            //No left node, just compare right and root
            if (root.left == null && root.right.val == root.val) {
                count++;
                return true;
            }
            // No right node, just compare left and root
            if (root.right == null && root.left.val == root.val) {
                count++;
                return true;
            }

            // Have both node, compare all root,left,right for equality
            if (root.left != null && root.right != null && root.left.val == root.val && root.right.val == root.val) {
                count++;
                return true;
            }
        }
        return false;
    }

}
