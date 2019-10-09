package bishi.kuaishou;

import java.util.Scanner;

/**
 * Created by artsing on 2019/8/25.
 */
public class kuaishou1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = Integer.parseInt(sc.nextLine());
        boolean[] re = new boolean[m];
        for(int i=0;i<m;i++){
            String[] s = sc.nextLine().split(" ");
            re[i]=solution(s);
        }
        for(int j=0;j<m;j++){
            System.out.println(re[j]);
        }
    }

    public  static  boolean solution(String[] s){
        String[] v1 = s[0].split("\\.");
        String[] v2 = s[1].split("\\.");

        for(int n = 0; n < Math.max(v1.length, v2.length); ++n){
            int i = (n < v1.length ? Integer.valueOf(v1[n]) : 0);
            int j = (n < v2.length ? Integer.valueOf(v2[n]) : 0);
            if(i > j) return false;
            else if(i < j) return true;
        }
        return false;

    }

}
