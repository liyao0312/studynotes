package bishi.aiqiyi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class leo20 {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        double re = solution(n,m);
        BigDecimal bg = new BigDecimal(re).setScale(5, RoundingMode.HALF_UP);
        System.out.print(bg);
    }

    private static double solution(int ns, int ms) {
        double n=ns;
        double m = ms;
        double init = n/(m+n);
        double res = 0;
        int fre = (int)((n+m)/2);

        double last = 1;
        for(int i=0; i < fre;i++){//循环次数
            if(m >= 3 && n>0){
                res+= last *(m/(m+n)) * ((m-1)/(m+n-1)) * (n/(m+n-2)) * ((n-1)/(m+n-3)) *2;
                last = last * (m/(m+n)) * ((m-1)/(m+n-1)) * (n/(m+n-2));
                m = m-2;//蓝球个数减去两个
                n = n-1;
            }else if (m==2 && n>0){
                res+= last * (2/(m+n)) * (1/(m+n-1)) ;
                m = m-2;
            }else {
                break;
            }

        }

        return init + res;
    }


}
