/*For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.

A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.

Write a function that determines whether two binary trees are flip equivalent.  The trees are given by root nodes root1 and root2.

 

Example 1:

Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
Output: true
Explanation: We flipped at nodes with values 1, 3, and 5.
Flipped Trees Diagram

 

Note:

    Each tree will have at most 100 nodes.
    Each value in each tree will be a unique integer in the range [0, 99].*/
    
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

    //subtree写法, 到每节点进行判断, 用这个
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return root1 == root2;
        }
        if (root1.val != root2.val) {
            return false;
        }
        //左左右右一组, 左右右左一组
        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right))
            || (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }


    //own version: 写的太复杂, 不过没问题
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) { //根为空
            return true;
        }
        if(root1 != null && root2 != null && root1.val != root2.val) { //根不为空, 但值不相等
            return false;
        }
        
        if(root1 != null && root2 != null) { //正常递归
            return flipEquivHelper(root1, root2);
        }
        return false;
    }
    
    //从每个根探索子节点向下走
    private boolean flipEquivHelper(TreeNode root1, TreeNode root2) {
        if((root1.left != null && root2.left != null && root1.left.val != root2.left.val) || 
           ((root1.left == null || root2.left == null) && root1.left != root2.left)) { //左子树这种情况, 翻转一次
            invertTree(root2);
        } else if((root1.right != null && root2.right != null && root1.right.val != root2.right.val) || 
           ((root1.right == null || root2.right == null) && root1.right != root2.right)) { //右子树这种情况, 翻转一次, 总共只能翻转一次
            invertTree(root2);
        }
        
        if((root1.left != null && root2.left != null && root1.left.val != root2.left.val) || 
           ((root1.left == null || root2.left == null) && root1.left != root2.left)) { //再次验证左边
            return false;
        } 
        
        if((root1.right != null && root2.right != null && root1.right.val != root2.right.val) || 
           ((root1.right == null || root2.right == null) && root1.right != root2.right)) { //再次验证右边
            return false;
        }
        
        boolean left = true;
        boolean right = true;
        if(root1.left != null && root2.left != null) { //向下递归
            left = flipEquivHelper(root1.left, root2.left);
        }
        
        if(root1.right != null && root2.right != null) {
            right = flipEquivHelper(root1.right, root2.right);
        }
        return left && right; //返回结果
    }
    
    private void invertTree(TreeNode root2) {
        TreeNode temp = root2.left;
        root2.left = root2.right;
        root2.right = temp;
    }
}
