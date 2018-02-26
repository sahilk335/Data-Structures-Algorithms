package LinkedList;

import java.util.HashMap;

public class LRUCacheImplementation {
    /*
     *@Author :Sahil
     * Date : 26 Feb 2018
     *
     * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following
     * operations: get and put.
     *
     * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
     * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
     * it should invalidate the least recently used item before inserting a new item.
     *
     * LRUCache cache = new LRUCache( 2  capacity );
     *
     * cache.put(1,1);
     * cache.put(2,2);
     * cache.get(1);       // returns 1
     * cache.put(3,3);    // evicts key 2
     * cache.get(2);       // returns -1 (not found)
     * cache.put(4,4);    // evicts key 1
     * cache.get(1);       // returns -1 (not found)
     * cache.get(3);       // returns 3
     * cache.get(4);       // returns 4
     *
     * Solution :
     *
     * 1. We use Hashmap (which acts as a Queue of the Most Recently Used Keys)
     * 2. Hasmap contains key -> and its Value is Double LinkedList node
     * 3. Each Double Linked List node has key,value  and prev,Next pointer
     * 4. When Get(Key) is called -> move the Node with given to head if it is present in Map and return
     * its value else return -1
     * 5. When set(key) is called 2 cases arises ->
     *      5.1 Key is already present in the map -> in that case -> remove the position of key and move it to head
     *      5.2 Key is not present in the map then two more cases arises ->
     *              5.2.1 If map size is >= capacity ->then remove the end node and create newNode & set it as head node
     *              5.2.2 if map size < capapcity -> then create a new node and set it as sethead Node
     *
     * 6. Note : - Maintain Global head and End node of the double linked list
     *
     * Refereces :
     * https://leetcode.com/problems/lru-cache/description/
     * https://www.geeksforgeeks.org/lru-cache-implementation/
     * http://androidsrc.net/lru-cache-java-implementation/
     * http://www.learn4master.com/algorithms/leetcode-lru-cache-solution-in-java
     */


    //Create Global Values
    Node head = null;
    Node end = null;
    int capacity;
    HashMap<Integer, Node> map;


    //Constructor to initialize the global values;
    LRUCacheImplementation(int capacity) {
        this.capacity = capacity;
        map = new HashMap<Integer, Node>();
    }


    public void setHead(Node node) {
        //3 Cases ---> 1. head null 2. head not null 3.end node null that is it is first node we are adding in map
        node.next = head;
        node.prev = null;
        if (head != null) {
            head.prev = node;
        }
        head = node;
        if (end == null) {
            end = head;
        }
    }

    public void remove(Node node) {
        /*
        Case 1 : If node is head node
        Case 2: If node is end node
        Case 3: If node is middle node
         */

        //Case 1
        if (node.prev == null) {
            head = node.next;
        } else {
            node.prev.next = node.next;

        }

        //Case 2
        if (node.next == null) {
            end = node.prev.next;
        } else {
            node.next.prev = node.prev;
        }
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            remove(node);
            setHead(node);
            return node.data;
        }
        return -1;
    }

    public void set(int key, int data) {
        if (map.containsKey(key)) {
            Node oldNode = map.get(key);
            oldNode.data = data;     //Update the data, it may be key is same but data is different
            remove(oldNode);
            setHead(oldNode);
        } else {
            Node newNode = new Node(key, data);
            if (map.size() >= capacity) {
                map.remove(end.key);//remove end node as memory is exceeding the limit
                remove(end);
                setHead(newNode);
                map.put(key, newNode);
            } else {
                setHead(newNode);
                map.put(key, newNode);
            }
        }
    }


    public void printLRUCache() {
        Node node=head;
        while(head!=null){
            System.out.println(head.data);
            head=head.next;
        }
    }

    public static void main(String args[]) {
        LRUCacheImplementation lruCacheImplementation = new LRUCacheImplementation(4);
        lruCacheImplementation.set(1, 1);
        lruCacheImplementation.set(2, 2);
        lruCacheImplementation.get(1);       // returns 1
        lruCacheImplementation.set(3, 3);    // evicts key 2
        lruCacheImplementation.get(2);       // returns -1 (not found)
        lruCacheImplementation.set(4, 4);    // evicts key 1
        //lruCacheImplementation.printLRUCache();
        lruCacheImplementation.get(1);       // returns -1 (not found)
        lruCacheImplementation.get(3);       // returns 3
        lruCacheImplementation.get(4);
        lruCacheImplementation.printLRUCache();
    }
}
