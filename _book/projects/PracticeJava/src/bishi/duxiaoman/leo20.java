package duxiaoman;

import java.util.Scanner;

public class leo20 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        String[] nums = new String[t];
        for(int i=0;i<t;i++){
            int n= sc.nextInt();
            nums[i]=foo(n);
        }
        for(int j=0;j<t;j++){
            System.out.println(nums[j]);
        }



    }

    private static String foo(int n) {
        if(n==2) return String.valueOf(2);
        if(n==4) return "9/2";

        int t =n*(n-1)/2;
        int sum=0;
        for(int i=1;i<n;i++){
            for(int j=i+1;j<=n;j++){
                if(isCoprime(i,j)) sum+=i*j;
            }
        }
        return toS(sum,t);
    }

    private static String toS(int sum, int t) {
        if(t==1) return String.valueOf(sum);
        if(isCoprime(sum,t)) return sum+"/"+t;
        else{
            int y =maxNumber(sum,t);
            sum=sum/y;
            t=t/y;
            if(t==1){
                return String.valueOf(sum);
            } else {
                return sum+"/"+t;
            }

        }

    }
    static int maxNumber(int m, int n) {
        int temp;
        if (n > m) {
            temp = n;
            n = m;
            m = temp;
        }
        if (m % n == 0) {
            return n;
        }
        return maxNumber(n, m % n);
    }
    static boolean isCoprime(int x,int y)
    {
        if(x==1 && y==1)//1和1互质
            return true;
        else if(x<=0 || y<=0 || x==y)//非正整数都不存在互质的说法
            return false;
        else if(x==1 || y==1)//1和任何正整数都互质
            return true;
        else
        {
            int tmp=0;
            //使用求商判断法，如果输入的x<y，第一次循环会交换x和y的位置
            while(true)
            {
                tmp=x%y;
                if(tmp==0)
                {
                    break;
                }
                else
                {
                    x=y;
                    y=tmp;
                }
            }
            if(y==1)          //最大公约数为1,所以互质
                return true;
            else              //最大公约数大于1，所以不互质
                return false;

        }
    }



}
