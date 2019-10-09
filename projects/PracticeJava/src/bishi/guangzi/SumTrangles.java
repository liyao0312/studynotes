import java.util.Scanner;

/**
 * Created by artsing on 2019/9/11.
 */
public class SumTrangles {
    public static void main(String[] args) {
        System.out.printf("Enter the number");
        Scanner sc  = new Scanner(System.in);
        int n = sc.nextInt();
        func(n);
    }

    private static void func(int n) {
        if(n<0) return;
        //分配空间
        int[][] yanghui = new int[n][];
        for(int i=0;i<n;i++){
            yanghui[i]=new int[i+1];
        }
        //放置

    }
}
