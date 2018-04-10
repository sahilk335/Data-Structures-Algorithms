package LinkedList;

public class Node {
    Integer data;
    Node next;
    Node random;
    Node prev;
    Node down;
    Integer key;

    Node(int data){
        this.data=data;
        this.next=null;
        this.random=null;
    }

    Node(Integer key,Integer data){
        this.key=key;
        this.data=data;
    }

}
