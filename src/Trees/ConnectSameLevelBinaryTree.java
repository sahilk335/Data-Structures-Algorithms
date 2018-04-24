package Trees;

public class ConnectSameLevelBinaryTree {
    /*
     *@Author : Sahil
     * Date : 23 Apr 2018
     *
     * Given a binary tree
     *
     * struct TreeLinkNode {
     *   TreeLinkNode *left;
     *   TreeLinkNode *right;
     *   TreeLinkNode *next;
     * }
     * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
     *
     * Initially, all next pointers are set to NULL.
     *
     * Note:
     *
     * You may only use constant extra space.
     * Recursive approach is fine, implicit stack space does not count as extra space for this problem.
     * Example:
     *
     * Given the following binary tree,
     *
     *      1
     *    /  \
     *   2    3
     *  / \    \
     * 4   5    7
     * After calling your function, the tree should look like:
     *
     *      1 -> NULL
     *    /  \
     *   2 -> 3 -> NULL
     *  / \    \
     * 4-> 5 -> 7 -> NULL
     */
    public void connect(Node root) {
        if (root == null) {
            return;
        }
        while (root != null) {
            Node tempChild = new Node(0);       //Tempchild will point to the first node of its level
            Node currChild = tempChild;               //Currchild is used to traverse nodes of its own level
            while (root != null) {
                if (root.left != null) {
                    currChild.next = root.left;
                    currChild = currChild.next;
                }
                if (root.right != null) {
                    currChild.next = root.right;
                    currChild = currChild.next;
                }
                root = root.next;
            }
            root = tempChild.next;      //Root is now shifted to the first node of the next level
        }

    }

    public void printTreeNext(Node root) {

        Node levelFirstNode = root;

        while (levelFirstNode != null) {
            Node curr = levelFirstNode;
            while (curr != null) {
                System.out.print(curr.data + " ");
                curr = curr.next;
            }
            levelFirstNode = levelFirstNode.left;
        }
    }

    public static void main(String args[]) {
        ConnectSameLevelBinaryTree completeLevel = new ConnectSameLevelBinaryTree();
          /*


	 				1
	 			/	   \
	 		   2		3
	 		  / \	     \
	         4   5        7

	     */
        BinaryTree.root = new Node(1);
        BinaryTree.root.left = new Node(2);
        BinaryTree.root.right = new Node(3);
        BinaryTree.root.left.left = new Node(4);
        BinaryTree.root.left.right = new Node(5);
        BinaryTree.root.right.right = new Node(7);

        completeLevel.connect(BinaryTree.root);
        completeLevel.printTreeNext(BinaryTree.root);

    }
}
