package LinkedList;

import static LinkedList.LinkList.printList;

public class AddTwoNumbers {

    /*
     *@Author : Sahil
     * Date : 6 Feb 2018
     *
     *
     * References : https://www.geeksforgeeks.org/add-two-numbers-represented-by-linked-lists/
     *
     * Example
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     * Explanation: 342 + 465 = 807.
     *
     *
     *Solution:
     * Keep on adding two digits from one's Place
     *
     * Carry can always be 0 or 1
     *
     * If last place is a new node check sum of last's place and add 1
     *
     */

    public Node addTwoNumbers(Node head1, Node head2) {
        Node list1 = head1;
        Node list2 = head2;
        Node headSolution = new Node(0);
        Node solution = headSolution;
        int sum = 0;
        while (head1 != null && head2 != null) {
            // sum is always two digit, so it will contains carry 1 or 0 always
            sum = sum / 10;
            if (head1 != null) {
                sum = sum + head1.data;
                head1 = head1.next;
            }
            if (head2 != null) {
                sum = sum + head2.data;
                head2 = head2.next;
            }
            //Since can be maximum 2 digit so mod 10 will always contains one's place
            solution.next = new Node(sum % 10);
            solution = solution.next;
        }

        //If after sum of last place is of 2 digit,that means carry is 1
        if (sum / 10 == 1) {
            solution.next = new Node(1);
        }

        //returns head of the new linked list
        return headSolution.next;
    }

    public static void main(String args[]) {
        LinkList linkList1 = new LinkList();
        Node head1 = null;
        head1 = linkList1.addNode(9, head1);
        head1 = linkList1.addNode(2, head1);
        head1 = linkList1.addNode(3, head1);
        LinkList linkList2 = new LinkList();
        Node head2 = null;
        head2 = linkList2.addNode(9, head2);
        head2 = linkList2.addNode(9, head2);
        head2 = linkList2.addNode(9, head2);

        AddTwoNumbers a2N = new AddTwoNumbers();
        Node solHead = a2N.addTwoNumbers(head1, head2);
        printList(solHead);
    }
}
