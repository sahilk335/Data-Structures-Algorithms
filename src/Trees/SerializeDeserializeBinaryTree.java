package Trees;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class SerializeDeserializeBinaryTree {
    /*
     *@Author : Sahil
     * Date : 24 Apr 2018
     *
     * Serialization is the process of converting a data structure or object into a sequence of bits so that
     * it can be stored in a file or memory buffer, or transmitted across a network connection link
     * to be reconstructed later in the same or another computer environment.
     *
     * Design an algorithm to serialize and deserialize a binary tree.
     * There is no restriction on how your serialization/deserialization algorithm should work.
     * You just need to ensure that a binary tree can be serialized to a string and this string can
     * be deserialized to the original tree structure.
     *
     * For example, you may serialize the following tree
     *
     *     1
     *    / \
     *   2   3
     *      / \
     *     4   5
     *
     * Note: Do not use class member/global/static variables to store states.
     * Your serialize and deserialize algorithms should be stateless.
     *
     * References :
     * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/
     * https://www.youtube.com/watch?v=jwzo6IsMAFQ&t=1083s
     */


    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null)
            return "x";
        return root.data + "," + serialize(root.left) + "," + serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {

        LinkedList<String> list = new LinkedList<String>();
        list.addAll(Arrays.asList(data.split(",")));    //Regex split will remove "," from the strings
        //asList will convert Arrays to List

        return deserial(list);
    }

    public Node deserial(LinkedList<String> q) {
        String data = q.remove(0);

        if (data.equals("x")) {
            return null;

        }

        Node root = new Node(Integer.valueOf(data));
        root.left = deserial(q);
        root.right = deserial(q);

        return root;
    }


    //Java Serialization
    public void javaSerialization(String data) {
        // Serialization
        String filename = "serializeBinaryTree.txt";
        try {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(data);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }


    //Java Deserialization
    public void javaDeserialization() {
        String data = null;

        String filename = "serializeBinaryTree.txt";
        // Deserialization
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            data = (String) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
            Node root = deserialize(data);
            PrintTree printTree = new PrintTree();
            printTree.inorder(root);

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
    }

    public static void main(String args[]) {
        SerializeDeserializeBinaryTree sd = new SerializeDeserializeBinaryTree();
        /*

	 			  1
	 			/  \
	 		   2	3
	 		  /    / \
	         6    4   5

	     */
        BinaryTree.root = new Node(1);
        BinaryTree.root.left = new Node(2);
        BinaryTree.root.left.left = new Node(6);
        BinaryTree.root.right = new Node(3);
        BinaryTree.root.right.left = new Node(4);
        BinaryTree.root.right.right = new Node(5);

        //Serialize result
        sd.javaSerialization(sd.serialize(BinaryTree.root));
        sd.javaDeserialization();

    }
}
