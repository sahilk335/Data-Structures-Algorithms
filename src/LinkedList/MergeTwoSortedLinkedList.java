package LinkedList;

public class MergeTwoSortedLinkedList {
    /*
     *@Author : Sahil
     * Date : 06 Apr 2018
     *
     * Given two heads of 2 sorted linked list, merge it to 1 sorted linked list
     *
     * References :
     * https://leetcode.com/problems/merge-two-sorted-lists/description/
     *
     * Example:
     * Input: 1->2->4, 1->3->4
     * Output: 1->1->2->3->4->4
     *
     * Solution 1:
     * Iterative
     *
     * Solution 2:
     * Recursivee
     */

    public Node mergeTwoListsIterative(Node l1, Node l2) {
        if (l1 == null && l2 == null)
            return null;
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        Node result = new Node(0);
        Node newHead = result;
        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                newHead.next = l1;
                l1 = l1.next;
            } else {
                newHead.next = l2;
                l2 = l2.next;
            }
            newHead = newHead.next;
        }
        if (l1 != null)
            newHead.next = l1;
        else {
            newHead.next = l2;
        }
        return result.next;
    }


    public Node mergeTwoListsRecursive(Node l1, Node l2) {
        if (l1 == null && l2 == null)
            return null;
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        Node newHead = null;
        if (l1.data < l2.data) {
            newHead = l1;
            newHead.next = mergeTwoListsIterative(l1.next, l2);
        }

        if (l1.data > l2.data) {
            newHead = l2;
            newHead.next = mergeTwoListsIterative(l1, l2.next);
        }
        return newHead;
    }

    public static void main(String args[]) {
        MergeTwoSortedLinkedList merger = new MergeTwoSortedLinkedList();
        LinkList linkList = new LinkList();
        Node head1 = null;
        head1 = LinkList.addNode(1, head1);
        head1 = LinkList.addNode(3, head1);
        head1 = LinkList.addNode(9, head1);

        Node head2 = null;
        head2 = LinkList.addNode(2, head2);
        head2 = LinkList.addNode(5, head2);
        head2 = LinkList.addNode(8, head2);

        Node head3 = merger.mergeTwoListsRecursive(head1, head2);
        LinkList.printList(head3);
    }
}
