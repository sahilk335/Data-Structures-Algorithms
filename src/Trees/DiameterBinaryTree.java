package Trees;

public class DiameterBinaryTree {

    /*
     * diameter of a binary tree T will be the largest of the following quantities:
     * the diameter of T’s left subtree
     * the diameter of T’s right subtree
     * the longest path between leaves that goes through the root of T (this can be computed from the heights of the subtrees of T)
     *
     *At every node its left height, right height, left diameter and right
     *
     * Diagram : https://crackinterviewtoday.files.wordpress.com/2010/03/diameter1.jpeg
     *
     * Source:
     * https://crackinterviewtoday.wordpress.com/2010/03/11/diameter-of-a-binary-tree/
     *
     *
     * Two Approaces
     * Solution 1 : O(N^2) -> calculating height each time
     * for  O(N^2)
     * Solution 2: O(N) -> calculaing height in the same loop
     */
    int answer=0;
    int DiameterBinaryTree(Node node) {
        if(node==null)
            return 0;           //Leaf node

        int leftDiameter=DiameterBinaryTree(node.left);
        int rightDiameter=DiameterBinaryTree(node.right);

        answer=Math.max(answer,(leftDiameter+rightDiameter+1));

        return 1+Math.max(leftDiameter,rightDiameter);  //this is actually the height of the node (This approach is O(N)
    }


    public static void main(String args[]) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.makeTree();
        DiameterBinaryTree dbt = new DiameterBinaryTree();
        dbt.DiameterBinaryTree(binaryTree.root);
        System.out.print("Diameter of Binary Tree : "+ dbt.answer);
    }
}
