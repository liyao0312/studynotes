package bishi.gensheixue;

import java.util.HashMap;
import java.util.Map;

public class leo1 {

    /*
     * 阿里巴巴笔试 有一个字符串它的构成是词+空格的组合，如“北京 杭州 杭州 北京”， 要求输入一个匹配模式（简单的以字符来写），
     * 比如 aabb,来判断该字符串是否符合该模式， 举个例子：
     *
     * pattern = "abba", str="北京 杭州 杭州 北京" 返回 ture
     * pattern = "aabb", str="北京 杭州 杭州 北京" 返回 false
     * pattern = "baab", str="北京 杭州 杭州 北京" 返回 ture
     *
     */
    public static boolean wordPattern(String pattern, String str) {
        if (str == null || pattern == null)
            return false;

        //reflect ： 模式字符与词的匹配关系
        //strs : 切分好的词
        Map<Character, String> reflect = new HashMap<>();
        String[] strs = str.split(" ");
        if (pattern.length() != strs.length) return false;

        //遍历模式，在匹配关系中查找key
        //如果找到，比较value是否与词是否相同，如果不同，返回false
        //如果未找到，查找value，如果存在返回false，不存在则将key，value存入
        for (int i = 0; i < pattern.length(); i++) {
            boolean haveKey=reflect.containsKey(pattern.charAt(i));
            boolean haveValue=reflect.containsValue(strs[i]);
            String v=reflect.get(pattern.charAt(i));
            if(haveKey){
                if (!v.equals(strs[i])) return false;
            }else{
                if(haveValue) return false;
                else reflect.put(pattern.charAt(i), strs[i]);
            }
        }

        //输入匹配关系
        for(Character ch:reflect.keySet()){
            System.out.println(ch+":"+reflect.get(ch));
        }

        return true;
    }

    public static void main(String[] args) {
        boolean flag = wordPattern("abba", "北京 杭州 杭州 北京");
        if (flag) {
            System.out.println("模式匹配");
        } else {
            System.out.println("模式不匹配");
        }
    }
}
