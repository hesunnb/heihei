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
     * @param node: the node in the list should be deleted
     * @return: nothing
     */
     
    //前提是这个node是链表中间的点，不包括表头和表尾(表头也可删, 表尾不行)
    //O(1)解决
    public void deleteNode(ListNode node) {
        // write your code here
        node.val=node.next.val;
        node.next=node.next.next;
    }
}
