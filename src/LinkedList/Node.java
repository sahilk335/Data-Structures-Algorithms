package LinkedList;

public class Node {
    public Integer data;
    public Node next;
    public Node random;
    public Node prev;
    public Node down;
    public Integer key;

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
