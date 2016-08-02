/*Remove all elements from a linked list of integers that have value val.
Example

Given 1->2->3->3->4->5->3, val = 3, you should return the list as 1->2->4->5
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
    /**
     * @param head a ListNode
     * @param val an integer
     * @return a ListNode
     */
    public ListNode removeElements(ListNode head, int val) {
        // Write your code here
        
        if(head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        while(head.next != null) {
           if(head.next.val != val) {
               head = head.next;
           } else {
               head.next = head.next.next;
           }
        }
        return dummy.next;
    }
    
    
    // recursive
    public ListNode removeElements(ListNode head, int val) {
        // Write your code here
        
        if(head == null) {
            return null;
        }
        head.next = removeElements(head.next, val); //一次性窜到最后
        return head.val == val ? head.next : head; //第一次返回的肯定是null, 接下来再边返边连
    }
}
