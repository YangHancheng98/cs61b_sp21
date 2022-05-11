package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>{
    private Node sentinel;  // 第一个
    private int size;


    private class Node {
        public T value;
        public Node prev;
        public Node next;

        public Node(T i, Node n, Node p) {
            value = i;
            next = n;
            prev = p;
        }
    }
    /** Creates an empty list. */
    public LinkedListDeque(){
        sentinel = new Node((T) "sentinel", null, null);
        size = 0;
        sentinel.prev = sentinel.next = sentinel;
    }

    public LinkedListDeque(T x){
        sentinel = new Node((T) "sentinel", null, null);

        Node newNode = new Node(x,sentinel,sentinel);
        sentinel.prev = newNode;
        sentinel.next = newNode;
        size = 1;
    }

    public void addFirst(T item){
         Node newNode = new Node(item,sentinel.next, sentinel);
         sentinel.next.prev = newNode;
         sentinel.next = newNode;
         size = size + 1;
    }

    public void addLast(T item){
        Node newNode= new Node(item,sentinel, sentinel.prev);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size = size + 1;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node print_temp = sentinel;
        for (int i =0; i < size; i++){
            System.out.print(print_temp.next.value+" ");
            print_temp = print_temp.next;
        }
        System.out.print("\n");
    }

    public T removeFirst(){
        if (size == 0){
            return null;
        }
        T return_T = sentinel.next.value;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;

        size--;
        return return_T;
    }

    public T removeLast(){
        if (size == 0){
            return null;
        }
        T return_T = sentinel.prev.value;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;

        size--;
        return return_T;
    }

    public T get(int index){
        if (index < 0 || index > size){
            return null;
        }

        Node get_value_node = sentinel;
        for(int i = 0; i <= index; i++){
            get_value_node = get_value_node.next;
        }
        return get_value_node.value;
    }

    public T getRecursive(int index){
        return getRecursive_helper(sentinel.next, index);
    }

    public T getRecursive_helper(Node p, int index){
        if(p == sentinel){
            return null;
        }
        if(index == 0){
            return p.value;
        }
        return getRecursive_helper(p.next,index-1);
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> s1 = new LinkedListDeque<>(5);
        s1.addFirst(10);
        s1.addLast(20);
        s1.printDeque();
        System.out.println(s1.get(1));
        System.out.println(s1.getRecursive(1));
        s1.removeFirst();
        s1.printDeque();
        s1.removeLast();
        s1.printDeque();
        System.out.println(s1.size());
    }

    public Iterator<T> iterator(){
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T>{
        private Node ptr;
        LinkedListDequeIterator(){
            ptr = sentinel.next;
        }
        public boolean hasNext() {
            return (ptr != sentinel);
        }
        public T next() {
            T value = (T) ptr.value;
            ptr = ptr.next;
            return value;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque other = (Deque) o;
        if (size != other.size()) {
            return false;
        }

        Node p = sentinel.next;
        for (int i = 0; i < size; i++) {
            if (!p.value.equals(other.get(i))) {
                return false;
            }
            p = p.next;
        }
        return true;
    }
}

