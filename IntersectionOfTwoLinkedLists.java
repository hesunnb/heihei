/*Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;      
 *     }
 * }
 */
 
//不开空间, 让A和B的起点归在离交点相同的地方
public class Solution {
    /**
     * @param headA: the first list
     * @param headB: the second list
     * @return: a ListNode 
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // Write your code here
        
        if(headA == null || headB == null) {
            return null;
        }
        
        int Alength = getLength(headA);
        int Blength = getLength(headB);
        if(Alength > Blength) {
            int diff = Alength - Blength;
            while(diff > 0) {
                headA = headA.next;
                diff--;
            }
        } else if(Alength < Blength) {
            int diff = Blength - Alength;
            while(diff > 0) {
                headB = headB.next;
                diff--;
            }
        }
        
        //相同距离之后一起走
        while(headA != null && headB != null) {
            if(headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        
        return null; //没有交点
    }  
    
    private int getLength(ListNode head) {
        int sum = 0;
        while(head != null) {
            head = head.next;
            sum++;
        }
        return sum;
    }
}


//开空间哈希表
public class Solution {
  
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }
        
        Map<ListNode, ListNode> map = new HashMap<ListNode, ListNode>();
        while(headA != null) {
            if(!map.containsKey(headA)) {
                map.put(headA, headA);
            }
            headA = headA.next;
        }
        
        while(headB != null) {
            if(map.containsKey(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        
        return null;
    }
}
