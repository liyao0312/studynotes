package shangguigu;

/**
 * Created by artsing on 2019/10/2.
 */
public class a_jicheng {
    public static void main(String[] args) {
        Animal a = new Animal();
        System.out.println(a);

    }
}

class Animal{
    private int legs;
    private  String eysColor;

    public String toString(){
        return  "Animal[legs="+legs+",eyeColor="+eysColor+"]";
    }
    //设置legs的get和set方法
    public  void setLegs(int legs){
        if(legs%2==0){
            this.legs=legs;
        }else{
            System.out.println("输入不合法，请重新输入");
        }


    }

}
