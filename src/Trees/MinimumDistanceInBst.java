package Trees;

public class MinimumDistanceInBst {
    /*
     *@Author : Sahil
     * Date : 12 Feb 2018
     *
     * References:https://leetcode.com/problems/minimum-distance-between-bst-nodes/description/
     *
     * Solution:
     * Sol 1 : Inorder Traversal of BST is sorted order . after that find minimum element bw consecutive elements in sorted array
     * Sol 2: same solution, but maintain the global minimum and find minimum bw 2 adjacent nodes while traversing itself
     */
    int answer;
    Node prev;

    public int minDiffInBST(Node root) {
        prev = null;
        answer = Integer.MAX_VALUE;
        inorder(root);
        return answer;
    }

    void inorder(Node root) {
        if (root == null)
            return;
        inorder(root.left);
        if (prev != null) {
            answer = Math.min(answer, root.data - prev.data);
        }
        prev = root;
        inorder(root.right);
    }


    public static void main(String args[]) {
        MinimumDistanceInBst minimumDistanceInBst = new MinimumDistanceInBst();
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.makeTree();
        System.out.print(minimumDistanceInBst.minDiffInBST(binaryTree.root));
    }
}
