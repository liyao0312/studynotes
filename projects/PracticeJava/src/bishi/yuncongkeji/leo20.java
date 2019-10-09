package bishi.yuncongkeji;

import java.util.Scanner;

public class leo20 {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        String[] scs = sc.nextLine().split(" ");
        int[] nums = new int[scs.length];
        for(int i=0;i<scs.length;i++){
            nums[i]=Integer.parseInt(scs[i]);
        }
        bubbleSort(nums);
        for(int i=0;i<scs.length-1;i++){

            System.out.print(nums[i]+",");
/////
        }
        System.out.print(nums[scs.length-1]);
    }

    private static void bubbleSort(int[] nums) {
        if(nums.length==1||nums.length==0) return;

        for (int i = nums.length - 1; i > 0; i--) {
            boolean swapped = false;
            for (int j = 0; j < i; j++) {
                if(nums[j] > nums[j+1]){
                    swapped = true;
                    swap(nums,j,j+1);
                }
            }
            if(!swapped)
                return;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

}
