/*Given -21->10->4->5, tail connects to node index 1, return true

Follow up:
Can you solve it without using extra space?*/

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
 
//不开空间快慢指针
public class Solution {
    /**
     * @param head: The first node of linked list.
     * @return: True if it has a cycle, or false
     */
     
    //fast与slow同一点出发
    public boolean hasCycle(ListNode head) {  
        // write your code here
        if (head == null || head.next == null) {
            return false;
        }

        ListNode fast, slow;
        fast = head;
        slow = head;
        while (fast.next != null && fast.next.next != null) { //fast的下一个与下下个都不为空fast才走, 就说明fast本身不可能为空
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                return true;
            }
        } 
        return false;
    }
    
    //fast在slow的下一点出发
    public boolean hasCycle(ListNode head) {  
        // write your code here
        if (head == null || head.next == null) {
            return false;
        }

        ListNode fast, slow;
        fast = head.next;
        slow = head;
        while (fast != slow) {
            if(fast==null || fast.next==null) {
                return false;
            }
                
            fast = fast.next.next;
            slow = slow.next;
        } 
        return true;
    }
}


//开空间用哈希表
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null) {
            return false;
        }
        
        Map<ListNode, ListNode> map = new HashMap<>();
        while(head != null) {
            if(map.containsKey(head)) {
                return true;
            }
            map.put(head, head);
            head = head.next;
        }
        return false;
    }
}
