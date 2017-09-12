/*Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.*/

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
    public boolean hasPathSum(TreeNode root, int sum) {
        
        if(root == null) {
            return false;
        }
        return hasPathSumHelper(root, sum, 0);
    }
    
    public boolean hasPathSumHelper(TreeNode root, int sum, int step) {
        
        if(root.left == null && root.right == null) {
            if(step + root.val == sum) {
                return true;
            }
        }
        
        if(root.left != null) {
            if(hasPathSumHelper(root.left, sum, step + root.val)) {
                return true;
            }
        }
        
        if(root.right != null) {
            if(hasPathSumHelper(root.right, sum, step + root.val)) {
                return true;
            }
        }
        return false;
    }
}
