/*You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of 
their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7*/



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    
    //solution1: follow up里面不让reverse linked list, 所以用discuss的方法
    
    
    //solution2: 自己的方法, 运用反转链表
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        if(l1 == null || l2 == null) {
            return null;
        }
        
        //反转链表函数, 反转l1链表
        ListNode prev1 = null;
        ListNode temp1 = null;
        while(l1 != null) {
            temp1 = l1.next; 
            l1.next = prev1; 
            prev1 = l1; 
            l1 = temp1; 
        }
        
        //反转l2链表
        ListNode prev2 = null;
        ListNode temp2 = null;
        while(l2 != null) {
            temp2 = l2.next; 
            l2.next = prev2; 
            prev2 = l2; 
            l2 = temp2; 
        }
        
        //链表求和1函数
        ListNode head = new ListNode(0);
        ListNode cur1 = prev1; 
        ListNode cur2 = prev2;
        ListNode p = head;
        int carry = 0;
        while(cur1 != null || cur2 != null) {
            if(cur1 != null) {
                carry += cur1.val;
                cur1 = cur1.next;
            }
            
            if(cur2 != null) {
                carry += cur2.val;
                cur2 = cur2.next;
            }
            p.next = new ListNode(carry % 10);
            p = p.next;
            carry /= 10;
        }
        if(carry != 0) {
            p.next = new ListNode(carry);
        }
        
        head = head.next; //向后走一位, 否则会有上面new出来的那个值为0的节点
        
        //反转最终的链表
        ListNode prev3 = null;
        ListNode temp3 = null;
        while(head != null) {
            temp3 = head.next; 
            head.next = prev3; 
            prev3 = head; 
            head = temp3; 
        }
        
        return prev3;
    }
}
