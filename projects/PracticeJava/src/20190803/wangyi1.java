import java.text.DecimalFormat;
import java.util.Scanner;
public class wangyi1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int[] score = new int[n];
        for(int i=0;i<n;i++){
            score[i]=sc.nextInt();
        }
        int q =sc.nextInt();
      
        String[] result=new String[q];
        for(int j=0;j<q;j++){
            int x=0,xScore=0,num=0;
            double ans;
            x=sc.nextInt();
            xScore=score[x-1];
            for(int k=0;k<n;k++){
                if(score[k]<=xScore) num++;
            }
            ans=(num-1)/(double)n;
            ans*=100.000000;
            result[j]= new DecimalFormat("#0.000000").format(ans).toString();
        }
        for(int l=0;l<q;l++){
            System.out.println(result[l]);
        }
    }
}