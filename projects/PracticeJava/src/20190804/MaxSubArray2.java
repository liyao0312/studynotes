import java.util.*;

public class MaxSubArray2{
  public static void main(String[] args){
    //System.out.println("«Î ‰»Î");
    //Scanner sc = new Scanner(System.in);
    //String[] str=sc.nextLine().toString().split(" ");
    //int[] a=new int[str.length];
    // for(int i=0;i<str.length;i++){
    //   a[i]=Integer.parseInt(str[i]);
    // }
    int[] a={1,-2,4,8,-4,7,-1,-5};
    System.out.println("The result is"+maxSubArray2(a));
  }


  public static  int  maxSubArray2(int[] a){
    int nAll=Integer.MIN_VALUE;
    
    if(a.length==0) return nAll;
    nAll=a[0];
    int nEnd=a[0];
    for(int i=1;i<a.length;i++){
      nEnd=max(nEnd+a[i],a[i]);
      //nAll=max(nEnd,nAll);
      nAll=maxSub(nAll,a[i]);
    }
    return nAll;
  }

  public static int max(int max ,int i){
    return max>i?max:i;
  }
    
  public static int maxSub(int max ,int i){
    int a=(max+i)>i?(max+i):i;
    int b=a>max?a:max;
    return b;
  }
}