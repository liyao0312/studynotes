package vivo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Welcome to vivo !
 */
public class leo10 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        int input[] = parseInts(inputStr.split(" "));
        int output = solution(input);
        System.out.println(output);
    }

    private static int[] parseInts(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return new int[0];
        }
        int[] intArr = new int[strArr.length];
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        return intArr;
    }

    private static int solution(int[] input) {
        if(input==null||input.length==0) return  -1;
        if(input.length==1) return 1;
        int e = 0;
        int m = 0;
        int s = 0;
        for(int i = 0; i < input.length - 1; i++){
            //找能跳的最远的
            m = Math.max(m, input[i] + i);
            if( i == e){ //遇到边界，就更新边界，并且步数加一
                if(e==m) return -1;
                e = m;
                s++;
            }
        }
        return s;
    }
}