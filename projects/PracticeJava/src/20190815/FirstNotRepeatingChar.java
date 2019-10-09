import java.util.LinkedHashMap;

/**
 * Created by artsing on 2019/8/15.
 */
public class FirstNotRepeatingChar {
    public static void main(String[] args){
        String str = "google";
        FirstNotRepeatingChar(str);
    }

    public static int FirstNotRepeatingChar(String str) {
        char[] ch = str.toCharArray();
        LinkedHashMap<Character,Integer> map = new LinkedHashMap<>();
        int times=0;
        for(int i=0;i<ch.length;i++){
            if(map.containsKey(ch[i])){
                times= map.get(ch[i]);
                map.put(ch[i],++times);
            }else{
                map.put(ch[i],1);
            }
        }
        for(int i=0;i<ch.length;i++){
            if(map.get(ch[i])==1){
                return i;
            }
        }
        return -1;
    }
}
