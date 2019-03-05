package Trees;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HuffmanCoding {
    /*
     *@Author : Sahil
     * Date : 05 Mar 2019
     *
     * Steps to Create Huffman Encoding
     *
     * 1. Create min Heap of all the elements given its frequency
     * 2. Extract minimum and second minimum element and combine it to make variable, say ,sum
     * 3. mark sum left child as min, and right child as second min
     * 4. Keep on repeating step 1 to step 3 until only 1 element is  left inside the min heap (priority Queue)
     *
     * References :
     * https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
     * https://www.youtube.com/watch?v=co4_ahEDCho&t=5s
     *
     * Complexity : O(N Log N)
     *
     */

    class HuffmanNode {
        int data;
        char ch;
        HuffmanNode left;
        HuffmanNode right;

        HuffmanNode(int data, char ch) {
            this.data = data;
            this.ch = ch;
            this.left = null;
            this.right = null;
        }
    }


    public HuffmanNode huffmanEncoding(char[] charArray, int[] charFreg) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(charArray.length, Comparator.comparingInt(x -> x.data));

        for (int i = 0; i < charFreg.length; i++) {
            HuffmanNode node = new HuffmanNode(charFreg[i], charArray[i]);
            priorityQueue.add(node);
        }

        HuffmanNode sumNode = new HuffmanNode(0, '-');
        //Now Making Huffman  Tree
        while (priorityQueue.size() > 1) {
            HuffmanNode minFreNode = priorityQueue.poll();
            HuffmanNode secondMinFreNode = priorityQueue.poll();
            sumNode = new HuffmanNode(minFreNode.data + secondMinFreNode.data, '-');
            sumNode.left = minFreNode;
            sumNode.right = secondMinFreNode;
            priorityQueue.add(sumNode);
        }

        //return root node
        return sumNode;
    }

    public void decodeCompleteHuffmanMap(HuffmanNode root, String s) {
        if (root == null)
            return;
        if (root.left == null && root.right == null && root.ch != '-') {
            System.out.println(root.ch + " -> " + s);
        }
        decodeCompleteHuffmanMap(root.left, s + "0");
        decodeCompleteHuffmanMap(root.right, s + "1");
    }


    public static void main(String[] args) {
        HuffmanCoding huffmanCoding = new HuffmanCoding();
        char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f'};
        int[] charfreq = {5, 9, 12, 13, 16, 45};
        HuffmanNode root = huffmanCoding.huffmanEncoding(charArray, charfreq);
        huffmanCoding.decodeCompleteHuffmanMap(root, "");
    }
}