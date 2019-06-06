package Trees;

/*
 *@Author : Sahil Khurana
 * Date : 06 June 2019
 *
 * Given an n-ary tree, return the postorder traversal of its nodes' values.
 *
 * References : https://leetcode.com/problems/n-ary-tree-postorder-traversal/
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class NaryPostOrderTree {

    List<Integer> list = new ArrayList<>();

    public List<Integer> postorder(Node root) {
        if (root == null)
            return list;

        for (Node node : root.children)
            postorder(node);

        list.add(root.data);

        return list;
    }

    public List<Integer> postorderIterative(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Stack<Node> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            root = stack.pop();
            list.add(root.data);
            for (Node node : root.children)
                stack.add(node);
        }
        Collections.reverse(list);
        return list;
    }
}
