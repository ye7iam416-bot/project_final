package Data;

public class LinkedList {
    
    Node head;
    Node tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    
    public void insertatfront(Node node) {
        node.prev = null;
        node.next = null;

        
        if (head == null) {
            head = node;
            tail = node;
        }
        else {
            node.next = head;  
            head.prev = node;  
            head = node;       
        }
    }
}
