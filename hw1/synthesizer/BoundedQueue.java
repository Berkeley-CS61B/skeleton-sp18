package synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T>{
    /** Returns size of the buffer. */
    int capacity();
    /** Returns number of items currently in the buffer. */
    int fillCount();
    /** Adds item x to the end. */
    void enqueue(T x);
    /** delete and return item from the front. */
    T dequeue();
    /** Return (but do not delete) item from the front. */
    T peek();

    /** Returns true iff the buffer is full. */
    default boolean isFull() {
        return fillCount() == capacity();
    }

    /** Returns true iff the buffer is empty. */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    @Override
    Iterator<T> iterator();
}