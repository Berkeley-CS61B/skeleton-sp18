package lab9tester;

import static org.junit.Assert.*;

import lab9.MyHashMap;
import lab9.MyHashMap;
import org.junit.Test;
import lab9.MyHashMap;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Tests by Brendan Hu, Spring 2015, revised for 2018 by Josh Hug
 */
public class TestMyHashMap {

    @Test
    public void sanityGenericsTest() {
        try {
            MyHashMap<String, String> a = new MyHashMap<String, String>();
            MyHashMap<String, Integer> b = new MyHashMap<String, Integer>();
            MyHashMap<Integer, String> c = new MyHashMap<Integer, String>();
            MyHashMap<Boolean, Integer> e = new MyHashMap<Boolean, Integer>();
        } catch (Exception e) {
            fail();
        }
    }

    //assumes put/size/containsKey/get work
    @Test
    public void sanityClearTest() {
        MyHashMap<String, Integer> b = new MyHashMap<String, Integer>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
            //make sure put is working via containsKey and get
            assertTrue(null != b.get("hi" + i)
                    && b.containsKey("hi" + i));
        }
        b.clear();
        assertEquals(0, b.size());
        for (int i = 0; i < 455; i++) {
            assertTrue(null == b.get("hi" + i) && !b.containsKey("hi" + i));
        }
    }

    // assumes put works
    @Test
    public void sanityContainsKeyTest() {
        MyHashMap<String, Integer> b = new MyHashMap<String, Integer>();
        assertFalse(b.containsKey("waterYouDoingHere"));
        b.put("waterYouDoingHere", 0);
        assertTrue(b.containsKey("waterYouDoingHere"));
    }

    // assumes put works
    @Test
    public void sanityGetTest() {
        MyHashMap<String, Integer> b = new MyHashMap<String, Integer>();
        assertEquals(null, b.get("starChild"));
        b.put("starChild", 5);
        assertNotEquals(null, b.get("starChild"));
        b.put("KISS", 5);
        assertNotEquals(null, b.get("KISS"));
        assertNotEquals(null, b.get("starChild"));
    }

    // assumes put works
    @Test
    public void sanitySizeTest() {
        MyHashMap<String, Integer> b = new MyHashMap<String, Integer>();
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
        MyHashMap<String, Integer> b = new MyHashMap<String, Integer>();
        b.put("hi", 1);
        assertTrue(b.containsKey("hi") && b.get("hi") != null);
    }

    /*
     * Test for general functionality and that the properties of Maps hold.
     */
    @Test
    public void functionalityTest() {
        MyHashMap<String, String> dictionary = new MyHashMap<>();
        assertEquals(0, dictionary.size());

        // can put objects in dictionary and get them
        dictionary.put("hello", "world");
        assertTrue(dictionary.containsKey("hello"));
        assertEquals("world", dictionary.get("hello"));
        assertEquals(1, dictionary.size());

        // putting with existing key updates the value
        dictionary.put("hello", "kevin");
        assertEquals(1, dictionary.size());
        assertEquals("kevin", dictionary.get("hello"));

        // putting key in multiple times does not affect behavior
        MyHashMap<String, Integer> studentIDs = new MyHashMap<>();
        studentIDs.put("sarah", 12345);
        assertEquals(1, studentIDs.size());
        assertEquals(12345, studentIDs.get("sarah").intValue());
        studentIDs.put("alan", 345);
        assertEquals(2, studentIDs.size());
        assertEquals(12345, studentIDs.get("sarah").intValue());
        assertEquals(345, studentIDs.get("alan").intValue());
        studentIDs.put("alan", 345);
        assertEquals(2, studentIDs.size());
        assertEquals(12345, studentIDs.get("sarah").intValue());
        assertEquals(345, studentIDs.get("alan").intValue());
        studentIDs.put("alan", 345);
        assertEquals(2, studentIDs.size());
        assertEquals(12345, studentIDs.get("sarah").intValue());
        assertEquals(345, studentIDs.get("alan").intValue());
        assertTrue(studentIDs.containsKey("sarah"));
        assertTrue(studentIDs.containsKey("alan"));

        // handle values being the same
        assertEquals(345, studentIDs.get("alan").intValue());
        studentIDs.put("evil alan", 345);
        assertEquals(345, studentIDs.get("evil alan").intValue());
        assertEquals(studentIDs.get("evil alan"), studentIDs.get("alan"));
    }

    // testing keySet.
    @Test (timeout = 25000)
    public void sanityKeySetTest() {
        MyHashMap<String, Integer> h = new MyHashMap<>();
        for (int i = 0; i < 455; i++) {
            h.put("hi" + i, 1 + i);
        }
        Set<String> keys = h.keySet();
        for (int i = 0; i < 455; i++) {
            assertTrue(keys.contains("hi" + i));
        }
    }

    // testing remove.
    @Test (timeout = 25000)
    public void sanityRemoveTest() {
        Random r = new Random(543);
        MyHashMap<String, Integer> b = new MyHashMap<String, Integer>();
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
        MyHashMap<String, Integer> b = new MyHashMap<String, Integer>();
        Set<String> key_set = new HashSet<>();
        for (int i = 0; i < TEST_SIZE; i++) {
            int val = Math.abs(r.nextInt()) % 80;
            String key = "hi" + val;
            key_set.add(key);
            b.put(key, 1 + val);
        }
        for (String key: b) {
            assertTrue(key_set.contains(key));
        }
        assertTrue(key_set.size() == b.size());
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestMyHashMap.class);
    }
}
