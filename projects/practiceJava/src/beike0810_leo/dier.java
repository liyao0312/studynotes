/**
 * Created by artsing on 2019/8/10.
 */
import java.util.Scanner;
public class dier {




        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = sc.nextInt();
            }
            System.out.println(solution(array));
        }
        public static int solution(int[] arr) {
            int n = arr.length;
            int[] temp = new int[n+1];
            temp[1] = arr[0];
            int len=1;
            int start=0;
            int end=len;
            int mid;
            for(int i = 1;i<n;i++){
                if(arr[i]>temp[len]) {
                    len++;
                    temp[len] = arr[i];
                }
                else{
                    start=1;end=len;
                    while(start<=end){
                        mid=(start+end)>>1;
                        if(temp[mid]<arr[i]) {
                            start=mid+1;
                        }
                        else{
                            end=mid-1;
                        }
                    }
                    temp[start] = arr[i];
                }
            }
            return len;

        }

}
