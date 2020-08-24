/** Deque data structure using arrays.
 *
 *   --- Class Invariant ---
 *   1- <size> is the USED elements of the array.
 *   2- <capacity> is the total length of the array.
 *   3- <nextFirst + 1> is the index of the first item.
 *   4- <nextLast - 1> is the index of the last item.
 *   5- <THRESHOLD> is <= (size / capacity) , whenever capacity > 16.
 *
 * @author: Adnan H. Mohamed
 */
public class ArrayDeque<T>{
    final private double THRESHOLD = 0.25;
    private T[] data;
    private int size;
    private int capacity = 8;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty ArrayDeque. */
    public ArrayDeque()
    {
        data = (T[]) new Object[capacity];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /** Copy Constructor -- Creates DEEP COPY. */
    public ArrayDeque(ArrayDeque other) {
        // ADD YOUR CODE HERE
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item)
    {
        if(isfull())
        {
            scale(capacity * 2);
        }
        data[nextFirst] = item;
        size += 1;
        if (nextFirst == 0) {
            nextFirst = data.length - 1;
        } else {
            nextFirst -= 1;
        }
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item)
    {
        if(isfull()) {
            scale(capacity * 2);
        }
        data[nextLast] = item;
        size += 1;
        nextLast = (nextLast + 1) % data.length;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty(){return size == 0;}

    /** Returns the number of items in the deque. */
    public int size(){return size;}

    /** Prints the items in the deque from first to last,
     *  separated by a space. Finally prints out a new line.
     */
    public void printDeque()
    {
        for(int i = nextFirst + 1, j = 0; j < size; ++i, ++j) {
            System.out.print(data[i % data.length] + " ");
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null.
     */
    public T removeFirst(){
        if(isEmpty())
            return null;

        size -= 1;
        T item = data[(nextFirst + 1) % data.length];
        data[(nextFirst + 1) % data.length] = null;
        nextFirst = (nextFirst + 1) % data.length;

        // Shrink the array if necessary
        double ratio = size / (double)capacity;
        if(ratio < THRESHOLD) {
            scale((int) (capacity * 0.5));
        }
        return item;
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null.
     */
    public T removeLast(){
        if(isEmpty()) {
            return null;
        }

        if (nextLast - 1 < 0) {
            nextLast = data.length;
        }
        T item = data[nextLast - 1];
        data[nextLast - 1] = null;
        nextLast -= 1;
        size -= 1;
        // Shrink the array if necessary
        double ratio = size / (double)capacity;
        if(ratio < THRESHOLD)
            scale((int)(capacity * 0.5));

        return item;
    }

    /** Gets the item at the given index, where 0 is the front,
     *  1 is the next item, and so forth. If no such item exists,
     *  returns null. Deque is NOT ALTERED.
     */
    public T get(int index){return data[(nextFirst + index + 1) % size];}

    /** ----- HELPER FUNCTIONS ------ */

    /** Scales the data array to the specified capacity. */
    private void scale(int c){
        T[] new_arr = (T[]) new Object [c];
        int i = nextFirst + 1;
        int k = 0;
        do {
            new_arr[k] = data[i % data.length];
            i += 1;
            k += 1;
        } while (k < size);
        nextLast = size;
        nextFirst = new_arr.length - 1;
        data = new_arr;
        capacity = c;
    }

    /** Returns true iff no extra space in the array.
     *  (i.e. capacity is all used.)
     */
    private boolean isfull(){return capacity == size;}
}