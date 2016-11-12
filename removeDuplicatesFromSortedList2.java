/*Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3. */

/**
 * Definition for ListNode
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
    /**
     * @param ListNode head is the head of the linked list
     * @return: ListNode head of the linked list
     */
     
    //九章方法，用一个虚拟节点，只用一个头指针(好呀，首选)
    //一般来说当链表的头也会发生变化的时候就要用到虚拟节点
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) {//没有节点以及一个节点返回head就行
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        while (head.next != null && head.next.next != null) { //保证head后两个元素都不是空，好进行判断
            if (head.next.val == head.next.next.val) {
                int val = head.next.val;
                while (head.next != null && head.next.val == val) { //空就是到结尾了，语句意思就是没到结尾且下一个值重复
                    head.next = head.next.next;
                }            
            } else {
                head = head.next; //不等就向后窜一位
            }
        }
        
        return dummy.next; //返回最开始的位置
    }
    
    /*
    Testcase: 1. 1,2,3,3,4,4,5  中间重复
              2. 1,1,1,2,3  头重复
              3. 1,1,1,2,3,5,6,6  尾重复
    */
    
    //for unsorted list, 删掉所有重复的, 扫两遍, 第一遍用哈希表存每个元素出现的次数, 第二次扫描大于1的就全部移除
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        Map<Integer, Integer> map = new HashMap<>();
        ListNode node = head;
        while(node != null) {
            if(!map.containsKey(node.val)) {
                map.put(node.val, 1);
            } else {
                map.put(node.val, map.get(node.val) + 1);
            }
            node = node.next;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        while(head.next != null) {
            if(map.get(head.next.val) > 1) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }
 
    //也是引用虚拟节点的方法，来自于网上用了一个虚拟节点，两个指针 
    /*public static ListNode deleteDuplicates(ListNode head) {
        // write your code here
        if(head == null || head.next == null)
        {
            return head;
        }
        ListNode p = head;
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while(p.next != null)
        {
            if(p.val == p.next.val)
            {
                int val;
                val = p.val;
                p = p.next.next;
                while(p != null && p.val == val)
                {
                    p = p.next;
                }
                if(p == null)
                {
                    return dummy.next;
                }
            }
            else
            {
                tail.next = p;
                tail = p;
                p = p.next;
                tail.next = null;
            }
        }
        tail.next = p;
        return dummy.next;
    }*/
}

