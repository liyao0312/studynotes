import java.util.Scanner;

/**
 * Created by artsing on 2019/10/7.
 */
public class EcmDef {
    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            int j = sc.nextInt();
            ecm(i,j);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public static void ecm (int i ,int j) throws EcDef {
        if(i<0||j<0) {
            throw new EcDef("输入的数值存在负数");
        }
        System.out.println(i/j);
    }



}

class  EcDef extends Exception{
    static final long serialVersionUID = -3387524229948L;
    public EcDef(){

    }
    public EcDef(String s){
        super(s);
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
