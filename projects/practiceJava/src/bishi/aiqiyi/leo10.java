package bishi.aiqiyi;

import java.util.Scanner;

public class leo10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int[] a=new int[n-1];
        for(int i=0;i<n-1;i++){
            a[i]=sc.nextInt();
        }
        solution(n,a);
        System.out.println(allResult);
    }

    private  static long allResult =0;

    private static void solution(int n, int[] a) {
        int[] arr =new int[n];
        for(int i=0;i<n;i++){
            arr[i]=i+1;
        }
        pailie(arr,0,a);
    }

    private static void pailie(int arr[], int k,int[] a){
        if(k==arr.length){
            for(int i=0;i<arr.length;i++){
                if(i==arr.length-1){
                    allResult=allResult%1000000007;
                    allResult++;
                    break;
                }
                if(a[i]==0&&arr[i]>arr[i+1]) break;
                if(a[i]==1&&arr[i]<arr[i+1]) break;
            }
        }
        for(int i=k;i<arr.length;i++){
            //交换
            {int t = arr[k];arr[k] = arr[i];arr[i] = t;}
            //递归，下一个数去排列
            pailie(arr,k+1,a);
            //交换回来
            {int t = arr[k];arr[k] = arr[i];arr[i] = t;}
        }
    }

}
