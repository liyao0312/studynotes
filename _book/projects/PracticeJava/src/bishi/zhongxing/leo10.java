package bishi.zhongxing;

public class leo10 {


    public static void main(String[] args) {
    
//        int reactorCap =
//        int[] arr = new int[];
//        for (int i = 0; i < n; i++) {
//            arr[i] = sc.nextInt();
//        }
        //int m = solution(arr,n);
        //System.out.println(m);
    }


    public int maxEnergyGenerate(int reactorCap, int numberOfRadLiquid,
                                 int criticalMass, int[] volumes, int[] masses, int[] energies)
    {
        if(numberOfRadLiquid<1) return 0;
        int[][] maxResult = new int[criticalMass+1][reactorCap+1];
        for(int i=0;i<numberOfRadLiquid;i++){
            for(int j=maxResult.length-1;j>0;j--){
                for(int k=maxResult[j].length-1;k>0;k--){
                    if(volumes[i]<=k&&masses[i]<=j)
                        maxResult[j][k] = Math.max(maxResult[j][k],maxResult[j-masses[i]][k-volumes[i]]+energies[i]);
                }
            }
        }
        return maxResult[criticalMass][reactorCap];
    }
    // METHOD SIGNATURE ENDS
}
