/** Array based list.
 *  @author Josh Hug
 */

/* The next item ALWAYS goes in the size position */

public class AList<Item> implements List61B<Item>{
	/* the stored integers */
	private Item[] items;
	private int size;

	private static int RFACTOR = 2;

    /** Creates an empty list. */
    public AList() {
    	size = 0;
    	items = (Item[]) new Object[100];
    }

    /** Resize our backing array so that it is
      * of the given capacity. */
    private void resize(int capacity) {
    	Item[] a = (Item[]) new Object[capacity];
    	System.arraycopy(items, 0, a, 0, size);
    	items = a;    	
    }

    @Override
    /** Inserts X into the back of the list. */
    public void addLast(Item x) {
    	if (size == items.length) {
    		resize(size * RFACTOR);
    	}

    	items[size] = x;
    	size = size + 1;
    }

    @Override
    /** Returns the item from the back of the list. */
    public Item getLast() {
    	int lastActualItemIndex = size - 1;
    	return items[lastActualItemIndex];
    }

    @Override
    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i) {
        return items[i];
    }

    @Override
    /** Returns the number of items in the list. */
    public int size() {
        return size;        
    }

    @Override
    /** Deletes item from back of the list and
      * returns deleted item. */
    public Item removeLast() {
		Item itemToReturn = getLast();
		/* setting to zero not strictly necessary, but
		 * it's a good habit (we'll see why soon) */
		items[size - 1] = null;
		size = size - 1;
		return itemToReturn;
    }

    @Override
    /** Inserts item into given position.
      * Code from discussion #3 */
    public void insert(Item x, int position) {
        Item[] newItems = (Item[]) new Object[items.length + 1];

        System.arraycopy(items, 0, newItems, 0, position);
        newItems[position] = x;

        System.arraycopy(items, position, newItems, position + 1, items.length - position);
        items = newItems;
    }

    @Override
    /** Inserts an item at the front. */
    public void addFirst(Item x) {
        insert(x, 0);
    }

    @Override
    /** Gets an item from the front. */
    public Item getFirst() {
        return get(0);
    }

}