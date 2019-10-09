package bishi;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by artsing on 2019/8/14.
 */
public class leo30 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int[] nums = readArr(s);
        int n = solution(nums);
        System.out.println(n);
    }

    public static int[] readArr(String s){
        String s2 = s.substring(1,s.length()-1);
        String[] strs = s2.split(", ");
        int[] arr = new int[strs.length];
        for(int i =0; i<strs.length; i++){
            arr[i] = Integer.parseInt(strs[i]);
        }
        return arr;
    }

    public static int solution(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num-1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum+1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }


}
