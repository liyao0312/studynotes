package zhuanzhuan;

import java.util.Scanner;

public class leo10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(foo(n));
    }

    private static String foo(int n) {
        StringBuffer sb = new StringBuffer();
        int f = n>=0?1:-1;
        n=Math.abs(n);
        if(n==0) sb.append(0);
        //if(n%27==0)
        while(n!=0){
            if(sb.length()==0&&n%27==0) {
                n=n/27;
            }else{
                sb.append(ts(n%27));
                n=n/27;
            }
        }
        if(f<0){
            sb.append("-");
        }

        return sb.reverse().toString();
    }

    private static String ts(int i) {
        switch (i){
            //case 0: return "";
            case 10: return "`";
            case 11: return "!";
            case 12: return "@";
            case 13: return "#";
            case 14: return "$";
            case 15: return "%";
            case 16: return "^";
            case 17: return "&";
            case 18: return "*";
            case 19: return "(";
            case 20: return ")";
            case 21: return "{";
            case 22: return "}";
            case 23: return "\\";
            case 24: return "<";
            case 25: return ">";
            case 26: return "?";
            default: break;
        }
        return String.valueOf(i);
    }


}