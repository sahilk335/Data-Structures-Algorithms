package Trees;

import java.util.List;

public class Node {
    int data;
    Node left;
    Node right;
    Node next;
    Node parent;
    int height;
    public List<Node> children;

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

    Node(int data, List<Node> _children) {
        this.data = data;
        this.children = _children;
    }
}
