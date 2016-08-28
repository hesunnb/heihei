/*Given a list, rotate the list to the right by k places, where k is non-negative.

Example
Given 1->2->3->4->5 and k = 2, return 4->5->1->2->3.*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param head: the List
     * @param k: rotate to the right k places
     * @return: the list after rotation
     */
    public ListNode rotateRight(ListNode head, int k) {
        // write your code here
        if (head == null) {
            return null;
        }
        
        int length = getLength(head);
        k = k % length; //要移动多少位
        if(k == 0) { //移动0位就不移动
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        ListNode tail = dummy;
        for (int i = 0; i < k; i++) {
            head = head.next; //head从dummy位置向后走k位
        }
        
        while (head.next != null) { //同时向后走
            tail = tail.next;
            head = head.next;
        }
        
        head.next = dummy.next; //进行rotate
        dummy.next = tail.next;
        tail.next = null;
        return dummy.next;
    }
    
    private int getLength(ListNode head) { //得到链表长度, 为了求余运算
        int length = 0;
        while (head != null) {
            length ++;
            head = head.next;
        }
        return length;
    }
}
