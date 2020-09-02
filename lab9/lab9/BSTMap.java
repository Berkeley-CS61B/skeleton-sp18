package lab9;

import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Adnan H. Mohamed
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K _key;
        private V _value;

        /* Children of this Node. */
        private Node _left;
        private Node _right;

        private Node(K k, V v) {
            _key = k;
            _value = v;
        }
    }

    private Node _root;  /* Root node of the tree. */
    private int _size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        _root = null;
        _size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null)
            return null;
        int cmp = key.compareTo(p._key);
        if (cmp == 0) {
            return p._value;
        }
        else if (cmp < 0) {
            return getHelper(key, p._left);
        }
        else {
            return getHelper(key, p._right);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, _root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            _size++;
            return new Node(key, value);
        }
        int cmp = key.compareTo(p._key);

        if (cmp == 0) {
            p._value = value;
            return p;
        }
        else if (cmp < 0) {
            p._left = putHelper(key, value, p._left);
        }
        else {
            p._right = putHelper(key, value, p._right);
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        _root = putHelper(key, value, _root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return _size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
