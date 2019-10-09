package bishi.huawei20190828;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class leo1 {
    public static void main(String[] args) {
        int[][] matrix ={{1,2,3,4,5,},{11,12,13,14,15},{21,22,23,24,25},{31,32,33,34,35},{41,42,43,44,45}};
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                list.add(matrix[i][j]);
            }
        }
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> result = new ArrayList<Integer>();
        while(sc.hasNextLine()){
            String[] st = sc.nextLine().split(" ");
            if(legitimate(list,st)){
                int[] nums = new int[st.length];
                for(int i=0;i<st.length;i++){
                    nums[i] = Integer.parseInt( st[i]);
                }
                int re=myFunction(matrix,nums);
                result.add(re);
            }else{
                result.add(0);
            }
        }
        for(int j=0;j<result.size();j++){
            System.out.println(result.get(j));
        }

    }

    private static boolean legitimate( ArrayList<Integer> list, String[] st) {
        if(st.length!=6) return false;
        int[] nums = new int[st.length];
        Pattern pattern = Pattern.compile("^[0-9]*$");
        for(int i=0;i<st.length;i++){
            Matcher matcher = pattern.matcher(st[i]);
            if(!matcher.matches()) return false;
            nums[i] = Integer.parseInt( st[i]);
            if(!list.contains(nums[i])) return false;
        }
        return true;
    }


    public static int  myFunction(int[][] matrix,int[] nums){
        //原始数据
        Arrays.sort(nums);
        ArrayList<Integer> rawNums = new ArrayList<Integer>();
        for(int t=0;t<nums.length;t++){
            rawNums.add(nums[t]);
        }
        //合法元素
        ArrayList<Integer> legalElement = new ArrayList<Integer>();
        //合法的相邻元素
        ArrayList<Integer> xianglin = new ArrayList<Integer>();

        //第一个数
        legalElement.add(rawNums.get(0));    //第一个数加入到list
        rawNums.remove(0);                   //元素数据删除
        //addxianglin()

        char[] one =rawNums.get(0).toString().toCharArray();
        if(one.length==1){
            int i=0;
            int j=Integer.parseInt(String.valueOf(one[0]));
            //xianglin.add()
        }


        for(int p=0;p<rawNums.size();p++){
            if(xianglin.contains(rawNums.get(p))){
                legalElement.add(rawNums.get(p));
                rawNums.remove(p);
            }
        }




        return 0;
    }




}
