package bishi.yuncongkeji;

import java.util.Scanner;

public class leo10 {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
        String string1=sc.nextLine();
        String string2 =sc.nextLine();
        String result=twoBigNumberAdd(string1,string2);
        System.out.println(result);
    }

    private static String twoBigNumberAdd(String s1, String s2) {
        char[] a = new StringBuffer(s1).reverse().toString().toCharArray();
        char[] b = new StringBuffer(s2).reverse().toString().toCharArray();

        int len = a.length > b.length ? a.length : b.length;
        int[] result = new int[len + 1];
        for (int i = 0; i < len + 1; i++) {

            int aaa = i < a.length ? (a[i] - '0') : 0;
            int bbb = i < b.length ? (b[i] - '0') : 0;
            result[i] = aaa + bbb;
        }

        for (int i = 0; i < result.length; i++) {
            if (result[i] >= 10) {
                result[i + 1] += result[i] / 10;
                result[i] %= 10;
            }
        }
        StringBuffer sbresult = new StringBuffer();

        boolean flag = true;

        for (int i = len; i >= 0; i--) {
            if (result[i] == 0 && flag) {
                continue;
            } else {
                flag = false;
            }
            sbresult.append(result[i]);
        }
        return sbresult.toString();
    }


}
