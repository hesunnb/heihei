/**
 * Definition for ListNode
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
     * @param ListNode head is the head of the linked list
     * @return: ListNode head of linked list
     */
     
    //用一个指针(首选) O(n)
    public static ListNode deleteDuplicates(ListNode head)
    {
        if(head == null || head.next == null)
        {
            return head;
        }
        
        ListNode node = head;
        while(node.next != null)
        {
            if(node.val == node.next.val)
            {
                node.next = node.next.next;
            }
            else
            {
                node = node.next;
            }
        }
        return head;
    }
     
    
    //Recursion
    public ListNode deleteDuplicates(ListNode head) {
            if(head == null || head.next == null)return head;
            head.next = deleteDuplicates(head.next);
            return head.val == head.next.val ? head.next : head;
    } 
    
     
    /*用两个指针
    public static ListNode deleteDuplicates(ListNode head) { 
        // write your code here
        if(head == null || head.next == null)
        {
            return head;
        }
        ListNode p1 = head;
        ListNode p2 = head.next;
        while(p2 != null)
        {
            if(p1.val == p2.val)
            {
                p1.next = p2.next;
                p2 = p2.next;
            }
            else
            {
                p1 = p1.next;
                p2 = p2.next;
            }
        }
        return head;
    }  */
}