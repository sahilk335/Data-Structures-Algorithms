package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ZigZagTraversal {
    /*
     * Solution 1
     *
     * Using 2 stacks
     *
     * In first stack -> first push left & then right
     * In Second stack -> first push right & then left
     *
     *
     * Solution 2
     *
     * 1. Travel level wise
     * 2. Check for modulus 2 for even or odd order level
     * 3. If even , start putting node val at end of list of that level
     * 4. If odd, start putting value at beginning of that level
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

    public void zigzagLevelOrder(Node root) {
        List<List<Integer>> sol = new ArrayList<>();
        zigzag2(root, sol, 0);
        for (int i = 0; i < sol.size(); i++) {
            List<Integer> currList = sol.get(i);
            for (int j = 0; j < currList.size(); j++) {
                System.out.print(currList.get(j) + " ");
            }
        }
    }

    public void zigzag2(Node root, List<List<Integer>> list, int level) {
        if (root == null)
            return;

        if (level >= list.size()) {
            List<Integer> currLevelList = new LinkedList<>();
            list.add(currLevelList);
        }

        List<Integer> levelList = list.get(level);
        if (level % 2 == 0)
            levelList.add(root.data);                  //Add at last index of Array list
        else
            levelList.add(0, root.data);         // Add at first index of linked list (reverse)

        zigzag2(root.left, list, level + 1);
        zigzag2(root.right, list, level + 1);
    }

    public static void main(String args[]) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.makeTree();
        ZigZagTraversal zzt = new ZigZagTraversal();
        zzt.zigzag(binaryTree.root);

    }
}
