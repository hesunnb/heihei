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
     * @param ListNode l1 is the head of the linked list
     * @param ListNode l2 is the head of the linked list
     * @return: ListNode head of linked list
     */
     
    //九章方法, 非递归
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // write your code here
        if(l1 == null)
        {
            return l2;
        }
        if(l2 == null)
        {
            return l1;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode lastNode = dummy;
        while(l1 != null && l2 != null)
        {
            if(l1.val < l2.val)
            {
                lastNode.next = l1;
                l1 = l1.next;
            }
            else
            {
                lastNode.next = l2;
                l2 = l2.next;
            }
            lastNode = lastNode.next; //lastNode是每次一定要窜的
        }
        
        if(l1 != null) //说明l2为空, lastNode在l2上
        {
            lastNode.next = l1; //把lastNode连到l1上
        }
        else //说明l1为空, lastNode在l1上
        {
            lastNode.next = l2; //把lastNode连到l2上
        }
        return dummy.next;
    }
    
    
    
    //递归, 没有用虚拟结点
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) 
        {
            return l2; //null和一个链表和, 和完之后肯定是那个不空的链表, 所以返回另一个链表
        }
        if(l2 == null)
        {
            return l1;
        } 
    
        if(l1.val < l2.val) { //谁小把谁留下, 合并剩下的链表
            l1.next = mergeTwoLists(l1.next, l2); //如果l1小, 就应该让l1后面的表和l2合并, 把合并好的链表链到l1.next上
            return l1; //然后把合并好的链表返回给上一级用
        } else {
            l2.next = mergeTwoLists(l2.next, l1); //和上面同理
            return l2;
        }
    }
}
