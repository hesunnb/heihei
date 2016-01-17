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
     * @return: You should return the head of the sorted linked list,
                    using constant space complexity.
     */
    public ListNode sortList(ListNode head) {  
        // write your code here
        
        //九章一共三个方法：merge sort, quick sort1, quick sort2
        //这个是merge sort,时间复杂度:O(nlogn),空间复杂度：在链表的情况下是O(1),本题就是；数组是O(n)，要开辟新数组
        if(head == null || head.next == null)
        {
            return head;
        }
        
        //每次都是先找中点，然后递归调用再找中点，最后排序回朔合并，所以有n个logn,复杂度就是O(nlogn)
        ListNode mid = findMiddle(head); //找中点
        ListNode right = sortList(mid.next); //先排中点右边
        mid.next = null; //从中间劈开
        ListNode left = sortList(head); //再排中点左边
        
        return merge(left, right); //合并排序好的左右链表
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
    
    private ListNode merge(ListNode head1, ListNode head2) //这个和合并merge two sorted lists那道题的程序一样
    {
        ListNode dummy = new ListNode(0);
        ListNode lastNode = dummy;
        
        while(head1 != null && head2 != null)
        {
            if(head1.val < head2.val)
            {
                lastNode.next = head1;
                head1 = head1.next;
            }
            else
            {
                lastNode.next = head2;
                head2 = head2.next;
            }
            lastNode = lastNode.next;
        }
        if(head1 != null)
        {
            lastNode.next = head1;
        }
        else
        {
            lastNode.next = head2;
        }
        return dummy.next;
    }
}
