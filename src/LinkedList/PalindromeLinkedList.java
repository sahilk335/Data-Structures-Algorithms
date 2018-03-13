package LinkedList;

public class PalindromeLinkedList {
    /*
     *@Author : Sahil
     * Date : 13 March 2018
     *
     * https://leetcode.com/problems/palindrome-linked-list/
     *
     * Solution :
     *
     * From mid-point to last node reverse the linked list
     * Now take two pointers, one at head , other at middle Node head
     * Start comparing data of both pointers , if at any point they are unequal then it is not palindrome else it is
     */

    public boolean isPalindrome(Node head) {
        Node slow = head;
        Node fast = head;
        int nodeCount = 1;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            nodeCount = nodeCount + 2;
            if (fast.next != null && fast.next.next == null)
                nodeCount++;
        }

        Node middleHead = slow.next;
      /* if (nodeCount % 2 == 0) {       //If node count is even or odd
           slow = slow.next;
       }*/
        //System.out.print(middleHead.data);

        middleHead = reverseIterative(middleHead);

        while (middleHead != null) {
            if (head.data == middleHead.data) {
                head = head.next;
                middleHead = middleHead.next;
            } else
                return false;
        }

        return true;
    }

    public Node reverseIterative(Node head) {
        Node prev = null;
        Node curr = head;
        Node next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        //This is the last node which is now a head, so we return it
        return prev;
    }

    public static void main(String args[]) {
        Node head = null;
        LinkList linkList = new LinkList();
        head = LinkList.addNode(1, head);
        head = LinkList.addNode(2, head);
        head = LinkList.addNode(3, head);
        head = LinkList.addNode(3, head);
        head = LinkList.addNode(2, head);
        head = LinkList.addNode(1, head);
        PalindromeLinkedList pl = new PalindromeLinkedList();
        System.out.print(pl.isPalindrome(head));

    }
}
