package Trees;

import java.util.ArrayList;

import static Trees.LevelOrderTraversal.printList;

public class LeftRightView {
    /*
     *  Two Key points for leftView :
     *    1. if left view , do level+1 on  left first and then right .
     *       if right view, do level +1 on right first and then left .
     *    2. Print first element of each level
     *    3. Traverse left and then right.
     */

    static int minLevel = Integer.MIN_VALUE;

    public void leftView(Node root, int level, ArrayList<Integer> leftList) {
        if (root == null)
            return;
        //Store only the first node data , because it will be the leftmost node
        if (level > minLevel) {
            minLevel = level;
            leftList.add(root.data);
        }

        leftView(root.left, level + 1, leftList);
        leftView(root.right, level + 1, leftList);
    }

    /*
     *  Two Key points for ReftView :
     *    1. Print first element of each level
     *    2. Traverse right and then left.
     */
    public void rightView(Node root, int level, ArrayList<Integer> rightList) {
        if (root == null)
            return;
        //Store only the first node data , because it will be the rightmost node
        if (level > minLevel) {
            minLevel = level;
            rightList.add(root.data);
        }

        rightView(root.right, level + 1, rightList);
        rightView(root.left, level + 1, rightList);

    }

    public void leftViewUtil(Node root) {

        ArrayList<Integer> leftList = new ArrayList<Integer>();
        ArrayList<Integer> rightList = new ArrayList<Integer>();
        leftView(root, 0, leftList);
        System.out.println("Left View : ");
        printList(leftList);
        System.out.println("\nRight View : ");
        minLevel = Integer.MIN_VALUE;
        rightView(root, 0, rightList);
        printList(rightList);


    }

    public static void main(String args[]) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.makeTree();
        LeftRightView lrv = new LeftRightView();
        lrv.leftViewUtil(binaryTree.root);

    }
}
