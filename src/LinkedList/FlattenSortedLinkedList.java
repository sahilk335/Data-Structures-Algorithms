package LinkedList;

public class FlattenSortedLinkedList {
    /*
     *@Author : Sahil
     * Date : 06 Apr 2018
     *
     * Given a linked list where every node represents a linked list and contains two pointers of its type:
     * (i) Pointer to next node in the main list (we call it ‘right’ pointer in below code)
     * (ii) Pointer to a linked list where this node is head (we call it ‘down’ pointer in below code).
     * 5 -> 10 -> 19 -> 28
     *  |    |     |     |
     * V    V     V     V
     * 7    20    22    35
     * |          |     |
     * V          V     V
     * 8          50    40
     * |                |
     * V                V
     * 30               45.
     *
     *
     * Write a function flatten() to flatten the lists into a single linked list.
     * The flattened linked list should also be sorted. For example, for the above input list,
     * output list should be 5->7->8->10->19->20->22->28->30->35->40->45->50.
     *
     * Solution :
     *
     * 1.Create a new link list head and copy first down sorted linked list in it
     * 2.Now move to next pointer of Current list and appy operation sorted merge on Resultant List (Always Sorted)
     * 3.Repeat step 2 till end of the Current list
     *
     */


    public Node flatterSortedList(Node head) {

        MergeTwoSortedLinkedList merger = new MergeTwoSortedLinkedList();


        Node result = new Node(0);
        Node newhead = result;
        Node tempHead = head;
        while (tempHead != null) {
            newhead.next = new Node(tempHead.data);
            tempHead = tempHead.down;
            newhead = newhead.next;
        }


        //New Head stores the downLink List for first node
        newhead = result.next;
        while (head != null && head.next != null) {
            head = head.next;
            //System.out.print("Merging : "+head.data+","+newhead.data+"\n");
            newhead = mergeTwoListsIterative(head, newhead);
        }

        return newhead;
    }


    //Modified merging of two list when Node l1 is traversed down and Node l2 is traversed in right
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
                newHead.next = new Node(l1.data);
                l1 = l1.down;
            } else {
                newHead.next = new Node(l2.data);
                l2 = l2.next;
            }
            newHead = newHead.next;
        }
        if (l1 != null) {
            while (l1 != null) {
                newHead.next = new Node(l1.data);
                newHead = newHead.next;
                l1 = l1.down;
            }
        } else {
            while (l2 != null) {
                newHead.next = new Node(l2.data);
                newHead = newHead.next;
                l2 = l2.next;
            }
        }
        return result.next;
    }


    public static void main(String args[]) {
        FlattenSortedLinkedList flatten = new FlattenSortedLinkedList();
        LinkList linkList = new LinkList();

        /* Let us create the following linked list
            5 -> 10 -> 19 -> 28
            |    |     |     |
            V    V     V     V
            7    20    22    35
            |          |     |
            V          V     V
            8          50    40
            |                |
            V                V
            30               45
        */

        Node head1 = null;
        head1 = LinkList.addNode(5, head1);
        head1.down = new Node(7);
        head1.down.down = new Node(8);
        head1.down.down.down = new Node(30);
        head1 = LinkList.addNode(10, head1);
        head1.next.down = new Node(20);
        head1 = LinkList.addNode(19, head1);
        head1.next.next.down = new Node(22);
        head1.next.next.down.down = new Node(50);
        head1 = LinkList.addNode(28, head1);
        head1.next.next.next.down = new Node(35);
        head1.next.next.next.down.down = new Node(40);
        head1.next.next.next.down.down.down = new Node(45);

        Node answer = flatten.flatterSortedList(head1);
        LinkList.printList(answer);

    }
}
