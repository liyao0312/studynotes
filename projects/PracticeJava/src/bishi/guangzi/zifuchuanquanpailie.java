
import java.util.Scanner;

/**
 * Created by artsing on 2019/10/7.
 */
public class zifuchuanquanpailie {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[] s = new String[n];
        for(int i=0;i<n;i++){
            s[i] = sc.nextLine();
        }
        String re = foo(s);
        System.out.println(re);
    }

    private static String foo(String[] s) {
        if(s.length==0||s==null) return null;
        char[] ch = s[0].toCharArray();
        boolean flag = true;
        for(int i=1;i<s.length;i++){
            char[] chI = s[i].toCharArray();
            flag=false;
            for(int j=0;j<ch.length;j++){
                ch[j]=(char)(ch[j]^chI[j]);
            }
        }

        if(flag){
            rev(ch);
        }
        return String.valueOf(ch);
    }

    private static void rev(char[] ch) {
        for(int i =0;i<ch.length/2;i++){
            char t=ch[i];
            ch[i]=ch[ch.length-1-i];
            ch[ch.length-1-i]=t;
        }
    }
}
