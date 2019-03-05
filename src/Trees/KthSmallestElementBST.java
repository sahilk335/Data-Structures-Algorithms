package Trees;

import Array.BinarySearch;

public class KthSmallestElementBST {
    /*
     * @Author : Sahil
     * Date : 03 Mar 2019
     *
     * Find k-th smallest element in BST (Order Statistics in BST)
     *
     * Approach 1 : Augmented Binary Search Tree ( Change the tree node information by adding count variable with each
     *              node that stores the number of node in the node's left subtree)
     *      * this approach takes O(H) time to tell the Kth's smallest element, assuming that count variable with each
     *        node was originally maintained when creating a BST. else it would take O(N) time to maintain the count.
     *
     *        Step 1 :Assume that root is having N nodes in its left subtree.
     *        if K = N+1 , then root is Kth node.
     *        Step 2 : if K < N+1 , we continue our search in left subtree
     *        Step 3 : else if k>N+1, we will now search in right subtree.
     *                  and update k=k-N-1
     *
     *          where N -> number of elements in root leftSubtree
     *
     * Approach 2 : Using DFS
     *
     * we start moving in Inorder fashion and stops as soon as counter hits the K.
     *
     * O(N) approach
     *
     */

    public int kthSmallest_1(Node root, int k) {

        int leftSubtreeCount = countNodes(root.left);

        if (k == leftSubtreeCount + 1)
            return root.data;
        else if (k < leftSubtreeCount + 1)
            return kthSmallest_1(root.left, k);
        else
            return kthSmallest_1(root.right, k - leftSubtreeCount - 1);
    }

    public int countNodes(Node root) {
        if (root == null)
            return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }


    //DFS Approach (O(N))

    private static int count = 0;
    private static int answerDFS=0;

    public void KthSmallestNumberDfs(Node root,int k){

        if(root.left!=null)
            KthSmallestNumberDfs(root.left,k);

        count++;

        if(count==k){
            answerDFS = root.data;
        }

        if(root.right!=null)
            KthSmallestNumberDfs(root.right,k);

    }

    public static void main(String[] args) {
        KthSmallestElementBST kbst = new KthSmallestElementBST();
        BinarySearchTree bst = new BinarySearchTree();
        bst.makeTree();
        int k = 5;
        System.out.println("Kth Smallest element in BST : " + kbst.kthSmallest_1(BinarySearchTree.root, k));

        kbst.KthSmallestNumberDfs(BinarySearchTree.root,k);
        System.out.println("Kth Smallest element in BST using dfs approach : " + answerDFS);


    }
}
