//package laohu;
//
///**
// * Created by artsing on 2019/9/13.
// */
//public class deleteDuplicates {
//
//    public ListNode solution(ListNode listHead) {
//        if (listHead == null) { return listHead;
//        }
//
//        if (listHead.next != null && listHead.val == listHead.next.val) {
//            while (listHead != null && listHead.next != null && listHead.val == listHead.next.val) {
//                listHead = listHead.next;
//            }
//            return solution(listHead.next);
//        } else {
//            listHead.next = solution(listHead.next);
//        }
//        return listHead;
//    }
//
//
//}
