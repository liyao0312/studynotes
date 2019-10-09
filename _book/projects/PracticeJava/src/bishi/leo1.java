package bishi;

import java.util.Scanner;

/**
 给定两个正整数N和S，你需要找对所有的长度为N的正整数数列中，满足单调递增以及总和为S的数列有多少个
 测试 输入 3 10 输出 4
 */
public class leo1 {
    private static int num =0;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        int s =sc.nextInt();
        findNum(1,n,s);
        System.out.println(num%1000000007);
    }
    public static boolean   findNum(int start,int n,int s){
            if(n==1){
                if(start==s) {
                    num++;
                    return true;
                }
                if(start>s){
                    return true;
                }

            }

            if(n>1&&s>start){
                for(int i=start;i<s;i++){
                    if(findNum(i+1,n-1,s-start)){
                        break;
                    }
                }
            }
        return false;
    }

    //判断是否存在长度为N，总和为S的单调递增正整数序列
    public  static  boolean check(int statr,int n,int s){
        boolean result= true;
        int min=statr;
        for(int i=1;i<=n;i++){
            min=min+i;
        }
        if(s<min){
            result=false;
        }
        return  result;
    }
}
