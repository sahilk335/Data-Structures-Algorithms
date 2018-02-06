package Trees;

import java.util.ArrayList;

public class PathSumIII {
    /*
     * @Author: Sahil
     * Date : 06 Feb 2018
     *
     * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
     *
     *        10
     *       /  \
     *      5   -3
     *     / \    \
     *    3   2   11
     *   / \   \
     *  3  -2   1
     *
     * Return 3. The paths that sum to 8 are:
     *
     * 1.  5 -> 3
     * 2.  5 -> 2 -> 1
     * 3. -3 -> 11
     *
     *
     * References :
     * https://www.geeksforgeeks.org/print-k-sum-paths-binary-tree/
     * https://leetcode.com/problems/path-sum-iii/description/
     */


    public int pathSum(Node root, int sum) {
        if (root == null)
            return 0;
        //for each node call dfs and traverse in Inorder at the same time
        return findPath(root, sum, root) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    //function that calls dfs from a given node.
    //rootCopy function is used just to store the real root, used to print the node path
    public int findPath(Node root, int sum, Node rootCopy) {
        int res = 0;
        if (root == null)
            return 0;

        //that means add this node data will lead to actual sum
        //that means this is the destination node , at this node we are getting desired sum
        if (sum == root.data) {
            ArrayList<Node> path = new ArrayList<Node>();
            printPathForKSum(rootCopy, root, path);
            printPathData(path);
            res++;
        }
        res += findPath(root.left, sum - root.data, rootCopy);
        res += findPath(root.right, sum - root.data, rootCopy);
        return res;
    }

    //This function prints the path from given Node to destination node
    //It is just like Tushar Roy LCA explaination when we keep on returning true or false from a node
    public boolean printPathForKSum(Node root, Node destNode, ArrayList<Node> path) {

        if (root == null) {
            return false;
        }
        //Destination node is reached, starting from here start returning true and push this node in path List also
        if (root == destNode) {
            path.add(root);
            return true;
        }

        boolean leftlist = printPathForKSum(root.left, destNode, path);
        boolean rightlist = printPathForKSum(root.right, destNode, path);

        //Note: we get true from real path only, so which one of it return true print it
        //it means it gets true either from left or right, we don't care from which one of the direction it gets true
        //we just know that current node is a path of path , if it also gets true from its bottom(lol!)
        if (leftlist || rightlist) {
            path.add(root);
            return true;
        }

        //if it reaches here , that means we are in wrong direction the whole time,return false
        return false;
    }

    public void printPathData(ArrayList<Node> path) {
        for (Node root : path)
            System.out.print(root.data + " ");
        System.out.print("\n");
    }

    public static void main(String args[]) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.makeTree();
        PathSumIII ps3 = new PathSumIII();
        System.out.println("Total nodes : " + ps3.pathSum(binaryTree.root, 8) + "\n");
    }
}
