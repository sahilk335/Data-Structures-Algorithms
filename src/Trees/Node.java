package Trees;

public class Node {
    int data;
    Node left;
    Node right;
    Node next;
    int height;

    Node() {
        this.data = 0;
        this.left = null;
        this.right = null;
        this.next = null;
        this.height = 1;
    }

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.next = null;
        this.height = 1;
    }
}
