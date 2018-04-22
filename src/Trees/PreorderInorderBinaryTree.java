package Trees;

import java.util.HashMap;
import java.util.Map;

public class PreorderInorderBinaryTree {
    /*
     * Author : Sahil
     * Date : 22 Apr 2018
     * Given preorder and inorder traversal of a tree, construct the binary tree.
     *
     * Note:
     * You may assume that duplicates do not exist in the tree.
     *
     * For example, given
     *
     * preorder = [3,9,20,15,7]
     * inorder = [9,3,15,20,7]
     * Return the following binary tree:
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * Solution :
     * The basic idea is here:
     * Say we have 2 arrays, PRE and IN.
     * Preorder traversing implies that PRE[0] is the root node.
     * Then we can find this PRE[0] in IN, say it’s IN[5].
     * Now we know that IN[5] is root,
     * so we know that IN[0] - IN[4] is on the left side, IN[6] to the end is on the right side.
     * Recursively doing this on subarrays, we can build a tree out of it
     *
     *
     * Our aim is to find out the index of right child for current node in the preorder array :
     *
     * We know the index of current node in the preorder array - preStart (or whatever your call it),
     * it’s the root of a subtree
     *
     * Remember pre order traversal always visit all the node on left branch
     * before going to the right ( root -> left -> … -> right), therefore,
     *
     * we can get the immediate right child index by skipping all the node on the left branches/subtrees of current node
     * The inorder array has this information exactly. Remember when we found the root in “inorder” array
     *
     * we immediately know how many nodes are on the left subtree and how many are on the right subtree
     * Therefore the immediate right child index is preStart + numsOnLeft + 1
     * (remember in preorder traversal array root is always ahead of children nodes but you don’t know which one
     * is the left child which one is the right, and this is why we need inorder array)
     *
     * numsOnLeft = root - inStart.
     *
     * References :
     * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
     * https://articles.leetcode.com/construct-binary-tree-from-inorder-and-preorder-postorder-traversal/
     *
     *
     * NOTE : ALL ELEMENTS ARE UNIQUE.
     *
     */

    public Node buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        Node root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
        return root;
    }

    public Node buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd) return null;

        Node root = new Node(preorder[preStart]);
        int inRoot = inMap.get(root.data);
        int numsLeft = inRoot - inStart;

        root.left = buildTree(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, inRoot - 1, inMap);
        root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, inMap);

        return root;
    }


    public static void main(String args[]) {
        PreorderInorderBinaryTree preorder = new PreorderInorderBinaryTree();
        int[] preorders = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        Node root = preorder.buildTree(preorders, inorder);
        PrintTree printTree = new PrintTree();
        printTree.preorder(root);
    }
}
