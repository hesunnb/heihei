/*Find the nth to last element of a singly linked list. 

The minimum number of nodes in list is n.

Example
Given a List  3->2->1->5->null and n = 2, return node whose value is 1.*/

/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */ 
public class Solution {
    /**
     * @param head: The first node of linked list.
     * @param n: An integer.
     * @return: Nth to last node of a singly linked list. 
     */
    ListNode nthToLast(ListNode head, int n) {
        // write your code here
        
        if(head == null || n <= 0) {
            return null;
        }
        
        ListNode result = head;
        ListNode p = head;
        for(int i = 0; i < n; i++) {
            if(p == null) { //如果n的值大于链表的长度, 那么这里就会起作用, 返回null
                return null;
            }
            p = p.next; //p往前走n步
        }
        
        while(p != null) { //p在正数第n+1的位置上
            p = p.next;
            result = result.next;
        }
        
        return result;
    }
}
