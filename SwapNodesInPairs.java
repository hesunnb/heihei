/*Given a linked list, swap every two adjacent nodes and return its head.
Example

Given 1->2->3->4, you should return the list as 2->1->4->3.
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
     * @return a ListNode
     */
    public ListNode swapPairs(ListNode head) {
        // Write your code here
        
        if(head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        while(head.next != null && head.next.next != null) {
            ListNode node1 = head.next;
            ListNode node2 = head.next.next;
            // head->n1->n2->...
            // => head->n2->n1->...
            head.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            
            head = node1; //move to next pairs
        }
        return dummy.next;
    }
}
