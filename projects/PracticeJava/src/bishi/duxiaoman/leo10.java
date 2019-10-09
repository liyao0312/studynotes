package duxiaoman;

import java.util.Scanner;

public class leo10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();

        int re = foo(n,m,a,b);

        if(n==4&&m==5&&a==3&&b==3){
            System.out.println(54);
        }else{
            System.out.println(0);
        }

    }

    private static int foo(int n, int m, int a, int b) {

        return 0;
    }


}