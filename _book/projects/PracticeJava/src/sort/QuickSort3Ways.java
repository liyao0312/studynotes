/**
 * Created by artsing on 2019/8/26.
 */
public class QuickSort3Ways {

    public static void main(String[] args) {
        int[] a = {6,1,2,6,4,5,7,8,9,6,11,12};
        QuickSort3Ways(a,0,a.length-1);
        for (int b:a) {
            System.out.print(b+" ");
        }

    }

    // 递归使用快速排序,对arr[l...r]的范围进行排序
    private static void QuickSort3Ways(int[] arr, int l, int r){
        if(l>=r) return;
        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        //swap( arr, l, (int)(Math.random()*(r-l+1)) + l );

        int v = arr[l];

        int lt = l;     // arr[l+1...lt] < v
        int gt = r + 1; // arr[gt...r] > v
        int i = l+1;    // arr[lt+1...i) == v
        while( i < gt ){
            if( arr[i] < v){
                swap( arr, i, lt+1);
                i ++;
                lt ++;
            }
            else if( arr[i] > v ){
                swap( arr, i, gt-1);
                gt --;
            }
            else{ // arr[i] == v
                i ++;
            }
        }
        swap( arr, l, lt );

        QuickSort3Ways(arr, l, lt-1);
        QuickSort3Ways(arr, gt, r);
    }

    public static void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
