/** Test file for UnionFind class.
 *
 * @author Adnan H. Mohamed
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {

    @Test(timeout = 20000)
    public void TestConstructor() {
        UnionFind uf = new UnionFind(15);
        for (int i = 0; i < 15; ++i) {
            assertEquals(1, uf.sizeOf(i));
            assertEquals(-1, uf.parent(i));
        }
    }

    /** Tests Union, find, parent, and sizeOf */
    @Test(timeout = 20000)
    public void Test2() {
        int SIZE = 15;
        UnionFind uf = new UnionFind(SIZE);
        uf.union(0, 10);
        /* Unit Test 1. */
        assertEquals(10, uf.find(0));
        assertEquals(2, uf.sizeOf(0));
        assertEquals(2, uf.sizeOf(10));

        uf.union(1, 2);
        uf.union(3, 6);
        uf.union(9, 12);
        uf.union(3, 2);
        uf.union(12, 6);

        /* Unit Test 2. */
        int[] parents = {10, 2, -6, 6, -1, -1, 2, -1,
                -1, 12, -2, -1, 2, -1, -1};

        for (int i = 0; i < SIZE; ++i) {
            assertEquals(parents[i], uf.parent(i));
        }

        /* Unit Test 3. */
        int[] nodes = {1, 3, 6, 9, 12};
        for (int n:nodes) {
            assertEquals(2, uf.find(n));
        }

        for (int n:nodes) {
            assertEquals(2, uf.parent(n));
        }

        for (int n: nodes) {
            assertEquals(6, uf.sizeOf(n));
        }

    }
}