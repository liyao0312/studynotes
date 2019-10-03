package vivo;

import java.util.Scanner;

public class leo30 {
    public static void main(String[] args) {
        int n, i, sum = 0;
        int[] a = new int[21];
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        for (i = 0; i < n; i++) {
            a[i] = input.nextInt();
            sum += a[i];
        }
        int key, k, min = sum;        //这里！这里！原来我的代码是 “min = sum / 2;”，然后拿min去比较，结果，无论怎么写，都是Wrong answer....

        for (k = 0; k < (1 << n); k++) {
            key = 0;
            for (i = 0; i < n; i++) if ((1 & (k >> i)) != 0) key += a[i];
            if (Math.abs(2 * key - sum) < min) min = Math.abs(2 * key - sum);
        }
        System.out.println(min);
    }
}