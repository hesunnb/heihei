/*Sort a linked list using insertion sort.

Example

Given 1->3->2->0->null, return 0->1->2->3->null.

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
    public ListNode insertionSortList(ListNode head) {
        
        ListNode dummy = new ListNode(0);
        while(head != null) {
            ListNode node = dummy;
            while(node.next != null && node.next.val < head.val) { //node不断向下窜, 寻找到head节点应该插入的位置
                node = node.next;
            }
            //node的下一个值不小于head的值, 那么head就应该指向大值(node.next), 然后让node.next指向head; 
            //如果node.next为null, 说明左边待插入的链表没有比head值大的节点了, 那么就应该让head.next指向null, 而node.next指向head;
            //总结起来就是head.next永远只向node.next(node.next要么就是比head大的值, 要么就是null), 而node.next永远指向head
            ListNode temp = head.next;
            head.next = node.next;
            node.next = head;
            head = temp;
        }
        return dummy.next; //head为null直接返回null
    }
}
