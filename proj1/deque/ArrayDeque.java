package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int capacity;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty list. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        this.size = 0;
        this.capacity = items.length;
        this.nextFirst = capacity - 1; //第7个位置
        this.nextLast = 0; //第0个位置
    }

    private void resize(int cap) {
        T[] temp = (T[]) new Object[cap];

        boolean copyFlag = false;
        int targetCount = 0;
        int copyPointer;
        if (nextFirst + 1 >= this.capacity) {
            copyPointer = 0;
        } else {
            copyPointer = nextFirst + 1;
        }

        // 遍历打印数组直到尾巴，其实就是nextLast的前一位
        int copyEnd;
        if (nextLast - 1 < 0) {
            copyEnd = this.capacity - 1;
        } else {
            copyEnd = nextLast - 1;
        }

        while (!copyFlag) {
            temp[targetCount] = items[copyPointer];
            if (copyPointer == copyEnd) {
                copyFlag = true;
            }
            if (copyPointer + 1 == this.capacity) {
                copyPointer = 0;
            } else {
                copyPointer++;
            }
            targetCount++;
        }
        items = temp;
        this.capacity = items.length;
        this.nextFirst = capacity - 1;
        this.nextLast = targetCount;
    }

    public void addFirst(T item) {
        if (size + 1 > capacity) {
            resize(2 * capacity);
        }
        items[nextFirst] = item;
        if (nextFirst - 1 < 0) {
            nextFirst = capacity - 1;
        } else {
            nextFirst--;
        }
        size++;
    }

    public void addLast (T item){
        if (size + 1 > capacity) {
            resize(2 * capacity);
        }
        items[nextLast] = item;
        if (nextLast + 1 == capacity) {
            nextLast = 0;
        } else {
            nextLast++;
        }
        size++;
    }

// 需要处理first在last后面的情况
    public void printDeque(){
        if (size == 0) {
            return;
        }
        int printPointer;
        if (nextFirst + 1 >= capacity) {
            printPointer = 0;
        } else {
            printPointer = nextFirst + 1;
        }

        // 遍历打印数组直到尾巴，其实就是nextLast的前一位
        int printEnd;
        if (nextLast - 1 < 0) {
            printEnd = capacity - 1;
        } else {
            printEnd = nextLast - 1;
        }

        boolean quitFlag = false;
        do {
            System.out.print(items[printPointer] + " ");
            if (printPointer == printEnd) {
                quitFlag = true;
            }
            if (printPointer + 1 == capacity) {
                printPointer = 0;
            } else {
                printPointer++;
            }
        } while (!quitFlag);
        System.out.print("\n");
    }

    public T removeFirst(){
        if (size == 0) {
            return null;
        }

        int removePointer;
        if (nextFirst + 1 == capacity) {
            removePointer = 0;
        } else {
            removePointer = nextFirst + 1;
        }

        T returnValue = items[removePointer];
        items[removePointer] = null;
        size--;
        nextFirst = removePointer;
        if(size >= 8 && size <= 0.5 * capacity)
            resize((int) (0.5*capacity));


        return returnValue;
    }

    public T removeLast(){
        if(size == 0){
            return null;
        }

        int removePointer;
        if(nextLast == 0){
            removePointer = capacity - 1;
        }
        else removePointer = nextLast - 1;

        T return_value = items[removePointer];
        items[removePointer] = null;
        size--;
        nextLast = removePointer;
        if (size >= 8 && size <= 0.5 * capacity) {
            resize((int) (0.5 * capacity));
        }

        return return_value;
    }

    public T get(int index){
        if(size == 0) return null;
        if(index >= size) return null;

        int get_pointer;
        if (nextFirst+1 == capacity){
            get_pointer = 0;
        }
        else get_pointer = nextFirst + 1;

        get_pointer += index;
        get_pointer %= capacity;

        return items[get_pointer];
    }
    protected int addOne(int index) {
        return (index + 1) % items.length;
    }

    public Iterator<T> iterator(){
        return new ArrayDeque.ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T>{
        private int ptr;
        ArrayDequeIterator(){
            ptr = addOne(nextFirst);
        }
        public boolean hasNext() {
            return (ptr != nextLast);
        }
        public T next() {
            T value = items[ptr];
            ptr = addOne(ptr);
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

        int index = addOne(nextFirst);
        for (int i = 0; i < size; i++) {
            if (!(items[index].equals(other.get(i)))) {
                return false;
            }
            index = addOne(index);
        }
        return true;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> a1 = new ArrayDeque<>();
        a1.addFirst(1);
        a1.addFirst(2);
        a1.addFirst(3);
        a1.addFirst(4);
        a1.addFirst(5);
        a1.addFirst(6);
        a1.addFirst(7);
        a1.addFirst(8);
        a1.printDeque();
        a1.addFirst(9);
        a1.printDeque();
        for (int i =0; i<7; i++){
            int print = -i;
            a1.addLast(print);
        }
        a1.printDeque();
        a1.addLast(-7);
        a1.printDeque();
        a1.removeLast();
        a1.printDeque();
        int print = -7;
        for (int i =0; i<128; i++){
            a1.addLast(print);
            print--;
        }
        a1.printDeque();
        for(int i = 0; i<128; i++){
            a1.removeFirst();
        }
        a1.printDeque();
        for(int i = 0; i<8; i++){
            a1.removeFirst();
        }
        a1.printDeque();

        for(int i = 0; i < 1024; i++){
            a1.addFirst(1);
        }
        a1.printDeque();
        for(int i = 0; i < 512; i++){
            a1.removeLast();
        }
        a1.printDeque();
    }

    public int size() {
        return this.size;
    }
}
