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
    public boolean isSymmetric(TreeNode root) {
        if(root == null)  return true;
    
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode left, right;
        if(root.left != null) {
            if(root.right == null) return false;
            stack.push(root.left); //左右都不为空, 就全部加入栈
            stack.push(root.right);
        }
        else if(root.right != null) { //左空, 右不空
            return false;
        }

        while(!stack.empty()) {
            if(stack.size()%2 != 0)   return false;
            right = stack.pop(); //对于栈来讲, 此处左右就不用分了, 先写left = stack.pop(), 再写right = stack.pop()也没问题
            left = stack.pop();
            if(right.val != left.val) return false;

            if(left.left != null) { //判断左边的左边与右边的右边
                if(right.right == null)   return false;
                stack.push(left.left); //两边都不为空, 全部加入
                stack.push(right.right);
            }
            else if(right.right != null) {
                return false;
            }

            if(left.right != null) { //判断左边的右边与右边的左边
                if(right.left == null)   return false;
                stack.push(left.right);
                stack.push(right.left);
            }
            else if(right.left != null) {
                return false;
            }
            //考虑例子中的树就行, 如果一个树的叶子节点左右都为空, 那么这些if判断都不会进入, 下一次循环自动从栈弹出新的值
        }

        return true;
    }
}
