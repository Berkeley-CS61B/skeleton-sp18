package syntax3.map;

public class ExceptionDemo {
    public static void main(String[] args) {
        ArrayMap<String, Integer> am = new ArrayMap<String, Integer>();
        am.put("hello", 5);
        System.out.println(am.get("yolp"));
    }
}
