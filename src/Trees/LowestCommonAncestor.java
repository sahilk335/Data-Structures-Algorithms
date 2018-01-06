package Trees;

public class LowestCommonAncestor {
    /*
     * Finds the lowest common ancestor of two given nodes
     * Assumption : Below code assumes that both nodes exist in tree.
     *
     */

    public Node LCA(Node root, Node N1, Node N2) {

        // leaf node , returns null
        if (root == null)
            return null;

        //Node is found N1 or N2 , then return itself
        if (root == N1 || root == N2) {
            return root;
        }

        Node left = LCA(root.left, N1, N2);
        Node right = LCA(root.right, N1, N2);

        //This is the LCA (one whose both child is not null)
        if (left != null && right != null) {
            return root;
        }

        //left child is not null but right child is null . return itself
        if (left != null)
            return left;
        //right child is not null but left child is null , return itself
        return right;
    }

    public static void main(String args[]) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.makeTree();
        LowestCommonAncestor lca = new LowestCommonAncestor();
        int lcaData = lca.LCA(binaryTree.root, binaryTree.root.left.left.left.left, BinaryTree.root.left.left.left).data;
        System.out.print("LCA : " + lcaData);

    }
}
