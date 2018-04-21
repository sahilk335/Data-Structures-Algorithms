package LinkedList;

public class SortInGroups {
    /*
     *@Author : Sahil
     * Date : 21 Apr 2018
     *
     * Given a link list and k, sort the link list in groups of k
     *
     * Example
     *
     * List :
     * 2->4->1->5->3->9->1->0->3
     * k :3
     *
     * Output : sort every k groups
     * 1->2->4 ----> 3->5->9---->0->1->3
     *
     *
     * Asked in Microsoft HackerRank Question
     */

    public Node sortGroupList(Node head, int k) {
        Node dummyHead = new Node(0);
        dummyHead.next = head;

        MergeSortLinkedList sorted = new MergeSortLinkedList();

        Node begin = dummyHead.next;

        Node temp;

        int i = 0;
        int firstTime = 0;
        Node prevEnd = null;

        while (head != null) {
            i++;

            if (i % k == 0) {
                firstTime++;
                temp = head.next;
                head.next = null;
                begin = sorted.sortList(begin);

                if (firstTime == 1)
                    dummyHead.next = begin;
                else {
                    prevEnd.next = begin;
                }

                Node end = endNode(begin);
                end.next = temp;
                begin = temp;
                head = temp;
                prevEnd = end;
            } else {
                head = head.next;
            }
        }
        return dummyHead.next;
    }

    public Node endNode(Node head) {
        Node dummy = head;
        while (dummy.next != null) {
            dummy = dummy.next;
        }
        return dummy;
    }

    public static void main(String args[]) {
        SortInGroups sortLists = new SortInGroups();
        Node head = null;
        LinkList linkList = new LinkList();
        head = LinkList.addNode(2, head);
        head = LinkList.addNode(4, head);
        head = LinkList.addNode(1, head);
        head = LinkList.addNode(5, head);
        head = LinkList.addNode(3, head);
        head = LinkList.addNode(9, head);
        head = LinkList.addNode(1, head);
        head = LinkList.addNode(0, head);
        head = LinkList.addNode(3, head);
        head = sortLists.sortGroupList(head, 3);
        LinkList.printList(head);
    }
}
