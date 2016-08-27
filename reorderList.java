/*Given a singly linked list L: L0 → L1 → … → Ln-1 → Ln

reorder it to: L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …

Example
Given 1->2->3->4->null, reorder it to 1->4->2->3->null.

You must do this in-place without altering the nodes' values.*/

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
     * @param head: The head of linked list.
     * @return: void
     */
     
    //就是findmiddle, reverse, merge这些小问题的集合
    //复杂度:O(n)
    public void reorderList(ListNode head) {  
        // write your code here
        if(head == null || head.next == null)
        {
            return;
        }
        
        ListNode mid = findMiddle(head); //找中点
        ListNode right = reverse(mid.next);
        mid.next = null;
        merge(head, right);
    }
    
    private ListNode findMiddle(ListNode head)
    {
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null) //要蹦两个next, 就要看一个next; 要看一个next, 就要看自己
        {
            fast = fast.next.next; //窜两位
            slow = slow.next; //窜一位，fast到尾, slow到中间
        }
        return slow;
    }
    
    private ListNode reverse(ListNode head)
    {
        ListNode prev = null;
        while(head != null)
        {
            ListNode temp = head.next; //把head的下一个节点保存一下
            head.next = prev; //head的指针指向前一个节点
            prev = head; //prev向后窜
            head = temp; //head向后窜，这里就要用到保存的temp节点，因为head的指向在head.next = prev就已经改变啦，所以要用临时节点保存
        }
        return prev;
    }
    
    private void merge(ListNode head1, ListNode head2) //这个和合并merge two sorted lists那道题的程序一样
    {
        ListNode dummy = new ListNode(0);
        ListNode lastNode = dummy;
        int index = 0;
        while(head1 != null && head2 != null)
        {
            if (index % 2 == 0) {
                lastNode.next = head1;
                head1 = head1.next;
            } else {
                lastNode.next = head2;
                head2 = head2.next;
            }
            lastNode = lastNode.next;
            index ++;
        }
        if(head1 != null)
        {
            lastNode.next = head1;
        }
        else
        {
            lastNode.next = head2;
        }
    }
}
