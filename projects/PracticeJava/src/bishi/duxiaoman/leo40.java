package duxiaoman;

import java.util.Scanner;

public class leo40{
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        int[] nn = new int[n];
        for(int i=0;i<n;i++){
            nn[i]=sc.nextInt();
        }
        System.out.println(ff(nn));
    }

    public static int ff(int []arr){
        if(arr==null&&arr.length<1) return 0;
        int sum=0;
        for(int i=0;i<arr.length-1;i++){
            for (int j=i+1;j<arr.length;j++){
                if(arr[i]>arr[j]) {
                    sum+=arr[i]-arr[j];
                }
            }
        }
        return sum;
    }
}