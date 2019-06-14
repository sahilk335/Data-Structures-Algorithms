package LinkedList;

public class SwapAdjacentNodes {
    /*
     *@Author : Sahil Khurana
     * Date : 14 June 2019
     *
     * Given a linked list, swap every two adjacent nodes and return its head.
     *
     * You may not modify the values in the list's nodes, only nodes itself may be changed.
     * Example:
     * Given 1->2->3->4, you should return the list as 2->1->4->3.
     *
     * References :
     * https://leetcode.com/problems/swap-nodes-in-pairs/
     */

    public Node swapPairs(Node head) {
        Node dummy = new Node(0);
        dummy.next = head;
        Node curr = dummy;
        while (curr.next != null && curr.next.next != null) {
            Node first = curr.next;
            Node second = curr.next.next;
            first.next = second.next;
            second.next = first;
            curr.next = second;
            curr = first;
        }
        return dummy.next;
    }
}
