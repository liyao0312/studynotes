/**
 * Created by artsing on 2019/8/19.
 */
public class ReverseSentence {
    public static void main(String[] args) {
        String s ="student. a am I";
        System.out.println(ReverseSentence(s));
    }
    public static String ReverseSentence(String str) {
        char[] ch = str.toCharArray();
        reserse(ch,0,ch.length-1);
        int start=0,end=0,e=0;
        while(end<ch.length){
            if(ch[end]==32){
                reserse(ch,start,end-1);
                start=++end;
            }else if(end==ch.length-1){
                reserse(ch,start,end);
                end++;
            }else{
                end++;
            }
        }
        return String.valueOf(ch);
    }
    public static void reserse(char[] ch,int start,int end){
        if(start>=end) return;
        while(start<end){
            char temp=ch[start];
            ch[start]=ch[end];
            ch[end] = temp;
            start++;
            end--;
        }
    }
}
