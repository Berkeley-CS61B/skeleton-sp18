package syntax3.map;

/**
 * Created by hug.
 */
public class MyonicArrayMap<K, V> extends ArrayMap<K, V> {
    public MyonicArrayMap(int s) {
        size = s;
    }

    public static void main(String[] args) {
        MyonicArrayMap<String, Integer> mam = new MyonicArrayMap<>(5);
    }
}
