 class TreeNode {
      int val;
     TreeNode left;
     TreeNode right;
      TreeNode(int x) { val = x; }
     public TreeNode() {}
}

public class Solution {
    public static  void main (String[] args){
        int[] pre =new int[]{1,2,4,7,3,5,6,8};
        int[] in  =new int[]{4,7,2,1,5,3,8,6};
        TreeNode root = reConstructBinaryTree(pre,in);
        System.out.print(root.val);
    }
    public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre.length==0||in.length==0) return null;
        TreeNode root =reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
        return root;

    }
    public static TreeNode reConstructBinaryTree(int [] pre,int pstart,int pend,int [] in,int istart,int iend) {
        if(pstart>pend||istart>iend) return null;
        TreeNode root = new TreeNode(pre[pstart]);
        int index = returnIndex(pre[pstart],in);
        int len = index-istart;
        root.left =reConstructBinaryTree(pre,pstart+1,pstart+len,in,istart,istart+len-1);
        root.right=reConstructBinaryTree(pre,pstart+len+1,pend,in,istart+len+1,iend);
        return root;
    }

    public static int returnIndex(int num, int[] in){
        int result=0;
        for(int i=0;i<in.length;i++){
            if(num==in[i]){
                result=i;
                break;
            }
        }
        return result;
    }
}