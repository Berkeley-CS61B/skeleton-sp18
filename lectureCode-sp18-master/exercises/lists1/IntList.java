public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

	/** Return the size of the list using... recursion! */
	public int size() {
		return 0;
	}

	/** Return the size of the list using no recursion! */
	public int iterativeSize() {
		return 0;
	}

	/** Returns the ith value in this list.*/
	public int get(int i) {
		return 0;
	}

	public static void main(String[] args) {
		IntList L = new IntList(15, null);
		L = new IntList(10, L);
		L = new IntList(5, L);

		System.out.println(L.iterativeSize());
	}
} 