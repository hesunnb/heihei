/*Given a linked list, return the node where the cycle begins.

If there is no cycle, return null.

Example
Given -21->10->4->5, tail connects to node index 1，return 10*/

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
 
//开哈希表
public class Solution {
    /**
     * @param head: The first node of linked list.
     * @return: The node where the cycle begins. 
     *           if there is no cycle, return null
     */
    public ListNode detectCycle(ListNode head) {  
        // write your code here
        
        if(head == null) {
            return head;
        }
        
        Map<ListNode, ListNode> map = new HashMap<ListNode, ListNode>();
        while(head != null) {
            if(!map.containsKey(head)) {
                map.put(head, head);
            } else {
                return map.get(head);
            }
            head = head.next;
        }
        
        return head;
    }
}
