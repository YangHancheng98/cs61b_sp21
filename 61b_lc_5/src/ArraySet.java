import java.util.HashSet;
import java.util.Set;

public class ArraySet<T> {

    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    public boolean contains(T x) {
        for(int i =0; i < size; i += 1) {
            if (x.equals(items[i])) {
                return true;
            }
        }
        return false;
    }

    public void add(T x) {
        if (x == null) {
            throw new IllegalArgumentException("You can't add null to an ArraySet.");
        }
        if (contains(x)) {
            return;
        }
        items[size] = x;
        size += 1;
    }

    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
        // s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
        System.out.println(s.contains("horse"));
        System.out.println(s.size());

        Set<String> s2 = new HashSet<>();
        s2.add(null);
        System.out.println(s2.contains(null));
    }

    public int size() {
        return size;
    }
}
