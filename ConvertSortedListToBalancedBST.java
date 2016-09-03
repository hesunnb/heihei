/*Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

Example
               2
1->2->3  =>   / \
             1   3*/
             
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
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */ 
 
//开空间然后是ConvertSortedArrayToBST的写法
//时间O(n)
public class Solution {
    /**
     * @param head: The first node of linked list.
     * @return: a tree node
     */
    public TreeNode sortedListToBST(ListNode head) {  
        // write your code here
        if(head == null) {
            return null;
        }
        
        List<Integer> list = new ArrayList<Integer>();
        while(head != null) { //把list都装到ArrayList里面
            list.add(head.val);
            head = head.next;
        }
        
        return buildTree(list, 0, list.size() - 1); //然后就方便查找了
    }
    
    private TreeNode buildTree(List<Integer> list, int start, int end) {
        int mid = 0;
        if(start <= end) {
            mid = start + (end - start) / 2;
        } else {
            return null;
        }
        
        TreeNode node = new TreeNode(list.get(mid));
        node.left = buildTree(list, start, mid - 1);
        node.right = buildTree(list, mid + 1, end);
        return node;
    }
}


//不开空间, 每次从头去找每段分下来的链表的中点
//时间O(nlogn)
public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) {
            return null;
        }
        
        return helper(head, null);
    }
    
    private TreeNode helper(ListNode head, ListNode tail) {
        if(head == tail) { //对于-1->0->1->3->null例子, 就会有head==tail的情况, 此时就要返回null, 否则就互相连就乱了
            return null;
        }
        if(head.next == tail) { //只剩下一个节点的时候, 就开始连接了
            return new TreeNode(head.val);
        }
        ListNode mid = findMiddle(head, tail); //找中点
        TreeNode root = new TreeNode(mid.val);
        root.left = helper(head, mid);
        root.right = helper(mid.next, tail);
        return root;
    }
    
    private ListNode findMiddle(ListNode head, ListNode tail) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
