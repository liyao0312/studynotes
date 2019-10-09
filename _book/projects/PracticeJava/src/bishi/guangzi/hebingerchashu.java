import java.util.*;

/**
 * Created by artsing on 2019/10/7.
 */
public class hebingerchashu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] nn = new int[n][3];
        for(int i=0;i<n;i++){
            nn[i][0]=sc.nextInt();
            nn[i][1]=sc.nextInt();
            nn[i][2]=sc.nextInt();
        }
        int[][] mm = new int[m][3];
        for(int j=0;j<m;j++){
            mm[j][0]=sc.nextInt();
            mm[j][1]=sc.nextInt();
            mm[j][2]=sc.nextInt();
        }
        ExamTreeNode tree1=madeTree(nn);
        ExamTreeNode tree2=madeTree(mm);
        ExamTreeNode tree3=addTree(tree1,tree2);
        printTree(tree3);
    }


    private static void printTree(ExamTreeNode root) {
         if(root==null) return;
        Queue<ExamTreeNode> queue =  new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()){
            root = queue.peek();
            System.out.print(root.val+" ");
            queue.poll();
            if(root.left!=null) queue.add(root.left);
            if(root.right!=null) queue.add(root.right);
        }


    }


    private static ExamTreeNode addTree(ExamTreeNode tree1, ExamTreeNode tree2) {
        if(tree1!=null&&tree2!=null){
            tree1.left =addTree(tree1.left,tree2.left);
            tree1.right =addTree(tree1.right,tree2.right);
            tree1.val+=tree2.val;
            return  tree1;
        }
        return tree1==null?tree2:tree1;
    }

    private static ExamTreeNode madeTree(int[][] nn) {
        ExamTreeNode root = new ExamTreeNode();
        ExamTreeNode head =root;
        Map<Integer,ExamTreeNode> map = new HashMap<>();
        for(int i=0;i<nn.length;i++){
            int number =i+1;
            int left=nn[i][0];
            int right=nn[i][1];
            int val=nn[i][2];

            if(map.containsKey(number)){
                root = map.get(number);
            }
            root.val=val;

            if(left==0){
                root.left=null;
            }else{
                root.left=new ExamTreeNode(0);
                map.put(left,root.left);
            }

            if(right==0){
                root.right=null;
            }else{
                root.right=new ExamTreeNode(0);
                map.put(right,root.right);
            }
        }
        return  head;
    }


}

class ExamTreeNode {
    int val;
    ExamTreeNode left;
    ExamTreeNode right;
    ExamTreeNode(int x) { val = x; }
    public ExamTreeNode() {}
}