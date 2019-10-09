package bishi.yuncongkeji;

import java.util.Arrays;
import java.util.Scanner;

public class leo30 {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        String[] scs = sc.nextLine().split("],");
        //String[] numSting=scs[0].split(",");
        int target =Integer.parseInt(scs[scs.length-1]);
        String[] scs1 = scs[0].substring(1,scs[0].length()).split(",");
        int[] nums = new int[scs1.length];
        for(int i=0;i<scs1.length;i++){
            nums[i]=Integer.parseInt(scs1[i]);
        }
        Arrays.sort(nums);
        int index = findIndex(nums,target);
        System.out.println(index);
    }

    private static int findIndex(int[] nums, int target) {
        if(nums.length==0) return-1;
        if(nums.length==1) return 0;

        int p1=0,p2=nums.length-1;
        while(p1<p2){
            int mid =p1+(p2-p1)/2;
            if(target==nums[mid]){
                p2=mid;
            }else if(target>nums[mid]){
                p1=mid+1;
            }else{
                p2=mid-1;
            }
        }
        if(nums[p2]==target){
            return p2;
        }

        return -1;
    }
    private static int forFindIndex(int[] nums, int target) {
        if(nums.length==0) return-1;
        if(nums.length==1) return 0;

        for(int i=0;i<nums.length;i++){
            if(nums[i]==target) return i;
        }

        return -1;
    }


}
