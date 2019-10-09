/**
 * Created by artsing on 2019/10/5.
 */
class TestArgsTransfer{

    public void swap(int m,int n){
        int temp=m;
        m=n;
        n=temp;
        System.out.println("swap方法内的参数的值 m = " + m + ", n = " + n + "");
    }
}

public class zhichuandi {
    public static void main(String[] args) {
        TestArgsTransfer tt = new TestArgsTransfer();
        int i=10;
        int j=5;
        System.out.println("初始值 i:" + i + " ; j:" + j);

        //交换变量i与j的值
//		int temp = i;
//		i = j;
//		j = temp;
//        System.out.println("main方法类交换后的值 i:" + i + " ; j:" + j);

        tt.swap(i,j);
        System.out.println("经过swap方法交换后的值 i:" + i + " ; j:" + j);
    }
}
