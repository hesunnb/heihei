/*Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.*/

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
 
public class Solution {

    //开空间哈希表
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }
        
        Map<ListNode, ListNode> map = new HashMap<ListNode, ListNode>();
        ListNode headA2 = headA;
        while(headA2 != null) {
            if(!map.containsKey(headA2)) {
                map.put(headA2, headA2);
            }
            headA2 = headA2.next;
        }
        
        ListNode headB2 = headB;
        while(headB2 != null) {
            if(map.containsKey(headB2)) {
                return headB2;
            }
            headB2 = headB2.next;
        }
        
        return null;
    }
}
