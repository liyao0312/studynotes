/**
 * Created by artsing on 2019/8/8.
 */
public class reOrderArray {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
        reOrderArray2(array);
    }
    public static void reOrderArray(int [] array) {
        int n = array.length;
        int[] tmp = new int [n];
        int index1=0,index2=n-1;
        for(int i =0 ;i<n;i++){
            if((array[i]&1)==1){
                tmp[index1++]=array[i];
            }

        }
        for(int j=n-1;j>=0;j--){
            if((array[j]&1)!=1){
                tmp[index2--]=array[j];
            }
        }
        array=tmp;
    }
    public static void reOrderArray2(int [] array) {
        int len = array.length;
        for(int i = 0 ; i < len - 1 ; i ++){
            for(int j = 0 ; j < len - 1 - i ; j ++){
                if(array[j] % 2 == 0 && array[j+1] % 2 != 0)
                {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }

        }
    }
}
