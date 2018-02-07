package LinkedList;

public class RemoveNthNodeFromEnd {
    /*
     * @Author : Sahil
     * Date : 8 Feb 2018
     *
     *
     *  Given a linked list, remove the nth node from the end of list and return its head.
     * For example,
     *
     *  Given linked list: 1->2->3->4->5, and n = 2.
     *
     *  After removing the second node from the end, the linked list becomes 1->2->3->5.
     * References : https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
     *
     * Solution : Take two pointers
     *
     * 1 pointer initiate from Nth Node
     * 2 pointer start froom First node
     *
     * when 1pointer reached end,then 2pointer node is the node to delete
     *
     */

    //Returns head of the new linked list after deleting the node in ONE PASS
    public Node removeNthFromEnd(Node head, int n) {
        Node first = head;
        Node second = head;
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }
        //After this loop second pointer will reach the node to be deleted
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return head;
    }

    public static void main(String args[]) {
        LinkList linkList = new LinkList();
        Node head = null;
        head = linkList.addNode(1, head);
        head = linkList.addNode(2, head);
        head = linkList.addNode(3, head);
        head = linkList.addNode(4, head);
        head = linkList.addNode(5, head);
        RemoveNthNodeFromEnd remove = new RemoveNthNodeFromEnd();
        remove.removeNthFromEnd(head, 2);
        linkList.printList(head);
    }

}
