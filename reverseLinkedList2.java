/*Reverse a linked list from position m to n.

 Notice

Given m, n satisfy the following condition: 1 ≤ m ≤ n ≤ length of list.

Example
Given 1->2->3->4->5->NULL, m = 2 and n = 4, return 1->4->3->2->5->NULL.*/

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
     * @oaram m and n
     * @return: The head of the reversed ListNode
     */
     
    //Testcase的话：就考虑链表头翻转，链表中翻转还有链表尾翻转就行啦
    //九章的解法，清晰，首选，棒棒哒
    public ListNode reverseBetween(ListNode head, int m, int n)
    {
        if(head == null || head.next == null || m >= n) //注意题目说1 ≤ m ≤ n ≤ length of list, 所以m,n越界的那些情况并没有处理
        {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        for(int i = 1; i < m; i++)
        {
            head = head.next; //head窜到需要翻转的节点的前一个位置
        }
        
        ListNode premNode = head; //premNode是需要翻转的节点的前一个位置
        ListNode mNode = head.next; //需要翻转的节点的第一个位置
        ListNode nNode = head.next; //需要翻转的节点的第一个位置
        ListNode postnNode = nNode.next; //在nNode后面
        
        for(int i = m; i < n; i++) //进行翻转
        {
            ListNode temp = postnNode.next;
            postnNode.next = nNode;
            nNode = postnNode;
            postnNode = temp;
        }
        premNode.next = nNode; //把翻转好的节点连回原来的节点
        mNode.next = postnNode;
        return dummy.next; //返回第一个位置
    }
    
    
    //自己写的，不具参考性
    /*public ListNode reverseBetween(ListNode head, int m , int n) {
        // write your code
        if(head == null || head.next == null)
        {
            return head;
        }
        
        ListNode dummytemp = new ListNode(0); //作为翻转节点的虚拟节点
        ListNode dummyhead = new ListNode(0); //因为head也会遭到翻转，所以引入这个节点把head提前
        
        dummyhead.next = head;
        head = dummyhead; //head到了dummyhead的位置
        
        ListNode start = head;
        
        for(int i = 0; i < m - 1; i++)
        {
            start = start.next; //让start窜到需要翻转的节点的前一个位置
        }
        
        ListNode flagstart = start.next; //flagstart是所要翻转的节点的第一个位置
        ListNode end = start;
        
        for(int i = 0; i < n - m + 1; i++)
        {
            end = end.next; //在start的基础上继续向后找end,不用从头找，降低复杂度，让其扫一遍就能完成；end是索要翻转节点的最后一个字母
        }
       
        ListNode flagend = end.next; //flagend是索要翻转节点最后一个字母的下一位，就算翻转的是尾部，那么flagend也就是null,不会访问不到
        ListNode p = start.next; //p和q都在flagstart的位置上，准备进行翻转
        ListNode q = start.next;
       
        while(q != flagend) //与reverse linked list 1的方法一样，进行翻转
        {
            q = q.next;
            p.next = dummytemp.next;
            dummytemp.next = p;
            p = q;
        }
        
        start.next = end; //把翻转好的节点连回原来的节点
        flagstart.next = flagend;
        return dummyhead.next; //返回第一个位置
    }*/
}
