import java.util.Arrays;

/**
 * Created by artsing on 2019/8/19.
 */
public class isContinuous {

    public static void main(String[] args) {
        int[] array ={3,0,0,0,0};
        System.out.println(isContinuous(array));
    }

    public static boolean isContinuous(int [] numbers) {
        Arrays.sort(numbers);
        int wang =0;
        int i=0;
        while(numbers[i]==0&&i<numbers.length){
            wang++;
            i++;
        }
        if((i<numbers.length&&numbers[numbers.length-1]-numbers[i]>numbers.length-1-i+wang)||(i<numbers.length&&numbers[numbers.length-1]-numbers[i]==0)){
            return false;
        }
        return true;

    }
}
