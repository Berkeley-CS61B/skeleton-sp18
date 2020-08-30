package synthesizer;
import org.junit.Test;
import synthesizer.ArrayRingBuffer;
import static org.junit.Assert.*;

/** Tests the synthesizer.ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        assertTrue(arb.isEmpty());
        assertTrue(arb.capacity() == 10);
        assertEquals(0, arb.fillCount());
    }

    @Test
    public void Test2() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        for (int i = 1; i < 4; ++i) {
            arb.enqueue(i * 10);
        }

        int elem = 10;
        while (!arb.isEmpty()) {
            assertEquals(elem, arb.peek());
            assertEquals(elem, arb.dequeue());
            elem += 10;
        }

        for (int i = 0; i < arb.capacity(); ++i) {
            arb.enqueue(i);
        }
        elem = 0;
        while (!arb.isEmpty()) {
            assertEquals(elem, arb.peek());
            assertEquals(elem, arb.dequeue());
            elem += 1;
        }
    }

    /** Calls tests for synthesizer.ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
