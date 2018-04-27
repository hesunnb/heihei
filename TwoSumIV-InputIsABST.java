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
    
    //solution2: 用一个HashSet, 对于非BST也有效
    public boolean findTarget(TreeNode root, int k) {
        
        if(root == null) {
            return false;
        }
        
        Set<Integer> set = new HashSet<>();
        return findTargetHelper(root, k, set);
    }
    
    private boolean findTargetHelper(TreeNode root, int k, Set<Integer> set) {
        if(root == null) {
            return false;
        }
        if(set.contains(k - root.val)) {
            return true;
        } else {
            set.add(root.val);
        }
        return findTargetHelper(root.left, k, set) || findTargetHelper(root.right, k, set);
    }
    
    
    //solution3: BST中序遍历得到一个升序数组, 然后双指针前后查有没有等于k的组合
    public boolean findTarget(TreeNode root, int k) {
        
        if(root == null) {
            return false;
        }
        
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        for(int i = 0, j = nums.size() - 1; i < j;) {
            if(nums.get(i) + nums.get(j) == k) {
                return true;
            }
            if(nums.get(i) + nums.get(j) < k) {
                i++;
            } else {
                j--;
            }
        }
        return false;
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
