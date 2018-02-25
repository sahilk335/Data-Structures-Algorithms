package LinkedList;

public class CyclePoint {
    /*
     * @Author: Sahil
     * Date : 20 Feb 2018
     *
     * 1 .Find Link List Cycle Existence
     * 2 . Link List Cycle Starting point
     *
     * References :
     * https://leetcode.com/problems/linked-list-cycle/
     *
     * Solution 1 :
     * Floyd's Algorithm
     * 1.Slow pointer takes one step
     * 2.Fast pointer takes 2 steps
     * 3.If fast and slow somewhere then it is a cycle else not
     *
     * Solution 2 :
     * Assets folder / Link List Cycle Detection Point
     * When fast and slow meet at point p, the length they have run are ‘a+2b+c’ and ‘a+b’.
     * Since the fast is 2 times faster than the slow. So a+2b+c == 2(a+b), then we get ‘a==c’.
     * So when another slow2 pointer run from head to ‘q’, at the same time, previous slow pointer will run from ‘p’ to ‘q’,
     * so they meet at the pointer ‘q’ together.
     *
     */

    public boolean isCycle(Node head) {
        if (head == null) {
            return false;
        }

        Node slow = head;
        Node fast = head; //If slow = fast Now! -> that means there are 2 nodes and they are forming cycle

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    public Node cyclePoint(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null || fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                Node slow2 = head;
                while (slow2 != slow) {
                    slow2 = slow2.next;
                    slow = slow.next;
                }
                return slow2;
            }
        }
        return null;
    }

    public static void main(String args[]) {
        CyclePoint cyclePoint = new CyclePoint();
        Node head = null;
        LinkList linkList = new LinkList();
        head = linkList.addNode(1, head);
        head = linkList.addNode(2, head);
        head = linkList.addNode(3, head);
        head = linkList.addNode(4, head);
        head = linkList.addNode(5, head);
        head = linkList.addNode(6, head);
        head = linkList.addNode(7, head);
        head = linkList.addNode(8, head);
        linkList.connectNode(head.next.next.next.next.next.next.next, head.next.next.next); //Connect 8->4
        System.out.println(cyclePoint.isCycle(head));
        if(cyclePoint.isCycle(head))
        System.out.println(cyclePoint.cyclePoint(head).data);

/*
        1->2->3->4->5->6->7->8---
                  ^              |
                  |---------------
*/

    }
}
