import java.util.Scanner;

/**
 * Created by artsing on 2019/8/29.
 */
public class leo20 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String  s = sc.nextLine();
        StringBuffer sb = new StringBuffer();
        char ch;
        int left=0;  //左括号计数
        int right=0; //右括号技术

        for(int i=0;i<s.length();i++){
            ch = s.charAt(i);
            //去掉退格
            if(ch=='<'&&sb.length()!=0&&sb.charAt(sb.length()-1)!=')'&&sb.charAt(sb.length()-1)!='('){
                sb.deleteCharAt(sb.length()-1);
            }else if(ch=='<'&&sb.length()==0){
                continue;
            }else if(ch=='('){   //去掉括号
                left++;
                while(left!=right&&i<s.length()){  //左右括号相等时，中间一段直接跳过
                    ch=s.charAt(++i);
                    if(ch=='(') {
                        left++;
                    }else if(ch==')'){
                        right++;
                    }
                }
            }else{
                sb.append(ch);
            }
        }
        System.out.println(sb.toString());
    }
}
