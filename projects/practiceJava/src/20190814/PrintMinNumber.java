

public class PrintMinNumber {

    public static void main(String[] args){
        int[] numbers = new int[]{3,32,321};
        PrintMinNumber(numbers);
    }

    private static int min=Integer.MAX_VALUE;
    private static StringBuffer sb=new StringBuffer();
    public static String PrintMinNumber(int [] numbers) {
        PrintMinNumber(numbers,0);
        return String.valueOf(min);
    }

    public static void PrintMinNumber(int [] numbers,int starindex) {
        if(starindex==numbers.length-1){
            sb.append(String.valueOf(numbers[starindex]));
            int temp = Integer.valueOf(sb.toString());
            min=min>temp?temp:min;
            sb.setLength(0);
        }else if(starindex<numbers.length-1){
            sb.append(String.valueOf(numbers[starindex]));
            for(int i=starindex;i<numbers.length;i++){
                PrintMinNumber(numbers,i+1);
                sawap(numbers,starindex,i);
            }
        }
    }

    public static void sawap(int[] numbers,int a,int b){
        int temp = numbers[a];
        numbers[a] = numbers[b];
        numbers[b] = temp;
    }
}