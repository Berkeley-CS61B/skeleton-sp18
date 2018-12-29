public class Sort {
	/** Sorts strings destructively. */
	public static void sort(String[] x) {
        sort(x, 0);
	}

	/** Sorts x starting at position start. */
	private static void sort(String[] x, int start) {
	    if (start == x.length) {
	        return;
        }
	    int smallestIndex = findSmallest(x, start);
	    swap(x, start, smallestIndex);
	    sort(x, start + 1);
    }

	/** Swap item a with b. */
	public static void swap(String[] x, int a, int b) {
	    String temp = x[a];
	    x[a] = x[b];
	    x[b] = temp;
    }

	/** Return the index of the smallest String in x, starting at start. */
	public static int findSmallest(String[] x, int start) {
        int smallestIndex = start;
        for (int i = start; i < x.length; i += 1) {
            int cmp = x[i].compareTo(x[smallestIndex]);
            // from the internet, if x[i] < x[smallestIndex], cmp will be -1.
            if (cmp < 0) {
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }
}