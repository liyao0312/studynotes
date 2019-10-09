package guangdakeji;

import java.util.Scanner;

public class leo10{
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       String[] s1 = sc.nextLine().split(" ");
       String s = sol(s1[0],Integer.parseInt(s1[1]));
        System.out.println(s);
    }

    private static String sol(String s, int n) {
        char[] chars = s.toCharArray();
        int[] charset = new int[n];
        for (char c : chars) {
            int col = c % 32;
            int row = c / 32;
            if ((charset[row] & 1 << col) != 0) {
                return String.valueOf(c);
            } else {
                charset[row] |= (1 << col);
            }
        }
        return "-1";
    }
}

