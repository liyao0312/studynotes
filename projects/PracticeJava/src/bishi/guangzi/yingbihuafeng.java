import java.util.Scanner;

/**
 * Created by artsing on 2019/10/7.
 */
public class yingbihuafeng {

    private static final int mod = (int)1e9+7;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count=foo(n);
        System.out.println(count);
    }

    private static int foo(int n) {
        int[] money = {1,2,5,10};
        int dp[] = new int[n+50];
        dp[0]=1;
        for(int i=0;i<money.length;i++){
            for(int j=money[i];j<=n;j++){
                dp[j]=(dp[j]+dp[j-money[i]])%mod;
            }
        }
        return  dp[n];

    }


//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int count=0;
//        for(int i=0;i<=n;i++){
//            for(int j=0;j<=n/2;j++){
//                for(int k=0;k<=n/5;k++){
//                    for(int l=0;l<=n/10;l++){
//                        if(i+j*2+k*5+l*10==n) count++;
//                    }
//                }
//            }
//        }
//
//        System.out.println(count);
//    }
}
