package Trees;

import java.util.ArrayList;

import static Trees.LevelOrderTraversal.printList;

public class LeftRightView {
    /*
     *  Two Key points for leftView :
     *    1. Print first element of each level
     *    2. Traverse left and then right.
     */
    public void leftView(Node root, int level, ArrayList<ArrayList<Integer>> list, ArrayList<Integer> leftList) {
        if (root == null)
            return;
        //Store only the first node data , because it will be the leftmost node
        if (level >= list.size()) {
            ArrayList levelCreate = new ArrayList();
            levelCreate.add(root.data);
            list.add(levelCreate);
            leftList.add(root.data);
        }
        leftView(root.left, level + 1, list, leftList);
        leftView(root.right, level + 1, list, leftList);
    }

    /*
     *  Two Key points for ReftView :
     *    1. Print first element of each level
     *    2. Traverse right and then left.
     */
    public void rightView(Node root, int level, ArrayList<ArrayList<Integer>> list, ArrayList<Integer> rightList) {
        if (root == null)
            return;
        //Store only the first node data , because it will be the rightmost node
        if (level >= list.size()) {
            ArrayList<Integer> levelCreate = new ArrayList<Integer>();
            levelCreate.add(root.data);
            list.add(levelCreate);
            rightList.add(root.data);
        }
        rightView(root.right, level + 1, list, rightList);
        rightView(root.left, level + 1, list, rightList);

    }

    public void leftViewUtil(Node root) {
        ArrayList<ArrayList<Integer>> list1 = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> list2 = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> leftList = new ArrayList<Integer>();
        ArrayList<Integer> rightList = new ArrayList<Integer>();
        leftView(root, 0, list1, leftList);
        System.out.println("Left View : ");
        printList(leftList);
        System.out.println("\nRight View : ");
        rightView(root, 0, list2, rightList);
        printList(rightList);


    }

    public static void main(String args[]) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.makeTree();
        LeftRightView lrv = new LeftRightView();
        lrv.leftViewUtil(binaryTree.root);

    }
}
