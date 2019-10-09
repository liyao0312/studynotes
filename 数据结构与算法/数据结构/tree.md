# 树和二叉树

- [树](#树)
- [红黑树](#红黑树)

- [二叉树](#二叉树)
  - [二分查找法](#二分查找法)
  - [二叉树遍历](#二叉树遍历)
- [二叉搜索树](#二叉搜索树)

  - [深度优先遍历](#深度优先遍历)
    - [前序遍历](#前序遍历)
    - [中序遍历](#中序遍历)
    - [后序遍历](#后序遍历)
  - [广度优先遍历](#广度优先遍历)
    - [层序遍历](#层序遍历)
- [AVL树](#avl树)

- [B数和B+树](#b树和b树)

## 树

```java
public class TreeNode<T> {  
    T value;  
          
    TreeNode<T> leftChild;  
    TreeNode<T> rightChild;  
  
    TreeNode(T value) {  
        this.value = value;  
    }  
    TreeNode() {  
    }     
}
```



## 红黑树

红黑树的特性:

（1）每个节点或者是黑色的，或者是红色的

（2）根节点是黑色的

（3）每个叶子节点（NIL，最后的空结点）是黑色。 [注意：这里叶子节点，是指为空(NIL或NULL)的叶子节点！]

（4）如果一个节点是红色的，那么他的孩子结点都是黑色的

（5）从任意一个节点到叶子节点，经过的黑色节点是一样的。[这里也就可以得到插入的节点必然为红色]

> [红黑树(一)之 原理和算法详细介绍 - 如果天空不死 - 博客园](http://www.cnblogs.com/skywang12345/p/3245399.html)

> [TreeSet and TreeMap](https://github.com/CarpenterLee/JCFInternals/blob/049c84bb65a3114ba4b8355d83c490fb9b26c6af/markdown/5-TreeSet%20and%20TreeMap.md)



## 二叉树

### 二分查找法

### 二叉树遍历

## 二叉搜索树

[java二叉树遍历——深度优先(DFS)与广度优先(BFS) 递归版与非递归版- *李耀*-天津大学 - *博客园*](https://www.cnblogs.com/liyao0312/p/11401019.html)

### 深度优先遍历

#### 前序遍历

#### 中序遍历

#### 后序遍历

### 广度优先遍历

#### 层序遍历

## AVL树

[平衡二叉树(*AVL* Tree) - *李耀*-天津大学 - *博客园*](https://www.baidu.com/link?url=XNlCZBPdrScwP2k8yU9vFadGwjpR1V-fLx05UbQ8jHzyzaJ4KE_X-NszvgyAlIFz-4Bh-dqSyQTQeb3st5xSQ_&wd=&eqid=a68a7c4d00280517000000035d613f41)

## B数和B+树

[【经典数据结构】B树与B+树- *李耀*-天津大学 - *博客园*](https://www.cnblogs.com/liyao0312/p/11406229.html)



