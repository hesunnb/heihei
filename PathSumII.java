/*Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]*/

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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null) {
            return result;
        }
        
        List<Integer> step = new ArrayList<Integer>();
        pathSumHelper(root, result, sum, 0, step);
        return result;
    }
    
    public void pathSumHelper(TreeNode root, List<List<Integer>> result, int sum, int tempSum, List<Integer> step) {
        
        step.add(root.val);
        if(root.left == null && root.right == null) {
            if(tempSum + root.val == sum) {
                result.add(new ArrayList<Integer>(step));
            }
            return;
        }
        
        if(root.left != null) {
            pathSumHelper(root.left, result, sum, tempSum + root.val, step);
            step.remove(step.size() - 1);
        }
        
        if(root.right != null) {
            pathSumHelper(root.right, result, sum, tempSum + root.val, step);
            step.remove(step.size() - 1);
        }
    }
}
