package laohu;

/**
 * Created by artsing on 2019/9/13.
 */
public class yushui {
    public int solution(int[] nums) {
        int ans=0;
        for(int i=0;i<nums.length;i++){
            //寻找左边的最小值
            int maxLeft =0;
            for(int j=i;j>=0;j--){
                maxLeft = max(maxLeft,nums[j]);
            }
            //寻找右边的最小值
            int maxRight = 0;
            for(int k=i;k<nums.length;k++){
                maxRight = max(maxRight,nums[k]);
            }
            //雨水累加到ans
            ans += min(maxLeft,maxRight)-nums[i];
        }
        return ans;
    }

    public int max (int a ,int b){
        if(a==b) return a;
        else    return a>b?a:b;
    }
    public int min (int a ,int b){
        if(a==b) return a;
        else    return a<b?a:b;
    }
}
