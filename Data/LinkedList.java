package Data;

public class LinkedList {
    
    Node head;
    Node tail;

    public LinkedList() {
        head = null;
        tail = null;
    }
 
    public void insert_at_front(Node node) {
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
      public void move(int old_pos, int New_pos) {

        if (head == null || old_pos == New_pos)
            return;
        Node current = head;
        int index = 0;
        while (current != null && index < old_pos) {
            current = current.next;
            index++;
        }
        if (current == null)
            return;
        if (current.prev != null)
            current.prev.next = current.next;
        else
            head = current.next;
        if (current.next != null)
            current.next.prev = current.prev;
        else
            tail = current.prev;
        if (New_pos == 0) {
            current.next = head;
            current.prev = null;
            if (head != null)
                head.prev = current;
            head = current;
            if (tail == null)
                tail = current;
            return;
        }
        Node temp = head;
        index = 0;
        while (temp != null && index < New_pos - 1) {
            temp = temp.next;
            index++;
        }
        if (temp == null)
            return;
        current.next = temp.next;
        current.prev = temp;
        if (temp.next != null)
            temp.next.prev = current;
        else
            tail = current;
        temp.next = current;
    }

    public void remove(int value) {
    if (head == null)
        return;
    Node current = head;
    while (current != null && current.value != value) {
        current = current.next;
    }
    if (current == null)
        return;
    if (current == head) {
        head = head.next;
        if (head != null)
            head.prev = null;
        else
            tail = null;
    }
    else if (current == tail) {
        tail = tail.prev;
        tail.next = null;
    }
    else {
        current.prev.next = current.next;
        current.next.prev = current.prev;
    }
}
public void removeTail() {

    if (tail == null)
        return;
    if (head == tail) {
        head = tail = null;
    }
    else {
        tail = tail.prev;
        tail.next = null;
    }
}   
public void display() {

    if (head == null) {
        System.out.println("List is empty");
        return;
    }

    Node current = head;

    while (current != null) {
        System.out.print(current.value + " ");
        current = current.next;
    }
    System.out.println();
}
}
