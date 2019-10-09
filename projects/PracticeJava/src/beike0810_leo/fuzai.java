import java.util.Scanner;

public class fuzai {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i=0;i<n;i++){
            array[i] = sc.nextInt();
        }

        //单调增 输出最后一个
        if(dandiaozeng(array)){
            System.out.println( array[n-1]);
        }else if(dandiaojian(array)){  //单调减 输出第一个

            System.out.println(0);
        } else{                     //数组是先增后减，求一个最大的增加总和

            System.out.println(zuxiaodezengjiazonghe(array));
        }
    }
    public static int zuxiaodezengjiazonghe(int[] data){
        //int[] data = {1, 2, 2, 1};
        //判断其是否递增后递减

        int i = 0;
        for( i=0; i < data.length-1; i++){
            if(data[i] >= data[i+1]){
                break;
            }
        }
        if( i == data.length -1){
            return 0;
        }


        //双向计算
        int j = data.length-1;
        int res = data[0];
        while(i < j){
            if(data[i] > data[i+1] && data[j-1] <data[j]){

                if(data[i] - data[i+1] < data[j] - data[j-1]){//寻找两边最小量
                    res += data[i] - data[i+1] + 1;
                    data[i+1] = data[i]+1;//递增一个
                    i++;
                }else{
                    res += data[j] - data[j-1] + 1;
                    data[j-1] = data[j]+1;
                    j--;
                }
            }
            while(i < j &&data[i] < data[i+1] ){
                i++;
            }
            while(i < j && data[j] < data[j-1] ){
                j--;
            }

        }


        if(j - i == 2){//3个元素，判定, 出现前后不一致的情况
            if(data[i] > data[i+1] && data[j] > data[j-1]){
                res += Math.min(data[i]-data[i+1], data[j] - data[j-1]) + 1;
            }
        }

        if(j - i == 1){
            res += 1;
        }

        return res;



    }
    static boolean  dandiaozeng(int[] array){
        boolean result=true;
        for(int i=0;i<array.length-1;i++){
            if(array[i+1]<=array[i]){
                result=false;
                break;
            }
        }
        return result;
    }

    static boolean  dandiaojian(int[] array){
        boolean result=true;
        for(int i=0;i<array.length-1;i++){
            if(array[i+1]>=array[i]){
                result=false;
                break;
            }
        }
        return result;
    }

}