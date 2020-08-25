/** Deque Interface */
public interface Deque<T> {

    /** Returns true if deque is empty, false otherwise */
    boolean isEmpty();

    /** Returns the number of items in the deque */
    int size();

    /** Prints the items in the deque from first to last, separated by a space */
    void printDeque();

    /** Adds an item of type T to the front of the deque. */
    void addFirst(T item);

    /** Adds an item of type T to the back of the deque */
    void addLast(T item);

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     * */
    T removeFirst();

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     * */
    T removeLast();

    /** Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such items exists, returns null.
     * */
    T get(int index);

}