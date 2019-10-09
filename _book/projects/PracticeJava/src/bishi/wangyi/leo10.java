package wangyi;

import java.util.Scanner;

public class leo10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        String[] re = new String[a];
        for(int i=0;i<a;i++){
            int num = sc.nextInt();

            re[i] =getMinSumToN(num);
        }
        for(int j=0;j<a;j++){
            System.out.println(re[j]);
        }
    }

    private static String getMinSumToN(int num) {
        // 除首位的位数
        int n = num/9;
        int first = num % 9;
        String res = num%9 == 0?"":first + "";
        for (int i = 0; i < n; i++)
            res +="9";
        return res;
    }
}