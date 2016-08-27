/*Given a linked list, return the node where the cycle begins.

If there is no cycle, return null.

Example
Given -21->10->4->5, tail connects to node index 1，return 10*/

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
     * @return: The node where the cycle begins. 
     *           if there is no cycle, return null
     */
    
    //快慢指针都在head出发
    public ListNode detectCycle(ListNode head) {  
        // write your code here
        
        if(head == null || head.next == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                ListNode slow2 = head; //让第二个slow指针从head出发
                while(slow2 != slow) {
                    slow2 = slow2.next; //slow2与slow同时走, 碰到的时候就是环的起始位置
                    slow = slow.next;
                }
                return slow;
            }
        }
        
        return null;
    }
    
    
    //快指针在慢指针的后一点出发
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next==null) {
            return null;
        }

        ListNode fast, slow;
        fast = head.next;
        slow = head;
        while (fast != slow) {
            if(fast==null || fast.next==null)
                return null;
            fast = fast.next.next;
            slow = slow.next;
        } 
        
        while (head != slow.next) {
            head = head.next;
            slow = slow.next;
        }
        return head;
    }
}


//开哈希表
public class Solution {
    /**
     * @param head: The first node of linked list.
     * @return: The node where the cycle begins. 
     *           if there is no cycle, return null
     */
    public ListNode detectCycle(ListNode head) {  
        // write your code here
        
        if(head == null) {
            return head;
        }
        
        Map<ListNode, ListNode> map = new HashMap<ListNode, ListNode>();
        while(head != null) {
            if(!map.containsKey(head)) {
                map.put(head, head);
            } else {
                return map.get(head);
            }
            head = head.next;
        }
        
        return head;
    }
}
