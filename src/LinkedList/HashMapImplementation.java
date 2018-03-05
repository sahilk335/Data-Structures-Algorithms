package LinkedList;

public class HashMapImplementation {
    /*
     *@Author : Sahil
     * Date : 5 March 2018
     *
     * Refernces :
     * https://www.geeksforgeeks.org/internal-working-of-hashmap-java/
     * http://www.javamadesoeasy.com/2015/02/hashmap-custom-implementation.html#1
     * https://discuss.leetcode.com/topic/102606/design-a-hashmap/4
     *
     * HashMap Implementation contains the :
     * 1. Add key-value pair (Node)
     * 2. Contains Key
     * 3. Remove Key
     *
     * Solution :
     * 1. Create array of Nodes called HashTable
     * 2. create hashCode of new Key-Value pair (Node) using default Java hashCode function (Bare minimum)
     * 3.In case of collision -> add a LinkedList node to same index where head is Hashtable's index (hashcode)
     *   calculated in Step 2.
     *      while putting Node there can be two cases after the hashcode has collision
     *      3.1 -> key is also same, in that case update the value of the collision Node and return from between
     *      3.2 -> there is no key collision, in that case add the new key-value pair at the end of the list
     *
     * 4. Get the key and return the corresponding value
     */

    /*
     *Hash node object is a single Entry of the hashMap that has key-value-next pointer
     *Key cannot be null,value can be
     */

    int SIZE = 2; //Initial capacity of hashtable
    Node hashTable[]; //Initial HashTable

    HashMapImplementation() {
        hashTable = new Node[SIZE];
    }

    public void put(Integer key, Integer value) {
        Node node = new Node(key, value);
        int hashCode = key.hashCode() % SIZE; //Use Default hashcode method of Java,Note thats why I make "Integer Key"
        if (hashTable[hashCode] == null) {
            hashTable[hashCode] = node;
        } else {                                      //collision occurs
            addLinkedNode(hashTable[hashCode], node); //add linklist of node to the current index
        }

    }

    public void addLinkedNode(Node head, Node curr) {
        Node prev = head;
        while (head != null) {
            if (head.key.equals(curr.key)) {//key is also same, in that case update the value of the collision Node
                head.data = curr.data;
                return;
            }
            prev = head;
            head = head.next;
        }
        prev.next = curr;       //add key-value pair at the end of the list
    }

    //Checks if key is present in the hashmap or not
    public boolean containsKey(Integer key) {
        int hashCode = key.hashCode() % SIZE;
        Node head = hashTable[hashCode];
        while (head != null) {
            if (head.key.equals(key)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    //Delete the key-value Pair from the HashTable

    public void remove(Integer key) {
        int hashCode = key.hashCode() % SIZE;
        Node head = hashTable[hashCode];
        Node dummy = new Node(0, 0); //This node is used only when the Hashed Node index contains only one Node itself
        dummy.next = head;
        Node prev = dummy;
        while (head != null) {
            if (head.key.equals(key)) {
                prev.next = head.next;
            }
            prev = head;
            head = head.next;
        }
        hashTable[hashCode] = dummy.next;
    }

    //Given the key , get the value from the HashMap
    public Integer get(Integer key) {
        int hashCode = key.hashCode() % SIZE;
        Node head = hashTable[hashCode];
        while (head != null) {
            if (head.key.equals(key)) {
                return head.data;
            }
            head = head.next;
        }
        return null;
    }

    public static void main(String args[]) {
        HashMapImplementation hashMapImplementation = new HashMapImplementation();
        hashMapImplementation.put(22, 25);
        hashMapImplementation.put(21, 22);
        hashMapImplementation.put(23, 34);
        hashMapImplementation.put(12, 13);
        hashMapImplementation.put(16, 17);
        System.out.println(hashMapImplementation.containsKey(16));
        hashMapImplementation.remove(16);
        System.out.println(hashMapImplementation.containsKey(16));
        System.out.println(hashMapImplementation.get(23));
    }
}
