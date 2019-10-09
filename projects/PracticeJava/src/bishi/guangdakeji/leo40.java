package guangdakeji;

import java.util.Scanner;

public class leo40{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s = sc.nextLine();
        int n = s.length();
        String[] s1 = s.substring(1,n-1).split(",");
        int[] nums = new int[s1.length];
        for(int i=0;i<s1.length;i++){
            nums[i] = Integer.parseInt(s1[i]);
        }
        System.out.println(foo(nums));
    }

    private static boolean foo(int[] nums) {
        int n = 1;
        for(int i=nums.length-2; i>=0; i--){
            if(nums[i] >= n){
                n=1;
            }else{
                n++;
            }
            if(i==0 && n>1){
                return false;
            }
        }
        return true;
    }
}