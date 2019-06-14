package Trees;

public class RightSibling {
    /*
     *@Author : Sahil Khurana
     * Date : 14 June 2019
     * Given a binary tree with parent pointers, find the right sibling of a given node(pointer to the node will be given), if it doesn’t exist return null. Do it in O(1) space and O(n) time?
     *
     * Examples:
     *
     *              1
     *             / \
     *            2   3
     *           /  \  \
     *          4    6  5
     *         /      \  \
     *        7        9  8
     *        /         \
     *       10         12
     * Input : Given above tree with parent pointer and node 10
     * Output : 12
     *
     * Solution :
     * 1. find out first right child of nearest ancestor
     *      and ignoring 2 conditions
     *          a) node should neither bethe current node
     *         b) nor parent of current node
     *2. keep track of level in those while going up. then, iterate through that node first left child, if left is not
     * there then, right child and if level becomes 0, then, this is the next right sibling of the given node.
     */
    Node findRightSibling(Node node, int level) {
        if (node == null || node.parent == null)
            return null;

        // GET Parent pointer whose right child is not
        // a parent or itself of this node. There might
        // be case when parent has no right child, but,
        // current node is left child of the parent
        // (second condition is for that).
        while (node.parent.right == node || (node.parent.right == null && node.parent.left == node)) {

            if (node.parent == null)
                return null;

            node = node.parent;
            level--;
        }

        // Move to the required child, where right sibling
        // can be present
        node = node.parent.right;

        // find right sibling in the given subtree(from current
        // node), when level will be 0
        while (level < 0) {
            // Iterate through subtree
            if (node.left != null)
                node = node.left;
            else if (node.right != null)
                node = node.right;
            else
                // if no child are there, we cannot have right
                // sibling in this path
                break;

            level++;
        }

        if (level == 0)
            return node;

        // This is the case when we reach 9 node in the tree,
        // where we need to again recursively find the right
        // sibling
        return findRightSibling(node, level);
    }
}
