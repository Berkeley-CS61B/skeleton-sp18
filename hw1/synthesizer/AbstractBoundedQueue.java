package synthesizer;


public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {

    protected int _capacity;
    protected int _size;

    @Override
    /** Returns if the buffer is empty */
    public int capacity() {
        return _capacity;
    }

    @Override
    /** Returns if the buffer is full */
    public int fillCount() {
        return _size;
    }

}