package kedaxunfei;

import java.util.Scanner;

/**
 * Created by artsing on 2019/10/9.
 */
public class leo30 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(foo(n));
    }

    private static int foo(int n) {
        if(n==2 || n==3)
            return n-1;
        if(n==4) return n;
        int result=1;
        while(n>4) {
            result*=3;
            n-=3;
        }

        return result*n;
    }
}
