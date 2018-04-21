package LinkedList;

public class MergeSortLinkedList {
    /*
     *@Author : Sahil
     *Date : 21 Apr 2018
     *
     * Merge sort a linked list
     *
     */

    public Node sortList(Node head) {
        if (head == null || head.next == null)
            return head;

        // step 1. cut the list to two halves
        Node prev = null, slow = head, fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        // step 2. sort each half
        Node l1 = sortList(head);
        Node l2 = sortList(slow);

        // step 3. merge l1 and l2
        return merge(l1, l2);
    }

    Node merge(Node l1, Node l2) {
        Node l = new Node(0);
        Node newList = l;

        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                newList.next = l1;
                l1 = l1.next;
            } else {
                newList.next = l2;
                l2 = l2.next;
            }
            newList = newList.next;
        }

        if (l1 != null)
            newList.next = l1;

        if (l2 != null)
            newList.next = l2;

        return l.next;
    }


    public static void main(String args[]) {

        MergeSortLinkedList sortList = new MergeSortLinkedList();
        Node head = null;
        LinkList linkList = new LinkList();
        head = LinkList.addNode(8, head);
        head = LinkList.addNode(3, head);
        head = LinkList.addNode(2, head);
        head = LinkList.addNode(5, head);
        head = LinkList.addNode(4, head);
        head = LinkList.addNode(7, head);
        head = LinkList.addNode(6, head);
        head = LinkList.addNode(1, head);
        head = sortList.sortList(head);
        LinkList.printList(head);
    }
}
