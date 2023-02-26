//
////Given a linked list containing an integer value, return the number of steps required to sort an array in
////        ascending order by eliminating elements at each step
//class Question4B {
//    static class ListNode {
//        int val;
//        ListNode next;
//
//        ListNode(int x) {
//            val = x;
//        }
//    }
//
//    public int sortList(ListNode head) {
//        if (head == null || head.next == null)
//            return 0;
//
//        int count = 0;
//        ListNode current = head;
//        while (current.next != null) {
//            if (current.val > current.next.val) {
//                current.next = current.next.next;
//                count++;
//            } else {
//                current = current.next;
//            }
//        }
//        return count;
//    }
//
//    public static void main(String[] args) {
//        Question4B obj = new Question4B();
//        ListNode head = new ListNode(5);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(1);
//        head.next.next.next = new ListNode(0);
//        head.next.next.next.next = new ListNode(8);
//        head.next.next.next.next.next = new ListNode(3);
//
//
//        System.out.println("Number of steps required to sort the linked list: " + obj.sortList(head));
//    }
//}


import java.util.*;

 class LinkedListSortSteps {

    public static int getSortSteps(LinkedList<Integer> list) {
        int steps = 0;
        boolean changed = true;

        while (changed) {
            changed = false;
            int i = 1;
            while (i < list.size()) {
                if (list.get(i - 1) > list.get(i)) {
                    list.remove(i);
                    changed = true;
                } else {
                    i++;
                }
            }
            if (changed) {
                steps++;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(3, 5, 2, 1, 4));
        int steps = getSortSteps(list);
        System.out.println("Number of steps required: " + steps);
    }
}