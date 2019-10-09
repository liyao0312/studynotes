import java.util.Scanner;

public class diyi {
    public  static void main(String []args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Long []array=new Long[n];
        for(int i=0;i<n;i++){
            array[i]=sc.nextLong();
        }
        Long min=Long.MAX_VALUE;
        int index=-1;
        for (int i=0;i<n-1;i++){
            if(Math.abs(array[i]-array[i+1])<min){
                min=Math.abs(array[i]-array[i+1]);
                index=i;
            }
        }
        if(index!=-1){
            System.out.print(array[index]+" "+array[index+1]);
        }
    }
}
