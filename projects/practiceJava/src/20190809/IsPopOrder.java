/**
 * Created by artsing on 2019/8/9.
 */
import java.util.*;

public class IsPopOrder {
    public static void main(String[] args){
        int[] pushA = new int[]{1,2,3,4,5};
        int[] popA  = new int[]{4,5,3,2,1};
        IsPopOrder(pushA,popA);
    }

    public static boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> pushStack = new Stack<Integer>();
        boolean result=false;
        int index =0 ;

        for(int j=0;j<pushA.length;j++){
            pushStack.push(pushA[j]);
            while (!pushStack.empty()&&popA[index]==pushStack.peek()){
                pushStack.pop();
                index++;
            }

        }
        if(pushStack.empty()){
            result=true;
        }

        return result;
    }
}
