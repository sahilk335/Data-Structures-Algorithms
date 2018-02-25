package LinkedList;

public class DoubleLinkList {
    /*
     *@Author : Sahil
     * Date : 25 Feb 2018
     *
     */

    //Add Node at front of Double Linked List
    public Node addNode(Node head, int data) {
        if (head == null) {
            head = new Node(data);
            return head;
        }
        Node newNode = new Node(data);
        Node current = head;
        while (current != null) {
            current = current.next;
        }
        current.next = newNode;
        newNode.prev = current;
        return head;
    }

    //Add node at front of Double Linked List
    public Node addAtFront(Node head, int data) {
        Node newNode = new Node(data);
        if (head == null) {
            return newNode;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
        return head;
    }

    //Print Double Linked list
    public void print(Node head){
        Node temp=head;
        while(temp!=null){
            System.out.print(temp.data);
            temp=temp.next;
        }
    }
    public void printFrontBack(Node head){
        Node prev = null;
        while(head != null){
            System.out.print(head.data + " ");
            prev = head;
            head = head.next;
        }
        System.out.println();
        while(prev != null){
            System.out.print(prev.data + " ");
            prev = prev.prev;
        }
    }
    public static void main(String args[]) {
        DoubleLinkList doubleLinkList = new DoubleLinkList();
        Node head = null;
        head = doubleLinkList.addNode(head, 1);
        head = doubleLinkList.addNode(head, 2);
        head = doubleLinkList.addNode(head, 3);
        head = doubleLinkList.addNode(head, 4);
        head = doubleLinkList.addNode(head, 5);
    }
}
