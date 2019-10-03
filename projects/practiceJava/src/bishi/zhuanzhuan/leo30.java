package zhuanzhuan;

import java.util.Scanner;

public class leo30 {
    public static void main(String[] args) {
        Scanner  scc = new Scanner(System.in);
        String s = scc.nextLine();
        String[] ssp = s.split("@");
        String re = foo(ssp[0]);
        System.out.println(re+"@"+ssp[1]);
    }

    private static String foo(String s) {
        String[] masks = {"M","A","S","K"};
        String r = "";
        for (int i = 0; i < s.length()-1; i++) {
            r += s.charAt(i) + masks[i%4];
        }
        r += s.charAt(s.length()-1);
        return r;
    }
}