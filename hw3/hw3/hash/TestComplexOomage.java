package hw3.hash;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class TestComplexOomage {

    @Test
    public void testHashCodeDeterministic() {
        ComplexOomage so = ComplexOomage.randomComplexOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(ComplexOomage.randomComplexOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /* TODO: Create a list of Complex Oomages called deadlyList
     * that shows the flaw in the hashCode function.
     */
    @Test
    public void testWithDeadlyParams() {
        List<Oomage> deadlyList = new ArrayList<>();

        final int TEST_SIZE = 200;
        for (int i = 0; i < TEST_SIZE; ++i) {
            List<Integer> lst = new ArrayList<>();
            lst.add(i);
            lst.add(0);
            lst.add(1);
            lst.add(2);
            lst.add(3);
            deadlyList.add(new ComplexOomage(lst));
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(deadlyList, 10));
    }

    /** Calls tests for ComplexOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestComplexOomage.class);
    }
}
