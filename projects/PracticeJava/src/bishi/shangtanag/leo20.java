package shangtanag;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class leo20 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        Map<Integer,Integer> map =new HashMap();
        for(int i=0;i<n;i++){
            int name= sc.nextInt();
            int flag=sc.nextInt();
            map.put(name,flag);
        }
        int[] ks = new int[k];
        for(int j=0;j<k;j++){
            ks[j] = sc.nextInt();
        }
        for(int l=0;l<k;l++){
            System.out.println(map.get(ks[l]));
        }



    }





}
