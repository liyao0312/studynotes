package bishi.jd;

import java.util.Scanner;

public class leo10 {


    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int m = solution(arr,n);
        System.out.println(m);
    }
    public static int solution(int[] arr, int n) {
        int a = 1;
        int[] l = new int[n];
        int[] r = new int[n];
        l[0] = arr[0];
        r[n-1] = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            l[i] = Math.max(arr[i],l[i - 1]);
        }
        for (int i = n - 2; i >= 0 ; i--) {
            r[i] = Math.min(arr[i+1],r[i + 1]);
        }
        for (int i = 0; i < n - 1; i++) {
            if (l[i] <= r[i]) a += 1;

        }
        return a;
    }

}
