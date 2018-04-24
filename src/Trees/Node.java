package Trees;

public class Node {
	int data;
	Node left;
	Node right;
	Node next;

	Node() {
		this.data = 0;
		this.left = null;
		this.right = null;
		this.next = null;
	}

	Node(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
		this.next = null;
	}
}
