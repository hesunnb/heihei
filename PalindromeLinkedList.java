/*Implement a function to check if a linked list is a palindrome.

Example
Given 1->2->1, return true

Challenge 
Could you do it in O(n) time and O(1) space?*/

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
     * @return a boolean
     */
    public boolean isPalindrome(ListNode head) {
        // Write your code here
        
        if(head == null || head.next == null) {
            return true;
        }
        ListNode headTail = reverse(findMiddle(head)); //找到中点, 然后把中点之后的链表reverse
        while(head != null) {
            if(head.val == headTail.val) { //然后尾节点和头节点同时往中间走, 判断值相不相等, 结束条件就是head为null
                head = head.next;
                headTail = headTail.next;
            } else {
                return false;
            }
        }
        return true;
    }
    
    private ListNode findMiddle(ListNode head)
    {
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null) //要蹦两个next, 就要看一个next; 要看一个next, 就要看自己
        {
            fast = fast.next.next; //窜两位
            slow = slow.next; //窜一位，fast到尾, slow到中间
        }
        return slow;
    }
    
    private ListNode reverse(ListNode head)
    {
        ListNode prev = null;
        ListNode temp = null;
        while(head != null)
        {
            temp = head.next; //把head的下一个节点保存一下
            head.next = prev; //head的指针指向前一个节点
            prev = head; //prev向后窜
            head = temp; //head向后窜，这里就要用到保存的temp节点，因为head的指向在head.next = prev就已经改变啦，所以要用临时节点保存
        }
        return prev;
    }
}
