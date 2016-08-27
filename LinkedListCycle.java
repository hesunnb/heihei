/*Given -21->10->4->5, tail connects to node index 1, return true

Follow up:
Can you solve it without using extra space?*/

//开空间用哈希表
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null) {
            return false;
        }
        
        Map<ListNode, Integer> map = new HashMap<ListNode, Integer>();
        while(head != null) {
            if(!map.containsKey(head)) {
                map.put(head, 1);
            } else {
                return true;
            }
            head = head.next;
        }
        
        return false;
    }
}
