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
     *@param preorder : A list of integers that preorder traversal of a tree
     *@param inorder : A list of integers that inorder traversal of a tree
     *@return : Root of a tree
     */
    
    /*
    已知前序和中序求原二叉树
    前序: ABCDEFGH
    中序: BDCEAFHG
    做法就是在中序中按照前序不断的找根节点: 首先根是A, 中序中A的左边都是左子树, 右边都是右子树, 在左子树BDCE中继续在前序中按顺序找根, 
    就是B, B左面没有元素, 那就是没有左子树, DCE就是右子树, 以此类推
    */
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // write your code here
        if(preorder == null || inorder == null || preorder.length != inorder.length || preorder.length == 0 || inorder.length == 0)
        {
            return null;
        }
        
        return buildTreeStep(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
    }
    
    private TreeNode buildTreeStep(int[] inorder, int instart, int inend, int[] preorder, int prestart, int preend)
    {
        if(instart > inend)  //instart和inend相等正好是递归到最后的那个叶子节点, 此时如果再往下去一层就会instart > inend, 
        //然后这个叶子节点的左右子树都会返回空, 这个点的分支就结束啦
        {
            return null;
        }
        
        TreeNode root = new TreeNode(preorder[prestart]); //建立结点
        int position = findPos(inorder, instart, inend, root.val);
        
        root.left = buildTreeStep(inorder, instart, position - 1, preorder, prestart + 1, prestart + (position - instart)); 
        //preorder传的下标翻译过来就是: 起点下标加1 到 起点下标加左子树长度, position - instart是中序数组整个左子树的长度
        root.right = buildTreeStep(inorder, position + 1, inend, preorder, position - inend + preend + 1, preend);
        //position - inend是中序数组整个右子树长度, 让preend减去右子树长度, 然后 + 1就是对应要传的preorder数组的起点下标
        return root;
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
