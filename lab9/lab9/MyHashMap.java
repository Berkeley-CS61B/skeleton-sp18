package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Adnan H. Mohamed
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int h = hash(key);  // gets the hash.
        return buckets[h].get(key);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        int h = hash(key);
        int old_size = buckets[h].size();
        buckets[h].put(key, value);
        size += buckets[h].size() - old_size;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /** Returns true iff the bst is empty. */
    public boolean is_Empty() {
        return size == 0;
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> key_set = new HashSet<>();
        for (ArrayMap bucket:buckets) {
            key_set.addAll(bucket.keySet());
        }
        return key_set;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * If you don't implement this, throw an UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        int h = hash(key);
        V val = buckets[h].remove(key);
        if (val != null) {
            size -= 1;
        }
        return val;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. If you don't implement this, throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        if (value.equals(get(key))) {
            return remove(key);
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
