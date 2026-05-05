package Data;

public class Node {
    int key;
    int value;
    Node next;
    Node prv;
    public Node(int key , int value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.prv = null;
    }

}
