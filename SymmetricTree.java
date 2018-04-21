/*Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.*/

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

    //solution1: 递归法
    public boolean isSymmetric(TreeNode root) {
        if(root==null) {
            return true;
        }
        return isSymmetricHelp(root.left, root.right);
    }
    
    private boolean isSymmetricHelp(TreeNode left, TreeNode right) {
        if(left==null || right==null) { //根
            return left == right;
        }
        if(left.val != right.val) { //根
            return false;
        }
        return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left); //左右
        //左边的左边, 右边的右边; 左边的右边, 右边的左边
    }
    
    
    //solution2: 迭代法, 栈
}
