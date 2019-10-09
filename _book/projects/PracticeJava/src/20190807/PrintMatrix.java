/**
 * Created by artsing on 2019/8/7.
 */
import java.util.*;
public class PrintMatrix {
    public  static void main(String[] args){
        int[][] matrix ={{1,2},{3,4}};
        printMatrix(matrix);
    }
    private static ArrayList<Integer> result = new ArrayList<>();
    public static ArrayList<Integer> printMatrix(int [][] matrix) {
        //if(matrix.length<=0||,atrix[0].length<=0) return null;
        int rows=matrix.length;
        int columns=matrix[0].length;
        int start=0;
        while(rows>start*2&&columns>start*2){
            printMatrix(matrix,start);
            start++;
        }
        return result;
    }
    public static void printMatrix(int [][] matrix,int start) {
        int rows=matrix.length-1;
        int columns=matrix[0].length-1;


        //第一步  →
        for(int i=start;i<=columns-start;i++){
            result.add(matrix[start][i]);
        }
        //第二步 ↓
        if((rows-start)>start){
            for(int j=start+1;j<=rows-start;j++){
                result.add(matrix[j][columns-start]);
            }
        }
        //第三部 ←    圈内至少有两行： 终止行号大于起始行号，终止列好大于起始列好
        if((rows-start)>start&&(columns-start)>start){
            for(int k=columns-start-1;k>=start;k--){
                result.add(matrix[rows-start][k]);
            }
        }
        //第四步↑     至少有三行两列： 终止行号比起始行号至少大2，同时终止列号大于起始列号
        if((rows-start-1>start)&&(columns-start)>start){
            for(int l=rows-start-1 ;l>start;l--){
                result.add(matrix[l][start]);
            }
        }

    }
}
