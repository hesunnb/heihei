/*Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the 
given target.

Example 1:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
Example 2:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False*/

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

    //solution2: 用个哈希表
    public boolean findTarget(TreeNode root, int k) {
        
        if(root == null) {
            return false;
        }
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        return findTargetHelper(root, k, map);
    }
    
    private boolean findTargetHelper(TreeNode root, int k, Map<Integer, Integer> map) {
        if(root == null) {
            return false;
        }
        if(map.containsKey(k - root.val)) {
            return true;
        } else {
            map.put(root.val, 0);
        }
        return findTargetHelper(root.left, k, map) || findTargetHelper(root.right, k, map);
    }
}
