import java.util.Scanner;

/**
 * Created by artsing on 2019/10/7.
 */
public class dancinixv {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        StringBuffer sb = new StringBuffer();
        for(int i=s.length-1;i>=0;i--){
            if(i==0){
                sb.append(s[i]);
            }else{
                sb.append(s[i]+" ");
            }

        }
        System.out.println(sb.toString());

    }
}
