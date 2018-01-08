package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class VerticalOrderView {

    public void verticalOrderTravel(Node root) {
        List<List<Integer>> verticalMatrix = new ArrayList<>();
        int range[] = {0, 0};
        getRange(root, 0, range);

        //Initialize the verticalMatrix for range values

        for (int i = range[0]; i <= range[1]; i++) {
            verticalMatrix.add(new ArrayList<Integer>());
        }

        Queue<Node> queue = new LinkedList<>();
        Queue<Integer> colNum = new LinkedList<Integer>();

        queue.add(root);
        /*
        Note this is important as we know left column minimum value is range[0]
        this will be always negative.
        Now as we want to use this value as index of arraylist, and it should be positive
        So we add negative of negative number so that now.
        if we perform left(col-1) , then minimum value in left reaches to zero.
         */
        colNum.add(-range[0]);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int column = colNum.poll();

            //add the node data in corresponding index
            verticalMatrix.get(column).add(node.data);

            if (node.left != null) {
                queue.add(node.left);
                colNum.add(column - 1);  //this column-1 minimum value is 0
            }

            if (node.right != null) {
                queue.add(node.right);
                colNum.add(column + 1);
            }
        }

        //Print vertical order List
        printVerticalList(verticalMatrix);
    }

    public void printVerticalList(List<List<Integer>> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                System.out.print(list.get(i).get(j) + " ");
            }
        }
    }

    //get the range of column
    public void getRange(Node root, int col, int range[]) {
        if (root == null)
            return;

        range[0] = Math.min(range[0], col);        // Min column index
        range[1] = Math.max(range[1], col);        //Max coulmn index

        getRange(root.left, col - 1, range);
        getRange(root.right, col + 1, range);
    }

    public static void main(String args[]) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.makeTree();
        VerticalOrderView vov = new VerticalOrderView();
        vov.verticalOrderTravel(binaryTree.root);
    }
}
