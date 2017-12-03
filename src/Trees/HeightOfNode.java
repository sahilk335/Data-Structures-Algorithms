package Trees;

public class HeightOfNode {

	public static int height(Node root) {
		if (root == null) {
			return 0;
		}
		int height = 1 + Math.max(height(root.left), height(root.right));
		return height;
	}

	public static void main(String args[]) {
		BinaryTree binaryTree = new BinaryTree();
		binaryTree.makeTree();
		HeightOfNode heightOfNode = new HeightOfNode();
		System.out.print(heightOfNode.height(binaryTree.root));
	}
}
