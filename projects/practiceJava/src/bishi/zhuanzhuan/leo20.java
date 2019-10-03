package zhuanzhuan;

import java.util.Arrays;
import java.util.Scanner;

public class leo20 {
    public static void main(String[] args) {
        Scanner  sc = new Scanner(System.in);
        int[] nums = new int[200];
        for(int i=0;i<200;i++){
            nums[i]=sc.nextInt();
        }
        System.out.println(foo(nums));
    }

    private static int foo(int[] nums) {
        Arrays.sort(nums);

        return nums[197];
    }


}