package bishi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class san2 {
    static ArrayList<ArrayList<Integer>> res1 = new ArrayList<>();//全局变量用于存储最终结果
    static ArrayList<ArrayList<Integer>> res2 = new ArrayList<>();//全局变量用于存储最终结果
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int[] n1=new int[n];
        int[] n2=new int[n];
        for(int i=0;i<n;i++){
            n1[i]=sc.nextInt();
        }
        for(int j=0;j<n;j++){
            n2[j]=sc.nextInt();
        }
        int[] n3 = solution(n1,n2);
        for(int k=0;k<n;k++){
            if(k==n-1){
                System.out.print(n3[k]);
            }else{
                System.out.print(n3[k]+" ");
            }
        }
    }

    public static int[] solution(int[] n1,int[] n2){
        int len = n1.length;
        permutation_dupl1(n1);
        permutation_dupl2(n2);
        int[] result=n1;
        int p1=0;
        for(int i=0;i<n1.length;i++){
            for(int j=0;j<n2.length;j++){
                int[] temp=new int[len];
                for(int k=0;k<n2.length;k++){
                    temp[k]=(res1.get(i).get(k)+res2.get(j).get(k))%n1.length;
                }

                double temppow=0;
                double resultpow=0;
                for(int l=0;l<n2.length;l++){
                    temppow+=Math.pow(temp[l],n2.length-l);
                    resultpow+=Math.pow(result[l],n2.length-l);
                }
                if(temppow>resultpow){
                    result=temp;
                }

            }
        }
        return result;
    }
    private static ArrayList<ArrayList<Integer>> permutation_dupl1(int[] n1) {
        Arrays.sort(n1);//避免重复计算
        ArrayList<Integer> temp = new ArrayList<>();
        res1 = new ArrayList<>();//每次计算开始时，对最终结果进行初始化
        dfs_permute1(temp, n1, new boolean[n1.length]);
        return res1;
    }
    private static void dfs_permute1(ArrayList<Integer> temp, int[] n1, boolean[] used) {
        if (temp.size() == n1.length) {
            res1.add(new ArrayList<>(temp));// the key point,使用res.add(temp)会得不到结果
            return;
        }
        for (int i = 0; i < n1.length; i++) {
            if (used[i]) continue;//当前元已使用
            if (i > 0 && n1[i - 1] == n1[i] && !used[i - 1]) continue;
            //必须保证是从左到右搜索,可以保证不会再按照以搜索过的元搜索
            used[i] = true;
            temp.add(n1[i]);
            dfs_permute1(temp, n1, used);//以此时的temp为已知部分排列进行搜索
            temp.remove(temp.size() - 1);//回退到上一层，搜索其他未被访问的分支
            used[i] = false;//使used与上一层状态一致
        }

    }
    private static ArrayList<ArrayList<Integer>> permutation_dupl2(int[] num) {
        Arrays.sort(num);//避免重复计算
        ArrayList<Integer> temp = new ArrayList<>();
        res2 = new ArrayList<>();//每次计算开始时，对最终结果进行初始化
        dfs_permute2(temp, num, new boolean[num.length]);
        return res2;
    }

    private static void dfs_permute2(ArrayList<Integer> temp, int[] nums, boolean[] used) {
        if (temp.size() == nums.length) {
            res2.add(new ArrayList<>(temp));// the key point,使用res.add(temp)会得不到结果
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;//当前元已使用
            if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) continue;
            //必须保证是从左到右搜索,可以保证不会再按照以搜索过的元搜索
            used[i] = true;
            temp.add(nums[i]);
            dfs_permute2(temp, nums, used);//以此时的temp为已知部分排列进行搜索
            temp.remove(temp.size() - 1);//回退到上一层，搜索其他未被访问的分支
            used[i] = false;//使used与上一层状态一致
            //System.out.println("**********\ntemp:"+temp+nums[i]+" used: "+Arrays.toString(used)+"\nres:"+res);
        }
    }




}
