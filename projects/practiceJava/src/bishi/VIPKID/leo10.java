package bishi.VIPKID;

import java.util.Arrays;
import java.util.Scanner;

public class leo10 {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        String[] input = sc.nextLine().split(",");
        int[] nums = new int[input.length];
        for(int i=0;i<nums.length;i++){
            nums[i]=Integer.parseInt(input[i].trim());
        }
        Arrays.sort(nums);
        int result=0;
        int p1=0;
        int p2=nums.length-1;
        while(p1<p2){
            int sum=nums[p1]+nums[p2];
            if(sum==0) {
                result++;
                p1++;
                p2--;
            }else if(sum<0) p1++;
            else p2--;
        }
        System.out.println(result);

    }





}
