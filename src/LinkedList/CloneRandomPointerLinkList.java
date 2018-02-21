package LinkedList;

public class CloneRandomPointerLinkList {
    /*
     *@Author : Sahil
     * Date : 20 Feb 2018
     *
     *  References :
     *
     *  https://leetcode.com/problems/copy-list-with-random-pointer/description/
     *
     * Solution :
     *
     * Check Assets folder for explaintaion
     *
     * Step 1 -> Iterate the original list and duplicate each node. The duplicate
     * of each node follows its original immediately.
     *
     * Step 2 -> Iterate the new list and assign the random pointer for each
     * duplicated node.
     *
     * Step 3 -> Restore the original list and extract the duplicated nodes.
     *
     */


    public Node copyRandomList(Node head) {
        if (head == null)
            return head;

        Node copyHead=head;

        //Step 1
        while(copyHead!=null){
            Node next=copyHead.next;
            copyHead.next=new Node(copyHead.data);
            copyHead.next.next=next;
            copyHead=next;
        }

        //Step 2
        copyHead=head;
        while(copyHead!=null){
            if(copyHead.random!=null){
                copyHead.next.random=copyHead.random;  //copy ka Random=Original ka Random
            }
            //Take 2 steps in each iteration , since it is cloned double list
            copyHead=copyHead.next.next;
        }

        //Step 3
        Node original=head;
        copyHead=head.next;
        Node copy=copyHead;

        while(copy.next!=null){
            original.next=original.next.next;
            original=original.next;

            copy.next=copy.next.next;
            copy=copy.next;
        }

        return copyHead;
    }

    public static void main(String args[]) {
        LinkList linkList = new LinkList();
        Node head = null;
        head = linkList.addNode(1, head);
        head = linkList.addNode(2, head);
        head = linkList.addNode(3, head);
        head = linkList.addNode(4, head);
        linkList.connectRandomPointer(head, head.next.next); //1->3
        linkList.connectRandomPointer(head.next, head.next.next.next); //2->4
        linkList.connectRandomPointer(head.next.next, head.next);//3->2
        linkList.connectRandomPointer(head.next.next.next, head.next.next);//4->3

        CloneRandomPointerLinkList cloneRandomPointerLinkList= new CloneRandomPointerLinkList();
        Node copyHead = cloneRandomPointerLinkList.copyRandomList(head);
        linkList.printListRandomPointers(copyHead);

    }
}
