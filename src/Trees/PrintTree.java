package Trees;

public class PrintTree {

	public void preorder(Node node) {

		if (node == null) {
			return;
		}

		System.out.print(node.data + " ");
		preorder(node.left);
		preorder(node.right);

	}

	public void inorder(Node node) {

		if (node == null) {
			return;
		}
		inorder(node.left);
		System.out.print(node.data + " ");
		inorder(node.right);

	}

	public void postorder(Node node) {

		if (node == null) {
			return;
		}

		postorder(node.left);
		postorder(node.right);
		System.out.print(node.data + " ");

	}

	public static void main(String args[]) {

		BinaryTree tree = new BinaryTree();
		tree.makeTree();
		PrintTree pt = new PrintTree();
		pt.inorder(tree.root);

	}

}
