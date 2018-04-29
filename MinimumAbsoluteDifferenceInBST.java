/*Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

Example:

Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
Note: There are at least two nodes in this BST.*/

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
   
    //中序遍历一遍放到ArrayList里面, 然后扫一遍求最小值即可
    public int getMinimumDifference(TreeNode root) {
        if(root == null) {
            return Integer.MIN_VALUE;
        }
        
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        int result = Integer.MAX_VALUE;
        
        for(int i = 0; i < nums.size() - 1; i++) {
            result = Math.min(result, Math.abs(nums.get(i) - nums.get(i+1)));
        }
        return result;
    }
    
    private void inorder(TreeNode root, List<Integer> nums){
        if(root == null) {
            return;
        }
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }
}
