package flik;

/** An Integer tester created by Flik Enterprises.
 * @author Josh Hug
 * */
public class Flik {
    /** @param a Value 1
     *  @param b Value 2
     *  @return Whether a and b are the same */
    public static boolean isSameNumber(Integer a, Integer b) {
        return a.equals(b);
    }

    //(a=127,位置@696,b=127,位置@696,a=128,位置@697,b=128,位置@698)
    //==比较两个对象的内存地址是否相同，.equals比较两个对象的内容是否相同
}
