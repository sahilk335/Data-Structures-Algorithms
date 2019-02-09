package Trees;

import LinkedList.LinkList;
import LinkedList.Node;

import java.util.LinkedList;
import java.util.Queue;


public class LinkedListToCompleteBinaryTree {
    /*
     *@Author : Sahil
     * Date : 09/02/2019
     *
     * Construct Complete Binary Tree from its Linked List Representation
     *
     *
     * Example :
     *
     * Linked List  : 10--12--15--25--30--36
     *
     * Complete Binary Tree
     *                   10
     *                 /    \
     *               12     15
     *              / \     /
     *             25  30  36
     * Solution :  Inorder : 25 12 30 10 36 15
     *
     * We are mainly given level order traversal in sequential access form.
     * We know head of linked list is always is root of the tree.
     * We take the first node as root and we also know that the next two nodes are left and right children of root.
     * So we know partial Binary Tree. The idea is to do Level order traversal of the partially built Binary Tree using
     * queue and traverse the linked list at the same time. At every step, we take the parent node from queue, make next
     * two nodes of linked list as children of the parent node, and enqueue the next two nodes to queue.
     *
     * 1. Create an empty queue.
     * 2. Make the first node of the list as root, and enqueue it to the queue.
     * 3. Until we reach the end of the list, do the following.
     * ………a. Dequeue one node from the queue. This is the current parent.
     * ………b. Traverse two nodes in the list, add them as children of the current parent.
     * ………c. Enqueue the two nodes into the queue.
     *
     * References :
     * https://www.geeksforgeeks.org/given-linked-list-representation-of-complete-tree-convert-it-to-linked-representation/
     */

    Trees.Node convertListToBinaryTree(Trees.Node root, Node head) {
        Queue<Trees.Node> queue = new LinkedList<Trees.Node>();
        Trees.Node parent = null;
        Trees.Node leftChild = null;
        Trees.Node rightChild = null;


        if (head == null) {
            root = null;
            return null;
        }

        //First node is always the root node
        root = new Trees.Node(head.data);
        queue.add(root);

        //move to next pointer in linked list
        head = head.next;

        //Continue while linked list is completely traversed

        while (head != null) {

            parent = queue.poll();
            leftChild = new Trees.Node(head.data);
            queue.add(leftChild);
            //move head for right child
            head = head.next;

            if (head != null) {
                rightChild = new Trees.Node(head.data);
                queue.add(rightChild);
                head = head.next;
            } else {
                rightChild = null;
            }

            //assign left and right child to parent now
            parent.left = leftChild;
            parent.right = rightChild;
        }

        return root;
    }


    void inorder(Trees.Node root) {
        if (root == null)
            return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }


    public static void main(String[] args) {
        LinkedListToCompleteBinaryTree ll2cbt = new LinkedListToCompleteBinaryTree();
        LinkList linkList1 = new LinkList();
        Node head1 = null;

        head1 = linkList1.addNode(10, head1);
        head1 = linkList1.addNode(12, head1);
        head1 = linkList1.addNode(15, head1);
        head1 = linkList1.addNode(25, head1);
        head1 = linkList1.addNode(30, head1);
        head1 = linkList1.addNode(36, head1);

        BinaryTree binaryTree = new BinaryTree();
        Trees.Node root = new Trees.Node();
        root = ll2cbt.convertListToBinaryTree(root, head1);
        ll2cbt.inorder(root);


    }
}
