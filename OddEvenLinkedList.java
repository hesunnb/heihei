/*Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node 
number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example 1:

Input: 1->2->3->4->5->NULL
Output: 1->3->5->2->4->NULL
Example 2:

Input: 2->1->3->5->6->4->7->NULL
Output: 2->3->6->7->1->5->4->NULL
Note:

The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    //solution2: (own)
    public ListNode oddEvenList(ListNode head) {
        
        if(head == null || head.next == null || head.next.next == null) {
            return head; //没有链表, 或者链表长度为1,2的时候直接返回
        }
        
        ListNode dummyNode1 = head;
        ListNode dummyNode2 = head.next;
        ListNode preNode1 = dummyNode1;
        ListNode preNode2 = dummyNode2;
        
        while((preNode1.next != null && preNode1.next.next != null) || (preNode2.next != null && preNode2.next.next != null)) {
            if(preNode1.next != null && preNode1.next.next != null) {
                preNode1.next = preNode1.next.next;
                preNode1 = preNode1.next;
            }
            
            if(preNode2.next != null && preNode2.next.next != null) {
                preNode2.next = preNode2.next.next;
                preNode2 = preNode2.next;
            }
        }
        preNode2.next = null;
        preNode1.next = dummyNode2;
        return dummyNode1;
    }
}
