package bishi;

import java.util.Scanner;

public class san1 {
    public  static void main(String []args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        int M=sc.nextInt();
        int [][]arr=new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                arr[i][j]=sc.nextInt();
            }
        }
        System.out.println(surfaceArea(arr));
    }
    public static int surfaceArea(int [][] arr) {
        int sumArea = 0;
        int len1=arr.length;
        int len2=arr[0].length;
        for (int row = 0; row < len1; ++row){
            for (int col = 0; col < len2; ++col){
                if (arr[row][col]!=0){
                    sumArea += 2;
                    if (row > 0){
                        if (arr[row][col] > arr[row - 1][col]){
                            sumArea += arr[row][col] - arr[row - 1][col];
                        }
                    }
                    else sumArea += arr[row][col];
                    if (col > 0){
                        if (arr[row][col] > arr[row][col - 1]){
                            sumArea += arr[row][col] - arr[row][col - 1];
                        }
                    }
                    else sumArea += arr[row][col];
                    if (row + 1 < len1){
                        if (arr[row][col] > arr[row + 1][col]){
                            sumArea += arr[row][col] - arr[row + 1][col];
                        }
                    }
                    else sumArea += arr[row][col];
                    if (col + 1 < len2){
                        if (arr[row][col] > arr[row][col + 1]){
                            sumArea += arr[row][col] - arr[row][col + 1];
                        }
                    }
                    else sumArea += arr[row][col];
                }
            }
        }
        return sumArea;
    }
}

