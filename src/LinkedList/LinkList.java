package LinkedList;

public class LinkList {

    /*
     * @Author : Sahil
     * Date : 4 Feb 2018
     *
     * This is the util class for Linked list that contains basic linked link utility functions
     * This class will be used elsewhere in my git repository also
     */

    //add node function returns the head of the linked list after adding given node in a linked list
    public static Node addNode(int data, Node head) {

        Node temp = head;
        Node newNode = new Node(data);

        //return node itslef as if head is null, given node is head
        if (head == null) {
            return newNode;
        }

        //find list end
        while (head.next != null) {
            head = head.next;
        }

        //Assign new node to last node
        head.next = newNode;

        //Return head,as stored initially as head remains intact
        return temp;

    }

    //This method returns the head, which is a new node which we are going to add now
    public static Node addAtFront(Node node, Node head) {
        if (head == null) {
            return node;
        }
        node.next = head;
        return node;
    }

    public static Node addAtFront(int data, Node head) {
        Node newNode = new Node(data);
        //return the head -> call above function
        return addAtFront(newNode, head);
    }

    //Function to find node with given data
    public static Node find(int data, Node head) {
        while (head != null) {
            if (head.data == data) {
                return head;
            }
            head = head.next;
        }
        //if node data is present , it will never reach here,
        return null;
    }

    public static void connectRandomPointer(Node n1,Node n2){
        n1.random=n2;
    }

    //return Linked list Size
    public static int size(Node head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    /*
     *Reverse the linkedList and return the head which is the last node
     *
     *          Head
     *           |
     * Example : 1->2->3->4
     *
     * After reverse
     *                  Head
     *                   |
     *          1<-2<-3<-4
     *
     *                  4 Step Algorithm
     *          1. Save curr next in next Variable for saving it for future
     *          2. As. we have alread save next now change the next to prev
     *          3&4.For next iteration, assign prev to curr and curr to next
     */

    public static Node reverseIterative(Node head) {
        Node prev = null;
        Node curr = head;
        Node next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        //This is the last node which is now a head, so we return it
        return prev;
    }

    public static Node reverseRecursive(Node head) {
        if (head == null || head.next == null) {
            return head;
            //Note this head has to return , and this is answer so we keep on returning this head in all
            // recursive iteration, it is independent of recursion
        }

        Node Answer = reverseRecursive(head.next);   //It does not change, constant in all recursion call when returning
        head.next.next = head;
        head.next = null;
        return Answer;
    }

    //Util funciton to print LinkList
    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
    }

    public static void printListRandomPointers(Node head){
        while(head!=null){
            if(head.random!=null){
                System.out.print(head.random.data);
            }
            head=head.next;
        }
    }

/*    public static void main(String args[]) {
        LinkList linkList = new LinkList();
        Node head = null;
        head = linkList.addNode(1, head);
        head = linkList.addNode(2, head);
        head = linkList.addNode(3, head);
        head = linkList.addNode(4, head);
        head = linkList.addNode(5, head);
        head = linkList.reverseIterative(head);
        System.out.print("Size : " + linkList.size(head));
        linkList.printList(head);
    }*/

}
