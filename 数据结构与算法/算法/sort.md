## 概述

|               | 平均时间复杂度   | 原地排序 | 额外空间    | 稳定排序 |
| ------------- | --------- | ---- | ------- | ---- |
| [冒泡排序](#冒泡排序) | O(n^2)    |      |         | √    |
| [选择排序](#选择排序) | O(n^2)    |      |         | ×    |
| [插入排序](#插入排序) | O(n^2)    | √    | O(1)    | √    |
| 希尔排序          | O(n*logn) |      |         | ×    |
| [归并排序](#归并排序) | O(n*logn) | ×    | O(n)    | √    |
| [快速排序](#快速排序) | O(n*logn) | √    | O(logn) | ×    |
| [堆排序](#堆排序)   | O(n*logn) | √    | O(1)    | ×    |
| 计数排序          | O(n+k)    |      |         | √    |
| 桶排序           | O(n+k)    |      |         | √    |
| 基数排序          | O(n*k)    |      |         | √    |



点击以下图片查看大图：

![img](https://www.runoob.com/wp-content/uploads/2019/03/0B319B38-B70E-4118-B897-74EFA7E368F9.png)



## 名词解释

**平方阶 (O(n2)) 排序 各类简单排序：**  直接插入、直接选择和冒泡排序。

**线性对数阶 (O(nlog2n)) 排序:** 快速排序、堆排序和归并排序；

**O(n1+§)) 排序，§ 是介于 0 和 1 之间的常数：**希尔排序

**线性阶 (O(n)) 排序：** 基数排序，此外还有桶、箱排序。

- n ： 数据规模
- k ： “桶”的个数
- In-place ：占用常数内存，不占用额外内存
- Out-place ：占用额外内存
- 稳定性 ：排序后2个相等键值的顺序和排序之前它们的顺序相同

## 冒泡排序

通过从左到右不断交换相邻逆序的相邻元素，在一轮的交换之后，可以让未排序的元素上浮到右侧。

在一轮循环中，如果没有发生交换，就说明数组已经是有序的，此时可以直接退出。

![img](https://www.runoob.com/wp-content/uploads/2019/03/bubbleSort.gif)

### 代码实现

```java
private static void sort(int[] arr) {
    for (int i = arr.length - 1; i > 0; i--) { // 从最后一位开始确定
        boolean swapped = false;
        for (int j = 0; j < i; j++) {
            if(arr[j] > arr[j+1]){
                swapped = true;
                swap(arr,j,j+1);
            }
        }
        if(!swapped)
            return;
    }
}

private static void swap(int[] arr, int i, int j) {
    int t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
}
```

### 算法分析

## 选择排序

选择出数组中的最小元素，将它与数组的第一个元素交换位置。再从剩下的元素中选择出最小的元素，将它与数组的第二个元素交换位置。不断进行这样的操作，直到将整个数组排序。

### 动图演示

![img](https://www.runoob.com/wp-content/uploads/2019/03/selectionSort.gif)

### 代码实现

```java
public static void sort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
        // 寻找[i, n)区间里的最小值的索引
        int minIndex = i;
        for (int j = i + 1; j < arr.length; j++) {
            if(arr[minIndex] > arr[j]){
                minIndex = j;
            }
        }
        swap( arr , i , minIndex);
    }
}

private static void swap(int[] arr, int i, int j) {
    int t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
}
```

### 算法分析

表现最稳定的排序算法之一，因为无论什么数据进去都是O(n2)的时间复杂度，所以用到它的时候，数据规模越小越好。唯一的好处可能就是不占用额外的内存空间了吧。理论上讲，选择排序可能也是平时排序一般人想到的最多的排序方法了吧。

## 插入排序

插入排序从左到右进行，每次都将当前元素插入到左侧已经排序的数组中，使得插入之后左部数组依然有序。

第 j 元素是通过不断向左比较并交换来实现插入过程：当第 j 元素小于第 j - 1 元素，就将它们的位置交换，然后令 j 指针向左移动一个位置，不断进行以上操作。

![img](https://www.runoob.com/wp-content/uploads/2019/03/insertionSort.gif)

### 代码实现

```java
public static void sort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
        for (int j = i + 1; j > 0; j--) {
            if (arr[j] < arr[j - 1])
                swap(arr, j, j - 1); // 大量的交换会消耗时间
            else
                break;
        }
    }
}

// 改进版插入排序（减少了数组元素的操作次数）
public static void better_sort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
        int e = arr[i];
        int j = i;
        for (; j > 0; j--) {
            if (e < arr[j - 1])
                arr[j] = arr[j - 1];
            else
                break;
        }
        arr[j] = e;
    }
}

private static void swap(int[] arr, int i, int j) {
    int t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
}
```

### 算法分析

插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。

## 希尔排序

希尔排序，也称递减增量排序算法，是插入排序的一种更高效的改进版本。但希尔排序是非稳定排序算法。

希尔排序是基于插入排序的以下两点性质而提出改进方法的：

- 插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率；
- 但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位；

希尔排序的基本思想是：先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，待整个序列中的记录"基本有序"时，再对全体记录进行依次直接插入排序。

![img](https://www.runoob.com/wp-content/uploads/2019/03/Sorting_shellsort_anim.gif)

## 归并排序

归并排序的思想是将数组分成两部分，分别进行排序，然后归并起来。把长度为n的输入序列分成两个长度为n/2的子序列；对这两个子序列分别采用归并排序；将两个排序好的子序列合并成一个最终的排序序列。

![img](https://www.runoob.com/wp-content/uploads/2019/03/mergeSort.gif)

### 代码实现

#### 1.归并方法

归并方法将数组中两个已经排序的部分归并成一个。

```java
private static void sort(int[] arr) {
    __MergeSort(arr, 0, arr.length - 1);
}

private static void __MergeSort(int[] arr, int l, int r) {
    if (l >= r)
        return;
    int mid = (l + r) / 2;
    __MergeSort(arr, l, mid);
    __MergeSort(arr, mid + 1, r);
    merge(arr, l, mid, r);
}

// 将arr[l...mid]和arr[mid+1...r]两部分进行归并
private static void merge(int[] arr, int l, int mid, int r) {
    int[] aux = Arrays.copyOfRange(arr, l, r + 1);

    // 初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
    int i = l, j = mid + 1;
    for (int k = l; k <= r; k++) {
        if (i > mid) {  // 如果左半部分元素已经全部处理完毕
            arr[k] = aux[j - l];
            j++;
        } else if (j > r) {   // 如果右半部分元素已经全部处理完毕
            arr[k] = aux[i - l];
            i++;
        } else if (aux[i - l] < aux[j - l]) {  // 左半部分所指元素 < 右半部分所指元素
            arr[k] = aux[i - l];
            i++;
        } else {  // 左半部分所指元素 >= 右半部分所指元素
            arr[k] = aux[j - l];
            j++;
        }
    }
}
```

#### 2.自底向上归并排序

```java
private static void sort(int[] arr) {
    int N = arr.length;
    int[] aux = new int[N];
    for (int sz = 1; sz < N; sz += sz)
        for (int i = 0; i + sz < N; i += sz + sz)
            merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, N - 1));
}
```

### 

## 快速排序

快速排序可以说是20世纪最伟大的算法之一了。相信都有所耳闻，它的速度也正如它的名字那样，是一个非常快的算法了。当然它也后期经过了不断的改进和优化，才被公认为是一个值得信任的非常优秀的算法。

![img](https://www.runoob.com/wp-content/uploads/2019/03/quickSort.gif)

### 代码实现

#### 1.普通快速排序

```java
// 递归使用快速排序,对arr[l...r]的范围进行排序
public static void QuickSort(int[] arr,int l,int r){
    if(l>=r)
        return;
    int p = partition(arr,l,r);
    QuickSort(arr,l,p-1);
    QuickSort(arr,p+1,r);
}

// 将数组通过p分割成两部分
// 对arr[l...r]部分进行partition操作
// 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
public static int partition(int[] arr, int l, int r) {
    swap(arr, l, (int) (Math.random() % (r - l + 1)) + l);  // 加入这一行变成随机快速排序

    int v = arr[l];
    int j = l;
    for(int i = j +1;i<=r;i++){
        if(arr[i] < v){
            j++;
            swap(arr,i,j);
        }
    }
    swap(arr,l,j);
    return j;
}

public static void swap(int[] arr,int i,int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}
```

快速排序是原地排序，不需要辅助数组，但是递归调用需要辅助栈。

快速排序最好的情况下是每次都正好能将数组对半分，这样递归调用次数才是最少的。这种情况下比较次数为 CN=2CN/2+N，复杂度为 O(NlogN)。

最坏的情况下，第一次从最小的元素切分，第二次从第二小的元素切分，如此这般。因此最坏的情况下需要比较 N2/2。为了防止数组最开始就是有序的，在进行快速排序时需要随机打乱数组。

#### 2.双路快速排序

若果数组中含有大量重复的元素，则partition很可能把数组划分成两个及其不平衡的两部分，时间复杂度退化成O(n²)。这时候应该把小于v和大于v放在数组两端。

![img](https://frank-lam.github.io/fullstack-tutorial/pics/partition2.jpg)

```java
// 双路快速排序的partition
// 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
private static int partition(int[] arr, int l, int r) {

    // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
    // swap(arr, l, (int) (Math.random() % (r - l + 1)) + l);

    int v = arr[l];

    // arr[l+1...i) <= v; arr(j...r] >= v
    int i = l + 1, j = r;
    while (true) {
        // 注意这里的边界, arr[i].compareTo(v) < 0, 不能是arr[i].compareTo(v) <= 0
        // 思考一下为什么?
        while (i <= r && arr[i] < v)
            i++;

        // 注意这里的边界, arr[j].compareTo(v) > 0, 不能是arr[j].compareTo(v) >= 0
        // 思考一下为什么?
        while (j >= l + 1 && arr[j] > v)
            j--;

        // 对于上面的两个边界的设定, 有的同学在课程的问答区有很好的回答:)
        // 大家可以参考: http://coding.imooc.com/learn/questiondetail/4920.html
        if (i > j)
            break;

        swap(arr, i, j);
        i++;
        j--;
    }

    swap(arr, l, j);

    return j;
}

// 递归使用快速排序,对arr[l...r]的范围进行排序
private static void QuickSort2Ways(int[] arr, int l, int r) {
    // 对于小规模数组, 使用插入排序
    if (l >= r) return;
    int p = partition(arr, l, r);
    QuickSort2Ways(arr, l, p - 1);
    QuickSort2Ways(arr, p + 1, r);
}
```

#### 3.三路快速排序

数组分成三个部分，大于v 等于v 小于v

在具有大量重复键值对的情况下使用三路快排

![img](https://frank-lam.github.io/fullstack-tutorial/pics/partition3.jpg)

```java
// 递归使用快速排序,对arr[l...r]的范围进行排序
private static void QuickSort3Ways(int[] arr, int l, int r){
	if(l>=r) return;
    // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
    swap( arr, l, (int)(Math.random()*(r-l+1)) + l );

    int v = arr[l];

    int lt = l;     // arr[l+1...lt] < v
    int gt = r + 1; // arr[gt...r] > v
    int i = l+1;    // arr[lt+1...i) == v
    while( i < gt ){
        if( arr[i] < v){
            swap( arr, i, lt+1);
            i ++;
            lt ++;
        }
        else if( arr[i] > v ){
            swap( arr, i, gt-1);
            gt --;
        }
        else{ // arr[i] == v
            i ++;
        }
    }
    swap( arr, l, lt );

    QuickSort3Ways(arr, l, lt-1);
    QuickSort3Ways(arr, gt, r);
}
```

## 堆排序

### 1.堆

堆的某个节点的值总是大于等于子节点的值，并且堆是一颗完全二叉树。

堆可以用数组来表示，因为堆是完全二叉树，而完全二叉树很容易就存储在数组中。位置 k 的节点的父节点位置为 k/2，而它的两个子节点的位置分别为 2k 和 2k+1。这里不使用数组索引为 0 的位置，是为了更清晰地描述节点的位置关系。

![img](https://frank-lam.github.io/fullstack-tutorial/pics/heap.png)

### 2.上浮和下沉

在堆中，当一个节点比父节点大，那么需要交换这个两个节点。交换后还可能比它新的父节点大，因此需要不断地进行比较和交换操作，把这种操作称为**上浮（ShiftUp）**。

![img](https://frank-lam.github.io/fullstack-tutorial/pics/shiftup_heap.png)

```java
private void shiftUp(int k){
    while( k > 1 && data[k/2] < data[k])){
        swap(k, k/2);
        k /= 2;
    }
}
```

类似地，当一个节点比子节点来得小，也需要不断地向下进行比较和交换操作，把这种操作称为**下沉（Shift Down）**。一个节点如果有两个子节点，应当与两个子节点中最大那么节点进行交换。

![img](https://frank-lam.github.io/fullstack-tutorial/pics/shiftdown_heap.png)

```java
private void shiftDown(int k){
    while( 2*k <= count ){ // 当前结点有左孩子
        int j = 2*k; // 在此轮循环中,data[k]和data[j]交换位置
        if( j+1 <= count && data[j+1] > data[j] )
            j ++;
        // data[j] 是 data[2*k]和data[2*k+1]中的最大值

        if( data[k] >= data[j] ) 
            break;
        swap(k, j);
        k = j;
    }
}Click to copy
```

### 3.插入元素

将新元素放到数组末尾，然后上浮到合适的位置。

```java
// 向最大堆中插入一个新的元素 item
public void insert(Item item){
    assert count + 1 <= capacity;
    data[count+1] = item;
    count ++;
    shiftUp(count);
}Click to copy
```

### 4. 删除最大元素

```java
// 从最大堆中取出堆顶元素, 即堆中所存储的最大数据
public Item extractMax(){
    assert count > 0;
    Item ret = data[1];

    swap( 1 , count );
    count --;
    shiftDown(1);
    return ret;
}Click to copy
```

### 5. 堆排序

由于堆可以很容易得到最大的元素并删除它，不断地进行这种操作可以得到一个递减序列。如果把最大元素和当前堆中数组的最后一个元素交换位置，并且不删除它，那么就可以得到一个从尾到头的递减序列，从正向来看就是一个递增序列。因此很容易使用堆来进行排序。并且堆排序是原地排序，不占用额外空间。

```java
// 不使用一个额外的最大堆, 直接在原数组上进行原地的堆排序
public class HeapSort {

    // 对整个arr数组使用HeapSort1排序
    // HeapSort1, 将所有的元素依次添加到堆中, 在将所有元素从堆中依次取出来, 即完成了排序
    // 无论是创建堆的过程, 还是从堆中依次取出元素的过程, 时间复杂度均为O(nlogn)
    // 整个堆排序的整体时间复杂度为O(nlogn)
    public static void sort1(Comparable[] arr){

        int n = arr.length;
        MaxHeap<Comparable> maxHeap = new MaxHeap<Comparable>(n);
        for( int i = 0 ; i < n ; i ++ )
            maxHeap.insert(arr[i]);

        for( int i = n-1 ; i >= 0 ; i -- )
            arr[i] = maxHeap.extractMax();
    }


    // 只通过shiftDown操作进行排序
    public static void sort2(Comparable[] arr){
        int n = arr.length;

        // 注意，此时我们的堆是从0开始索引的
        // 从(最后一个元素的索引-1)/2开始
        // 最后一个元素的索引 = n-1
        for( int i = (n-1-1)/2 ; i >= 0 ; i -- )
            shiftDown2(arr, n, i);

        for( int i = n-1; i > 0 ; i-- ){ // 这个的目的是让序列从小到大排序
            swap( arr, 0, i);
            shiftDown2(arr, i, 0);
        }
    }

    // 交换堆中索引为i和j的两个元素
    private static void swap(Object[] arr, int i, int j){
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    // 原始的shiftDown过程
    private static void shiftDown(Comparable[] arr, int n, int k){
        while( 2*k+1 < n ){
            int j = 2*k+1;
            if( j+1 < n && arr[j+1].compareTo(arr[j]) > 0 )
                j += 1;

            if( arr[k].compareTo(arr[j]) >= 0 )break;

            swap( arr, k, j);
            k = j;
        }
    }

    // 优化的shiftDown过程, 使用赋值的方式取代不断的swap,
    // 该优化思想和我们之前对插入排序进行优化的思路是一致的
    private static void shiftDown2(Comparable[] arr, int n, int k){

        Comparable e = arr[k];
        while( 2*k+1 < n ){
            int j = 2*k+1;
            if( j+1 < n && arr[j+1].compareTo(arr[j]) > 0 )
                j += 1;

            if( e.compareTo(arr[j]) >= 0 )
                break;

            arr[k] = arr[j];
            k = j;
        }

        arr[k] = e;
    }

    // 测试 HeapSort
    public static void main(String[] args) {
        Integer[] arr = {10, 91, 8, 7, 6, 5, 4, 3, 2, 1};
        HeapSort.sort2(arr);
        PrintHelper.printArray(arr);
    }
}Click to copy
```

#### 6. 堆排序的应用——Top K问题

例如，有1亿个浮点数，如何找出其中最大的10000个？（B326）

## 计数排序

[计数排序详解](https://www.cnblogs.com/freedom314/p/5847092.html)

 算法的步骤如下：

- （1）找出待排序的数组中最大和最小的元素

- （2）统计数组中每个值为i的元素出现的次数，存入数组C的第i项

- （3）对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）

- （4）反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1

  ![img](https://www.runoob.com/wp-content/uploads/2019/03/countingSort.gif)

## 桶排序

桶排序是计数排序的升级版。它利用了函数的映射关系，高效与否的关键就在于这个映射函数的确定。为了使桶排序更加高效，我们需要做到这两点：

1. 在额外空间充足的情况下，尽量增大桶的数量
2. 使用的映射函数能够将输入的 N 个数据均匀的分配到 K 个桶中

### 1. 什么时候最快

当输入的数据可以均匀的分配到每一个桶中。

### 2. 什么时候最慢

当输入的数据被分配到了同一个桶中。

### 3. 示意图

元素分布在桶中：

![img](https://www.runoob.com/wp-content/uploads/2019/03/Bucket_sort_1.svg_.png)

然后，元素在每个桶中排序：

![img](https://www.runoob.com/wp-content/uploads/2019/03/Bucket_sort_2.svg_.png)

## 基数排序

基数排序是一种非比较型整数排序算法，其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。由于整数也可以表达字符串（比如名字或日期）和特定格式的浮点数，所以基数排序也不是只能使用于整数。

### 1. 基数排序 vs 计数排序 vs 桶排序

基数排序有两种方法：

这三种排序算法都利用了桶的概念，但对桶的使用方法上有明显差异：

- 基数排序：根据键值的每位数字来分配桶；
- 计数排序：每个桶只存储单一键值；
- 桶排序：每个桶存储一定范围的数值；

### 2. LSD 基数排序动图演示

![img](https://www.runoob.com/wp-content/uploads/2019/03/radixSort.gif)