import java.util.*;

/**
 * Created by artsing on 2019/10/9.
 */
public class danci {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String s = sc.nextLine();
        int sum = foo(str,s);
        System.out.println(sum);
    }

    private static int foo(String str, String s) {
        int sum=0;
        int sLen=s.length();
        int temp=0;
        while(str.contains(s)){
            sum++;
            temp = str.indexOf(s);
            str=str.substring(temp+sLen);
        }
        return sum;
    }
}
