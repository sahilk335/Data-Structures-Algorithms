package Trees;

public class DistanceBetweenNodes {
    /*
     *@Author :Sahil
     * Date : 10 March 2018
     *
     * Find the distance between two keys in a binary tree, no parent pointers are given.
     * Distance between two nodes is the minimum number of edges to be traversed to reach one node from other.
     *
     * References : https://www.geeksforgeeks.org/find-distance-between-two-nodes-of-a-binary-tree/
     *
     * Solution :
     * The distance between two nodes can be obtained in terms of lowest common ancestor. Following is the formula.

     * Dist(n1, n2) = Dist(root, n1) + Dist(root, n2) - 2*Dist(root, lca)
     * 'n1' and 'n2' are the two given keys
     * 'root' is root of given Binary Tree.
     * 'lca' is lowest common ancestor of n1 and n2
     * Dist(n1, n2) is the distance between n1 and n2.
     */

    public int distance(Node root, Node n1, Node n2) {
        LowestCommonAncestor lca = new LowestCommonAncestor();
        Node LCA = lca.LCA(root, n1, n2);

        int rootDistance1 = rootDistance(root, n1);
        int rootDistance2 = rootDistance(root, n2);
        int rootDistanceLCA = rootDistance(root, LCA);

        return (rootDistance1 + rootDistance2 - (2 * rootDistanceLCA));
    }

    //Returns the distance of the node from root
    public Integer rootDistance(Node root, Node n1) {
        if (root == null)
            return -1;

        if (root == n1)
            return 0;

        int left = rootDistance(root.left, n1);
        int right = rootDistance(root.right, n1);

        if (left == -1 && right == -1)
            return -1;

        return 1 + Math.max(left, right);
    }

    public static void main(String args[]) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.makeTree();
        DistanceBetweenNodes dbn = new DistanceBetweenNodes();
        System.out.print(dbn.distance(BinaryTree.root, BinaryTree.root.right.left.left.left.left.right, BinaryTree.root.left.left.right));//13->8
    }
}
