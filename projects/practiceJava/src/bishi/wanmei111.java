package bishi;

/**
 * Created by artsing on 2019/8/23.
 */
public class wanmei111 {
    public String showPword(String str1,String str2){
        return str1+str2;
    }
    private String showPefectWorld(){
        return "showPefectWorld";
    }
}

class SuperClass {
    public String showPword(String str1,String str2){
        return str1+str2;
    }
    private String showPefectWorld(){
        return "showPefectWorld";
    }
}

class  SubClass extends  SuperClass{
    public void showPword(String str1,StringBuffer str2){
        System.out.println(str1+str2);
    }
    public String showPword(String str1,String str2){
        return str1+str2;
    }
    private  String showPefectWorld(){
        return "showPefectWorld";
    }
    private void showPefectWorld(String string){
        System.out.println(string);
    }
}