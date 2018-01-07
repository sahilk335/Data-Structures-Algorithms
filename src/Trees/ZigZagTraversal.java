package Trees;

import java.util.Stack;

public class ZigZagTraversal {
    /*
     * Using 2 stacks
     *
     * In first stack -> first push left & then right
     * In Second stack -> first push right & then left
     *
     */

    public void zigzag(Node root) {
        if (root == null)
            return;
        Stack<Node> stack1 = new Stack<Node>();
        Stack<Node> stack2 = new Stack<Node>();
        stack1.add(root);

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                root = stack1.pop();
                System.out.print(root.data + " ");
                if (root.left != null)
                    stack2.push(root.left);
                if (root.right != null)
                    stack2.push(root.right);
            }

            while (!stack2.isEmpty()) {
                root = stack2.pop();
                System.out.print(root.data + " ");
                if (root.right != null)
                    stack1.push(root.right);
                if (root.left != null)
                    stack1.push(root.left);
            }
        }
    }

    public static void main(String args[]) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.makeTree();
        ZigZagTraversal zzt = new ZigZagTraversal();
        zzt.zigzag(binaryTree.root);

    }
}
