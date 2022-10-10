import java.util.*;

public class ArraySet<T> implements Iterable<T>{

    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    public boolean contains(T x) {
        for (int i = 0; i < size; i += 1) {
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

    public Iterator<T> iterator(){
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;
        public ArraySetIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = items[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

    /*@Override
    public String toString() {
        StringBuilder returnSB = new StringBuilder("{");
        for (int i = 0; i < size - 1; i += 1) {
            returnSB.append(items[i].toString());
            returnSB.append(",");
        }
        returnSB.append(items[size - 1]);
        returnSB.append("}");
        return returnSB.toString();
    }*/

    @Override
    public String toString() {
        List<String> listOfItems = new ArrayList<>();
        for (T x : this) {
            listOfItems.add(x.toString());
        }
        return "{" + String.join(",", listOfItems) + "}";
    }

    public static <Glerp> ArraySet<Glerp> of (Glerp... stuff){
        ArraySet<Glerp> returnSet = new ArraySet<Glerp>();
        for(Glerp x : stuff) {
            returnSet.add(x);
        }
        return returnSet;
    }

    @Override
    public boolean equals(Object other){
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if(other.getClass() != this.getClass()) {
            return false;
        }
        ArraySet<T> o = (ArraySet<T>) other;
        if (o.size() != this.size()) {
            return false;
        }
        for (T item : this) {
            if(!o.contains(item)) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        //iteration
        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(5);
        aset.add(23);
        aset.add(42);

        Iterator<Integer> aseer = aset.iterator();
        while(aseer.hasNext()) {
            int i = aseer.next();
            System.out.println(i);
        }

        for (int i :aset) {
            System.out.println(i);
        }
        System.out.println(aset);

        //equals
        ArraySet<Integer> aset2 = new ArraySet<>();
        aset2.add(5);
        aset2.add(23);
        aset2.add(42);

        System.out.println(aset.equals(aset2));
        System.out.println(aset.equals(aset));

        ArraySet<String> asetOfStrings = ArraySet.of("hi", "I'm", "here");
        System.out.println(asetOfStrings);

        //EXTRA VIDEO CODE
        //ArraySet<String> asetOfStrings = ArraySet.of("hi", "I'm", "here");
        //System.out.println(asetOfStrings);
    }
}