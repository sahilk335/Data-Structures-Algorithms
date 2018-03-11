package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryMinHeap<T> {
    /*
     *@Author :Sahil
     * Date : 8 March 2018
     *
     * Implement Binary heap with HashMap
     *
     * Data structure to support following operations
     * extracMin - O(logn)
     * addToHeap - O(logn)
     * containsKey - O(1)
     * decreaseKey - O(logn)
     * getKeyWeight - O(1)
     */

    //Map of key and its position in the ArrayList
    Map<T, Integer> nodesPositionMap = new HashMap<>();
    List<Node> nodesHeap = new ArrayList<>();

    class Node {
        T key;
        int weight;

        Node(T key, int weight) {
            this.key = key;
            this.weight = weight;
        }
    }

    public void add(int weight, T key) {
        Node newNode = new Node(key, weight);
        nodesHeap.add(newNode);
        int currIdx = nodesHeap.size() - 1;
        int parentIdx = (currIdx - 1) / 2;
        nodesPositionMap.put(key, currIdx);


        while (parentIdx >= 0) {
            if (nodesHeap.get(parentIdx).weight > nodesHeap.get(currIdx).weight) {

                swap(nodesHeap.get(parentIdx), nodesHeap.get(currIdx));

                updateMapPosition(nodesHeap.get(parentIdx).key, nodesHeap.get(currIdx).key, parentIdx, currIdx);

                currIdx = parentIdx;

                parentIdx = (currIdx - 1) / 2;
            } else {
                break;
            }
        }
    }

    //swaps nodes inside the heap
    public void swap(Node n1, Node n2) {
        T tempKey = n1.key;
        int tempweight = n1.weight;

        n1.weight = n2.weight;
        n1.key = n2.key;

        n2.key = tempKey;
        n2.weight = tempweight;
    }

    //updates position in map for given keys
    public void updateMapPosition(T key1, T key2, int pos1, int pos2) {
        nodesPositionMap.remove(key1);
        nodesPositionMap.remove(key2);
        nodesPositionMap.put(key1, pos1);
        nodesPositionMap.put(key2, pos2);
    }

    public void printHeap() {
        for (Node node : nodesHeap) {
            System.out.println(node.key + "->" + node.weight + " ");
        }
    }

    //get minimum value without extracting lowest value from heap
    public T min() {
        return nodesHeap.get(0).key;
    }

    //Checks if heap is empty
    public boolean empty() {
        return nodesHeap.size() == 0;
    }

    //gets the weight of the given key by looking into the map
    public Integer getWeight(T key) {
        Integer position = nodesPositionMap.get(key);
        if (position == null)
            return null;
        else
            return nodesHeap.get(position).weight;
    }


    //check if given nodes exists in the heap or not, by looking into hash map
    public boolean containsData(T key) {
        return nodesPositionMap.containsKey(key);
    }

    //Extract the minimum value from heap, replace it with the last node and heapifies it
    public Node extractMinNode() {
        int size = nodesHeap.size() - 1;        //1 element deleted so, size -1
        Node minNode = new Node(nodesHeap.get(0).key, nodesHeap.get(0).weight); //store in minNode and return it in end

        //Apply last node value of heap to 0 element of heap node
        nodesHeap.get(0).weight = nodesHeap.get(size).weight;
        nodesHeap.get(0).key = nodesHeap.get(size).key;

        //remove its value from node position map
        nodesPositionMap.remove(minNode.key);
        nodesPositionMap.remove(nodesHeap.get(size));//remove last node from heap's position because it is gone to 0 now
        nodesPositionMap.put(nodesHeap.get(0).key, 0);
        nodesHeap.remove(size);

        //Apply heapify down now from root node, as new node is not culprit now !
        int currIdx = 0;
        while (true) {
            int left = 2 * currIdx + 1;
            int right = 2 * currIdx + 2;

            //if left or right index is out of bound, i.e. more than size then break
            if (left >= size)
                break;

            //If right is exceeding the limit.i.e more than
            if (right >= size)
                right = left;

            int smallerIdx = nodesHeap.get(left).weight <= nodesHeap.get(right).weight ? left : right;

            //Check where there is problem in heap because rest will remain as it was already a heap
            if (nodesHeap.get(currIdx).weight > nodesHeap.get(smallerIdx).weight) {
                swap(nodesHeap.get(currIdx), nodesHeap.get(smallerIdx));
                updateMapPosition(nodesHeap.get(currIdx).key, nodesHeap.get(currIdx).key,
                        currIdx, smallerIdx);
                currIdx = smallerIdx;
            } else {  //If at any time get here, stop it, because rest of it is already a heap
                break;
            }
        }
        return minNode;
    }

    //Decrease the weight of given key to newWeight

    public void decrease(int newWeight, T key) {
        //get position of given key from heap using heap in O(1) operation
        Integer pos = nodesPositionMap.get(key);
        //change its weight to new Weight
        nodesHeap.get(pos).weight = newWeight;
        int parent = (pos - 1) / 2;

        //apply heap Up ,because key is decrased, so its position MIGHT go up in a tree

        while (parent >= 0) {
            if (nodesHeap.get(parent).weight > nodesHeap.get(pos).weight) {
                swap(nodesHeap.get(parent), nodesHeap.get(pos));
                updateMapPosition(nodesHeap.get(parent).key, nodesHeap.get(pos).key, parent, pos);
                pos = parent;
                parent = (pos - 1) / 2;
            } else {
                break;
            }
        }
    }

    /**
     * Extract min value key from the heap
     */
    public T extractMin() {
        Node node = extractMinNode();
        return node.key;
    }

    public static void main(String args[]) {
        BinaryMinHeap binaryMinHeap = new BinaryMinHeap();

        binaryMinHeap.add(0, "Khurana");
        binaryMinHeap.add(5, "E");
        binaryMinHeap.add(3, "F");
/*        binaryMinHeap.add(2, "G");
        binaryMinHeap.add(1, "H");
        binaryMinHeap.add(9, "A");
        binaryMinHeap.add(8, "B");
        binaryMinHeap.add(7, "C");
        binaryMinHeap.add(6, "D");*/
        binaryMinHeap.decrease(2, "E");

        //System.out.println(binaryMinHeap.extractMin());
        System.out.println(binaryMinHeap.getWeight("E"));
        binaryMinHeap.printHeap();
    }
}