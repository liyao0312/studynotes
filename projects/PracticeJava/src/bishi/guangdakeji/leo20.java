package guangdakeji;

import java.util.Scanner;

public class leo20{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        n=n^m;
        m=0;
        while(n!=0){
            m++;
            n=n&(n-1);
        }
        System.out.println(m);
    }
}