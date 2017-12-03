package Trees;

import java.util.Stack;

public class FindElementPath {

	public static Stack<Integer> st;
	static boolean b;

	public void searchNode(Node node, int key) {
		if (node == null || b == true) {
			return;
		}

		st.push(node.data);

		if (node.data == key) {
			b = true;
			return;
		}

		searchNode(node.left, key);
		searchNode(node.right, key);

		if (b == false) {
			st.pop();
		}

	}

	public static void main(String args[]) {
		FindElementPath fp = new FindElementPath();
		b = false;
		st = new Stack<Integer>();

		BinaryTree tree = new BinaryTree();
		tree.makeTree();
		fp.searchNode(tree.root, 9);
		System.out.println(st);

	}

}
