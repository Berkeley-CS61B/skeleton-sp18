/** Implementation of BoundedQueue (a.k.a queue) using circular arrays.
 *
 * @author Adnan H. Mohamed
 */
package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int _first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int _last;
    /* Array for storing the buffer data. */
    private T[] _rb;

    /**
     * Create a new synthesizer.ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        _capacity = capacity;
        _rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        _rb[_last] = x;
        _last = (_last + 1) % _capacity;
        _size++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        T item = _rb[_first];
        _first = (_first + 1) % _capacity;
        _size--;
        return item;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        return _rb[_first];
    }
    // TODO: When you get to part 5, implement the needed code to support iteration.
}
