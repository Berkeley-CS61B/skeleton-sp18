/** Contains the class of a Deque data structure.
 *
 *   ---- CLASS INVARIANT ----
 *   1- size is the number of elements in the Deque.
 *   2- sentinel.next.item is the first item in the Deque.
 *   3- sentinel.prev is pointing to the last element (possibly null).
 *   4- The tail element's <next> is pointing to sentinel
 *
 * @author: Adnan H. Mohamed
 */
public class LinkedListDeque<T> implements Deque<T> {

    private class Node{
        T item;
        Node next;
        Node prev;

        public Node(){}

        public Node(T i, Node p, Node n)
        {
            item = i;
            prev = p;
            next = n;
        }
    }
    private int size;
    private Node sentinel;

    /** Creates an empty Deque. */
    public LinkedListDeque()
    {
        size = 0;
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /** Copy Constructor --Provides DEEP COPY. */
    public LinkedListDeque(LinkedListDeque other)
    {
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        for (Node cursor = other.sentinel; cursor.next != other.sentinel; cursor = cursor.next)
        {
            addLast(cursor.next.item);
        }
    }

    /** Adds an item of type T to the front of the deque. */
    @Override
    public void addFirst(T item)
    {
        // create the new element
        Node new_item = new Node(item, sentinel, sentinel.next);
        // make the new second item (or possibly the sentinal's prev) point
        // to the new node.
        sentinel.next.prev = new_item;
        // point sentinal's next pointer to the new node.
        sentinel.next = new_item;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    @Override
    public void addLast(T item)
    {
        // create the new element
        Node new_item = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = new_item; // updating the before last.
        sentinel.prev = new_item;  // updating the tail
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    @Override
    public boolean isEmpty(){return size == 0;}

    /** Returns the number of items in the deque. */
    @Override
    public int size(){return size;}

    /** Prints the items in the deque from first to last,
     *  separated by a space. Finally prints out a new line.
     */
    @Override
    public void printDeque()
    {
        for(Node cursor = sentinel.next; cursor != sentinel; cursor = cursor.next)
            System.out.print(cursor.item + " ");
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null.
     */
    @Override
    public T removeFirst()
    {
        if(sentinel.next == sentinel)
            return null;

        // save the item
        T first = sentinel.next.item;
        // setting the element after first to point to sentinal.
        // (This also handles the case where the deque's size = 1
        sentinel.next.next.prev = sentinel;
        // point to the 2nd item (possibly sentinal).
        sentinel.next = sentinel.next.next;
        size -= 1;
        return first;
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null.
     */
    @Override
    public T removeLast()
    {
        if(sentinel.prev == sentinel)  // no elements available
            return null;
        T last = sentinel.prev.item;
        // point the before last element's next pointer to sentinel
        sentinel.prev.prev.next = sentinel;
        // point sentinal's prev to the before last element.
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return last;
    }

    /** Gets the item at the given index, where 0 is the front,
     *  1 is the next item, and so forth. If no such item exists,
     *  returns null. Deque is NOT ALTERED.
     */
    @Override
    public T get(int index)
    {
        if(index < 0 || index >= size)
            return null;
        Node cursor = sentinel.next;
        int i = 0;
        while(i < index) {
            cursor = cursor.next;
            ++i;
        }
        return cursor.item;
    }

    /** Helper function for getRecursive. */
    private T getRecursive(int index, Node node) {
        if(index < 0 || index >= size)
            return null;
        if(index == 0)
            return node.item;
        return getRecursive(index - 1, node.next);
    }

    /** Recursive version of get(). See get() for doc. */
    public T getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }
}
