package bishi;

import java.util.Scanner;


public class leo10 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int target = scanner.nextInt();
        int[] arr = readArr(s);
        execute(arr, target);//
    }

    public static int[] readArr(String s){
        String s2 = s.substring(1,s.length()-1);
        String[] strs = s2.split(",");
        int[] arr = new int[strs.length];
        for(int i =0; i<strs.length; i++){
            arr[i] = Integer.parseInt(strs[i]);
        }
        return arr;
    }

    private static void execute(int[] array, int m) {
        int first = 0;
        int last = array.length -1;
        int sum = 0;
        while(first < last ) {
            sum = array[first] + array[last];
            if (sum == m) {
                System.out.println(array[first] + " " + array[last]);
                first++;
                last--;
            } else if (sum < m) {
                first++;
            } else {
                last--;
            }
        }
    }
}
