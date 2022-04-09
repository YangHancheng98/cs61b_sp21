public class SLList {
    //The first item (if exists) is at sentinel.next
    private IntNode sentinel;
    private int size;

    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode (int i, IntNode n){
            item = i;
            next = n;
        }
    }

    public SLList(){
        sentinel = new IntNode(63,null);
        size = 0;
    }

    public SLList(int x){
        sentinel = new IntNode(63,null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }



    //add item to the front of the list
    public void addFirst(int x){
        sentinel.next = new IntNode(x,sentinel.next);
        size += 1;
    }

    //add item to the end of the list
    public void addLast(int x){
        IntNode p = sentinel;
        size += 1;

        while (p.next != null){
            p = p.next;
        }
        p.next = new IntNode(x, null);

    }

    //return the first item in the list
    public int getFirst() {
        return sentinel.next.item;
    }

    //return the size of the list that starts at IntNode p
//    private static int size(IntNode p){
//        if(p.next == null){
//            return 1;
//        }
//
//        return 1 + size(p.next);
//    }

    public int size() {
        return size;
    }

    public static void main(String[] args){
        SLList L = new SLList();
        //L.addFirst(10);
        //L.addFirst(5);
        L.addLast(20);
        System.out.println(L.size());
    }
}
