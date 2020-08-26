import org.junit.Test;
import static org.junit.Assert.*;

//import java.lang.reflect.Array;

public class TestArrayDequeGold {
    @Test
    public void testAddFirstLastEmpty() {
        StudentArrayDeque<Integer> sd = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> rd = new ArrayDequeSolution<>();
        String msg = "";
        Integer randNum;

        for (int i = 0; i < 10; ++i) {
            msg += "isEmpty()\n";
            assertEquals(msg, rd.isEmpty(), sd.isEmpty());
            if (StdRandom.uniform() < .65) {
                randNum = StdRandom.uniform(20);
                msg += "addFirst(" + randNum + ")\n";
                sd.addFirst(randNum);
                rd.addFirst(randNum);
            } else {
                randNum = StdRandom.uniform(20);
                msg += "addLast(" + randNum + ")\n";
                sd.addLast(randNum);
                rd.addLast(randNum);
            }
        }
    }
    @Test (timeout = 30000)
    public void testAddFirstRemoveFirst() {
        StudentArrayDeque<Integer> sd = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> rd = new ArrayDequeSolution<>();
        Integer randNum;
        Integer removed1, removed2;
        String msg = "";
        int counter = 0;
        for (int i = 0; i < 1000; ++i, ++counter) {
            if (StdRandom.uniform() < .65) {
                randNum = StdRandom.uniform(20);
                msg += "addFirst(" + randNum + ")\n";
                sd.addFirst(randNum);
                rd.addFirst(randNum);
            } else {
                if (!rd.isEmpty()) {
                    msg += "removeFirst()\n";
                    removed1 = sd.removeFirst();
                    removed2 = rd.removeFirst();
                    assertEquals(removed2, removed1);
                }
            }

            if (counter != 0 && counter % 30 == 0) {
                while (!rd.isEmpty()) {
                    msg += "removeFirst()\n";
                    assertEquals(msg, rd.removeFirst(), sd.removeFirst());
                }
            }
        }
    }

    @Test (timeout = 30000)
    public void testAddLastRemoveLast() {
        StudentArrayDeque<Integer> sd = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> rd = new ArrayDequeSolution<>();
        Integer randNum;
        Integer removed1, removed2;
        int counter = 0;
        String msg = "";
        for (int i = 0; i < 1000; ++i, ++counter) {
            if (StdRandom.uniform() < .7) {
                randNum = StdRandom.uniform(20);
                msg += "addLast(" + randNum + ")\n";
                sd.addLast(randNum);
                rd.addLast(randNum);
            } else {
                if (!rd.isEmpty()) {
                    msg += "removeLast()\n";
                    removed1 = sd.removeLast();
                    removed2 = rd.removeLast();
                    assertEquals(msg, removed2, removed1);
                }
            }

            if (counter % 30 == 0 && counter != 0) {
                while (!rd.isEmpty()) {
                    msg += "removeFirst()\n";
                    assertEquals(msg, rd.removeFirst(), sd.removeFirst());
                }
            }
        }
    }

    @Test (timeout = 30000)
    public void testAddLastRemoveFirst() {
        StudentArrayDeque<Integer> sd = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> rd = new ArrayDequeSolution<>();
        Integer randNum;
        Integer removed1, removed2;
        int counter = 0;
        String msg = "";
        for (int i = 0; i < 1000; ++i, ++counter) {
            if (StdRandom.uniform() < .7) {
                randNum = StdRandom.uniform(20);
                msg += "addLast(" + randNum + ")\n";
                sd.addLast(randNum);
                rd.addLast(randNum);
            } else {
                if (!rd.isEmpty()) {
                    msg += "removeFirst()\n";
                    removed1 = sd.removeFirst();
                    removed2 = rd.removeFirst();
                    assertEquals(msg, removed2, removed1);
                }
            }

            if (counter % 50 == 0 && counter != 0) {
                while (!rd.isEmpty()) {
                    msg += "removeFirst()\n";
                    assertEquals(msg, rd.removeFirst(), sd.removeFirst());
                }
            }
        }
    }

    @Test (timeout = 30000)
    public void testAddFirstRemoveLast() {
        StudentArrayDeque<Integer> sd = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> rd = new ArrayDequeSolution<>();
        Integer randNum;
        Integer removed1, removed2;
        int counter = 0;
        String msg = "";
        for (int i = 0; i < 1000; ++i, ++counter) {
            if (StdRandom.uniform() < .7) {
                randNum = StdRandom.uniform(20);
                msg += "addFirst(" + randNum + ")\n";
                sd.addFirst(randNum);
                rd.addFirst(randNum);
            } else {
                if (!rd.isEmpty()) {
                    msg += "removeLast()\n";
                    removed1 = sd.removeLast();
                    removed2 = rd.removeLast();
                    assertEquals(msg, removed2, removed1);
                }
            }

            if (counter % 50 == 0 && counter != 0) {
                while (!rd.isEmpty()) {
                    msg += "removeLast()\n";
                    assertEquals(msg, rd.removeLast(), sd.removeLast());
                }
            }
        }
    }
}
