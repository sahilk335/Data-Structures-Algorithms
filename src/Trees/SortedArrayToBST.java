package Trees;

public class SortedArrayToBST {
    /*
     *@Author:Sahil
     * Date : 13 March 2018
     *
     * Given the sorted array: [-10,-3,0,5,9],
     *
     * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
     *
     *      0
     *     / \
     *   -3   9
     *   /   /
     * -10  5
     *
     * References :
     * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
     * https://www.youtube.com/watch?v=VCTP81Ij-EM
     */

    public Node sortedArrayToBST(int[] num) {
        if (num.length == 0)
            return null;

        Node head = sortedArrayToBSTUtil(num, 0, num.length - 1);
        return head;
    }

    public Node sortedArrayToBSTUtil(int arr[], int low, int high) {
        if (low > high)
            return null;
        int mid = (low + high) / 2;
        Node node = new Node(arr[mid]);
        node.left = sortedArrayToBSTUtil(arr, low, mid - 1);
        node.right = sortedArrayToBSTUtil(arr, mid + 1, high);
        return node;
    }

    public static void main(String args[]) {
        SortedArrayToBST sBST = new SortedArrayToBST();
        int nums[] = {1, 2, 3, 4, 5};
        PrintTree printTree = new PrintTree();
        //For BST, inorder should be sorted (For checking my solution)
        printTree.inorder(sBST.sortedArrayToBST(nums));

    }
}
