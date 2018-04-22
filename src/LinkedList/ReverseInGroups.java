package LinkedList;

public class ReverseInGroups {
    /*
     *@Author : Sahil
     * Date : 21 Apr 2018
     *
     * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
     *
     * k is a positive integer and is less than or equal to the length of the linked list.
     *  If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
     *
     *  Example:
     *
     * Given this linked list: 1->2->3->4->5
     *
     * For k = 2, you should return: 2->1->4->3->5
     *
     * For k = 3, you should return: 3->2->1->4->5
     *
     * Note:
     *
     * Only constant extra memory is allowed.
     * You may not alter the values in the list's nodes, only nodes itself may be changed.
     *
     */


    public Node reverseInGroups(Node head, int k) {
        if (head == null || head.next == null || k == 1)
            return head;
        Node begin;
        Node dummyHead = new Node(0);
        dummyHead.next = head;

        begin = dummyHead;


        int i = 0;

        while (head != null) {
            i++;
            if (i % k == 0) {
                //Begin is actually a Prev node of currList and head.next is one node ahead of currList
                begin = reverse(begin, head.next);
                head = begin.next;    //Assign new head to first node of next reversal list
            } else {
                head = head.next;
            }
        }
        return dummyHead.next;
    }

    public Node reverse(Node begin, Node end) {
        Node prev = begin;      //as begin is actually a prev node
        Node curr = begin.next;
        Node next = null;
        Node first = begin.next;       //we save the value of prev Node, as it will be last node after reversal which is
        // used for returning it after reversing
        while (curr != end) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        //This is the last node which is now a head, so we return it

        first.next = end;         //Since First node is now the LAST node, assign it to the next value of curr list
        begin.next = prev;        //assign prev value of curr list to HEAD of Reversed List
        return first;           //return Prev value for new List
    }

    public static void main(String args[]) {
        ReverseInGroups reverseInGroups = new ReverseInGroups();
        Node head = null;
        LinkList linkList = new LinkList();
        head = LinkList.addNode(1, head);
        head = LinkList.addNode(2, head);
        head = LinkList.addNode(3, head);
        head = LinkList.addNode(4, head);
        head = LinkList.addNode(5, head);
        head = LinkList.addNode(6, head);
        head = LinkList.addNode(7, head);
        head = LinkList.addNode(8, head);
        head = LinkList.addNode(9, head);
        head = reverseInGroups.reverseInGroups(head, 3);
        LinkList.printList(head);
    }
}
