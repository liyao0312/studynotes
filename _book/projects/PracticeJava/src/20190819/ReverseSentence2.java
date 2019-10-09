/**
 * Created by artsing on 2019/8/19.
 */
public class ReverseSentence2 {
    public static void main(String[] args) {
        String s ="student. a am I";
        System.out.println(ReverseSentence(s));
    }
    public static String ReverseSentence(String str) {
        String[] a = str.split(" ");
        StringBuffer res = new StringBuffer();
        for(int i=a.length-1;i>=0;i--){
            res.append(a[i]);
            if(i>0){
                res.append(" ");
            }
        }
        return res.toString();
    }

}
