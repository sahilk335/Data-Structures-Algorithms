package Trees;


public class AVLTreeInsertion {
    /*
     *@Author : Sahil
     * Date : 30 March 2019
     *
     * AVL tree is self balancing binary search tree with difference between heights of left and right subtrees can not
     * be more than one for all nodes
     *
     * Height of AVL tree is always O(Log(N)), whereas in skewed BST it can be O (N)
     * T1, T2 and T3 are subtrees of the tree
     * rooted with y (on the left side) or x (on
     * the right side)
     *      y                               x
     *     / \     Right Rotation          /  \
     *    x   T3   - - - - - - - >        T1   y
     *   / \       < - - - - - - -            / \
     *  T1  T2     Left Rotation            T2  T3
     * datas in both of the above trees follow the
     * following order
     *  datas(T1) < data(x) < datas(T2) < data(y) < datas(T3)
     * So BST property is not violated anywhere.
     *
     * Steps to follow for insertion
     * Let the newly inserted node be w
     * 1) Perform standard BST insert for w.
     * 2) Starting from w, travel up and find the first unbalanced node. Let z be the first unbalanced node, y be the
     * child of z that comes on the path from w to z and x be the grandchild of z that comes on the path from w to z.
     * 3) Re-balance the tree by performing appropriate rotations on the subtree rooted with z. There can be 4 possible
     * cases that needs to be handled as x, y and z can be arranged in 4 ways. Following are the possible 4 arrangements:
     * a) y is left child of z and x is left child of y (Left Left Case)
     * b) y is left child of z and x is right child of y (Left Right Case)
     * c) y is right child of z and x is right child of y (Right Right Case)
     * d) y is right child of z and x is left child of y (Right Left Case)
     */

    Node Root;


    // A utility function to get the height of the tree
    int height(Node root) {
        if (root == null)
            return 0;

        return root.height;
    }

    private int setHeight(Node root) {
        if (root == null) {
            return 0;
        }

        int leftChildHeight = root.left != null ? root.left.height : 0;
        int rightChildHeight = root.right != null ? root.right.height : 0;
        return 1 + Math.max(leftChildHeight, rightChildHeight);
    }


    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    private Node rightRotate(Node z) {
        Node y = z.left;
        z.left = y.right;
        y.right = z;

        z.height = setHeight(z);
        y.height = setHeight(y);

        return y;
    }

    private Node leftRightRotate(Node z) {
        z.left = leftRotate(z.left);
        return rightRotate(z);
    }

    private Node rightLeftRotate(Node z) {
        z.right = rightRotate(z.right);
        return leftRotate(z);
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    private Node leftRotate(Node z) {
        Node y = z.right;
        z.right = y.left;
        y.left = z;

        z.height = setHeight(z);
        y.height = setHeight(y);

        return y;
    }

    // Get Balance factor of node N
    int getBalance(Node root) {
        if (root == null)
            return 0;

        return height(root.left) - height(root.right);
    }

    Node insert(Node root, int data) {

        /* 1.  Perform the normal BST insertion */
        if (root == null)
            return (new Node(data));

        if (data < root.data)
            root.left = insert(root.left, data);
        else if (data > root.data)
            root.right = insert(root.right, data);
        else // Duplicate datas not allowed
            return root;

        /* 2. Update height of this ancestor node */
        root.height = setHeight(root);

        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there
        // are 4 cases
        //
        // Left Left Case -> Right Rotate
        if (balance > 1 && data < root.left.data)
            return rightRotate(root);


        // Left Right Case ->Left Right Rotate
        if (balance > 1 && data > root.left.data) {
            return leftRightRotate(root);
        }

        // Right Right Case -> Left Rotate
        if (balance < -1 && data > root.right.data)
            return leftRotate(root);

        // Right Left Case -> Right Left Rotate
        if (balance < -1 && data < root.right.data) {
            return rightLeftRotate(root);
        }

        /* return the (unchanged) node pointer */
        return root;
    }

    public void inorder(Node root) {

        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.println(root.data + " -> " + getBalance(root));
        inorder(root.right);

    }


    public static void main(String[] args) {
        AVLTreeInsertion tree = new AVLTreeInsertion();
        tree.Root = tree.insert(tree.Root, 10);

        tree.Root = tree.insert(tree.Root, 20);

        tree.Root = tree.insert(tree.Root, 30);

        tree.Root = tree.insert(tree.Root, 40);

        tree.Root = tree.insert(tree.Root, 50);

        tree.Root = tree.insert(tree.Root, 25);


        tree.inorder(tree.Root);



    }
}