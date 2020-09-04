package lab9tester;

import static org.junit.Assert.*;

import org.junit.Test;
import lab9.BSTMap;

import java.util.HashSet;
import java.util.Set;
import java.util.Random;


/**
 * Tests by Brendan Hu, Spring 2015, revised for 2018 by Josh Hug
 */
public class TestBSTMap {

    @Test
    public void sanityGenericsTest() {
        try {
            BSTMap<String, String> a = new BSTMap<String, String>();
            BSTMap<String, Integer> b = new BSTMap<String, Integer>();
            BSTMap<Integer, String> c = new BSTMap<Integer, String>();
            BSTMap<Boolean, Integer> e = new BSTMap<Boolean, Integer>();
        } catch (Exception e) {
            fail();
        }
    }

    //assumes put/size/containsKey/get work
    @Test
    public void sanityClearTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1 + i);
            //make sure put is working via containsKey and get
            assertTrue(null != b.get("hi" + i));
            assertTrue(b.get("hi" + i).equals(1 + i));
            assertTrue(b.containsKey("hi" + i));
        }
        assertEquals(455, b.size());
        b.clear();
        assertEquals(0, b.size());
        for (int i = 0; i < 455; i++) {
            assertTrue(null == b.get("hi" + i) && !b.containsKey("hi" + i));
        }
    }

    // assumes put works
    @Test
    public void sanityContainsKeyTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertFalse(b.containsKey("waterYouDoingHere"));
        b.put("waterYouDoingHere", 0);
        assertTrue(b.containsKey("waterYouDoingHere"));
    }

    // assumes put works
    @Test
    public void sanityGetTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertEquals(null, b.get("starChild"));
        assertEquals(0, b.size());
        b.put("starChild", 5);
        assertTrue(((Integer) b.get("starChild")).equals(5));
        b.put("KISS", 5);
        assertTrue(((Integer) b.get("KISS")).equals(5));
        assertNotEquals(null, b.get("starChild"));
        assertEquals(2, b.size());
    }

    // assumes put works
    @Test
    public void sanitySizeTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertEquals(0, b.size());
        b.put("hi", 1);
        assertEquals(1, b.size());
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
        }
        assertEquals(456, b.size());
    }

    //assumes get/containskey work
    @Test
    public void sanityPutTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        b.put("hi", 1);
        assertTrue(b.containsKey("hi"));
        assertTrue(b.get("hi") != null);
    }

    // testing keySet.
    @Test (timeout = 25000)
    public void sanityKeySetTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1 + i);
        }
        Set<String> keys = b.keySet();
        for (int i = 0; i < 455; i++) {
            assertTrue(keys.contains("hi" + i));
        }
    }

    // testing remove.
    @Test (timeout = 25000)
    public void sanityRemoveTest() {
        Random r = new Random(543);
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 0; i < 70; i++) {
            int val = r.nextInt() % 70;
            b.put("hi" + val, 1 + val);
        }

        int many_elements = b.size();
        while (!b.is_Empty()) {
            String key = "hi" + (r.nextInt() % 70);
            if (b.get(key) != null) {
                b.remove(key);
                many_elements -= 1;
                assertEquals(many_elements, b.size());
                Integer ans = b.get(key);
                assertEquals(null, ans);
            }
        }
    }

    @Test
    public void sanityIteratorTest() {
        final int TEST_SIZE = 100;
        Random r = new Random(54);
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        Set<String> key_set = new HashSet<>();
        for (int i = 0; i < TEST_SIZE; i++) {
            int val = Math.abs(r.nextInt()) % 80;
            String key = "hi" + val;
            key_set.add(key);
            b.put(key, 1 + val);
        }
        String[] expected = key_set.toArray(new String[key_set.size()]);
        java.util.Arrays.sort(expected);
        String[] actual = new String[expected.length];
        int j = 0;
        for (String key: b) {
            actual[j] = key;
            j += 1;
        }

        assertArrayEquals(expected, actual);
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestBSTMap.class);
    }
}
