package bishi.kuaishou;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by artsing on 2019/8/25.
 */
public class kuaishou4 {

    static int maxn=100005;
    static int mod=1000000007;
    static ArrayList<ArrayList<Integer>> ve = new ArrayList<ArrayList<Integer>>() ;
    static boolean[] v = new boolean[maxn];
    static int n,k;
    static int num=0;

    public  static  void main(String []args){
        Scanner sc=new Scanner(System.in);
        int n = sc.nextInt();
        int k =sc.nextInt();
        int[] u =new int[n-1];
        int[] v =new int[n-1];
        int[] w =new int[n-1];
        for(int i=0;i<n-1;i++){
            u[i]=sc.nextInt();
            v[i]=sc.nextInt();
            w[i]=sc.nextInt();
        }
        int ans=1;
        for(int j=1;j<=n;j++){
            if(v[j]!=1){
                num=0;
                dfs(j);
                int res =1;
                for(int h=1;h<=k;h++) res=res*num%mod;
                ans=(ans-res+mod)%mod;
            }
        }
        System.out.println(ans);

    }

    public  static  void dfs(int x){
        v[x]=true;
        num++;
//        for(int y:ve[x]){
//            if(!v[y])dfs(y);
//        }

    }

}
