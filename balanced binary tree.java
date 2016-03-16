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
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    
    //九章answer, 没有用全局flag, 首选
    public boolean isBalanced(TreeNode root) {
        return maxDepth(root) != -1;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left); //左子树深度
        int right = maxDepth(root.right); //右子树深度
        //left == -1 || right == -1的意思就是, 如果在下面的判断中要是出现了非平衡的情况, 那么就让左子树或者右子树为-1,
        //然后接下来上面的树就不用判断啦, 一路返回-1就结束啦
        if (left == -1 || right == -1 || Math.abs(left-right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
    
    //超级简练版, 一句话就写完
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(getDepth(root.left) - getDepth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int getDepth(TreeNode root) {
    	 if (root == null) return 0;
         return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
    }

    //自己answer
    /*boolean flag = true;
    public boolean isBalanced(TreeNode root) {
        // write your code here
        
        if(root == null)
        {
            return true;
        }
        
        ishelper(root);
        return flag;
    }
    
    public int ishelper(TreeNode root)
    {
        if(root == null)
        {
            return 0;
        }
        int left = ishelper(root.left);
        int right = ishelper(root.right);
        int result = Math.abs(left - right);
        if(result >= 2)
        {
            flag = false;   
        }
        return Math.max(left, right) + 1;
    }*/
}
