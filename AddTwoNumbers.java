/*You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and 
each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.*/
 
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

// Iteratively, More concise(一个while就搞定) 
public class Solution {
     
    //正因为数是倒着的才可以从头开始逐位计算, 如果数是正着的倒没法这么算了
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode();
        ListNode head = dummy;
        int carry = 0;
        while(l1 != null || l2 != null) {
            if(l1 != null) {
                carry += l1.val;
                l1 = l1.next;
            }
            
            if(l2 != null) {
                carry += l2.val;
                l2 = l2.next;
            }
            
            ListNode node = new ListNode(carry % 10);
            head.next = node;
            head = node;
            carry /= 10;
        }
        if(carry != 0) {
            head.next = new ListNode(carry);
        }
        
        return dummy.next;
    }
}


// Recursively

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return helper(l1, l2, 0);
    }

    private ListNode helper(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null) {
            if (carry == 0) {
                return null;
            } else {
                return new ListNode(1);
            }
        } else if (l1 == null) {
            return helper(new ListNode(0), l2, carry);
        } else if (l2 == null) {
            return helper(l1, new ListNode(0), carry); //对于null的点传一个新的0的点, 就可以继续向下调用然后创建新的ans了
        } else {
            // both l1 and l2 have value
            int sum = l1.val + l2.val + carry;
            int resultNumber = sum % 10;
            int newCarry = sum / 10;
            ListNode ans = new ListNode(resultNumber);
            ans.next = helper(l1.next, l2.next, newCarry);
            return ans;
        }
    }
}


//Iteratively(三个while)
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
        	return null;
        }
        if (l1 == null && l2 != null) {
        	return l2;
        }
        if (l1 != null && l2 == null) {
        	return l1;
        }
        ListNode dummy = new ListNode(0);
		ListNode res = dummy;


        int carry = 0;
        while (l1 != null && l2 != null) {
        	int val = (l1.val + l2.val + carry) % 10;
        	carry = (l1.val + l2.val + carry) / 10;
        	res.next = new ListNode(val);
        	res = res.next;
        	l1 = l1.next;
        	l2 = l2.next;
        }

        if (l1 != null) {
        	while (l1 != null) {
        		int val =(l1.val + carry) % 10;
        		carry = (l1.val + carry) / 10;
        		res.next = new ListNode(val);
        		res = res.next;
        		l1 = l1.next;
        	}
        }

        if (l2 != null) {
        	while (l2 != null) {
        		int val =(l2.val + carry) % 10;
        		carry = (l2.val + carry) / 10;
        		res.next = new ListNode(val);
        		res = res.next;
        		l2 = l2.next;
        	}
        }
        if (carry != 0) {
            res.next = new ListNode(carry);
        }

        return dummy.next;
    }
}
