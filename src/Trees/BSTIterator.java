package Trees;

import java.util.Stack;

public class BSTIterator {
    /*
     *@Author : Sahil
     * Date : 1 May 2019
     *
     * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the
     * root node of a BST.
     *
     * Calling next() will return the next smallest number in the BST.
     * Example:
     * BSTIterator iterator = new BSTIterator(root);
     * iterator.next();    // return 3
     * iterator.next();    // return 7
     * iterator.hasNext(); // return true
     * iterator.next();    // return 9
     * iterator.hasNext(); // return true
     * iterator.next();    // return 15
     * iterator.hasNext(); // return true
     * iterator.next();    // return 20
     * iterator.hasNext(); // return false
     *
     *
     * Note:
     *
     * next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
     * You may assume that next() call will always be valid, that is, there will be at least a next smallest number in
     * the BST when next() is called.
     *
     * References :
     * https://leetcode.com/problems/binary-search-tree-iterator/
     *
     * Solution :
     * 1. Maintain a stack and initially keep traversing root.left until null is reached
     * 2. when next is called, pop current element and  push all its right child's left nodes
     */

    Stack<Node> stack = new Stack<>();

    public BSTIterator(Node root) {
        pushAll(root);
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        Node minNode = stack.pop();
        pushAll(minNode.right);
        return minNode.data;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public void pushAll(Node root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

}
