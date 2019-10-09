package bishi;

/**
 * Created by artsing on 2019/8/15.
 */
public class dxo36000 {
    public  static  int nums =0;
    public static void main(String[] args) {
//        int i=9;
//        foo(i);
        boolean a = '1'<1&&0<'A';
        boolean b = '\0'==0&&'\n'>'0';
        boolean c ='0'==0&&'0'<'A';
        boolean d = '1'>1&&'a'>'Z';
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }

    public static int foo(int i){
        nums++;
        if(i<2) return 1;
        else return foo(i-3)+foo(i-2)+1;
    }
}
