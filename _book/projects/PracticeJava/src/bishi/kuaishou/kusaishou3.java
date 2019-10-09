package bishi.kuaishou;

import java.util.Scanner;

/**
 * Created by artsing on 2019/8/25.
 */
public class kusaishou3 {
    public  static  void main(String []args){
        Scanner sc=new Scanner(System.in);
        String str1=sc.nextLine();
        String str2=sc.nextLine();
        String[]ss1=str1.split(" ");
        String []ss2=str2.split(" ");
        String sss="";
        int count=0;
        for(int i=0;i<ss1.length;i++){
            if(i>3&&i%4==0) {
                sss+=ss2[count++]+" ";
            }
            sss+=ss1[i]+" ";
        }
        if(count<ss2.length) {
            for(int j=count;j<ss2.length;j++){
                sss+=ss2[j]+" ";
            }
        }

        System.out.println(sss.trim());

    }

}
