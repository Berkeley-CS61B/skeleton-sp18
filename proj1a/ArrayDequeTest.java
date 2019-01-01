public class ArrayDequeTest {
    public static void main(String[] args) {
        // testConstructor();
        // testAddFirst();
        // System.out.println((-1) % 3);
        // testPrint();
        // testAddLast();
        // testRemoveFirst();
        //  testRemoveLast();
        // testGet();
        testRemoveFirstW();
    }

    public static void testConstructor() {
        ArrayDeque<Integer> L = new ArrayDeque();
    }

    public static void testAddFirst() {
        ArrayDeque<Integer> L = new ArrayDeque();
        for(int i = 0; i < 3; i++){
            L.addFirst(3);
            L.addFirst(4);
            L.addFirst(5);
            L.addFirst(6);
        }
    }

    public static void testPrint() {
        ArrayDeque<Integer> L = new ArrayDeque();
        for(int i = 0; i < 3; i++){
            L.addFirst(3);
            L.addFirst(4);
            L.addFirst(5);
            L.addFirst(6);
        }
        L.printDeque();  
    }

    public static void testAddLast() {
        ArrayDeque<Integer> L = new ArrayDeque();
        for(int i = 0; i < 3; i++){
            L.addLast(3);
            L.addLast(4);
            L.addLast(5);
            L.addLast(6);
        }
        L.printDeque();  
    }

    public static void testRemoveFirst() {
        ArrayDeque<Integer> L = new ArrayDeque();
        for(int i = 0; i < 12; i++) {
            L.addFirst(i);
        }
        for(int i = 0; i < 15; i++) {
            L.removeFirst();
        }
        System.out.println(L.removeFirst());
    }

    public static void testRemoveLast() {
        ArrayDeque<Integer> L = new ArrayDeque();
        for(int i = 0; i < 3; i++) {
            L.addFirst(3);
            L.addFirst(4);
            L.addFirst(5);
            L.addFirst(6);
        }
        for(int i = 0; i < 12; i++) {
            System.out.println(L.removeLast());
        }
    }

    public static void testGet() {
        ArrayDeque<Integer> L = new ArrayDeque();
        for(int i = 0; i < 12; i++) {
            L.addLast(i);
        }
         for(int i = 0; i < 15; i++) {
            System.out.println(L.get(i));
        }
    }

    public static void testRemoveFirstW() {
        ArrayDeque<Integer> ArrayDeque = new ArrayDeque();
        ArrayDeque.addLast(0);
        ArrayDeque.addFirst(1);
        ArrayDeque.removeFirst();     
        ArrayDeque.get(0);      
        ArrayDeque.removeFirst();     
        ArrayDeque.addLast(5);
        ArrayDeque.get(0);      
        ArrayDeque.get(0);      
        ArrayDeque.addFirst(8);
        ArrayDeque.removeFirst();     
        ArrayDeque.addLast(10);
        ArrayDeque.removeLast();     
        ArrayDeque.removeFirst();    
        ArrayDeque.addFirst(13);
        ArrayDeque.removeFirst();     
        ArrayDeque.addFirst(15);
        ArrayDeque.removeFirst();
        ArrayDeque.addFirst(17);
        ArrayDeque.addFirst(18);
        ArrayDeque.addFirst(17);
        ArrayDeque.addFirst(18);
    }
}