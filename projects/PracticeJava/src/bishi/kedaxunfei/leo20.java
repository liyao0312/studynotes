package kedaxunfei;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by artsing on 2019/10/9.
 */
public class leo20 {
    static HashSet<String> hashSet = new HashSet<String>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        String s3 = sc.nextLine();
        hashSet.add("zhangsan");
        hashSet.add("lisi");
        System.out.println(foo(s1,s2,s3));
    }

    private static String foo(String s1, String s2, String s3) {
       if(!hashSet.add(s1)){
           return  "该用户名研存在";
       }
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        if(!s2.matches(regex)){
            return  "密码格式错误";
        }
        String regex1 = "[1]([0-9]{11})";
        if(s3.length()!=11||!s3.matches(regex1)){
            return  "请输入正确的手机号码";
        }
        return "注册成功";
    }

}
