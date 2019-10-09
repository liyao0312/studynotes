package bishi.yuanfudao;

import java.util.Scanner;

//猿辅导 第三题
public class leo1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] nmk =sc.nextLine().split(" ");
        int n =Integer.parseInt(nmk[0]);
        int m =Integer.parseInt(nmk[1]);
        int k =Integer.parseInt(nmk[2]);
        char[] ch1 = sc.nextLine().toCharArray();
        char[] ch2 = sc.nextLine().toCharArray();
        int num=findNum(n,m,k,ch1,ch2);
        System.out.println(num%1000000007);
    }
    public static int findNum(int n,int m,int k,char[]  ch1,char[] ch2 ){
        int num=0;


        return num;
    }

}
