package LinkedList;

public class MiddleElementInLinkedList {

    /* @Author : Sahil
     * Date : 7 Feb 2018
     *
     * Middle Node of Linklist
     *
     * Solution:
     * Fast pointer takes 2 steps (Starts from one step head)
     * Slow pointer rakes 1 step
     *
     * when  fast pointer is last node
     * slow pointer is the middle element
     */

    public Node middleElement(Node head) {
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String args[]) {
        LinkList linkList = new LinkList();
        Node head = null;
        head = linkList.addNode(1, head);
        head = linkList.addNode(2, head);
        head = linkList.addNode(3, head);
        head = linkList.addNode(4, head);
        head = linkList.addNode(5, head);
        MiddleElementInLinkedList mde = new MiddleElementInLinkedList();
        Node middleNode = mde.middleElement(head);
        System.out.print("Middle Element : " + middleNode.data);

    }
}
