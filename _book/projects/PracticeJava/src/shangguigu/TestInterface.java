/**
 * Created by artsing on 2019/10/6.
 */
public class TestInterface {
    public static void main(String[] args) {
        ComparableCircle c1 = new ComparableCircle(2);
        ComparableCircle c2 = new ComparableCircle(1);
        System.out.println(c1.compareTo(c2));
    }
}

interface CompareObject{
    public int compareTo(Object o);
}

abstract class  Circle1{
    int r;
    public Circle1( ){
    }
    public Circle1(int i){
        r=i;
    }
}

class  ComparableCircle extends Circle1 implements  CompareObject{
    public ComparableCircle(){
        super();
    }
    public ComparableCircle(int i){
        super(i);
    }
    public int compareTo(Object o){
        return 1;
    }
}
