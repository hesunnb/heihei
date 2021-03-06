/*We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.

Return the same tree where every subtree (of the given tree) not containing a 1 has been removed.

(Recall that the subtree of a node X is X, plus every node that is a descendant of X.)

Example 1:
Input: [1,null,0,0,1]
Output: [1,null,0,null,1]
 
Explanation: 
Only the red nodes satisfy the property "every subtree not containing a 1".
The diagram on the right represents the answer.


Example 2:
Input: [1,0,1,0,0,0,1]
Output: [1,null,1,null,1]



Example 3:
Input: [1,1,0,1,1,0,1,0]
Output: [1,1,0,1,1,null,1]

图见题目

Note:

    The binary tree will have at most 100 nodes.
    The value of each node will only be 0 or 1.*/
    
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
    public TreeNode pruneTree(TreeNode root) {
        
        if(root == null) {
            return root;
        }
        
        pruneTreeHelper(root);
        return root;
    }
    
    private boolean pruneTreeHelper(TreeNode root) {
        if(root == null) {
            return false;
        }
        
        boolean left = pruneTreeHelper(root.left);
        boolean right = pruneTreeHelper(root.right);
        if(!left) { //左边null裁掉左边
            root.left = null;
        }
        if(!right) { //右边null裁掉右边
            root.right = null;
        }
        
        if(left || right) { //左右一边有一个是真, 就是有1, 就返回真
            return true;
        }
        if(root.val == 1) { //左右两边都为false, 但是根是1, 也返回真
            return true;
        }
        return false; //其余都返回假
    }
}
