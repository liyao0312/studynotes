import javax.xml.soap.SOAPPart;

public class GetNumberOfK {
    public static void main(String[] args) {
        int[] arr = {1,2,2,2,4,4,4,5};
        int k = 3;
        System.out.println(GetNumberOfK(arr,k));

    }
    public static int GetNumberOfK(int [] array , int k) {
        int times=0;
        if(array.length>0){
            int first = GetFirstKey(array,k,0,array.length-1);
            int last = GetLastKey(array,k,0,array.length-1);
            if(first>-1&&last>-1){
                times = last - first +1 ;
            }
        }
        return times;
    }

    public  static int GetFirstKey(int [] array , int k, int start, int end){
        //if(start==end&&array[start]!=k) return -1;
        if(start>end) return -1;
        int index = start+(end-start)/2;
        if(array[index]==k){
            while(index>0&&array[index-1]==k){
                index--;
            }
            return index;
        }else if(array[index]>k){
            end = index -1;
        }else {
            start = index +1;
        }
        return GetFirstKey(array,k,start,end);
    }

    public static int GetLastKey(int [] array , int k, int start, int end){
        //if(start==end&&array[start]!=k) return -1;
        if(start>end) return -1;
        int index = start+(end-start)/2;
        if(array[index]==k){
            while(index<array.length-1&&array[index+1]==k){
                index++;
            }
            return index;
        }else if(array[index]<k){
            start = index +1;
        }else {
            end = index -1;
        }
        return GetLastKey(array,k,start,end);
    }
}