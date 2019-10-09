import java.util.Random;

public class kuaishou3 {

    public static void main(String[] args) {
        int[] n={1,2,3,4,5,6,7,8,9,10,11};
        int m=5;
        shuffleCards(n,m);
        for(int i=0;i<m;i++){
            System.out.print(n[i]+" ");
        }
    }

    public static void shuffleCards(int[] n,int m){
        int len =n.length;
        for(int i=0;i<m;i++){
            int randomIndex=(len-1)-new Random().nextInt(len-i);
            int tmp=n[randomIndex];
            n[randomIndex]=n[i];
            n[i]=tmp;
        }
    }

}
