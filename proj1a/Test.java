//public class Test{
//    public static void main(String[] args) {
//        System.out.println("Running tests.");
//        // testAddFirst();
//        // testPrintDeque();
//        // testAddLast();
//        // testRemoveFirst();
//        // testGet();
//        testGetRecursive();
//    }
//
//    public static void testAddFirst() {
//        System.out.println("Test AddFirst");
//        LinkedListDeque<String> L = new LinkedListDeque();
//        L.addFirst("abc");
//        L.addFirst("sfsf");
//    }
//
//    public static void testPrintDeque() {
//        System.out.println("Test printDeque");
//        LinkedListDeque<String> L = new LinkedListDeque();
//        L.addFirst("abc");
//        L.addFirst("sfsf");
//        System.out.println("Should be sfsf abc");
//        L.printDeque();
//    }
//
//    public static void testAddLast() {
//        System.out.println("Test addLast");
//        LinkedListDeque<Integer> L = new LinkedListDeque();
//        L.addLast(3);
//        L.addLast(4);
//        L.addLast(5);
//        System.out.println("Should be 3 4 5");
//        L.printDeque();
//    }
//
//    public static void testRemoveFirst() {
//        System.out.println("Test removeFirst");
//        LinkedListDeque<Integer> L = new LinkedListDeque();
//        L.addLast(3);
//        L.addLast(4);
//        System.out.println("Should return 3");
//        System.out.println(L.removeFirst());
//        System.out.println("The new queue shoud be 4");
//        L.printDeque();
//    }
//
//    public static void testGet() {
//        System.out.println("Test get");
//        LinkedListDeque L = new LinkedListDeque();
//        L.addLast(3);
//        L.addLast(4);
//        L.addLast(5);
//        System.out.println("the first element should be 3");
//        System.out.println(L.get(0));
//        System.out.println("the 2nd element should be 4");
//        System.out.println(L.get(1));
//        System.out.println("the 4th element should be null");
//        System.out.println(L.get(4));
//    }
//
//    public static void testGetRecursive() {
//        System.out.println("TestRecursive get");
//        LinkedListDeque L = new LinkedListDeque();
//        L.addLast("123123");
//        L.addLast(4);
//        L.addLast(5);
//        System.out.println("the first element should be 3");
//        System.out.println(L.getRecursive(0));
//        System.out.println("the 2nd element should be 4");
//        System.out.println(L.getRecursive(1));
//        System.out.println("the 4th element should be null");
//        System.out.println(L.getRecursive(4));
//    }
//
//}