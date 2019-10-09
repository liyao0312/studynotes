import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by artsing on 2019/8/29.
 */
public class maxInWindows {
    public static void main(String[] args) {
        int[] a = {2,3,4,2,6,2,5,1};
        int size =3;
        ArrayList<Integer> result =maxInWindows(a,size);
        for(int i:result){
            System.out.print(i+" ");
        }
    }

    public static ArrayList<Integer> maxInWindows(int[] a,int size){
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(size==0) return result;
        Queue<Integer> queue = new LinkedList<>();



        return result;
    }
}
