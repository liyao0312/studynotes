public class BinaryTree2{
    private Node root;
    public BinaryTree2(){
        this.root=null;
    }
    //排序二叉树插入data
    public void insert(int data){
        Node newNode=new Node(data);
        if(root==null){root=newNode;}
        else{
            Node current =root;
            Node parent;
            while(true){
                parent=current;
                if(data<parent.data){
                    current=current.left;
                    if(current==null){
                        parent.left=newNode;
                        return;
                    }
                }else{
                    current=current.right;
                    if(current==null){
                        parent.right=newNode;
                        return;
                    }
                }

            }
        }
    }
    //数组构建二叉树
    public void buildTree(int[] data){
        for(int i =0 ;i<data.length;i++){
            insert(data[i]);
        }
    }
    //中序遍历方法递归实现
    public void inOrder(){
        this.inOrder(this.root);
    }
    public void inOrder(Node localRoot){
        if(localRoot!=null){
            inOrder(localRoot.left);
            System.out.print(localRoot.data);
            inOrder(localRoot.right);
        }
    }
    //先序遍历方法递归实现
    public void preOrder(){
        this.preOrder(this.root);
    }
    public void preOrder(Node localNode){
        if(localNode!=null){
            System.out.print(localNode.data);
            preOrder(localNode.left);
            preOrder(localNode.right);
        }
    }
    //后序遍历方法递归实现
    public void postOrder(){
        this.postOrder(this.root);
    }
    public void postOrder(Node localNode){
        if(localNode!=null){
        postOrder(localNode.left);
        postOrder(localNode.right);
        System.out.print(localNode.data);
        }
    }
    public void initTree(int[] preOrder,int[] inOrder){
        this.root=this.initTree(preOrder,0,preOrder.length-1,inOrder,0,inOrder.length-1);
    }

    public Node initTree(int[] preorder,int start1,int end1,int[] inorder,int start2,int end2){
        if(start1>end1||start2>end2) {return null;}     
        Node head = new Node(preorder[start1]);
        int index=getIndex(head.data,inorder,start2,end2);  //头结点在中序序列中的位置
        int num=index-start2-1;
        head.left=initTree(preorder,start1+1,start1+1+num,inorder,start2,start2+num);
        head.right=initTree(preorder,start1+2+num,end1,inorder,index+1,end2);
        return head;
    }
    public int getIndex(int data,int[] inorder,int start,int end){
        for(int i=start;i<=end;i++){
            if(inorder[i]==data) return i;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] preOrder = {1,2,4,8,9,5,10,3,6,7};  
        int[] inOrder = {8,4,9,2,10,5,1,6,3,7};
        BinaryTree2 biTree=new BinaryTree2();
        biTree.initTree(preOrder,inOrder);
        System.out.println("后序遍历二叉树：");
        biTree.postOrder();
    }

   


}