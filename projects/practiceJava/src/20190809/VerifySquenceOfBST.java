public class VerifySquenceOfBST {
    public static void main(String[] args){
        int[] sequence  = new int[]{4,6,7,5};
        VerifySquenceOfBST(sequence);
    }
    public static boolean VerifySquenceOfBST(int [] sequence) {
        int end = sequence.length-1;
        boolean result=VerifySquenceOfBST(sequence,0,end);
        return result;
    }

    public static boolean VerifySquenceOfBST(int [] sequence,int start,int end) {
        if (start>=end) return true;

        int i =start;
        for(;i<end;i++){
            if(sequence[i]>sequence[end]){
                break;
            }
        }

        for(int j=i;j<end;j++){
            if(sequence[j]<sequence[end]){
                return false;
            }
        }
        boolean result1=VerifySquenceOfBST(sequence,start,i-1);

        boolean result2=VerifySquenceOfBST(sequence,i,end-1);

        return result1&&result2;
    }
}