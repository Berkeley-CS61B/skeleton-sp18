package lab9;

import java.util.*;

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
        Set<K> key_set = new HashSet<>();
        keySetHelper(_root, key_set);
        return key_set;
    }

    /** Adds the keys from the bst rooted at N
     *  to the given set KEY_SET.
     */
    private void keySetHelper(Node n, Set<K> key_set) {
        /** using in-order traversal to add the element to the set. */
        if (n == null)
            return;
        keySetHelper(n._left, key_set);  // do the work for the left sub-tree.
        key_set.add(n._key);             // Adding the root.
        keySetHelper(n._right, key_set); // do the work for the right sub-tree.
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        V val = get(key);
        if (val == null)
            return null;
        _root = remove(_root, key);
        return val;
    }

    /** Removes and returns the updated bst rooted
     *  at the returned node. If the tree is empty
     *  null is returned.
     */
    private Node remove(Node n, K key) {
       if (n == null)
           return null;
       int cmp = key.compareTo(n._key);

       if (cmp < 0) {
           n._left = remove(n._left, key);
       }
       else if (cmp > 0) {
           n._right = remove(n._right, key);
       }
       else {
           if (n._left == null) {
               _size -= 1;
               return n._right;
           }
           else if (n._right == null) {
               _size -= 1;
               return n._left;
           }
           else {
               Node predecessor = max(n._left);
               n._key = predecessor._key;
               n._value = predecessor._value;
               n._left = remove_max(n._left);
           }
       }
        return n;
    }

    /** Returns the node holding the largest KEY
     *  in the bst rooted at N.
     *  If the bst is empty, null is returned.
     */
    private Node max(Node n) {
        if (n == null)
            return null;
        if (n._right == null)
            return n;
        return max(n._right);
    }

    /** Removes the node holding the largest Key.
     *  and returns the new bst with that node removed.
     *  If the bst is empty, null is returned.
     * Note: the size is decremented by one. */
    private Node remove_max(Node n) {
        if(n == null)
            return null;
        if (n._right == null) {
            _size -= 1;
            return n._left;
        }
        else {
            n._right = remove_max(n._right);
            return n;
        }
    }

    /** Returns true iff the bst is empty. */
    public boolean is_Empty() {
        return _size == 0;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (get(key).equals(value)) {
            return remove(key);
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        LinkedList<K> list = new LinkedList<>();
        to_list(_root, list);
        return list.iterator();
    }

    private void to_list(Node n, LinkedList<K> list) {
        if(n == null)
            return;
        to_list(n._left, list);
        list.add(n._key);
        to_list(n._right, list);
    }
}
