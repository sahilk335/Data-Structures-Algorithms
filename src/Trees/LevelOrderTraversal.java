package Trees;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    //Iterative Level Order Traversal using queue

    public void LevelTraversalQueue(Node root) {
        if (root == null)
            return;
        Queue<Node> queue = new LinkedList<Node>();
        ArrayList<Integer> list = new ArrayList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            for (int i = 0; i < levelNum; i++) {
                Node node = queue.peek();
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
                list.add(queue.poll().data);
            }
        }
        printList(list);

    }

    //Level order traversal using DFS
    public void LevelOrderTraversalRecusion(Node root, int level, ArrayList<ArrayList<Integer>> list) {
        if (root == null)
            return;

        if (level >= list.size()) {             //Level is not yet created
            ArrayList<Integer> levelCreate = new ArrayList();
            //Add first data into the new level created
            levelCreate.add(root.data);
            //Add the level into the list of level
            list.add(levelCreate);
        } else {                              //Level is already created, now get the level and just add
            ArrayList<Integer> alreadyCreatedLevel = list.get(level);
            alreadyCreatedLevel.add(root.data);
        }

        LevelOrderTraversalRecusion(root.left, level + 1, list);
        LevelOrderTraversalRecusion(root.right, level + 1, list);

    }

    public void LevelOrderTraversalRecursionUtil(Node root) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        LevelOrderTraversalRecusion(root, 0, list);

        for (int i = 0; i < list.size(); i++) {
            System.out.print("Level " + i + " = ");
            for (int j = 0; j < list.get(i).size(); j++) {
                System.out.print(list.get(i).get(j) + " ");
            }
            System.out.print("\n");
        }
    }

    public static void printList(ArrayList list) {
        //Print the list
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }

    public static void main(String args[]) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.makeTree();
        LevelOrderTraversal Lot = new LevelOrderTraversal();
        //Lot.LevelTraversalQueue(binaryTree.root);
        Lot.LevelOrderTraversalRecursionUtil(binaryTree.root);
    }
}
