/*Given a linked list, remove the nth node from the end of list and return its head.

For example,

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.

Note:
Try to do this in one pass.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        if(head == null || n <= 0) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode predelete = dummy;
        for(int i = 0; i < n; i++) {
            //假设总共有6个节点, 我要删除倒数第7个, 那么就会到这里判断head == null, 从而返回null; 如果n == 6的时候, head == null之后, 会退出循环, 不会判断
            if(head == null) { 
                return null;
            }
            head = head.next;
        }
        
        while(head != null) {
            head = head.next;
            predelete = predelete.next; //走到要删除节点的前一个
        }
     
        predelete.next = predelete.next.next;
        return dummy.next;
    }
}
