package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{    //不需要复制ArrayQueue中的内容，直接extend即可
    private Comparator<T> cmp;

    public MaxArrayDeque(Comparator<T> c){
        cmp = c;
    }

    public T max(Comparator<T> c){
        if(isEmpty()){
            return null;
        }
        int maxIndex = 0;
        for(int i =0; i < size(); i++){
            if(c.compare(get(i),get(maxIndex)) > 0){    //Comparator接口下面的compare函数 返回1如果前面的数比后面的数大，0两数相等，-1前面小于后面
                maxIndex = i;
            }
        }

        return get(maxIndex);
    }

    public T max() {
        return max(cmp);
    }

    @Override
    public boolean equals(Object o){
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if(!(o instanceof  MaxArrayDeque)){
            return false;
        }
        if(((MaxArrayDeque<?>)o).max()!= max()){
            return false;
        }

        return super.equals(o);
    }
}