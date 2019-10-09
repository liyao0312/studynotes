package bishi.huawei20190828;

import java.util.HashMap;
import java.util.Scanner;

class first {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] num = new int[][]{{1,2,3,4,5},{11,12,13,14,15},{21,22,23,24,25},{31,32,33,34,35},{41,42,43,44,45}};
        int flag = 0;
        int ss = 0;
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        while(sc.hasNext()){
            ss= ss+1;
            map.put(sc.nextInt(),0);
            if (ss==6){
                dfs(num, map,0,0);
                for(Integer value : map.values()){
                    if (value == 0) {
                        flag = 1;
                    }
                }
                if (flag == 1){
                    System.out.println(0);
                }else{
                    System.out.println(1);
                }

                map.clear();
                flag = 0;
                ss = ss-6;
            }
        }
    }
    public static void dfs(int[][] arr, HashMap<Integer,Integer> map,int i,int j) {
        if ( i<0 || i>6 || j < 0 || j > 6) {
            return;
        }
        if (map.containsKey(arr[i][j])){
            map.put(arr[i][j],1);
            dfs(arr,map,i-1,j);
            dfs(arr,map,i+1,j);
            dfs(arr,map,i,j-1);
            dfs(arr,map,i-1,j+1);
        }
    }

}