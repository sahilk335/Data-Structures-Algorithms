package LinkedList;

public class Node {
    int data;
    Node next;
    Node random;
    Node prev;
    int key;

    Node(int data){
        this.data=data;
        this.next=null;
        this.random=null;
    }

    Node(int key,int data){
        this.key=key;
        this.data=data;
    }
}
