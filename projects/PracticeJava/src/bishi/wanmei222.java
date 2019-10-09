package bishi;

import java.util.Scanner;

/**
 * Created by artsing on 2019/8/23.
 */
public class wanmei222 {
    static int M=10000;//(此路不通)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x=6,y=6;
        int[][] v = new int[x][y];
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                v[i][j]=sc.nextInt();
            }
        }

        int[] r = sl(v,0);
        for(int k=0;k<6;k++){
            System.out.println(r[k]);
        }

    }


    public static int[] sl(int[][] weight,int start){
        //接受一个有向图的权重矩阵，和一个起点编号start（从0编号，顶点存在数组中）
        //返回一个int[] 数组，表示从start到它的最短路径长度
        int n = weight.length;        //顶点个数
        int[] shortPath = new int[n];    //存放从start到其他各点的最短路径
        String[] path=new String[n]; //存放从start到其他各点的最短路径的字符串表示
        for(int i=0;i<n;i++)
            path[i]=new String(start+"-->"+i);
        int[] visited = new int[n];   //标记当前该顶点的最短路径是否已经求出,1表示已求出

        //初始化，第一个顶点求出
        shortPath[start] = 0;
        visited[start] = 1;

        for(int count = 1;count <= n - 1;count++)  //要加入n-1个顶点
        {

            int k = -1;    //选出一个距离初始顶点start最近的未标记顶点
            int dmin = Integer.MAX_VALUE;
            for(int i = 0;i < n;i++)
            {
                if(visited[i] == 0 && weight[start][i] < dmin)
                {
                    dmin = weight[start][i];

                    k = i;
                }

            }
            System.out.println("k="+k);

            //将新选出的顶点标记为已求出最短路径，且到start的最短路径就是dmin
            shortPath[k] = dmin;

            visited[k] = 1;

            //以k为中间点，修正从start到未访问各点的距离
            for(int i = 0;i < n;i++)
            {                 // System.out.println("k="+k);
                if(visited[i] == 0 && weight[start][k] + weight[k][i] < weight[start][i]){
                    weight[start][i] = weight[start][k] + weight[k][i];

                    path[i]=path[k]+"-->"+i;

                }

            }

        }
        for(int i=0;i<n;i++)
            System.out.println("从"+start+"出发到"+i+"的最短路径为："+path[i]);
        System.out.println("=====================================");

        return shortPath;
    }

}
