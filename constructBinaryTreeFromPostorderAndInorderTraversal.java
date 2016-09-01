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
     *@param inorder : A list of integers that inorder traversal of a tree
     *@param postorder : A list of integers that postorder traversal of a tree
     *@return : Root of a tree
     */
     
    //先序和中序组合的时候, 在先序中谁先出现谁是根; 中序和后序组合的时候, 后序中谁后出现谁就是根
    //中序: BDCEAFHG
    //后序: DECBHGFA
    //整体和先序中序那个非常像, 就是位置需要自己算一下
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // write your code here
        if(inorder == null || postorder == null || inorder.length != postorder.length || inorder.length == 0 || 
        postorder.length == 0) {
            return null;
        }
        
        return buildTreeStep(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }
    
    private TreeNode buildTreeStep(int[] inorder, int instart, int inend, int[] postorder, int poststart, int postend)
    {
        if(instart > inend)
        {
            return null;
        }
        
        TreeNode root = new TreeNode(postorder[postend]);
        int position = findPos(inorder, instart, inend, root.val);
        
        root.left = buildTreeStep(inorder, instart, position - 1, postorder, poststart, poststart + (position - instart) - 1); 
        //后序当中, 从poststart到poststart加上左子树的长度再减1, 就是要传的下标范围
        
        root.right = buildTreeStep(inorder, position + 1, inend, postorder, postend + (position - inend), postend - 1);
        return root; //后序当中, postend减去右子树的范围到postend - 1是要传下标的范围
    }
    
    private int findPos(int[] inorder, int start, int end, int key)
    {
        for(int i = start; i <= end; i++)
        {
            if(inorder[i] == key)
            {
                return i;
            }
        }
        return -1;
    }
}
