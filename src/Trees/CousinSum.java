package Trees;

import java.util.ArrayList;

/*
 *@Author : Sahil Khurana
 * Date : 19 June 2019
 *
 * Sum of cousins of a given node in a Binary Tree
 * Given a binary tree and data value of a node. The task is to find the sum of cousin nodes of given node. If given node has no cousins then return -1.
 * Note: It is given that all nodes have distinct values and the given node exists in the tree.
 *
 * Examples:
 *
 * Input:
 *                 1
 *               /  \
 *              3    7
 *            /  \  / \
 *           6   5  4  13
 *              /  / \
 *             10 17 15
 *          key = 13
 * Output: 11
 * Cousin nodes are 5 and 6 which gives sum 11.
 *
 * Input:
 *                 1
 *               /  \
 *              3    7
 *            /  \  / \
 *           6   5  4  13
 *              /  / \
 *             10 17 15
 *            key = 7
 * Output: -1
 * No cousin nodes of node having value 7.
 *
 *
 * Solution :
 * 1. Do level order traversal
 * 2. if root.left or root.right == target then return
 */

public class CousinSum {

    int targetLevel = 0;
    ArrayList<ArrayList<Integer>> list;

    public void cousinSumHelper(Node root, Node target, int level, ArrayList<ArrayList<Integer>> list) {
        if (root == null)
            return;

        if (level >= list.size()) {
            ArrayList<Integer> createArrayList = new ArrayList<>();
            list.add(level, createArrayList);
        }
        if (root.left == target || root.right == target) {
            targetLevel = level + 1;
            return;
        }

        list.get(level).add(root.data);

        cousinSumHelper(root.left, target, level + 1, list);
        cousinSumHelper(root.right, target, level + 1, list);
    }

    public int cousinSum(Node root, Node target) {
        int sum = 0;
        ArrayList<Integer> res = new ArrayList<>();
        list = new ArrayList<ArrayList<Integer>>();
        cousinSumHelper(root, target, 0, list);
        if (targetLevel == 1 || targetLevel + 1 > list.size() || list == null || list.get(targetLevel) == null) {
            return -1;
        }
        res = list.get(targetLevel);

        for (int i : res) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        CousinSum cousinSum = new CousinSum();
        BinaryTree.root = new Node(1);
        BinaryTree.root.left = new Node(2);
        BinaryTree.root.right = new Node(3);
        BinaryTree.root.left.left = new Node(4);
        BinaryTree.root.left.left.left = new Node(7);
        BinaryTree.root.left.left.left.left = new Node(10);
        BinaryTree.root.left.left.right = new Node(8);
        BinaryTree.root.left.right = new Node(5);
        BinaryTree.root.right.left = new Node(6);
        BinaryTree.root.right.left.left = new Node(9);
        BinaryTree.root.right.left.left.left = new Node(11);
        BinaryTree.root.right.left.left.left.left = new Node(12);
        BinaryTree.root.right.left.left.left.left.right = new Node(13);
        System.out.print(cousinSum.cousinSum(BinaryTree.root, BinaryTree.root.left.right));
    }
}
