public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    public class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(Node p, T i, Node n) {
            item = i;
            next = n;
            prev = p;            
        }

        public T getHelper(int i, Node s) {
            if (this.next == s) {
                return null;
            } else if (i == 0) {
                return this.next.item;
            }
            return this.next.getHelper(i - 1, s);      
        }
    }

    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T x) {
        sentinel.next = new Node(sentinel, x, sentinel.next);
        sentinel.next.next.prev = sentinel.next;                  /* set the second node's prev to the new node */
        size += 1;
    }

    public void addLast(T x) {
        sentinel.prev = new Node(sentinel.prev, x, sentinel);
        sentinel.prev.prev.next = sentinel.prev;                  /* set the last second node's next to the new node */
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel;
        while (p.next != sentinel) {
            System.out.println(p.next.item);
            p = p.next;
        }
    }

    public T removeFirst() {
        if(isEmpty()) {
            return null;
        }
        T temp = sentinel.next.item;
        sentinel.next = sentinel.next.next;           /* set sentinel point to the second node(the new 1st node) */
        sentinel.next.prev = sentinel;                /* set new first node's prev to the sentinel */
        size -= 1;
        return temp;
    }

    public T removeLast() {
        if(isEmpty()) {
            return null;
        }
        T temp = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;           /* about the same with the removeFirst() */
        sentinel.prev.next = sentinel;                /* always remember the invariants plz */
        size -= 1;
        return temp;
    }

    public T get(int index) {
        Node p = sentinel;
        for(int i = 0; i < index; i++) {
            if (p.next == sentinel) {
                return null;
            }
            p = p.next;
        }
        if(p.next == sentinel){
            return null;
        }
        return p.next.item;
    }

   
    public T getRecursive(int index) {
        return sentinel.getHelper(index, sentinel); 
    }
    


}