/*Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1

Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the 
kthSmallest routine?
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    //dfs
    private int result = 0;
    public int kthSmallest(TreeNode root, int k) {
        
        if(root == null || k <= 0) {
            return Integer.MIN_VALUE;
        }

        kthSmallestHelper(root, k);
        return result;
    }
    
    private int kthSmallestHelper(TreeNode root, int k) {
        if(root == null) {
            return k;
        }
        
        k = kthSmallestHelper(root.left, k); //k回朔的时候一定要更新, 这样才能改变之前栈中k的大小
        k--;
        if(k == 0) {
            result = root.val; //找到就给result
            return k;
        }
        k = kthSmallestHelper(root.right, k);
        return k;
    }
}
