/*You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored in forward order, 
such that the 1's digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list.

Example
Given 6->1->7 + 2->9->5. That is, 617 + 295.

Return 9->1->2. That is, 912.*/

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


//自己的方法
public class Solution {
    /*
     * @param l1: The first list.
     * @param l2: The second list.
     * @return: the sum list of l1 and l2.
     */
    
    public ListNode addLists2(ListNode l1, ListNode l2) {
        // write your code here
        
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
    
};
