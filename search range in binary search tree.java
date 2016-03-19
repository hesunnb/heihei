/**
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
public class Solution {
    /**
     * @param root: The root of the binary search tree.
     * @param k1 and k2: range k1 to k2.
     * @return: Return all keys that k1<=key<=k2 in ascending order.
     */
     
    //思路就是中序遍历一遍
    ArrayList<Integer> result = new ArrayList<Integer>();
    public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here
        
        if(root == null)
        {
            return result;
        }
        
        if(root.left != null)
        {
            searchRange(root.left, k1, k2);
        }
        if(root.val >= k1 && root.val <= k2)
        {
            result.add(root.val);
        }
        if(root.right != null)
        {
            searchRange(root.right, k1, k2);
        }
        
        return result;
    }
    
    //九章做法一样，就是用个helper
    private ArrayList<Integer> results;
    
    public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        results = new ArrayList<Integer>();
        helper(root, k1, k2);
        return results;
    }
    
    private void helper(TreeNode root, int k1, int k2) {
        if (root == null) {
            return;
        }
        if (root.val > k1) {
            helper(root.left, k1, k2);
        }
        if (root.val >= k1 && root.val <= k2) {
            results.add(root.val);
        }
        if (root.val < k2) {
            helper(root.right, k1, k2);
        }
    }
}