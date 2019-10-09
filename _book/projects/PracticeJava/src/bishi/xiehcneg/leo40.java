//package bishi.xiehcneg;
//
//import java.util.Scanner;
//
//public class leo40 {
//
//    public static class ListNode {
//        int val;
//        ListNode next;
//        ListNode(int x) {
//            val = x;
//            next = null;
//        }
//    }
//
//    /*请完成下面这个函数，实现题目要求的功能
//    ******************************开始写代码******************************/
//    static ListNode partition(ListNode head,int m) {
//        ListNode h1 = new ListNode(-1);
//        ListNode h2 = new ListNode(-1);
//        ListNode p1 = h1;
//        ListNode p2 = h2;
//        ListNode p = head;
//        while (p != null) {
//            if (p.val <= m) {
//                p1.next = p;
//                p1 = p1.next;
//            } else {
//                p2.next = p;
//                p2 = p2.next;
//            }
//            p = p.next;
//        }
//        if (h1.next == null) {
//            return head;
//        } else {
//            p1.next = h2.next;
//            p2.next = null;
//            return h1.next;
//        }
//
//    }
//    /******************************结束写代码******************************/
//
//
//    public static void main(String[] args){
//        Scanner in = new Scanner(System.in);
//        ListNode head=null;
//        ListNode node=null;
//        int m=in.nextInt();
//        while(in.hasNextInt()){
//            int v=in.nextInt();
//            if(head==null){
//                node=new ListNode(v);
//                head=node;
//            }else{
//                node.next=new ListNode(v);
//                node=node.next;
//            }
//        }
//        head= partition2(head,m);
//        if(head!=null){
//            System.out.print(head.val);
//            head=head.next;
//            while(head!=null){
//                System.out.print(",");
//                System.out.print(head.val);
//                head=head.next;
//            }
//        }
//        System.out.println();
//    }
//}
