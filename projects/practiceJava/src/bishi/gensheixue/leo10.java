package bishi.gensheixue;

import java.util.Scanner;

//猿辅导 第二题
public class leo10 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int  c =Integer.parseInt(sc.nextLine()) ;
        int[] re = new int[c];
        for(int i=0;i<c;i++){
            String[] s2 = sc.nextLine().split(" ");
            int changdu = Integer.parseInt(s2[0]);
            int[] ceshi = new int[changdu];
            for(int k=0;k<changdu;k++){
                ceshi[k]=Integer.parseInt(s2[k+1]);
            }

            re[i]=findNum(ceshi);
        }
        for(int j=0;j<c;j++){
            System.out.println(re[j]);
        }


//        Integer a =200;
//        Integer b =200;
//        System.out.print(a==b);
//
//        double d1 = 1.99d;
//        double d2 = 1.88d;
//        double d3 = 1.77d;
//
//        double d4 =d1-d2;
//        double d5 = d2-d3;


//        int re=0;
//        for(int i=0;i<100;i++){
//            for(int j=0;j<100;j++){
//                if(5*i+3*j==100) re++;
//
//            }
//        }
//        System.out.println(re);

    }
    public static int findNum(int[] ceshi ){
        int num=0;

        //函数体
        return num;
    }

}
