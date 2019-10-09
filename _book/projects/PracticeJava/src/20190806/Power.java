/**
 * Created by artsing on 2019/8/6.
 */
public class Power {
    public static  void  main(String[] args){
        System.out.println(Power(2,-3));
    }
    public static double Power(double base, int exponent) {
        if (base==0) return 0;
        int n=Math.abs(exponent);
        if (n==0) return 1;
        if (n==1 ) return base;
        double result=Power(base,n>>1);
        result*=result;
        if((n&1)==1){
            result*=base;
        }
        if(exponent<0){
            result=1/result;
        }
        return result;
    }
}
