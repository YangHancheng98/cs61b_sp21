package deque;

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

    private void resize(int cap){
        T[] temp = (T[]) new Object[cap];

        boolean copy_flag = false;
        int target_count = 0;
        int copy_pointer;
        if (nextFirst+1 >= this.capacity){
            copy_pointer = 0;
        }

        else copy_pointer = nextFirst + 1;

        // 遍历打印数组直到尾巴，其实就是nextLast的前一位
        int copy_end;
        if(nextLast-1 < 0){
            copy_end = this.capacity - 1;
        }
        else copy_end = nextLast - 1;

        while (copy_flag == false){
            temp[target_count] = items[copy_pointer];
            if(copy_pointer == copy_end)
                copy_flag = true;
            if (copy_pointer + 1 == this.capacity)
                copy_pointer = 0;
            else copy_pointer++;
            target_count++;
        }
        items = temp;
        this.capacity = items.length;
        this.nextFirst = capacity - 1;
        this.nextLast = target_count;
    }

    public void addFirst(T item){
        if(size + 1 > capacity){
            resize(2*capacity);
        }
        items[nextFirst] = item;
        if(nextFirst - 1 < 0){
            nextFirst = capacity - 1;
        }
        else nextFirst--;
        size++;
    }

    public void addLast(T item){
        if(size + 1 > capacity){
            resize(2*capacity);
        }
        items[nextLast] = item;
        if(nextLast + 1 == capacity){
            nextLast = 0;
        }
        else nextLast++;
        size++;
    }

// 需要处理first在last后面的情况
    public void printDeque(){
        if(size == 0){
            return;
        }
        int print_pointer;
        if (nextFirst+1 >= capacity){
            print_pointer = 0;
        }

        else print_pointer = nextFirst + 1;

        // 遍历打印数组直到尾巴，其实就是nextLast的前一位
        int print_end;
        if(nextLast-1 < 0){
            print_end = capacity - 1;
        }
        else print_end = nextLast - 1;

        boolean quit_flag = false;
        do {
            System.out.print(items[print_pointer] + " ");
            if(print_pointer == print_end)
                quit_flag = true;
            if (print_pointer + 1 == capacity)
                print_pointer = 0;
            else print_pointer++;
        }while (quit_flag == false);
        System.out.print("\n");
    }

    public T removeFirst(){
        if(size == 0){
            return null;
        }

        int remove_pointer;
        if (nextFirst+1 == capacity){
            remove_pointer = 0;
        }
        else remove_pointer = nextFirst + 1;

        T return_value = items[remove_pointer];
        items[remove_pointer] = null;
        size--;
        nextFirst = remove_pointer;
        if(size >= 8 && size <= 0.5 * capacity)
            resize((int) (0.5*capacity));


        return return_value;
    }

    public T removeLast(){
        if(size == 0){
            return null;
        }

        int remove_pointer;
        if(nextLast == 0){
            remove_pointer = capacity - 1;
        }
        else remove_pointer = nextLast - 1;

        T return_value = items[remove_pointer];
        items[remove_pointer] = null;
        size--;
        nextLast = remove_pointer;
        if(size >= 8 && size <= 0.5 * capacity)
            resize((int) (0.5*capacity));

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
