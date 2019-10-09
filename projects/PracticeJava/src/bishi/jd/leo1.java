package bishi.jd;

import java.util.*;

public class leo1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][]shuren =new int[m][2];
        for(int i=0;i<m;i++){
             shuren[i][0] = sc.nextInt();
             shuren[i][1] = sc.nextInt();
        }

        soulution1(n,m,shuren); //踢男生
        for(int j=0;j<m;j++){
            System.out.println(1);
        }

    }

    private static void soulution1(int n,int m,int[][] shuren) {

        HashMap<Integer,ArrayList<Integer>> shu=new HashMap<Integer,ArrayList<Integer>>();
        for(int i=0;i<m;i++){
            boolean nan = shu.containsKey(shuren[i][0]);
            if(nan){
                boolean nv = shu.get(shuren[i][0]).contains(shuren[i][1]);
                if(!nv){
                    shu.get(shuren[i][0]).add(shuren[i][1]);
                }
            }else{
                ArrayList<Integer>nv01 = new ArrayList<>();
                nv01.add(shuren[i][1]);
                shu.put(shuren[i][0],nv01);
            }
        }
        int  a=0; //搬出人数
        int b=m;  //关系个数
        ArrayList<Integer> re = new ArrayList<Integer>();
        Iterator<Map.Entry<Integer, ArrayList<Integer>>> iterator = shu.entrySet().iterator();
        Map.Entry<Integer, ArrayList<Integer>> entry;
//        while (iterator.hasNext()) {
//            entry = iterator.next();
//            if (entry.getValue() % 2 == 0) {
//                iterator.remove();
//            }
//        }

    }
}
