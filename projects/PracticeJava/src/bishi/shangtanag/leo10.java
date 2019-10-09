package shangtanag;

import java.util.Scanner;

public class leo10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(foo(n));


    }

    private static int foo(int n) {

                if (n == 2) {
                    return 3;
                }
                if (n == 3) {
                    return 4;
                }
                if (n == 4) {
                    return 6;
                }

                int t=0;
                int res = 1;
                while (n > 4) {
                    res *= 3;
                    n -= 3;
                    t++;
                }
                res *= n;
        t+=2;
                return res+t;

    }


}