public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int front;
    private int rear;

    // creates an empty ArrayDeque
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        front = 0;
        rear = 0;
    }

    public void addFirst(T item) {
        if(front == (rear + 1 + items.length) % items.length) {
            int j = 0;
            T[] a = (T[]) new Object[items.length * 2];
            for(int i = front; i != rear; i = (i + 1 + items.length) % items.length) {            /*this line takes me a lot of time*/
                a[j] = items[i];
                j++;          
            }
            items = a;
            rear = j++;
            front = 0;
            front = (front - 1 + items.length) % items.length;
            items[front] = item;
            size++;
        } else if(isEmpty()) {
            items[front] = item;
            rear += 1;
            size++;
        } else {
            front = (front - 1 + items.length) % items.length;
            items[front] = item;
            size++;
        }
    }

    public void addLast(T item) {
         if(front == (rear + 1 + items.length) % items.length) {
            int j = 0;
            T[] a = (T[]) new Object[items.length * 2];
            for(int i = front; i != rear; i = (i + 1 + items.length) % items.length) {            /*this line takes me a lot of time*/
                a[j] = items[i];
                j++;          
            }
            items = a;
            rear = j++;
            front = 0;

            items[rear] = item;
            rear++;
            size++;
        } else if(isEmpty()) {
            items[rear] = item;
            rear = (rear + 1 + items.length) % items.length;
            size++;
        } else {
            items[rear] = item;
            rear = (rear + 1 + items.length) % items.length;
            size++;
        }
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (!isEmpty()) {
            for(int i = front; i != rear; i = (i + 1) % items.length) {
                System.out.println(items[i]);
            }
        }
    }

    public T removeFirst() {
        if(!isEmpty()) {
            T temp = items[front];
            items[front] = null;
            front = (front + 1 + items.length) % items.length;
            size -= 1;
            if (size < items.length / 4) {
                int j = 0;
                T[] a = (T[]) new Object[items.length / 2];
                for(int i = front; i != rear; i = (i + 1) % items.length) {
                    a[j] = items[i];
                    j++;      
                }
                items = a;
                rear = j++;
                front = 0;   
            }
            return temp;
        } 
        
        return null;
    }

    public T removeLast() {
        if(!isEmpty()) {
            T temp = items[(rear - 1 + items.length) % items.length];
            items[(rear - 1 + items.length) % items.length] = null;
            rear = (rear - 1 + items.length) % items.length;
            size -= 1;
            if (size < items.length / 4) {
                int j = 0;
                T[] a = (T[]) new Object[items.length / 2];
                for(int i = front; i != rear; i = (i + 1) % items.length) {
                    a[j] = items[i];
                    j++;      
                }
                items = a;
                rear = j++;
                front = 0;   
            }
            return temp;
        } 
        return null;
    }

    public T get(int index) {
        if (isEmpty() || index >= size) {
            return null;
        }
        return items[(front + index + items.length) % items.length];
    }
}