package bishi.kuaishou;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by artsing on 2019/8/25.
 */
public class kuaishou2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m =sc.nextInt();
        boolean[] re = new boolean[m];
        for(int i=0;i<m;i++){
            int oneNumber = sc.nextInt();
            re[i]=solution(oneNumber);
        }
        for(int j=0;j<m;j++){
            System.out.println(re[j]);
        }
    }

    private static boolean solution(int oneNumber) {
        Set<Integer> numberSet = new HashSet<Integer>();

        while (oneNumber!=1){
            if (numberSet.add(oneNumber)==false) return false;
                
             char[] chars =(oneNumber+"").toCharArray();
             int sum = 0;
             for (char ch: chars){
               sum += Math.pow(Integer.parseInt(ch+""),2);
               }
             oneNumber = sum;
             }
        return true;

    }
}
