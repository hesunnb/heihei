/*Given a linked list and two values v1 and v2. Swap the two nodes in the linked list with values v1 and v2. It's guaranteed there is no duplicate values in the linked list. If v1 or v2 does not exist in the given linked list, do nothing.

 Notice

You should swap the two nodes with values v1 and v2. Do not directly swap the values of the two nodes.

Example
Given 1->2->3->4->null and v1 = 2, v2 = 4.

Return 1->4->3->2->null.*/

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
     * @oaram v1 an integer
     * @param v2 an integer
     * @return a new head of singly-linked list
     */
    public ListNode swapNodes(ListNode head, int v1, int v2) {
        // Write your code here
        
        if(head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        ListNode node1Prev = null;
        ListNode node2Prev = null;
        int count1 = 0;
        int count2 = 0;
        while(current.next != null) { //找到两个交换节点各自的前一个节点
            if(current.next.val == v1) {
                node1Prev = current;
            }
            if(current.next.val == v2) {
                node2Prev = current;
            }
            if(node1Prev == null) {
                count1++;
            }
            if(node2Prev == null) {
                count2++;
            }
            current = current.next;
        }
        
        if(node1Prev == null || node2Prev == null) { //有一个没有被赋值就说明没有交换的节点
            return head;
        }
        
        if(count1 > count2) { //node1Prev在node2Prev后面, 那就交换一下, 保证node1Prev一直在前
            ListNode temp = node2Prev;
            node2Prev = node1Prev;
            node1Prev = temp;
        }
        
        ListNode node1 = node1Prev.next;
        ListNode node2 = node2Prev.next;
        ListNode node2Next = node2.next;
        if(node1Prev.next == node2Prev) { //两个交换节点相邻
            node1Prev.next = node2;
            node1.next = node2Next;
            node2.next = node1;
        } else { //两个交换节点不相邻
            node1Prev.next = node2; //交换方法再看的时候画图
            node2.next = node1.next;
            node2Prev.next = node1;
            node1.next = node2Next;
        }
        
        return dummy.next;
    }
}
