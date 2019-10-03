import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by artsing on 2019/8/27.
 */

public class PrintTree {

    public static void main(String[] args) {
        TreeNode pRoot = new TreeNode(1);
        pRoot.left = new TreeNode(2);
        pRoot.right = new TreeNode(3);
        pRoot.left.left = new TreeNode(4);
        pRoot.left.right = new TreeNode(5);
        pRoot.right.left = new TreeNode(6);
        pRoot.right.right = new TreeNode(7);
        ArrayList<ArrayList<Integer>> a = Print(pRoot);
        System.out.println(a.toString());

    }

    static  ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer> > res =new  ArrayList<ArrayList<Integer> >();
        Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
        queue1.add(pRoot);
        Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
        while(!queue1.isEmpty()||!queue2.isEmpty()){
            ArrayList<Integer> ceng1 = new ArrayList<Integer>();
            while(!queue1.isEmpty()){
                TreeNode temp1 = queue1.poll();
                if (temp1!=null) {
                    ceng1.add(temp1.val);
                    queue2.add(temp1.left);
                    queue2.add(temp1.right);
                }
            }
            if(!ceng1.isEmpty())   res.add(ceng1);

            ArrayList<Integer> ceng2 = new ArrayList<Integer>();
            while(!queue2.isEmpty()){
                TreeNode temp2 = queue2.poll();
                if (temp2!=null) {
                    ceng2.add(temp2.val);
                    queue1.add(temp2.left);
                    queue1.add(temp2.right);
                }
            }
            if(!ceng2.isEmpty())  res.add(ceng2);

        }
        return res;
    }
}
