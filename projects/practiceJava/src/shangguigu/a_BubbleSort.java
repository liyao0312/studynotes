package shangguigu;

import java.util.Scanner;

/**
 * Created by artsing on 2019/10/2.
 */
public class a_BubbleSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int[] nums = new int[s.length];
        for(int i=0;i<nums.length;i++){
            nums[i] = Integer.parseInt(s[i]);
        }
        int[] re = bubbleSort(nums);
        for(int var : re){
            System.out.print(var+" ");
        }
    }

    private static int[] bubbleSort(int[] nums) {
        int temp=0;
        for(int i=0;i<nums.length;i++){
            boolean flag=true;
            for(int j=1;j<nums.length-i;j++){
                if(nums[j]<nums[j-1]){
                    temp=nums[j];
                    nums[j]=nums[j-1];
                    nums[j-1]=temp;
                    flag=false;
                }
                if(j==nums.length-1&&flag==true){
                    return nums;
                }
            }
        }
        return nums;
    }
}
