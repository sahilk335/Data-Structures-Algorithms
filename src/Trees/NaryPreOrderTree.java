package Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NaryPreOrderTree {
    /*
     * @Author : Sahil Khurana
     * Date : 06 June 2019
     *
     * Given an n-ary tree, return the preorder traversal of its nodes' values.
     *
     * Solution 1 : Iterative
     * Solution 2 : Recursive
     */

    List<Integer> preOrderRecList = new ArrayList<>();
    List<Integer> preOrderIteList = new ArrayList<>();

    public List<Integer> preorderRecursive(Node root) {
        if (root == null)
            return preOrderRecList;

        preOrderRecList.add(root.data);

        for (Node node : root.children) {
            preorderRecursive(node);
        }

        return preOrderRecList;

    }

    public List<Integer> preOrderIterative(Node root) {
        if (root == null)
            return preOrderIteList;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            preOrderIteList.add(curr.data);
            for (int i = curr.children.size() - 1; i >= 0; i--) {
                stack.push(curr.children.get(i));
            }
        }
        return preOrderIteList;
    }

}
