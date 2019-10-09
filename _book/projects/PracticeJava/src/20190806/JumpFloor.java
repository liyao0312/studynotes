import java.util.Scanner;

/**
 * Created by artsing on 2019/8/6.
 */
public class JumpFloor {
    public static void  main(String[] args){
        Scanner sc =new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(JumpFloor(n));
    }
    public static int JumpFloor(int target) {
        if(target==0) return 0;
        if(target==1) return 1;
        if(target==2) return 2;
        int j1=1;
        int j2=2;
        int jn=0;
        for(int i=3;i<=target;i++){
            jn=j1+j2;
            j1=j2;
            j2=jn;

        }
        return jn;
    }
}