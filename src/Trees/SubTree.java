package Trees;

import java.util.HashSet;

public class SubTree {
    /*
     *@Author : Sahil
     * Date : 25 May 2018
     *
     * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
     *
     * Example 1:
     * Given tree s:
     *
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     * Given tree t:
     *    4
     *   / \
     *  1   2
     * Return true, because t has the same structure and node values with a subtree of s.
     * Example 2:
     * Given tree s:
     *
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     *     /
     *    0
     * Given tree t:
     *    4
     *   / \
     *  1   2
     * Return false.
     *
     * References : https://leetcode.com/problems/subtree-of-another-tree/description/
     *
     *
     * Solutions :
     *
     * Solution 1 O(m*n)
     *
     * we can treat every node of the given tree tt as the root, treat it as a subtree and compare the corresponding
     * subtree with the given subtree ss for equality. For checking the equality, we can compare the all the nodes
     * of the two subtrees.
     *
     * For doing this, we make use a function traverse(s,t) which traverses over the given tree ss and treats every
     * node as the root of the subtree currently being considered. It also checks the two subtrees currently being
     * considered for their equality. In order to check the equality of the two subtrees, we make use of equals(x,y)
     * function, which takes xx and yy, which are the roots of the two subtrees to be compared as the inputs and
     * returns True or False depending on whether the two are equal or not. It compares all the nodes of the two
     * subtrees for equality. Firstly, it checks whether the roots of the two trees for equality and then calls
     * itself recursively for the left subtree and the right subtree.
     *
     * Solution 2 O(M+N) but with space complexity and KMP
     *
     *
     *
     */

    //Solution 1
    public boolean isSubtreeSol1(Node s, Node t) {
        return traverse(s, t);
    }


    public boolean equals(Node x, Node y) {
        if (x == null && y == null)
            return true;
        if (x == null || y == null)
            return false;
        return x.data == y.data && equals(x.left, y.left) && equals(x.right, y.right);
    }


    public boolean traverse(Node s, Node t) {
        return s != null && (equals(s, t) || traverse(s.left, t) || traverse(s.right, t));
    }


    //Solution 2

    //Using Pre_order with LNull and RNull and then comparing string using KMP

    HashSet<String> trees = new HashSet<>();

    public boolean isSubtreeSol2(Node s, Node t) {
        String tree1 = preOrder(s, true);
        String tree2 = preOrder(t, false);
        return tree1.indexOf(tree2) != -1;
    }

    public String preOrder(Node root, boolean left) {
        if (root == null) {
            if (left)
                return "lnull";
            else
                return "rnull";
        }
        return "#" + root.data + " " + preOrder(root.left, true) + " " + preOrder(root.right, false);
    }
}
