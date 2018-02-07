package Trees;

public class ValidateBST {
    /*
     * @Author : Sahil
     * Date : 7 Feb 2018
     *
     * References :
     * https://leetcode.com/problems/validate-binary-search-tree/description/
     * https://www.youtube.com/watch?v=MILxfAbIhrE
     */
    public boolean isValidateBst(Node root) {
        boolean solution = validateBst(root, null, null);
        return solution;
    }

    public boolean validateBst(Node root, Integer min, Integer max) {
        if (root == null)
            return true;
        if (min != null && root.data <= min)
            return false;
        if (max != null && root.data >= max)
            return false;

        return validateBst(root.left, min, root.data) && validateBst(root.right, root.data, max);
    }


    public static void main(String args[]) {
        ValidateBST vbst = new ValidateBST();
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.makeTree();
        System.out.print(vbst.isValidateBst(binaryTree.root));
    }
}
