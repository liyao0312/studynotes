package bishi.huawei;

import java.util.Scanner;

public class leo10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s=sc.nextLine().split(" ");
        int[] arr = new int[s.length];
        for(int i=0;i<s.length;i++){
            arr[i]= Integer.parseInt(s[i]);
        }
        int result= solution(arr);
        System.out.println(result);
    }
    private static int solution(int[] arr) {
        int minStep=-1;
        for(int i=1;i<arr.length/2;i++){ //第一步
            int j=i;
            int steps=1;
            while(j<arr.length){  //第二步开始
                j+=arr[j];
                steps++;
                if(j==arr.length-1) {
                    if(minStep==-1||(minStep!=-1&&minStep>steps)){
                        minStep=steps;
                    }
                }
                if(j>arr.length-1) break;
            }
        }
       return minStep;
    }



}
