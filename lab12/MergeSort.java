import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        Queue<Queue<Item>> allQ = new Queue<>();
        for (Item item : items) {
            Queue<Item> q = new Queue<>();
            q.enqueue(item);
            allQ.enqueue(q);
        }
        return allQ;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> mergedQ = new Queue<>();
        while (!q1.isEmpty() && !q2.isEmpty()) {
            // neither queues are empty.
            if (q1.peek().compareTo(q2.peek()) < 0) {
                // q1's next element is less than q2's
                mergedQ.enqueue(q1.dequeue());
            } else {
                // q2's next element is less than or equal to q1's
                mergedQ.enqueue(q2.dequeue());
            }
        }

        // Dealing with the case where one of the q's emptied at the
        // top while loop. So, this requires the other q
        // to be emptied into the mergedQ.
        while (!q1.isEmpty()) {
            mergedQ.enqueue(q1.dequeue());
        }
        while (!q2.isEmpty()) {
            mergedQ.enqueue(q2.dequeue());
        }
        return mergedQ;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        if (items.size() < 2) {
            return items;
        }
        Queue<Queue<Item>> singleQs = makeSingleItemQueues(items);
        Queue<Item> answer;
        while (singleQs.size() > 1) {
            singleQs.enqueue(mergeSortedQueues(singleQs.dequeue(), singleQs.dequeue()));
        }

        return singleQs.dequeue();
    }

    public static void main(String[] args) {
        Queue<String> students = new Queue<String>();
        students.enqueue("Alice");
        students.enqueue("Vanessa");
        students.enqueue("Ethan");

        Queue<String> q = mergeSort(students);
        for (String s : q) {
            System.out.println(s);
        }
    }
}
