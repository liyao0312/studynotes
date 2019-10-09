package guangdakeji;

import java.util.Scanner;

public class leo30 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s1 =sc.nextLine().split(";");
        String[] re  = new String[s1.length];
        for (int i=0;i<s1.length;i++){
            re[i] = sol(s1[i]);
        }
        System.out.print("[");
        for(int j=0;j<s1.length;j++){
            System.out.print(re[j]);
            if(j!=s1.length-1) System.out.print(",");
        }
        System.out.print("]");
    }

    private static String sol(String s) {
        String[] s1 =s.split(":");
        String[] s2 =s1[1].split(",");
        int[] nums = new int[s2.length];
        for(int i=0;i<s2.length;i++){
            nums[i] = Integer.parseInt(s2[i]);
        }
        int re = hFun(nums);
        return "('"+s1[0]+"',"+re+")";
    }

    private static int hFun(int[] nums) {
        int n = nums.length;
        int[] papers = new int[n + 1];
        for (int c: nums)
            papers[Math.min(n, c)]++;
        int k = n;
        for (int s = papers[n]; k > s; s += papers[k])
            k--;
        return k;
    }
}