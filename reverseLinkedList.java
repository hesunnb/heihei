For linked list 1->2->3, the reversed linked list is 3->2->1

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
     * @return: The new head of reversed linked list.
     */
    
    //不用虚拟节点，九章方法，更简单(首选)
    //思路上很简单
    //链表testCase的考虑就是1个节点, 2个节点, 3个节点, 基本就可以了
    public ListNode reverse(ListNode head)
    {
        if(head == null || head.next == null)
        {
            return head;
        }
        
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
    
    
    //recursion
    public ListNode reverseList(ListNode head) {
    	if (head == null) {
    	    return head;
    	}
    	if (head.next == null) {
    	    return head;
    	}
    	
    	ListNode secondEle = head.next;
    	head.next = null;
    	ListNode revSecondEle = reverseList(secondEle);
    	secondEle.next = head;
    	return revSecondEle;
    }
    
    
    //也是虚拟节点法，跟着走一遍就好啦 
    public ListNode reverse(ListNode head) {
        // write your code here
        if(head == null || head.next == null)
        {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode p = head;
        ListNode q = head;
        while(q != null)
        {
            q = q.next;
            p.next = dummy.next;
            dummy.next = p;
            p = q;
        }
        return dummy.next;
    }
}

