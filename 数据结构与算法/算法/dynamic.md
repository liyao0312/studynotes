## 动态规划

递归和动态规划都是将原问题拆成多个子问题然后求解，他们之间最本质的区别是，动态规划保存了子问题的解，避免重复计算。

动态规划一般可分为4类：

1. 线性动规
2. 区域动规
3. 树形动规
4. 背包动规

以`198. House Robber`为例，动态规划的**状态定义**和**状态转移方程**如下：

注意其中对状态的**定义**：

- 考虑偷取 [x…n-1] 范围⾥里里的房子 （函数的定义）

根据对状态的定义，决定状态的**转移**：

- f(0) = max{ v(0) + f(2) , v(1) + f(3) , v(2) + f(4) , … ,v(n-3) + f(n-1) , v(n-2),v(n-1) }

  (状态转移方程)

![img](https://frank-lam.github.io/fullstack-tutorial/pics/dynamic_programming.png)

### 1. 斐波那契数列

#### 1.1 递归方式（自顶向下）

```java
public int fib( int n ){
    if( n == 0 )
        return 0;
    if( n == 1 )
        return 1;
    return fib(n-1) + fib(n-2);
}

//控制台输出
fib(42) = 267914296
time : 1949 ms
run function fib() 866988873 times.Click to copy
```

#### 1.2 记忆化搜索（自底向上）

```java
public int fib(int n){
    int[] memo = new int[n + 1];
    Arrays.fill(memo, -1);
    return fib(n, memo);
}

private int fib(int n, int[] memo){
    if(n == 0)
        return 0;
    if(n == 1)
        return 1;
    if(memo[n] == -1)
        memo[n] = fib(n - 1, memo) + fib(n - 2, memo);
    return memo[n];
}

//控制台输出
fib(1000) = 1556111435
time : 1 ms
run function fib() 1999 times.Click to copy
```

#### 1.3 动态规划

```java
public int fib(int n){
    int[] memo = new int[n + 1];
    Arrays.fill(memo, -1);

    memo[0] = 0;
    memo[1] = 1;
    for(int i = 2 ; i <= n ; i ++)
        memo[i] = memo[i - 1] + memo[i - 2];

    return memo[n];
}Click to copy
```

### 2. 背包问题

先得到该问题的局部解然后扩展到全局问题解。

我们可以假设一个B(k,C) 方法，第k件物品，当前背包所剩下的容量C（初始则C=W）情况下，能够偷的最大价值量。

B( i , c ) = max{ F( i - 1 , C ) , v(i) + F( i - 1, C - w[i] ) };

#### （1）记忆化搜索

```java
/**
 * 记忆化搜索
 * 时间复杂度: O(n * C) 其中n为物品个数; C为背包容积
 * 空间复杂度: O(n * C)
 */
public class Solution01 {
    private static int count = 0;
    private static int[][] memo;

    public int knapsack(int[] w, int[] v, int C) {
        int n = w.length;
        memo = new int[n][C + 1];
        for(int i = 0;i<n;i++)
            Arrays.fill(memo[i],-1);

        return bestValue(w, v, n - 1, C);
    }

    // 用 [0...index]的物品,填充容积为c的背包的最大价值
    private int bestValue(int[] w, int[] v, int i, int C) {
        count++;
        if (i < 0 || C <= 0)
            return 0;

        if (memo[i][C] != -1) // 记忆化搜索
            return memo[i][C];

        int res = 0;
        res = bestValue(w, v, i - 1, C);
        if (C >= w[i])
            res = max(res, v[i] + bestValue(w, v, i - 1, C - w[i]));

        return memo[i][C] = res;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    public static void main(String[] args) {
        int[] w = {5,4,6,3};
        int[] v = {10,40,30,50};
        System.out.println(new Solution01().knapsack(w, v, 10));
        System.out.println("count of bestValue() exec：" + count);
        PrintHelper.print2DArray(memo);
    }
}Click to copy
```

#### （2）动态规划

![img](https://frank-lam.github.io/fullstack-tutorial/pics/knapsack-01.png)

```java
/**
 * 动态规划
 * 时间复杂度: O(n * C) 其中n为物品个数; C为背包容积
 * 空间复杂度: O(n * C)
 */
public class Solution02 {
    public int knapsack(int[] w, int[] v, int C) {
        int n = w.length;
        int[][] memo = new int[n][C + 1];

        if (n == 0 || C == 0)
            return 0;

        for (int j = 0; j <= C; j++)
            memo[0][j] = (j >= w[0] ? v[0] : 0);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= C; j++) {
                memo[i][j] = memo[i - 1][j];
                if (j >= w[i]) {
                    memo[i][j] = max(memo[i][j], v[i] + memo[i - 1][j - w[i]]);
                }
            }
        }

        return memo[n - 1][C];
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }


    public static void main(String[] args) {
        int[] w = {1, 2, 3};
        int[] v = {6, 10, 12};
        int C = 5;
        System.out.println(new Solution02().knapsack(w, v, C));
    }
}
Click to copy
```

#### （3）动态规划优化思路1

优化思路：第i行元素只依赖于第i-1行元素，理论上，只需要保持两行元素即可

![img](https://frank-lam.github.io/fullstack-tutorial/pics/knapsack-optimized1.png)

```java
/// 动态规划改进: 滚动数组
/// 时间复杂度: O(n * C) 其中n为物品个数; C为背包容积
/// 空间复杂度: O(C), 实际使用了2*C的额外空间
public class Solution1 {

    public int knapsack01(int[] w, int[] v, int C){

        if(w == null || v == null || w.length != v.length)
            throw new IllegalArgumentException("Invalid w or v");

        if(C < 0)
            throw new IllegalArgumentException("C must be greater or equal to zero.");

        int n = w.length;
        if(n == 0 || C == 0)
            return 0;

        int[][] memo = new int[2][C + 1];

        for(int j = 0 ; j <= C ; j ++)
            memo[0][j] = (j >= w[0] ? v[0] : 0);

        for(int i = 1 ; i < n ; i ++)
            for(int j = 0 ; j <= C ; j ++){
                memo[i % 2][j] = memo[(i-1) % 2][j];
                if(j >= w[i])
                    memo[i % 2][j] = Math.max(memo[i % 2][j], v[i] + memo[(i-1) % 2][j - w[i]]);
            }

        return memo[(n-1) % 2][C];
    }
}Click to copy
```

#### （4）动态规划优化思路2

![img](https://frank-lam.github.io/fullstack-tutorial/pics/knapsack-optimized2.png)

```java
/// 动态规划改进
/// 时间复杂度: O(n * C) 其中n为物品个数; C为背包容积
/// 空间复杂度: O(C), 只使用了C的额外空间
public class Solution2 {

    public int knapsack01(int[] w, int[] v, int C){

        if(w == null || v == null || w.length != v.length)
            throw new IllegalArgumentException("Invalid w or v");

        if(C < 0)
            throw new IllegalArgumentException("C must be greater or equal to zero.");

        int n = w.length;
        if(n == 0 || C == 0)
            return 0;

        int[] memo = new int[C+1];

        for(int j = 0 ; j <= C ; j ++)
            memo[j] = (j >= w[0] ? v[0] : 0);

        for(int i = 1 ; i < n ; i ++)
            for(int j = C ; j >= w[i] ; j --)
                memo[j] = Math.max(memo[j], v[i] + memo[j - w[i]]);

        return memo[C];
    }
}Click to copy
```

#### （5）背包问题更多变种

- 多重背包问题：每个物品不不⽌止1个，有num(i)个
- 完全背包问题：每个物品可以⽆无限使⽤用
- 多维费⽤用背包问题：要考虑物品的体积和重量量两个维度？
- 物品间加⼊入更更多约束：物品间可以互相排斥；也可以互相依赖

### 3. 最长上升子序列

**Longest Increasing Subsequence (LIS)**

**【Leetcode 300】最长上升子序列**

给定一个无序的整数数组，找到其中最长上升子序列的长度。

**示例:**

```java
输入: [10,9,2,5,3,7,101,18]
输出: 4 
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。Click to copy
```

**说明:**

- 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
- 你算法的时间复杂度应该为 O(*n2*) 。

**进阶:** 你能将算法的时间复杂度降低到 O(*n* log *n*) 吗?

LIS( i ) 表示以第 i 个数字为结尾的最长上升子序列的长度

LIS( i ) 表示 [0…i] 的范围内，选择数字nums[i]可以获得的最长上升子序列的长度

LIS ( i ) = maxj<i( 1 + LIS( j ) if nums[i] > nums[j] )

```java
public class Solution {

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n==0) {
            return 0;
        }

        int res = 1;
        int[] memo = new int[n];

        Arrays.fill(memo, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i])
                    memo[i] = max(memo[i] , memo[j]+1);
            }
        }

        for(int i = 0;i<n;i++){
            res = max(memo[i],res);
        }
        return res;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    public static void main(String[] args) {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(new Solution().lengthOfLIS(arr));
    }
}Click to copy
```

这里思考一个问题：在上面的代码中只求解出了上升子序列的长度，那么如何求出具体的上升子序列呢？

```java
public class Solution2 {
    private static List<Integer> LISindex = new ArrayList<>(); // 记录一下有几个上升子序列

    public List<List<Integer>> lengthOfLIS(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        int n = nums.length;
        if (n == 0) {
            return null;
        }

        int res = 1;
        int[] memo = new int[n];

        Arrays.fill(memo, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i])
                    memo[i] = max(memo[i], memo[j] + 1);
            }
        }

        for (int i = 0; i < n; i++) {
            res = max(memo[i], res);
        }

        for (int i = 0; i < n; i++) {
            if (memo[i] == res)
                LISindex.add(i); // 遍历一下最长子序列最后一位是谁，统计一共有多少个子序列
        }

        for (int lastIndex : LISindex) {
            ArrayList<Integer> list = new ArrayList<>();
            int nowMemoCount = memo[lastIndex];

            for (int i = lastIndex; i >= 0; i--) {
                if (nowMemoCount - memo[i] == 1 || nowMemoCount - memo[i] == 0) {
                    list.add(nums[i]);
                    nowMemoCount--;
                }
            }
            resList.add(reverseList(list));
        }

        return resList;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    private List<Integer> reverseList(ArrayList<Integer> list) {
        List<Integer> newList = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            newList.add(list.get(i));
        }
        return newList;
    }

    public static void main(String[] args) {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(new Solution2().lengthOfLIS(arr));
    }
}Click to copy
```

### 4. 最长公共子序列

**Longest Common Sequence (LCS)**：给出两个字符串S1和S2，求这两个字符串的最长公共子序列的长度

LCS( m , n ) S1[0…m] 和 S2[0…n] 的最长公共子序列的长度

**S1[m] == S2[n] :**

LCS(m,n) = 1 + LCS(m-1,n-1)

**S1[m] != S2[n] :**

LCS(m,n) = max( LCS(m-1,n) , LCS(m,n-1) )

![img](https://frank-lam.github.io/fullstack-tutorial/pics/LCS.png)

```java
/**
 * 最长公共子序列
 */
public class Solution3 {

    public int LCS(String s1, String s2) {
        return bestLength(s1, s2, s1.length() - 1, s2.length() - 1);
    }

    public int bestLength(String s1, String s2, int m, int n) {
        if (m < 0 || n < 0)
            return 0;
        int lcs = 0;
        if (s1.charAt(m) == s2.charAt(n)) {
            lcs = 1 + bestLength(s1, s2, m - 1, n - 1);
        } else {
            lcs = max(bestLength(s1, s2, m - 1, n), bestLength(s1, s2, m, n - 1));
        }
        return lcs;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    public static void main(String[] args) {
        System.out.println(new Solution3().LCS("ABCDEE", "ABDCEE"));
    }
}Click to copy
```

参考资料：

- [动态规划解决01背包问题 - Christal_R - 博客园](https://www.cnblogs.com/Christal-R/p/Dynamic_programming.html)
- [【经典算法】01背包问题_土豆视频](http://new-play.tudou.com/v/XMTQ3MzI0NzI2OA==.html?spm=a2h0k.8191414.0.0&from=s1.8-1-1.2&f=28521433)

## 