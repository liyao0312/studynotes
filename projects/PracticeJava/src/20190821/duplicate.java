public class duplicate {
    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public static void main(String[] args) {
        int[] numbers  ={2,3,1,0,2,5,3};
        int length = 7;
        int[] duplication = new int[1];
        System.out.println(duplicate(numbers,length,duplication)+" "+duplication[0]);
    }

    public static boolean duplicate(int numbers[],int length,int [] duplication) {
        if(length==0) return false;
        for(int i=0;i<length;i++){
            while(numbers[i]!=i){
                if(numbers[i]==numbers[numbers[i]]){
                    duplication[0]=numbers[i];
                    return true;
                }
                int temp=numbers[i];
                numbers[i]=numbers[numbers[i]];
                numbers[temp]=temp;
            }

        }
        return false;
    }

}