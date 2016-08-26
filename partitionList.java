/*Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example
Given 1->4->3->2->5->2->null and x = 3,
return 1->2->2->4->3->5->null.*/

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
     * @param head: The first node of linked list.
     * @param x: an integer
     * @return: a ListNode 
     */
     
    //思路就是用两个虚拟节点一个走比x小的，一个走大于等于x的，然后分出来两条链表，然后左右一合就行啦
    public ListNode partition(ListNode head, int x) {
        // write your code here
        if(head == null)
        {
            return null;
        }
        
        ListNode leftDummy = new ListNode(0); //左链表虚拟节点
        ListNode rightDummy = new ListNode(0); //右链表虚拟节点
        ListNode left = leftDummy; //left负责往下跑
        ListNode right = rightDummy; //right也负责往下跑
        while(head != null)
        {
            if(head.val < x) //小于的加入到左链表
            {
                left.next = head;
                left = head;
            }
            else //大于等于的加入到右链表
            {
                right.next = head;
                right = head;
            }
            head = head.next; //每次head都向下窜
        }
        right.next = null; //连接左右链表
        left.next = rightDummy.next;
        return leftDummy.next; //返回第一个位置
    }
}
