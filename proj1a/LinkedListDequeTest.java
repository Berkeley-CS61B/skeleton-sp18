import java.util.ArrayList;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
	
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");
		LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");
		
		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);
	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");


		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.removeFirst();
		// should be empty
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		printTestStatus(passed);

	}

	public static void addIsEmptySizeTest2() {
		System.out.println("Running add/isEmpty/Size test 2.");
		ArrayDeque<String> arrd1 = new ArrayDeque<>();

		boolean passed = checkEmpty(true, arrd1.isEmpty());

		arrd1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, arrd1.size()) && passed;
		passed = checkEmpty(false, arrd1.isEmpty()) && passed;

		arrd1.addLast("middle");
		passed = checkSize(2, arrd1.size()) && passed;

		arrd1.addLast("back");
		passed = checkSize(3, arrd1.size()) && passed;

		System.out.println("Printing out deque: ");
		arrd1.printDeque();

		printTestStatus(passed);
	}

	/** tests add first with more data */
	public static void addFirstArrayDequeTest() {
		System.out.println("Running addFirstArrayDequeTest.");
		ArrayDeque<Integer> arrd1 = new ArrayDeque<>();
		int[] results = new int[4];
		arrd1.addFirst(0);
		results[0] = arrd1.removeFirst();
		arrd1.addFirst(2);
		results[1] = arrd1.removeFirst();
		arrd1.isEmpty();
		arrd1.addFirst(5);
		results[2] = arrd1.removeFirst();
		arrd1.addFirst(7);
		results[3] = arrd1.removeFirst();
	}

	/** tests get method in LinkedListDeque. */
	public static void getTest() {
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
		// should be empty
		for (int i = 0; i < 5; ++i) {
			lld1.addLast(i);
		}
		boolean passed;
		LinkedListDeque<Integer> lld2 = new LinkedListDeque<>(lld1);
		lld2.removeFirst();
		lld2.removeFirst();
//		lld1.addFirst(10);
//		// should not be empty
//		passed = checkEmpty(false, lld1.isEmpty()) && passed;
//
//		lld1.removeFirst();
//		// should be empty
//		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		// printTestStatus(passed);
	}

	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		addIsEmptySizeTest();
		addRemoveTest();
		addIsEmptySizeTest2();
		getTest();
		addFirstArrayDequeTest();
	}
} 