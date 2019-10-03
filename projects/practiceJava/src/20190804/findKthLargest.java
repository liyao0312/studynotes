public class findKthLargest{
    public static void main(String[] args){
        int[] a ={3,2,1,5,6,7};
        int result = findK(a, 2);
        System.out.println(result);
    }

    public static int findK(int[] nums, int k) {
        if(nums==null) return Integer.MIN_VALUE;
        if(nums.length<k) return Integer.MIN_VALUE;
        return quickSort(nums,0,nums.length-1,k);
        
    }
       
    public static int quickSort(int[] nums,int start,int end,int k){
        if(start>end) return Integer.MIN_VALUE;
        int i=start;
        int j=end;
        int tmp=nums[i];
        while(i<j){
     
            while(nums[j]<=tmp&&i<j){
                j--;
            }
            if(i<j){
                nums[i++]=nums[j];
            } 
            while(nums[i]>=tmp&&i<j){
                i++;
            }
            if(i<j){
                nums[j--]=nums[i];
            } 
         
        }
        nums[i]=tmp;
        if(i+1==k) return nums[i];
        if(i+1<k) return quickSort(nums,i+1,end, k);
        else  return quickSort(nums, start,i-1, k);
 
    }
}