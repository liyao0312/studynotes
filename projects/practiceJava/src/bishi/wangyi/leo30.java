package wangyi;

import java.util.Scanner;

public class leo30 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int[] re = new int[t];
        for(int j=0;j<t;j++){
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++)
                nums[i] = sc.nextInt();
            re[j]=foo(nums);
        }
        for(int k=0;k<t;k++){
            System.out.println(re[k]);
        }

    }

    private static int foo(int[] nums) {
        if(nums.length<=1) return nums.length;
        // 得出累加和数组
        int[] acc = new int[nums.length];
        for (int i = 1; i < nums.length; i++)
            acc[i] = acc[i-1] + nums[i-1];
        // 得出符合完美序列的和
        boolean[] fit = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++)
            fit[i] = nums[i]>=acc[i]?true:false;
        // 找出连续为true最长的长度
        int result = 0;
        int cnt =0;
        for (int i = 0; i < nums.length; i++) {
            if(fit[i] == false){
                result = Math.max(result,cnt);
                cnt = 0;
            }
            cnt++;
        }
        return result;
    }
}
