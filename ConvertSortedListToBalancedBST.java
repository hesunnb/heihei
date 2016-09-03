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
