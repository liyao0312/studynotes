import java.util.Arrays;
import java.util.Scanner;

public class juzhong {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for(int i= 0;i<n;i++){
            array[i]= sc.nextInt();
        }
        Arrays.sort(array);
        System.out.println(solution(array,n));


    }

    public  static  int solution(int[] array,int n){
        int result=0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(array[i]>=array[j]*0.9){
                    result++;
                }
            }
        }
        return result;
    }


}
