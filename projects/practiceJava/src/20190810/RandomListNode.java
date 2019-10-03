/**
 * Created by artsing on 2019/8/10.
 */
public class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }

    public static  void main(String[] args){
        RandomListNode node0 = new RandomListNode(0);
        RandomListNode node1 = new RandomListNode(1);
        RandomListNode node2 = new RandomListNode(2);
        RandomListNode node3 = new RandomListNode(3);
        RandomListNode node4 = new RandomListNode(4);
        node0.next=node1;
        node0.random=node2;
        node1.next=node2;
        node1.random=node4;
        node2.next=node3;
        node3.next=node4;
        Clone(node0);
    }
    public static RandomListNode Clone(RandomListNode pHead)
    {
        if(pHead==null) return null;
        //先加一遍next节点
        RandomListNode temp =pHead;
        while(temp!=null){
            RandomListNode add = new RandomListNode(temp.label);
            RandomListNode nextNode =temp.next;     //为什么要再复制出来？？？  //不存一下就找不见了
            temp.next=add;
            add.next=nextNode;
            temp=add.next;
        }
        //再把random关联上
        temp =pHead;
        while(temp!=null){
            if(temp.random!=null){
                temp.next.random=temp.random.next;
            }
            temp=temp.next.next;
        }
        //再把新加的节点连接起来
        RandomListNode result= pHead.next;
        temp =pHead;
        while(temp!=null){
            RandomListNode add =temp.next;
            temp.next =add.next;
            if(add.next!=null){
                add.next=add.next.next;
            }
            temp =temp.next;
        }

        return result;
    }
}

