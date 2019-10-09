import java.util.ArrayList;

/**
 * Created by artsing on 2019/8/18.
 */
public class FindNumbersWithSum {
    public static void main(String[] args) {
        int[] array ={1,2,4,7,11,15};
        int sum=15;
        System.out.print("ab"=="a"+"b");
        System.out.println(FindNumbersWithSum(array,sum).toString());
    }

    public static ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        int low=0,high=array.length-1;
        ArrayList<Integer> a = new ArrayList<Integer>();
        if(array==null||array.length<2) return a;
        while(low<high){
            int s = array[low]+array[high];
            if(s==sum){
                a.add(array[low]);
                a.add(array[high]);
                break;
            }else if(s>sum){
                high--;
            }else{
                low++;
            }
        }

        return a;
    }
}
