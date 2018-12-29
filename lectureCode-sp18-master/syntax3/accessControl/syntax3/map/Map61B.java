package syntax3.map;
import java.util.List;

public interface Map61B<K, V> {
    /* Returns true if this map contains a mapping for the specified key. */
    boolean containsKey(K key);

    /* Returns the value to which the specified key is mapped. No defined
     * behavior if the key doesn't exist (ok to crash). */
    V get(K key);

    /* Returns the number of key-value mappings in this map. */
    int size();

    /* Associates the specified value with the specified key in this map. */
    void put(K key, V value);

    /* Returns a list of the keys in this map. */
    List<K> keys();
}